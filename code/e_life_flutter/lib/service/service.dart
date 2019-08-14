import 'package:e_life_flutter/service/pay.dart';
import 'package:flutter/material.dart';
import 'repair.dart';
import 'package:e_life_flutter/service/group.dart';
import 'package:scoped_model/scoped_model.dart';
import 'package:e_life_flutter/user.dart';
import 'supermarket.dart';
import 'computer.dart';

import 'package:url_launcher/url_launcher.dart';




class Choice {
  const Choice({this.title, this.icon, this.position});
  final String title;
  final int position;
  final IconData icon;
}
class service  extends StatefulWidget {
  final username1;
  final communityId1;
  final role1;
  service(this.username1,this.communityId1,this.role1);
  @override
  State<StatefulWidget> createState() {
    return new serviceWidget(username1,communityId1,role1);
  }
}
class serviceWidget extends State<service> with SingleTickerProviderStateMixin {
  final username1;
  final communityId1;
  final role1;
  serviceWidget(this.username1,this.communityId1,this.role1);
  @override
  Widget build(BuildContext context) {
//    void _torepair(String username) {
//      Navigator.push(context, new MaterialPageRoute(builder: (context) {
//        return new myRepair(username);
//      }));
//    }
//    void _toPay(String username) {
//      Navigator.push(context, new MaterialPageRoute(builder: (context) {
//        return new pay(username);
//      }));
//    }
    _launchURL() async {
      const url = 'https://www.sf-express.com/cn/sc/dynamic_function/order/quick/';
      if (await canLaunch(url)) {
        await launch(url);
      } else {
        throw 'Could not launch $url';
      }
    }
    return  ScopedModelDescendant<UserModel>(
        builder: (context, child, model)

    {
      print("小区服务"+username1);
      return Scaffold(
        appBar: AppBar(
          actions: <Widget>[],
          title: Text('小区服务'),
        ),
        body: GridView(
          padding: EdgeInsets.all(5),
          gridDelegate: SliverGridDelegateWithMaxCrossAxisExtent(
              maxCrossAxisExtent: 150.0, childAspectRatio: 1.0 //宽高比为2
          ),
          children: <Widget>[
            FlatButton(
              child: Column(
                children: <Widget>[

                  IconButton(
                    icon: new Icon(Icons.event),
                    color: Colors.black38,
                    onPressed: _launchURL,
                  ),
                  Text("上门寄件")
                ],
              ),
              onPressed: _launchURL,
            ),


            FlatButton(
              child: Column(
                children: <Widget>[

                  IconButton(
                    icon: new Icon(Icons.settings_applications),
                    color: Colors.black38,
                    onPressed: (){
                      Navigator.push(context, new MaterialPageRoute(builder: (context) {
                        print("我的维修"+username1);
                        return new myRepair(username1);
                      }));
                    }
                  ),
                  Text("物业维修")
                ],
              ),
              onPressed: (){
                Navigator.push(context, new MaterialPageRoute(builder: (context) {
                  return new myRepair(username1);
                }));
              },
            ),
            FlatButton(
              child: Column(
                children: <Widget>[

                  IconButton(
                    icon: new Icon(Icons.shopping_cart),
                    color: Colors.black38,
                    onPressed:  (){
                      Navigator.push(context, new MaterialPageRoute(builder: (context) {
                        return new surpermarket(communityId1);
                      }));
                    },
                  ),
                  Text("超市送货")
                ],
              ),
              onPressed: (){
                Navigator.push(context, new MaterialPageRoute(builder: (context) {
                  return new surpermarket(communityId1);
                }));
              },
            ),
            FlatButton(
              child: Column(
                children: <Widget>[

                  IconButton(
                    icon: new Icon(Icons.computer),
                    color: Colors.black38,
                    onPressed:  (){
                      Navigator.push(context, new MaterialPageRoute(builder: (context) {
                        return new computer(communityId1);
                      }));
                    },
                  ),
                  Text("电脑维修")
                ],
              ),
              onPressed:  (){
                Navigator.push(context, new MaterialPageRoute(builder: (context) {
                  return new computer(communityId1);
                }));
              },
            ),
            FlatButton(
              child: Column(
                children: <Widget>[

                  IconButton(
                    icon: new Icon(Icons.free_breakfast),
                    color: Colors.black38,
                      onPressed: (){
                        Navigator.push(context, new MaterialPageRoute(builder: (context) {
                          return new groupWeight(username1, communityId1);
                        }));
                      }
                  ),
                  Text("团购")
                ],
              ),
                onPressed: (){
                  Navigator.push(context, new MaterialPageRoute(builder: (context) {
                    return new groupWeight(username1, communityId1);
                  }));
                }
            ),
            FlatButton(
              child: Column(
                children: <Widget>[

                  IconButton(
                    icon: new Icon(Icons.monetization_on),
                    color: Colors.black38,
                    onPressed:(){
                      Navigator.push(context, new MaterialPageRoute(builder: (context) {
                        return new pay(username1);
                      }));
                    },
                  ),
                  Text("账单")
                ],
              ),
              onPressed: (){
                Navigator.push(context, new MaterialPageRoute(builder: (context) {
                  return new pay(username1);
                }));
              },
            ),

          ],
        ),
      );
    });
  }
}
