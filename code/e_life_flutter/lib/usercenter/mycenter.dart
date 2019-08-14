import 'package:flutter/material.dart';
import '../map.dart';
import 'package:scoped_model/scoped_model.dart';
import '../user.dart';
import 'package:e_life_flutter/friends/friend.dart';
import '../main.dart';


class mycenter extends StatefulWidget {
  final username;
  final role;
  mycenter(this.username,this.role);

  @override
  State<StatefulWidget> createState() {
    return new myCenterWidget(username,role);
  }
}
class myCenterWidget extends State<mycenter> with SingleTickerProviderStateMixin {

  final username1;
  final role1;
  myCenterWidget(this.username1,this.role1);

  void _toMap() {
    var androidView = new AndroidView(viewType: "MyMap");
    Navigator.push(context,
        new MaterialPageRoute(builder: (context) {
          return new Map(androidView : androidView);
        })).then((var onValue){
          androidView = onValue;
    });
  }
 String username="未登录";
 String role = "用户";
  @override
  Widget build(BuildContext context) {
    return  ScopedModelDescendant<UserModel>(
        builder: (context, child, model)
    {

      username=username1;

      if(role1=="0"){
        role="用户";
      }
      if(role1=="1"){
        role="管理员";
      }
      if(role1=="2"){
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
//                  title: const Text('我的周边'),
//                  onTap:  () {
//                    _toMap();
//                  },
//                ),
                  ListTile(
                    leading: const Icon(Icons.people_outline),
                    title: const Text('我的好友'),
                    onTap: (){
                      Navigator.push(context,
                          new MaterialPageRoute(builder: (context) {
                            return new friendWidget(username);
                          }));
                    }
                  ),

                ],
              ),
            ),
          ],
        ),
      floatingActionButton: FloatingActionButton(
        onPressed: (){
          Navigator.push<String>(context, new MaterialPageRoute(builder: (context) {
            return new MyApp();
          }));
        },
        tooltip: '退出登录',
        child: Icon(Icons.keyboard_return),
      ),
      );
    });
  }
}
