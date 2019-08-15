import 'dart:convert';

import 'package:flutter/cupertino.dart';

import 'userhttp.dart';
import 'package:scoped_model/scoped_model.dart';
import 'package:http/http.dart' as http;


class UserModel extends Model with NetListener{
  userHttp manager = new userHttp();
  User user;
  bool loginSuccess = false;

  Future<bool> login(String username,String password,String id) async{
     bool temp = await _getLogin(username,password,id);
    notifyListeners();
      await new Future.delayed(new Duration(milliseconds: 1000));
    print("login请求");
    return temp;
  }
  Future<bool>  _getLogin(String username,String password,String id)  async{
    bool temp = await manager.login(this, username,password,id);
    print("ciji");
    return temp;

  }
  @override
  void onUserResponse(User body,bool login) {
    user = body;
    loginSuccess = login;
    print("response");
    notifyListeners();
  }
  @override void onLogoutResponse(bool logout) {
    // TODO: implement onLogoutResponse
  }
  @override
  void onError(error) {
    // TODO: implement onError
  }
}
