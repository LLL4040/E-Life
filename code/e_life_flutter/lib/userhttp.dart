import 'dart:convert';

import 'package:http/http.dart' as http;
class userHttp {
//  三天预报
  var userUrl = "http://elife.natapp1.cc/user-server/api/user/login";

  var logOutUrl = "http://elife.natapp1.cc/user-server/api/user/logout";


  /**
   * 三天预报
   * cityName 城市名称 我们应该使用外部传入
   * net 监听网络请求的结果 因为他不是同步的
   */
  Future login(NetListener net,String username,String password,String id) {
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
        ) async{
      print(response.headers);
      Map<String,dynamic> responseJson = json.decode(response.body);
      print(responseJson.toString());
      User user = User.fromJson(responseJson);
      user.session=response.headers["set-cookie"];
      print(user.session+"session");
      bool login = false;


      if(user.login==1){
        print("httpTrue");
        login=true;
      }
      net.onUserResponse(user,login);
      await new Future.delayed(new Duration(milliseconds: 1));
      print("userhttp");
      return  login;
    }, onError: (error) {
      net.onError(error);
    }).whenComplete(
      client.close,
    );
  }

  logout(NetListener net,String username,String session){
    var client = new http.Client();
    client.post(
      logOutUrl,
      headers: {
        "cookie": session,
      },
      body: {
        "username": username,
      },
    ).then((response,) {
      print(response);
      Map<String, dynamic> responseJson = json.decode(response.body);
      bool success =  responseJson.containsValue("1");

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
  void onUserResponse(User body,bool login) ;
  void onLogoutResponse(bool logout);
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




