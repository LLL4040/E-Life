import 'dart:convert';

import 'package:http/http.dart' as http;
class packageHttp {

  var myPackageUrl = "http://elife.natapp1.cc/package-server/api/package/findNew";

  var takeOutUrl = "http://elife.natapp1.cc/package-server/api/package/takeOut";

  var deleteUrl = "http://elife.natapp1.cc/package-server/api/package/deleteOne";



  myBill(NetListener net, String username,) {
    var client = new http.Client();
    client.post(
        myPackageUrl,
        body: {
          "username": username,
        }
    ).then((response,) {
      List responseJson = json.decode(response.body);
      List<Package> packageList = responseJson.map((m) => new Package.fromJson(m)).toList();
      print(response.body);
      net.onMyPackageResponse(packageList);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }

  takeOut(NetListener net,int id) {
    var client = new http.Client();
    client.post(
        takeOutUrl,
        body: {
          "id": id,
        }
    ).then((response,) {
      net.onTakeOutResponse(response.body);
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }
  delete(NetListener net,int id) {
    var client = new http.Client();
    client.post(
        deleteUrl,
        body: {
          "id": id,
        }
    ).then((response,) {
      net.onDeleteResponse(response.body);
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


  void onMyPackageResponse(List<Package> bill);
  void onTakeOutResponse(String bool);
  void onDeleteResponse(String bool);
  void onError(error);
}
class Package{
  final int id;
  final String time;
  final int status;
  final String managerName;
  final int communityId;

  Package({
    this.id,
    this.time,
    this.status,
    this.managerName,
    this.communityId,
  }) ;

  factory Package.fromJson(Map<String, dynamic> json){
    return new Package(
      id: json['id'],
      time: json['time'],
      status: json['status'],
      managerName: json['managerName'],
      communityId: json['communityId'],
    );
  }
}




