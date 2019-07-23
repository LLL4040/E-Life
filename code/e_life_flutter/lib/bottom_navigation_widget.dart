import 'package:e_life_flutter/message.dart';
import 'package:flutter/material.dart';
import 'mycenter.dart';
import 'service.dart';
import 'mainpage.dart';
import 'package:scoped_model/scoped_model.dart';
import 'user.dart';

class BottomNavigationWidget extends StatefulWidget {
  @override
  _BottomNavigationWidget createState() => _BottomNavigationWidget();
}

class _BottomNavigationWidget extends State<BottomNavigationWidget>{
  final _BottomNavigationColor = Colors.black54; //组件内部私有变量
  int _currentIndex = 0;
  List<Widget> list = List();
  @override
  void initState() {
    list..add(mainpage())..add(service())..add(message())..add(mycenter());
    super.initState(); //无名无参需要调用
  }

  @override
  Widget build(BuildContext context) {
    UserModel userModel = UserModel();
    return ScopedModel<UserModel>(
        model: userModel,
        child: ScopedModelDescendant<UserModel>(
        builder: (context, child, model)
    {
      if(model.user != null){
        print("phone"+model.user.phone);
      }
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
    },
    ),
    );
  }
}
