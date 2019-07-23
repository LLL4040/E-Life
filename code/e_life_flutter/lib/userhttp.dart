import 'dart:convert';

import 'package:http/http.dart' as http;

class userHttp {

  var userUrl = "http://elife.natapp1.cc/user-server/api/user/login";


  login(NetListener net, String username, String password, String id) {
    print("发请求");
    var client = new http.Client();
    client.post(userUrl, body: {
      "username": username,
      "password": password,
      "id": id,
    }).then((
      response,
    ) {
      Map<String, dynamic> responseJson = json.decode(response.body);
      print(responseJson.toString());
      User user = User.fromJson(responseJson);
      print(user.email);
      net.onUserResponse(user);
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

  void onUserResponse(User body);
  void onError(error);
}

class User {
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
  });

  factory User.fromJson(Map<String, dynamic> json) {
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
