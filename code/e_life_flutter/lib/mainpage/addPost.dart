import 'dart:io';

import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:image_picker/image_picker.dart';


class addPost extends StatefulWidget {
  final username;
  final communityId;
  addPost(this.username, this.communityId);
  @override
  _addPostCenter createState() => _addPostCenter(username, communityId);
}

class _addPostCenter extends State<addPost>
    with SingleTickerProviderStateMixin {
  final username;
  final communityId;
  _addPostCenter(this.username, this.communityId);
  final TextEditingController _titleController =
      new TextEditingController.fromValue(new TextEditingValue(text: ""));
  final TextEditingController _contentController =
      new TextEditingController.fromValue(new TextEditingValue(text: ""));
  List photos = [];//上传要用
  List images=[];//显示要用
  File _image;

//use image_picker to open photo
  Future getImage() async {
    var image = await ImagePicker.pickImage(source: ImageSource.gallery);

    String path = image.path;
    var name = path.substring(path.lastIndexOf("/") + 1, path.length);
    var suffix = name.substring(name.lastIndexOf(".") + 1, name.length);
    photos.add(new UploadFileInfo(new File(path), name,
        contentType: ContentType.parse("image/$suffix")));
    images.add(image);
    //_upLoadImage();
    setState(() {
      //_image = image;

    });
  }

  List<Widget> postWidgets = [];
  Widget buildpostBody(BuildContext ctxt, int index) {
    return postWidgets[index];
  }
  @override
  Widget build(BuildContext context) {
    postWidgets = [];
    Widget addtitle =ListTile(
      title: new TextField(
        controller: _titleController,
        decoration: new InputDecoration(
          hintText: '输入题目',
        ),
      ),
      leading: Text("题目: "),

    );
    Widget addContent = ListTile(
      leading: Text("内容: "),
      title:  new TextField(
        maxLines: 5,
        controller: _contentController,
        decoration: new InputDecoration(
          hintMaxLines: 6,
          enabledBorder: OutlineInputBorder(
            borderSide: BorderSide(
              color: Colors.black38,
              width: 1, //边线宽度为2
            ),
          ),
          focusedBorder: OutlineInputBorder(
              borderSide: BorderSide(
                color: Colors.blue, //边框颜色为绿色
                width: 2, //宽度为5
              )),
        ),
      ),


    );

    Widget postall = Container(

      child: Row(
        mainAxisSize: MainAxisSize.max,
        mainAxisAlignment: MainAxisAlignment.end,
        children: <Widget>[
          FlatButton(
            color: Colors.blue,
            highlightColor: Colors.blue[700],
            colorBrightness: Brightness.dark,
            splashColor: Colors.grey,
            child: Text("确定"),
            shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(20.0)),
            onPressed: _upLoadPost,
          ),
          FlatButton(
            color: Colors.black54,
            highlightColor: Colors.black38,
            colorBrightness: Brightness.dark,
            splashColor: Colors.grey,
            child: Text("取消"),
            shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(20.0)),
            onPressed: () => Navigator.pop(context, "取消发布帖子"),
          )
        ],
      ),
    );

    postWidgets.add(addtitle);
    postWidgets.add(addContent);
    //postWidgets.add(addPhotos);

    if(photos.length>0){
      for(int i=0;i<photos.length;i++){
        Widget addphoto1= SizedBox(
          //height: 210.0, //设置高度
          child: new Card(
            //elevation: 0.0, //设置阴影
//设置shape，这里设置成了R角
            shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.all(Radius.circular(10.0))),
            //对Widget截取的行为，比如这里 Clip.antiAlias 指抗锯齿
            clipBehavior: Clip.antiAlias,
            child: new ListTile(

              leading: new Icon(
                Icons.photo,
                color: Colors.blue[500],
              ),
              //title: _image==null?new Text('图片区域'):Image.file(_image),
              title: SizedBox(
                height: 100,
                width: 100,
                child: images[i]==null?new Text('图片区域'):Image.file(images[i]),

              ),
              trailing: IconButton(
                icon: Icon(Icons.delete),
                onPressed: () {
                  photos.removeAt(i);
                  images.removeAt(i);
                  setState(() {

                  });
                },),
            ),
          ),
        );
        postWidgets.add(addphoto1);

      }
    }
    postWidgets.add(postall);
    Widget addPostSection2 = new Container(
      child: new ListView.builder(
          itemCount: postWidgets.length,
          itemBuilder: (BuildContext ctxt, int index) =>
              buildpostBody(ctxt, index)),
    );
    return Scaffold(
      appBar: AppBar(
        title: Text('Image Picker Example'),
      ),

      body:addPostSection2,
      floatingActionButton: FloatingActionButton(
        onPressed: getImage,
        tooltip: 'Pick Image',
        child: Icon(Icons.add_a_photo),
      ),
    );
  }
//upload photo

  _upLoadPost() async {
//    String path = image.path;
//    var name = path.substring(path.lastIndexOf("/") + 1, path.length);
//    var suffix = name.substring(name.lastIndexOf(".") + 1, name.length);
    FormData formData = new FormData.from({
      "title": _titleController.text,
      "postContent": _contentController.text,
      "posterName": username,
      "communityId": communityId,
      "photo": photos,
//      "photo":new UploadFileInfo(new File(path), name,
//          contentType: ContentType.parse("image/$suffix")),
    });

    Dio dio = new Dio();
    //改成你的内网穿透网址
    var respone = await dio.post<String>(
        "http://zhimo.natapp1.cc/estateforum-server/api/post/addPost/",
        data: formData);
    if (respone.statusCode == 200) {
      //showToast("图片上传成功");
      //print("图片上传成功");
      Navigator.pop(context,"发布帖子成功");

    }
    photos = [];
    images=[];
  }
}
