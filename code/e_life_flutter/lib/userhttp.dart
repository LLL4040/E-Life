import 'dart:convert';
import 'package:flutter/material.dart';

import 'package:http/http.dart' as http;
class userHttp {
//  三天预报
  var userUrl = "http://elife.natapp1.cc/user-server/api/user/login";

  var logOutUrl = "http://zhimo.natapp1.cc/user-server/api/user/logout";
  var phoneAvailableUrl="http://zhimo.natapp1.cc/user-server/api/user/phoneAvailable";
  var sendIdentifyUrl="http://zhimo.natapp1.cc/user-server/api/user/sendIdentify";
  var phoneLoginUrl="http://zhimo.natapp1.cc/user-server/api/user/loginPhone";

  /**
   * 三天预报
   * cityName 城市名称 我们应该使用外部传入
   * net 监听网络请求的结果 因为他不是同步的
   */
  Future login(NetListener net,String username,String password,String id) async{
    var client = new http.Client();
    client.post(
        userUrl,
        body: {
          "username": username,
          "password": password,
          "id": id,
        }
    ).then((
        response,
        ) {
      print(response.headers);
      Map<String,dynamic> responseJson = json.decode(response.body);
      print(responseJson.toString());
      User user = User.fromJson(responseJson);
      user.session=response.headers["set-cookie"];
      //print(user.session+"session");
      bool login = false;
      if(user.login==1){
        print("httpTrue");
        login=true;
      }
      net.onUserResponse(user,login);
      //await new Future.delayed(new Duration(milliseconds: 1000));
      print("userhttp");
      return  login;
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }
  phoneLogin(NetListener net,String phone,String code,String id)async{
    var client = new http.Client();
    client.post(
        phoneLoginUrl,

        body: {
          "phone": phone,
          "code": code,
          "id": id,
        }
    ).then((
        response,
        ) async{
      print(response.headers);
      Map<String,dynamic> responseJson = json.decode(response.body);
      print(responseJson.toString());
      User user = User.fromJson(responseJson);
      user.session=response.headers["set-cookie"];
      bool login = false;
      if(user.login==1){
        print("httpTrue");
        login=true;
      }
      net.onUserResponse(user,login);
      //await new Future.delayed(new Duration(milliseconds: 1000));
      print("userhttp");
      return  login;
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }
  getNote(NetListener net,String phone,String id)async{
    var client = new http.Client();

    client.post(
      phoneAvailableUrl,

      body: {
        "phone": phone,
        "id":id,
      },
    ).then((response,) {
      print(response.body);
      Map<String, dynamic> responseJson = json.decode(response.body);
      bool success = (responseJson["available"]==0);
      print(success.toString()+"是否可用");
      if(success){
        print("hahah");
        client.post(
          sendIdentifyUrl,

          body: {
            "phone": phone,
            "id":id,
          },
        ).then((response,) {
          print(response.body+"00000");
//          Map<String, dynamic> responseJson = json.decode(response.body);
          bool success1 =  response.body=="true";
          print("success1==="+success1.toString());
          net.onGetNoteResponse(success1);
        });
      }

    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );

  }
  logout(NetListener net,String username,String session)async{
    var client = new http.Client();
    client.post(
      logOutUrl,
      headers: {
        "cookie": session,
      },
//      body: {
//        "username": username,
//      },
    ).then((response,) {
      print(response.body);
      Map<String, dynamic> responseJson = json.decode(response.body);
      bool success =   (responseJson["logout"]==1);

      net.onLogoutResponse(success);
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
  onUserResponse(User body,bool login) ;
  void onLogoutResponse(bool logout);
  onGetNoteResponse(bool ifGetNote);
  void onError(error);
}

class User{
  final int role;
  final String phone;
  final int login;
  final int communityId;
  final String username;
  final String email;
  String session;


  User({
    this.role,
    this.phone,
    this.login,
    this.communityId,
    this.username,
    this.email,
    this.session,
  }) ;

  factory User.fromJson(Map<String, dynamic> json){
    return new User(
      role: json['role'],
      phone: json['phone'],
      login: json['login'],
      communityId: json['communityId'],
      username: json['username'],
      email: json['email'],
    );
  }
}




