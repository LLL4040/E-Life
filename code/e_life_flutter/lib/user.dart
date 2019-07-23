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
    await _getLogin(username,password,id);
    notifyListeners();
    return loginSuccess;
  }
  Future  _getLogin(String username,String password,String id)  async{
    manager.login(this, username,password,id);

  }
  @override
  void onUserResponse(User body,bool login) {
    user = body;
    loginSuccess = login;
    print(user.username);
    notifyListeners();
  }
  @override
  void onError(error) {
    // TODO: implement onError
  }
}
