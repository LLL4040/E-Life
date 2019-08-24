import 'dart:convert';

import 'package:http/http.dart' as http;
class payHttp {

  var myBillUrl = "http://zhimo.natapp1.cc/pay-server/api/Pay/findHistory";



  myBill(NetListener net, String username,String session) {
    print("session:"+session);
    var client = new http.Client();
    client.post(
        myBillUrl,
        headers: {
          "cookie": session,
        },
        body: {
          "username": username,
          "page":"1",

        }
    ).then((response,) {
      List responseJson = json.decode(response.body);
      List<Bill> billList = responseJson.map((m) => new Bill.fromJson(m)).toList();
      print(response.body);
      net.onMyBillResponse(billList);
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


  void onMyBillResponse(List<Bill> bill);
  void onError(error);
}
class Bill{
  final int id;
  final String time;
  final int status;
  final double bill;
  final String username;
  final String phone;
  final String managerName;
  final int communityId;

  Bill({
    this.id,
    this.time,
    this.status,
    this.bill,
    this.username,
    this.phone,
    this.managerName,
    this.communityId,
  }) ;

  factory Bill.fromJson(Map<String, dynamic> json){
    return new Bill(
      id: json['id'],
      time: json['time'],
      status: json['status'],
      bill: json['bill'],
      username: json['username'],
      phone: json['phone'],
      managerName: json['managerName'],
      communityId: json['communityId'],
    );
  }
}




