import 'package:e_life_flutter/message.dart';
import 'package:flutter/material.dart';
import 'mycenter.dart';
import 'package:e_life_flutter/service/service.dart';
import 'mainpage.dart';
import 'package:scoped_model/scoped_model.dart';
import 'user.dart';
class BottomNavigationWidget extends StatefulWidget {
  final username;
  final communityId;
  BottomNavigationWidget(this.username,this.communityId);
  @override
  _BottomNavigationWidget createState() => _BottomNavigationWidget(username,communityId);
}

class _BottomNavigationWidget extends State<BottomNavigationWidget> {
  final username;
  final communityId;
  _BottomNavigationWidget(this.username,this.communityId);
  final _BottomNavigationColor = Colors.black54; //组件内部私有变量
  int _currentIndex = 0;
  List<Widget> list = List();
  @override
  void initState() {
    print(username+"登录");
    print(communityId+"登录");

    list..add(mainpage(communityId))..add(service())..add(message(username))..add(mycenter());
    super.initState(); //无名无参需要调用
  }

  @override
  Widget build(BuildContext context) {

    return Scaffold(

        body: list[_currentIndex],
        bottomNavigationBar: BottomNavigationBar(
          items: [
            BottomNavigationBarItem(
                //导航栏子按钮
                icon: Icon(

                  Icons.domain,
                  color: _BottomNavigationColor,
                ),
                title: Text(
                  '主页',
                  style: TextStyle(color: _BottomNavigationColor),
                )),
            BottomNavigationBarItem(
                icon: Icon(
                  Icons.insert_invitation,
                  color: _BottomNavigationColor,

                ),
                title: Text(
                  '服务',
                  style: TextStyle(color: _BottomNavigationColor),
                )),
            BottomNavigationBarItem(
                icon: Icon(
                  Icons.mail,
                  color: _BottomNavigationColor,
                ),
                title: Text(
                  '消息',
                  style: TextStyle(color: _BottomNavigationColor),
                )),
            BottomNavigationBarItem(
                icon: Icon(
                  Icons.account_circle,
                  color: _BottomNavigationColor,
                ),
                title: Text(
                  '我的',
                  style: TextStyle(color: _BottomNavigationColor),
                )),
          ],
          type: BottomNavigationBarType.fixed, //导航栏类型
          currentIndex: _currentIndex,
          onTap: (int index) {
            setState(() {
              _currentIndex = index;
            });
          },
        ));

}
}
