import 'package:flutter/material.dart';
import 'package:scoped_model/scoped_model.dart';
import 'user.dart';
import 'friend.dart';
class mycenter extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new myCenterWidget();
  }
}
class myCenterWidget extends State<mycenter> with SingleTickerProviderStateMixin {
 String username="用户";
 String role = "用户";
  void _toFrind(){
    Navigator.push(context,
        new MaterialPageRoute(builder: (context) {
          return new friendWidget();
        }));
  }
  @override
  Widget build(BuildContext context) {
    return  ScopedModelDescendant<UserModel>(
        builder: (context, child, model)
    {

      username=model.user.username;
      if(model.user.role==0){
        role="用户";
      }
      if(model.user.role==1){
        role="管理员";
      }
      if(model.user.role==2){
        role="商家";
      }
      return Scaffold(
        appBar: AppBar(
          actions: <Widget>[
          ],
          title: Text('个人中心'),
        ),
        body: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Padding(
              padding: const EdgeInsets.only(top: 38.0),
              child: Row(
                children: <Widget>[
                  Padding(
                    padding: const EdgeInsets.symmetric(horizontal: 16.0),
                    child: ClipOval(
                      child: Image.asset(
                        "images/app.png",
                        width: 80,
                      ),
                    ),
                  ),
                  Column(
                    children: <Widget>[
                      Text(

                        username,
                        style: TextStyle(fontWeight: FontWeight.bold),
                      ),
                      Text(
                        role,
                        style: TextStyle(fontWeight: FontWeight.bold),
                      ),
                    ],
                  ),

                ],
              ),
            ),
            Expanded(
              child: ListView(
                children: <Widget>[
//                ListTile(
//                  leading: const Icon(Icons.fiber_new),
//                  title: const Text('我的通知'),
//                  onTap:  () {
//                    _toNotice();
//                  },
//                ),
                  ListTile(
                    leading: const Icon(Icons.people_outline),
                    title: const Text('我的好友'),
                    onTap: () {
                      _toFrind();
                    },
                  ),
                  ListTile(
                    leading: const Icon(Icons.settings_system_daydream),
                    title: const Text('邮包提醒'),
                    onTap: () {
                    },
                  ),
                ],
              ),
            ),
          ],
        ),

      );
    });
  }
}
