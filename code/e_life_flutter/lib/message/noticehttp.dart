import 'dart:convert';

import 'package:http/http.dart' as http;
class noticeHttp {

  var myNoticeHttp = "http://zhimo.natapp1.cc/news-server/api/notice/findMyNotice";

  var deleteNoticeHttp = "http://zhimo.natapp1.cc/news-server/api/notice/deleteMyOneNotice";



  myNotice(NetListener net, String username,String session) {
    var client = new http.Client();
    print(session+"通知通知");
    client.post(
        myNoticeHttp,
      headers: {
        "cookie": session,
      },
        body: {
          "username": username,
        },
    ).then((response,) {

      List responseJson = json.decode(response.body);
      print(response.body);
      List<Notice> noticeList = responseJson.map((m) => new Notice.fromJson(m)).toList();
      net.onMyNoticeResponse(noticeList);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }
  deleteNotice(NetListener net, String username, int noticeId,String session){
    var client = new http.Client();
    client.post(
        deleteNoticeHttp,
      headers: {
        "cookie": session,
      },
        body: {
          "username": username,
          "noticeId": noticeId.toString(),
        },
    ).then((response,) {
      print(response);
      Map<String, dynamic> responseJson = json.decode(response.body);
      bool success =  responseJson.containsValue("1");

      net.onDeleteNoticeResponse(success);
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


  void onMyNoticeResponse(List<Notice> notice);
  void onDeleteNoticeResponse(bool success);
  void onError(error);
}
class Notice{
  final int id;
  final String time;
  final String content;
  final String managerName;
  final int communityId;

  Notice({
    this.id,
    this.time,
    this.content,
    this.managerName,
    this.communityId,
  }) ;

  factory Notice.fromJson(Map<String, dynamic> json){
    return new Notice(
      id: json['id'],
      time: json['noticeTime'],
      content: json['noticeContent'],
      managerName: json['managerName'],
      communityId: json['communityId'],
    );
  }
}




