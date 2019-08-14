import 'dart:convert';

import 'package:http/http.dart' as http;
class comsupHttp {

  var myComputerUrl = "http://zhimo.natapp1.cc/user-server/api/merchant/findAllByType";

  var mySupermarketUrl = "http://zhimo.natapp1.cc/user-server/api/merchant/findAllByType";



  myComputer(NetListener net) {
    var client = new http.Client();
    client.post(
        myComputerUrl,
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

  mySupermarket(NetListener net) {
    var client = new http.Client();
    client.post(
        mySupermarketUrl,
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




