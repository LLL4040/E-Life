import 'package:flutter/material.dart';
import 'notice.dart';
import 'map.dart';
import 'friend.dart';


class mycenter extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new myCenterWidget();
  }
}
class myCenterWidget extends State<mycenter> with SingleTickerProviderStateMixin {

  void _toNotice(){
    Navigator.push(context,
        new MaterialPageRoute(builder: (context) {
          return new myNotice();
        }));
  }
  void _toMap() {
    var androidView = new AndroidView(viewType: "MyMap");
    Navigator.push(context,
        new MaterialPageRoute(builder: (context) {
          return new Map(androidView : androidView);
        })).then((var onValue){
          androidView = onValue;
    });
  }
  void _toFriend(){
    Navigator.push(context,
        new MaterialPageRoute(builder: (context) {
          return new friendWidget();
        }));
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        actions: <Widget>[

        ],
        title: Text('个人中心'),
      ),
      body:  Column(
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
                      "用户名",
                      style: TextStyle(fontWeight: FontWeight.bold),
                    ),
                    Text(
                      "身份",
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
                ListTile(
                  leading: const Icon(Icons.fiber_new),
                  title: const Text('我的通知'),
                  onTap:  () {
                    _toNotice();
                  },
                ),
                ListTile(
                  leading: const Icon(Icons.people_outline),
                  title: const Text('我的好友'),
                  onTap:  () {
                    _toFriend();
                  },
                ),
                ListTile(
                  leading: const Icon(Icons.settings_system_daydream),
                  title: const Text('邮包'),
                  onTap:  () {
                    print("邮包");
                  },
                ),
                ListTile(
                  leading: const Icon(Icons.add_location),
                  title: const Text('我的周边'),
                  onTap:  () {
                    _toMap();
                  },
                ),
              ],
            ),
          ),
        ],
      ),

    );
  }
}
