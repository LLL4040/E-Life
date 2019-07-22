import 'dart:convert';

import 'package:flutter/cupertino.dart';

import 'userhttp.dart';
import 'package:scoped_model/scoped_model.dart';
import 'package:http/http.dart' as http;

class UserModel extends Model with NetListener{
  userHttp manager = new userHttp();
  User weather;

  void login(String username,String password,String id) async{
    await _getLogin(username,password,id);
    print("75858585");
    notifyListeners();
  }
  _getLogin(String username,String password,String id) async {
    await manager.login(this, username,password,id);
    print("123");
  }
  @override
  void onUserResponse(User body) {
    weather = body;
    print("shshshh"+weather.username);
    notifyListeners();
  }
  @override
  void onError(error) {
    // TODO: implement onError
  }
}
