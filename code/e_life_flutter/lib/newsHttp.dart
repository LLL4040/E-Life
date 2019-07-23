import 'dart:convert';

import 'package:http/http.dart' as http;
class httpManager {

  var urgentUrl = "http://elife.natapp1.cc/news-server/api/Urgent/findHistory";

  var newsUrl = "http://elife.natapp1.cc/news-server/api/News/findNews";

  var activityUrl = "http://elife.natapp1.cc/news-server/api/Activity/findNewActivity";
  var joinUrl = "http://elife.natapp1.cc/news-server/api/Activity/saveParticipator";


  getUrgent(NetListener net,num communityId) {
    var client = new http.Client();
    client.post(
        urgentUrl,
        body: {
          "communityId": communityId.toString(),
          "page": "1"
        }
    ).then((
        response,
        ) {
      //print(response.body);
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

  getNews(NetListener net,num communityId) {
    var client = new http.Client();
    client.post(
        newsUrl,
        body: {
          "communityId": communityId.toString(),
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

  getActivity(NetListener net,num communityId) {
    var client = new http.Client();
    client.post(
        activityUrl,
        body: {
          "communityId": communityId.toString(),
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
  saveParticipator(NetListener net,String aid,String content,String username){
    var client = new http.Client();
    client.post(
        joinUrl,
        body: {
          "aid": aid,
          "content": content,
          "username": username,
        }
    ).then((
        response,
        ) {
      print("发送请求结果"+response.body);
      net.onSaveParticipantResponse(response.body);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
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


