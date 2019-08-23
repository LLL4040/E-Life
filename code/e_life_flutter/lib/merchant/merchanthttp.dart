
import 'dart:convert';

import 'package:http/http.dart' as http;

class merchantHttp {
//  三天预报
  var merhcantUrl = "http://zhimo.natapp1.cc/user-server/api/merchant/getMerchantByUsername";


  /**
   * 三天预报
   * cityName 城市名称 我们应该使用外部传入
   * net 监听网络请求的结果 因为他不是同步的
   */
   getMerchant(NetListener net,String username) {
    var client = new http.Client();
    client.post(
        merhcantUrl,
        body: {
          "username": username,
        }
    ).then((
        response,
        ) async{
      Map<String,dynamic> responseJson = json.decode(response.body);
      print(responseJson.toString());
      Merchant merchant = Merchant.fromJson(responseJson);
      net.onMerchantResponse(merchant);
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
  //三天预报
  void onMerchantResponse(Merchant body) ;
  void onError(error);
}
class Merchant{
  final int id;
  final String phone;
  final String address;
  final String detail;
  final String username;
  final String type;


  Merchant({
    this.id,
    this.phone,
    this.address,
    this.detail,
    this.username,
    this.type,
  }) ;

  factory Merchant.fromJson(Map<String, dynamic> json){
    return new Merchant(
      id: json['role'],
      phone: json['phone'],
      address: json['login'],
      detail: json['communityId'],
      username: json['username'],
      type: json['email'],
    );
  }
}
