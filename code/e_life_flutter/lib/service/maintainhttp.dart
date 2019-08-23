import 'dart:convert';

import 'package:http/http.dart' as http;
class maintainHttp {

  var addMaintainUrl = "http://zhimo.natapp1.cc/lifeservice-server/api/maintain/addMaintain";

  var myMaintainUrl = "http://zhimo.natapp1.cc/lifeservice-server/api/maintain/userFindMaintain";

  addMaintain(NetListener net, String username, String userphone,
      String content,String session) {
    var client = new http.Client();
    print(session+"添加维修");
    client.post(
        addMaintainUrl,
        headers: {
          "cookie": session,
        },
        body: {
          "username": username,
          "userphone": userphone,
          "content": content,
        }
    ).then((response,) {
      Map<String, dynamic> responseJson = json.decode(response.body);
      bool success = responseJson.containsValue("1");
      net.onAddMaintainResponse(success);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }

  myMaintain(NetListener net, String username,String session) {
    var client = new http.Client();
    client.post(
        myMaintainUrl,
        headers: {
          "cookie": session,
        },
        body: {
          "username": username,
        }
    ).then((response,) {
      List responseJson = json.decode(response.body);
      List<Maintain> maintainList = responseJson.map((m) => new Maintain.fromJson(m)).toList();
      print(response.body);
      net.onMyMaintainResponse(maintainList);
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

  void onAddMaintainResponse(bool send);
  void onMyMaintainResponse(List<Maintain> maintain);

  void onError(error);
}
class Maintain{
  final int id;
  final String time;
  final int status;
  final String content;
  final String maintainname;
  final String phone;
  final String managername;

  Maintain({
    this.id,
    this.time,
    this.status,
    this.content,
    this.phone,
    this.maintainname,
    this.managername,
  }) ;

  factory Maintain.fromJson(Map<String, dynamic> json){
    return new Maintain(
      id: json['id'],
      time: json['time'],
      status: json['status'],
      content: json['content'],
      phone: json['phone'],
      maintainname: json['maintainname'],
      managername: json['managername'],
    );
  }
}




