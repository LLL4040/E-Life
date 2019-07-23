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

class myCenterWidget extends State<mycenter>
    with SingleTickerProviderStateMixin {
  void _toFrind() {
    Navigator.push(context, new MaterialPageRoute(builder: (context) {
      return new friendWidget();
    }));
  }

  @override
  Widget build(BuildContext context) {
    return ScopedModel<UserModel>(
      model: UserModel(),
      child: ScopedModelDescendant<UserModel>(
        rebuildOnChange: true,
        builder: (context, child, model) {
          print("ppppppppp" + model.user.toString());
          return Scaffold(
            appBar: AppBar(
              actions: <Widget>[],
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
                            //model.user.username,
                            "hah",
                            style: TextStyle(fontWeight: FontWeight.bold),
                          ),
                          Text(
                            //model.user.role.toString(),
                            "居民",
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
                          print("邮包");
                        },
                      ),
                    ],
                  ),
                ),
              ],
            ),
          );
        },
      ),
    );
  }
}
