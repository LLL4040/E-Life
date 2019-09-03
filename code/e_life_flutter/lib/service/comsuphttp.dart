import 'dart:convert';

import 'package:http/http.dart' as http;
class comsupHttp {

  var myComputerUrl = "http://zhimo.natapp1.cc/user-server/api/merchant/findAllByType";

  var mySupermarketUrl = "http://zhimo.natapp1.cc/user-server/api/merchant/findAllByType";

  var allMerchantUrl = "http://zhimo.natapp1.cc/user-server/api/merchant/findAll";

  var allBargainUrl = "http://zhimo.natapp1.cc/group-server/api/bargain/getAllBargain";

  allMerchant(NetListener net,String session) async{
    var client = new http.Client();
    client.post(
        allMerchantUrl,
        headers: {
          "cookie": session,
        },
    ).then((response,) {
      List responseJson = json.decode(response.body);
      List<Merchant> merList = responseJson.map((m) => new Merchant.fromJson(m)).toList();
      print(response.body);
      net.allMerchantResponse(merList);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }
  allBargain(NetListener net,String session) async{
    var client = new http.Client();
    client.post(
      allBargainUrl,
      headers: {
        "cookie": session,
      },
    ).then((response,) {
      List responseJson = json.decode(response.body);
      List<Bargain> barList = responseJson.map((m) => new Bargain.fromJson(m)).toList();
      print(response.body);
      net.allBargainResponse(barList);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }
  myComputer(NetListener net,String session) {
    var client = new http.Client();
    client.post(
        myComputerUrl,
        headers: {
          "cookie": session,
        },
        body: {
          "type": "电脑维修",
        }
    ).then((response,) {
      List responseJson = json.decode(response.body);
      List<Merchant> comList = responseJson.map((m) => new Merchant.fromJson(m)).toList();
      print(response.body);
      net.onMyComputerResponse(comList);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }

  mySupermarket(NetListener net,String session) {
    var client = new http.Client();
    client.post(
        mySupermarketUrl,
        headers: {
          "cookie": session,
        },
        body: {
          "type": "超市送货",
        }
    ).then((response,) {
      List responseJson = json.decode(response.body);
      List<Merchant> supList = responseJson.map((m) => new Merchant.fromJson(m)).toList();
      print(response.body);
      net.onMySupermarketResponse(supList);
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


  void onMyComputerResponse(List<Merchant> computer);
  void onMySupermarketResponse(List<Merchant> supermarket);
  void allMerchantResponse(List<Merchant> merchant);
  void allBargainResponse(List<Bargain> bargain);
  void onError(error);
}
class Merchant{
  final int id;
  final String username;
  final String name;
  final String phone;
  final String address;
  final String detail;
  final String type;

  Merchant({
    this.id,
    this.username,
    this.name,
    this.phone,
    this.address,
    this.detail,
    this.type,
  }) ;

  factory Merchant.fromJson(Map<String, dynamic> json){
    return new Merchant(
      id: json['id'],
      username: json['username'],
      name: json['name'],
      phone: json['phone'],
      address: json['address'],
      detail: json['detail'],
      type: json['type'],
    );
  }
}
class Bargain{

  final String address;
  final int merchantId;
  final String phone;
  final String start;
  final String name;

  final String end;
  final String title;
  final String goods;
  final String content;
  final int status;

  Bargain({
    this.address,
    this.merchantId,
     this.phone,
    this.start,
    this.name,
    this.end,
    this.title,
    this.goods,
    this.content,
    this.status,
});
  factory Bargain.fromJson(Map<String, dynamic> json){
    return new Bargain(
      address: json['address'],
      merchantId: json['merchantId'],
      phone: json['phone'],
      start: json['start'],
      name: json['name'],
      end: json['end'],
      title: json['title'],
      goods: json['goods'],
      content: json['content'],
      status: json['status'],
    );
  }
}




