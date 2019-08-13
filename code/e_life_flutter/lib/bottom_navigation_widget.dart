import 'package:e_life_flutter/message/message.dart';
import 'package:flutter/material.dart';
import 'usercenter/mycenter.dart';
import 'package:e_life_flutter/service/service.dart';
import 'mainpage/mainpage.dart';
import 'package:scoped_model/scoped_model.dart';
import 'user.dart';
class BottomNavigationWidget extends StatefulWidget {
  final username;
  final communityId;
  final role;
  BottomNavigationWidget(this.username,this.communityId,this.role);
  @override
  _BottomNavigationWidget createState() => _BottomNavigationWidget(username,communityId,role);
}

class _BottomNavigationWidget extends State<BottomNavigationWidget> {
  final username;
  final communityId;
  final role;
  _BottomNavigationWidget(this.username,this.communityId,this.role);
  final _BottomNavigationColor = Colors.black54; //组件内部私有变量
  int _currentIndex = 0;
  List<Widget> list = List();
  @override
  void initState() {
    print(username+"登录");
    print(communityId+"登录");

    list..add(mainpage(communityId,username))..add(service(username,communityId,role))..add(message(username))..add(mycenter(username,role));
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
