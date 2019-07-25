import 'dart:convert';

import 'package:http/http.dart' as http;
class demandHttp {

  var addDemandUrl = "http://elife.natapp1.cc/group-server/api/demand/addDemand";


  var allDemandUrl= "http://elife.natapp1.cc/group-server/api/demand/getAllDemand";

  var joinDemandUrl= "http://elife.natapp1.cc/group-server/api/demand/participateDemand";

  var quitDemandUrl= "http://elife.natapp1.cc/group-server/api/demand/quitDemand";

  var getDiscountUrl= "http://elife.natapp1.cc/group-server/api/discount/findDiscountByCommunityId";



  addDemand(NetListener net, String startTime, String endTime, String content,
      String username, String communityId, String title) {
    var start  = startTime.split('.');
    var end = startTime.split('.');
    print(start[0]);
    print(end[0]);
    var client = new http.Client();
    client.post(
        addDemandUrl,
        body: {
          "username": username,
          "startTime": start[0],
          "endTime": end[0],
          "content": content,
          "communityId": communityId,
          "title": title,
        }
    ).then((
        response,
        ) {
      print(response.body);
      Map<String,dynamic> responseJson = json.decode(response.body);
      bool success = responseJson.containsValue("1");
      net.onAddDemandResponse(success);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }

  getDemandList(NetListener net,String communityId) {
    var client = new http.Client();
    client.post(
        allDemandUrl,
        body: {
          "communityId": communityId,
        }
    ).then((
        response,
        ) {
      print(response.body);
      print(jsonDecode(response.body));
      List responseJson = json.decode(response.body);
      List<Demand> demandList = responseJson.map((m) => new Demand.fromJson(m)).toList();
      net.onAllDemandResponse(demandList);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }


  participantDemand(NetListener net,String username,String id) {
    var client = new http.Client();
    client.post(
        joinDemandUrl,
        body: {
          "username": username,
          "id": id,
        }
    ).then((
        response,
        ) {
      Map<String,dynamic> responseJson = json.decode(response.body);
      bool success = responseJson.containsValue("1");
      net.onParticipateDemandResponse(success);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }

  quitDemand(NetListener net,String username,String id) {
    var client = new http.Client();
    client.post(
        quitDemandUrl,
        body: {
          "username": username,
          "id": id,
        }
    ).then((
        response,
        ) {
      Map<String,dynamic> responseJson = json.decode(response.body);
      bool success = responseJson.containsValue("1");
      net.onQuitDemandResponse(success);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }
  getDiscountList(NetListener net,String communityId) {
    var client = new http.Client();
    client.post(
        getDiscountUrl,
        body: {
          "communityId": communityId,
        }
    ).then((
        response,
        ) {
      print(response.body);
      print(jsonDecode(response.body));
      List responseJson = json.decode(response.body);
      List<Discount> discount = responseJson.map((m) => new Discount.fromJson(m)).toList();
      net.onAllDiscountResponse(discount);
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

  void onAddDemandResponse(bool success);
  void onAllDemandResponse(List<Demand> demandList);
  void onParticipateDemandResponse(bool success);
  void onQuitDemandResponse(bool success);
  void onAllDiscountResponse(List<Discount> discountList);
  void onError(error);
}

class Demand{
  final String id;
  final String start;
  final String end;
  final String title;
  final String content;
  final String username;
  final int communityId;
  final int num;

  Demand({
    this.id,
    this.start,
    this.end,
    this.title,
    this.content,
    this.username,
    this.communityId,
    this.num,
  }) ;

  factory Demand.fromJson(Map<String, dynamic> json){
    return new Demand(
      id: json['id'].toString(),
      start: json['start'],
      end: json['end'],
      title: json['title'],
      content: json['content'],
      username: json['username'],
      communityId: json['communityId'],
      num: json['num'],
    );
  }
}

class Discount{
  final String id;
  final String start;
  final String end;
  final int mId;
  final int num;
  final String title;
  final String content;
  final int status;
  final int communityId;
  final String path;
  final String photo;


  Discount({
    this.id,
    this.start,
    this.end,
    this.mId,
    this.num,
    this.title,
    this.content,
    this.status,
    this.communityId,
    this.path,
    this.photo,
  }) ;

  factory Discount.fromJson(Map<String, dynamic> json){
    return new Discount(
      id: json['id'].toString(),
      start: json['start'],
      end: json['end'],
      mId: json['mId'],
      num: json['num'],
      title: json['title'],
      content: json['content'],
      status: json['status'],
      communityId: json['communityId'],
      path: json['path'],
      photo: json['photo'],
    );
  }
}




