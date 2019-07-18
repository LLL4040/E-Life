import 'package:e_life_flutter/maintain.dart';
import 'package:flutter/material.dart';
import 'forum.dart';
import 'groupBuy.dart';
import 'news.dart';

class BottomNavigationWidget extends StatefulWidget {
  @override
  _BottomNavigationWidget createState() => _BottomNavigationWidget();
}

class _BottomNavigationWidget extends State<BottomNavigationWidget> {
  final _BottomNavigationColor = Colors.blue; //组件内部私有变量
  int _currentIndex = 0;
  List<Widget> list = List();
  @override
  void initState() {
    list..add(newsPage())..add(GroupBuy())..add(maintain())..add(forum());
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
                  Icons.mail,
                  color: _BottomNavigationColor,
                ),
                title: Text(
                  '资讯',
                  style: TextStyle(color: _BottomNavigationColor),
                )),
            BottomNavigationBarItem(
                icon: Icon(
                  Icons.shopping_cart,
                  color: _BottomNavigationColor,
                ),
                title: Text(
                  '团购',
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
                  Icons.message,
                  color: _BottomNavigationColor,
                ),
                title: Text(
                  '论坛',
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
