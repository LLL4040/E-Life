import 'dart:convert';
import 'main.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'userhttp.dart';
import 'package:scoped_model/scoped_model.dart';
import 'package:http/http.dart' as http;
import 'package:oktoast/oktoast.dart';
import 'bottom_navigation_widget.dart';


class UserModel extends Model with NetListener{
  userHttp manager = new userHttp();
  User user;
  bool loginSuccess = false;

  Future<bool> login(String username,String password,String id) async{
    return _getLogin(username,password,id);
//    notifyListeners();
//      await new Future.delayed(new Duration(milliseconds: 2000));
//    print("login请求");
//    return loginSuccess;
  }
  Future<bool>  _getLogin(String username,String password,String id)  async{
    bool temp = await manager.login(this, username,password,id);
    print("ciji");
    return temp;

  }
  @override
  onUserResponse(User body,bool login) {
    user = body;
    loginSuccess = login;
    print("response");
//    if(loginSuccess!=false){
//      Navigator.of(context).pushAndRemoveUntil(
//          new MaterialPageRoute(
//              builder: (context) => new BottomNavigationWidget(user.username,user.communityId.toString(),user.role.toString(),user.session)),
//              (route) => route == null);
//      showToast("登录成功");
//    }else{
//      showToast("登录失败");
//    }

  }
  @override void onLogoutResponse(bool logout) {
    // TODO: implement onLogoutResponse
  }
  @override
  void onError(error) {
    // TODO: implement onError
  }
  @override
  void onGetNoteResponse(bool ifGetNote){

  }
}
