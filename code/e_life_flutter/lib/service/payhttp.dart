import 'dart:convert';

import 'package:http/http.dart' as http;
class payHttp {

  var myBillUrl = "http://elife.natapp1.cc/pay-server/api/Pay/findHistory";

  var myOrdersUrl = "http://elife.natapp1.cc/pay-server/api/Pay/getOrderInfo";

  var payUrl = "http://elife.natapp1.cc/pay-server/api/Pay/aliMobile";



  myBill(NetListener net, String username,String session) {
    var client = new http.Client();
    client.post(
        myBillUrl,
        headers: {
          "cookie": session,
        },
        body: {
          "username": username,
          "page": 1.toString(),
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
  myOrders(NetListener net, String username,String pid,String bill) {
    var client = new http.Client();
    client.post(
        myOrdersUrl,

        body: {
          "username": username,
          "pid": pid,
          "bill": bill,
        }
    ).then((response,) {
      Map<String, dynamic> responseJson = json.decode(response.body);
      Orders orderInfo = Orders.fromJson(responseJson);
      print(orderInfo.id);
      net.onOrderResponse(orderInfo);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }

  pay(NetListener net, String orderId,String bill,String time) {
    var client = new http.Client();
    client.post(
        payUrl,
        body: {
          "id": orderId,
          "bill": bill,
          "time": time,
        }
    ).then((response,) {
      net.onAliResponse(response.body);
      print(response.body);
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
  void onAliResponse(String payInfo);
  void onOrderResponse(Orders orderInfo);
  void onError(error);
}
class Bill{
  final int id;
  final String time;
  final int status;
  final double bill;
  final String username;
  final String managerName;
  final int communityId;

  Bill({
    this.id,
    this.time,
    this.status,
    this.bill,
    this.username,
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
      managerName: json['managerName'],
      communityId: json['communityId'],
    );
  }
}

class Orders{
  final String id;
  final String time;
  final int status;
  final String bill;

  Orders({
    this.id,
    this.time,
    this.status,
    this.bill,
  }) ;

  factory Orders.fromJson(Map<String, dynamic> json){
    return new Orders(
      id: json['id'],
      time: json['time'],
      status: json['status'],
      bill: json['bill'],
    );
  }
}


