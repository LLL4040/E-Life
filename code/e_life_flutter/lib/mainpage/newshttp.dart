import 'dart:convert';
import 'dart:io';

import 'package:http/http.dart' as http;
class httpManager {

  var urgentUrl = "http://zhimo.natapp1.cc/news-server/api/Urgent/findHistory";

  var newsUrl = "http://zhimo.natapp1.cc/news-server/api/News/findNews";

  var activityUrl = "http://zhimo.natapp1.cc/news-server/api/Activity/findNewActivity";

  var joinUrl = "http://zhimo.natapp1.cc/news-server/api/Activity/saveParticipator";

  var photoUrl= "http://zhimo.natapp1.cc/news-server/api/Activity/photo";


  getUrgent(NetListener net,String communityId,String session) {
    var client = new http.Client();
    client.post(
        urgentUrl,
      headers: {
    "cookie": session,
    },
        body: {
          "communityId": communityId,
          "page": "1"
        },
    ).then((
        response,
        ) {
      print(response.body);
      //print(jsonDecode(response.body));
      List responseJson = json.decode(response.body);
      List<urgent> urgentList = responseJson.map((m) => new urgent.fromJson(m)).toList();
      net.onUrgentResponse(urgentList);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }

  getNews(NetListener net,String communityId,String session) {
    var client = new http.Client();
    client.post(
        newsUrl,
        headers: {
          "cookie": session,
        },
        body: {
          "communityId": communityId,
        }
    ).then((
        response,
        ) {
      List responseJson = json.decode(response.body);
      List<News> newsList = responseJson.map((m) => new News.fromJson(m)).toList();
      //print(jsonDecode(response.body));
      net.onNewsResponse(newsList);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }

  getActivity(NetListener net,String communityId,String session) {
    var client = new http.Client();
    client.post(
        activityUrl,
      headers: {
        "cookie": session,
      },
        body: {
          "communityId": communityId,
        }
    ).then((
        response,
        ) {
      List responseJson = json.decode(response.body);
      List<Activity> activityList = responseJson.map((m) => new Activity.fromJson(m)).toList();
      //print(jsonDecode(response.body));
      net.onActivityResponse(activityList);

    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }

  saveParticipator(NetListener net,String aid,String content,String username,String session){
    var client = new http.Client();
    client.post(
        joinUrl,
        headers: {
          "cookie": session,
        },
        body: {
          "aid": aid,
          "content": content,
          "username": username,
        }
    ).then((
        response,
        ) async {
      print("发送请求结果"+response.body);

      net.onSaveParticipantResponse(response.body);
      //await new Future.delayed(new Duration(milliseconds: 1000));
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }

  getPhoto(NetListener net,String path,String session)async {
    var client = new http.Client();
    String photo;
    client.post(
        photoUrl,
        headers: {
          "cookie": session,
        },
        body: {
          "path": path,
        }
    ).then((
        response,
        ) async{
      Map<String,dynamic> responseJson = json.decode(response.body);
      //await new Future.delayed(new Duration(milliseconds: 1500));
      photo = await responseJson["photo"];
      //print("photo"+photo);
      //print(response.body);
      //print(jsonDecode(response.body));
      net.onPhotoResponse(photo);
      return photo;

    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
    //return photo;
  }

}

/**
 * 用来回调成功和失败的结果
 */
abstract class NetListener {

  void onUrgentResponse(List<urgent> body);
  void onNewsResponse(List<News> body);
  void onActivityResponse(List<Activity> body);
  void onSaveParticipantResponse(String body);
  void onError(error);
   onPhotoResponse(String photo);
}

class urgent{
  final String time;
  final String managerName;
  final String content;

  urgent({
    this.time,
    this.content,
    this.managerName,
  }) ;

  factory urgent.fromJson(Map<String, dynamic> json){
    return new urgent(
      time: json['time'],
      content: json['content'],
      managerName: json['managerName']
    );
  }
}

class News{
  final String title;
  final String content;
  final String photo;
  final String time;
  final String path;

  News({
    this.time,
    this.content,
    this.title,
    this.path,
    this.photo,
  }) ;

  factory News.fromJson(Map<String, dynamic> json){
    return new News(
      time: json['time'],
      content: json['content'],
      title: json['title'],
      photo: json['photo'],
      path: json['path'],
    );
  }
}

class Activity{
  final int id;
  final String title;
  final String content;
  final String photo;
  final String startTime;
  final String endTime;
  final String path;
  final int status;

  Activity({
    this.id,
    this.title,
    this.content,
    this.photo,
    this.startTime,
    this.endTime,
    this.path,
    this.status,
  }) ;

  factory Activity.fromJson(Map<String, dynamic> json){
    return new Activity(
      id: json['id'],
      title: json['title'],
      content: json['content'],
      photo: json['photo'],
      startTime: json['startTime'],
      endTime: json['endTime'],
      path: json['path'],
      status: json['status'],
    );
  }
}


