import 'dart:convert';

import 'package:http/http.dart' as http;
class userHttp {
//  三天预报
  var userUrl = "http://elife.natapp1.cc/user-server/api/user/login";


  /**
   * 三天预报
   * cityName 城市名称 我们应该使用外部传入
   * net 监听网络请求的结果 因为他不是同步的
   */
  void login(NetListener net,String username,String password,String id) {
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
      Map<String,dynamic> responseJson = json.decode(response.body);
      print(responseJson.toString());
      User user = User.fromJson(responseJson);
      bool login = false;
      if(user.login==1){
        print("httpTrue");
        login=true;
      }
      net.onUserResponse(user,login);
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
  void onUserResponse(User body,bool login);
  void onError(error);
}

class User{
  final int role;
  final String phone;
  final int login;
  final int communityId;
  final String username;
  final String email;


  User({
    this.role,
    this.phone,
    this.login,
    this.communityId,
    this.username,
    this.email,
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




