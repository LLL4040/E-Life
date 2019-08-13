import 'dart:io';

import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:image_picker/image_picker.dart';

class HeadImageUploadPage extends StatefulWidget {
  @override
  _HeadImageUploadPageState createState() => _HeadImageUploadPageState();
}

class _HeadImageUploadPageState extends State<HeadImageUploadPage> {
  File _image;
//use image_picker to open photo
  Future getImage() async {
    var image = await ImagePicker.pickImage(source: ImageSource.gallery);
    _upLoadImage(image);
    setState(() {
      _image = image;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Image Picker Example'),
      ),
      body: Center(
        child: _image == null
            ? Text('No image selected.')
            : Image.file(_image),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: getImage,
        tooltip: 'Pick Image',
        child: Icon(Icons.add_a_photo),
      ),
    );
  }
//upload photo
  _upLoadImage(File image) async {
    String path = image.path;
    var name = path.substring(path.lastIndexOf("/") + 1, path.length);
    var suffix = name.substring(name.lastIndexOf(".") + 1, name.length);
    FormData formData = new FormData.from({
      "managerName": "admin",
      "content": "hhhsihd",
      "title": "手机端",
      "communityId": 1,
      "photo": new UploadFileInfo(new File(path), name,
          contentType: ContentType.parse("image/$suffix"))
    });

    Dio dio = new Dio();
    //改成你的内网穿透网址
    var respone = await dio.post<String>("http://elife.natapp1.cc/news-server/api/News/saveNews/", data: formData);
    if (respone.statusCode == 200) {
      Fluttertoast.showToast(
          msg: "图片上传成功",
          gravity: ToastGravity.CENTER,
          textColor: Colors.grey);
    }
  }
}
