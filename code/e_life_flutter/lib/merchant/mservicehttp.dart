import 'dart:convert';

import 'package:http/http.dart' as http;
class mServiceHttp {


  var allDemandUrl= "http://zhimo.natapp1.cc/group-server/api/demand/getAllDemand";

  var getDiscountUrl= "http://zhimo.natapp1.cc/group-server/api/discount/findDiscountByMerchantId";

  var getBargainUrl = "http://zhimo.natapp1.cc/group-server/api/bargain/getBargainByMerchantId";


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

   getDiscountList(NetListener net,String merchantId) {
    var client = new http.Client();
    client.post(
        getDiscountUrl,
        body: {
          "merchantId": merchantId,
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

  getBargainList(NetListener net,String merchantId){
    var client = new http.Client();
    client.post(
        getBargainUrl,
        body: {
          "merchantId": merchantId,
        }
    ).then((
        response,
        ) {
      print(response.body);
      print(jsonDecode(response.body));
      List responseJson = json.decode(response.body);
      List<Bargain> bargain = responseJson.map((m) => new Bargain.fromJson(m)).toList();
      net.onAllBargainResponse(bargain);
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


  void onAllDemandResponse(List<Demand> demandList);
  void onAllDiscountResponse(List<Discount> discountList);
  void onAllBargainResponse(List<Bargain> bargain);
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

class Bargain{
  final String id;
  final String start;
  final String end;
  final String goods;
  final String title;
  final String content;
  final int mId;
  final String merchantName;
  final String address;
  final int status;
  final int communityId;



  Bargain({
    this.id,
    this.start,
    this.end,
    this.mId,
    this.title,
    this.content,
    this.status,
    this.communityId,
    this.address,
    this.goods,
    this.merchantName

  }) ;

  factory Bargain.fromJson(Map<String, dynamic> json){
    return new Bargain(
      id: json['id'].toString(),
      start: json['start'],
      end: json['end'],
      mId: json['merchantId'],
      title: json['title'],
      content: json['content'],
      status: json['status'],
      communityId: json['communityId'],
      goods: json['goods'],
      merchantName: json['name'],
      address: json['address'],

    );
  }
}




