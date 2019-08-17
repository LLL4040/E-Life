import 'dart:io';

import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:image_picker/image_picker.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

class addPost extends StatefulWidget {
  final username;
  final communityId;
  var session;
  addPost(this.username, this.communityId, this.session);
  @override
  @override
  State<StatefulWidget> createState() {
    return new _addPostCenter(username, communityId, session);
  }
}

class _addPostCenter extends State<addPost>
    with SingleTickerProviderStateMixin {
  final username;
  final communityId;
  var session;
  _addPostCenter(this.username, this.communityId, this.session);
  final TextEditingController _titleController =
      new TextEditingController.fromValue(new TextEditingValue(text: ""));
  final TextEditingController _contentController =
      new TextEditingController.fromValue(new TextEditingValue(text: ""));
  List photos = []; //上传要用
  List images = []; //显示要用
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

    setState(() {
      //_image = image;
    });
  }

  Widget _getPhoto1(var photo1, var num) {
    return new SizedBox(
      //height: 210.0, //设置高度
      child: new Card(
        //elevation: 0.0, //设置阴影
//设置shape，这里设置成了R角
        shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.all(Radius.circular(10.0))),
        //对Widget截取的行为，比如这里 Clip.antiAlias 指抗锯齿
        clipBehavior: Clip.antiAlias,
        child: new Column(
          // card只能有一个widget，但这个widget内容可以包含其他的widget
          mainAxisSize: MainAxisSize.max,
          mainAxisAlignment: MainAxisAlignment.start,
          children: [
            new Padding(padding: new EdgeInsets.fromLTRB(0.0, 5.0, 0.0, 5.0)),
            new Row(
              children: <Widget>[
                new Padding(
                    padding: new EdgeInsets.fromLTRB(10.0, 0.0, 5.0, 0.0)),
                new SizedBox(
                  height: 100, //设置高度
                  width: 100, //设置宽度

                  child: Stack(
                    alignment: Alignment.center,
                    children: <Widget>[
                      new Card(
                        //elevation: 0.1, //设置阴影
                        //设置shape，这里设置成了R角
                        shape: RoundedRectangleBorder(
                            borderRadius:
                                BorderRadius.all(Radius.circular(10.0))),
                        //对Widget截取的行为，比如这里 Clip.antiAlias 指抗锯齿
                        clipBehavior: Clip.antiAlias,

                        child: photo1 == ""
                            ? Text("null")
                            : Image.file(
                                photo1,
                                fit: BoxFit.fill, //填充
                                gaplessPlayback: true, //防止重绘
                              ),
                      ),
                      IconButton(
                        icon: Icon(
                          Icons.delete_outline,
                          color: Colors.black38,
                        ),
                        onPressed: () {
                          photos.removeAt(num);
                          images.removeAt(num);
                          setState(() {});
                        },
                      ),
                    ],
                  ),
                ),
                new Padding(
                    padding: new EdgeInsets.fromLTRB(5.0, 0.0, 5.0, 0.0)),
              ],
            ),
            new Padding(padding: new EdgeInsets.fromLTRB(0.0, 5.0, 0.0, 5.0)),
          ],
        ),
      ),
    );
  }

  Widget _getPhoto2(var photo1, var num1, var photo2, var num2) {
    return new SizedBox(
      //height: 210.0, //设置高度
      child: new Card(
//        elevation: 15.0, //设置阴影
        //设置shape，这里设置成了R角
        shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.all(Radius.circular(10.0))),
        //对Widget截取的行为，比如这里 Clip.antiAlias 指抗锯齿
        clipBehavior: Clip.antiAlias,
        child: new Column(
          // card只能有一个widget，但这个widget内容可以包含其他的widget
          mainAxisSize: MainAxisSize.max,
          mainAxisAlignment: MainAxisAlignment.start,
          children: [
            new Padding(padding: new EdgeInsets.fromLTRB(0.0, 5.0, 0.0, 5.0)),
            new Row(
              children: <Widget>[
                new Padding(
                    padding: new EdgeInsets.fromLTRB(11.0, 0.0, 5.0, 0.0)),
                new SizedBox(
                  height: 100, //设置高度
                  width: 100, //设置宽度
                  child: Stack(
                    alignment: Alignment.center,
                    children: <Widget>[
                      new Card(
                        //elevation: 0.1, //设置阴影
                        //设置shape，这里设置成了R角
                        shape: RoundedRectangleBorder(
                            borderRadius:
                                BorderRadius.all(Radius.circular(10.0))),
                        //对Widget截取的行为，比如这里 Clip.antiAlias 指抗锯齿
                        clipBehavior: Clip.antiAlias,

                        child: photo1 == ""
                            ? Text("null")
                            : Image.file(
                                photo1,
                                fit: BoxFit.fill, //填充
                                gaplessPlayback: true, //防止重绘
                              ),
                      ),
                      IconButton(
                        icon: Icon(
                          Icons.delete_outline,
                          color: Colors.black38,
                        ),
                        onPressed: () {
                          photos.removeAt(num1);
                          images.removeAt(num1);
                          setState(() {});
                        },
                      ),
                    ],
                  ),
                ),
                new Padding(
                    padding: new EdgeInsets.fromLTRB(5.0, 0.0, 5.0, 0.0)),
                new SizedBox(
                  height: 100, //设置高度
                  width: 100, //设置宽度
                  child: Stack(
                    alignment: Alignment.center,
                    children: <Widget>[
                      new Card(
                        //elevation: 0.1, //设置阴影
                        //设置shape，这里设置成了R角
                        shape: RoundedRectangleBorder(
                            borderRadius:
                                BorderRadius.all(Radius.circular(10.0))),
                        //对Widget截取的行为，比如这里 Clip.antiAlias 指抗锯齿
                        clipBehavior: Clip.antiAlias,

                        child: photo2 == ""
                            ? Text("null")
                            : Image.file(
                                photo2,
                                fit: BoxFit.fill, //填充
                                gaplessPlayback: true, //防止重绘
                              ),
                      ),
                      IconButton(
                        icon: Icon(
                          Icons.delete_outline,
                          color: Colors.black38,
                        ),
                        onPressed: () {
                          photos.removeAt(num2);
                          images.removeAt(num2);
                          setState(() {});
                        },
                      ),
                    ],
                  ),
                ),
                new Padding(
                    padding: new EdgeInsets.fromLTRB(5.0, 0.0, 5.0, 0.0)),
              ],
            ),
            new Padding(padding: new EdgeInsets.fromLTRB(0.0, 5.0, 0.0, 5.0)),
          ],
        ),
      ),
    );
  }

  Widget _getPhoto3(
      var photo1, var num1, var photo2, var num2, var photo3, var num3) {
    return new SizedBox(
      //height: 210.0, //设置高度
      child: new Card(
        //elevation: 5.0, //设置阴影
        //设置shape，这里设置成了R角
        shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.all(Radius.circular(10.0))),
        //对Widget截取的行为，比如这里 Clip.antiAlias 指抗锯齿
        clipBehavior: Clip.antiAlias,
        child: new Column(
          // card只能有一个widget，但这个widget内容可以包含其他的widget
          mainAxisSize: MainAxisSize.max,
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            new Padding(padding: new EdgeInsets.fromLTRB(0.0, 5.0, 0.0, 5.0)),
            new Row(
              mainAxisSize: MainAxisSize.max,
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                new SizedBox(
                  height: 100, //设置高度
                  width: 100, //设置宽度
                  child: Stack(
                    alignment: Alignment.center,
                    children: <Widget>[
                      new Card(
                        //elevation: 0.1, //设置阴影
                        //设置shape，这里设置成了R角
                        shape: RoundedRectangleBorder(
                            borderRadius:
                                BorderRadius.all(Radius.circular(10.0))),
                        //对Widget截取的行为，比如这里 Clip.antiAlias 指抗锯齿
                        clipBehavior: Clip.antiAlias,

                        child: photo1 == ""
                            ? Text("null")
                            : Image.file(
                                photo1,
                                fit: BoxFit.fill, //填充
                                gaplessPlayback: true, //防止重绘
                              ),
                      ),
                      IconButton(
                        icon: Icon(
                          Icons.delete_outline,
                          color: Colors.black38,
                        ),
                        onPressed: () {
                          photos.removeAt(num1);
                          images.removeAt(num1);
                          setState(() {});
                        },
                      ),
                    ],
                  ),
                ),
                new Padding(
                    padding: new EdgeInsets.fromLTRB(5.0, 0.0, 5.0, 0.0)),
                new SizedBox(
                  height: 100, //设置高度
                  width: 100, //设置宽度
                  child: Stack(
                    alignment: Alignment.center,
                    children: <Widget>[
                      new Card(
                        //elevation: 0.1, //设置阴影
                        //设置shape，这里设置成了R角
                        shape: RoundedRectangleBorder(
                            borderRadius:
                                BorderRadius.all(Radius.circular(10.0))),
                        //对Widget截取的行为，比如这里 Clip.antiAlias 指抗锯齿
                        clipBehavior: Clip.antiAlias,

                        child: photo2 == ""
                            ? Text("null")
                            : Image.file(
                                photo2,
                                fit: BoxFit.fill, //填充
                                gaplessPlayback: true, //防止重绘
                              ),
                      ),
                      IconButton(
                        icon: Icon(
                          Icons.delete_outline,
                          color: Colors.black38,
                        ),
                        onPressed: () {
                          photos.removeAt(num2);
                          images.removeAt(num2);
                          setState(() {});
                        },
                      ),
                    ],
                  ),
                ),
                new Padding(
                    padding: new EdgeInsets.fromLTRB(5.0, 0.0, 5.0, 0.0)),
                new SizedBox(
                  height: 100, //设置高度
                  width: 100, //设置宽度
                  child: Stack(
                    alignment: Alignment.center,
                    children: <Widget>[
                      new Card(
                        //elevation: 0.1, //设置阴影
                        //设置shape，这里设置成了R角
                        shape: RoundedRectangleBorder(
                            borderRadius:
                                BorderRadius.all(Radius.circular(10.0))),
                        //对Widget截取的行为，比如这里 Clip.antiAlias 指抗锯齿
                        clipBehavior: Clip.antiAlias,

                        child: photo3 == ""
                            ? Text("null")
                            : Image.file(
                                photo3,
                                fit: BoxFit.fill, //填充
                                gaplessPlayback: true, //防止重绘
                              ),
                      ),
                      IconButton(
                        icon: Icon(
                          Icons.delete_outline,
                          color: Colors.black38,
                        ),
                        onPressed: () {
                          photos.removeAt(num3);
                          images.removeAt(num3);
                          setState(() {});
                        },
                      ),
                    ],
                  ),
                ),
              ],
            ),
            new Padding(padding: new EdgeInsets.fromLTRB(0.0, 5.0, 0.0, 5.0)),
          ],
        ),
      ),
    );
  }

  List<Widget> postWidgets = [];
  Widget buildpostBody(BuildContext ctxt, int index) {
    return postWidgets[index];
  }

  @override
  Widget build(BuildContext context) {
    postWidgets = [];
    Widget addtitle = ListTile(
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
      title: new TextField(
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

    if (images != null) {
      if (images.length <= 3 && images.length >= 0) {
        if (images.length == 3) {
          Widget photo1 = _getPhoto3(images[0], 0, images[1], 1, images[2], 2);
          postWidgets.add(photo1);
        }
        if (images.length == 2) {
          Widget photo1 = _getPhoto2(images[0], 0, images[1], 1);
          postWidgets.add(photo1);
        } else if (images.length == 1) {
          Widget photo1 = _getPhoto1(images[0], 0);
          postWidgets.add(photo1);
        }
      }
      if (images.length > 3) {
        int length = images.length % 3;
        print(length.toString() + "jsjs");

        int length1 = images.length - length;
        for (int i = 0; i < length1 / 3; i++) {
          Widget photo1 = _getPhoto3(images[3 * i], 3 * i, images[3 * i + 1],
              3 * i + 1, images[3 * i + 2], 3 * i + 2);
          postWidgets.add(photo1);
        }
        if (length == 1) {
          Widget photo1 = _getPhoto1(images[length1], length1);
          postWidgets.add(photo1);
        }
        if (length == 2) {
          Widget photo1 = _getPhoto2(
              images[length1], length1, images[length1 + 1], length1 + 1);
          postWidgets.add(photo1);
        }
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
      body: addPostSection2,
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
      "tag": "111",

//      "photo":new UploadFileInfo(new File(path), name,
//          contentType: ContentType.parse("image/$suffix")),
    });

    Dio dio = new Dio();
    //改成你的内网穿透网址
    Map<String, dynamic> headers = new Map();
    headers['cookie'] = session;
    var respone = await dio.post(
      "http://zhimo.natapp1.cc/estateforum-server/api/post/addPost",
      data: formData,
      options: Options(
        headers: headers,
      ),
    );

    if (respone.statusCode == 200) {
      //showToast("图片上传成功");
      //print("图片上传成功");
      Navigator.pop(context, "发布帖子成功");
    }
    photos = [];
    images = [];
  }
}
