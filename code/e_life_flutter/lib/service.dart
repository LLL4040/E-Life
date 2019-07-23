import 'package:e_life_flutter/group.dart';
import 'package:flutter/material.dart';
import 'repair.dart';
import 'package:scoped_model/scoped_model.dart';
import 'user.dart';
class Choice {
  const Choice({this.title, this.icon, this.position});
  final String title;
  final int position;
  final IconData icon;
}
class service  extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new serviceWidget();
  }
}
class serviceWidget extends State<service> with SingleTickerProviderStateMixin {
  @override
  Widget build(BuildContext context) {
    void _torepair(String username) {
      Navigator.push(context, new MaterialPageRoute(builder: (context) {
        return new myRepair(username);
      }));
    }
    void _toGroup() {
      Navigator.push(context, new MaterialPageRoute(builder: (context) {
        return new group();
      }));
    }
    return  ScopedModelDescendant<UserModel>(
        builder: (context, child, model)

    {
      print("小区服务"+model.user.username);
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
                    onPressed: null,
                  ),
                  Text("上门寄件")
                ],
              ),
              onPressed: null,
            ),


            FlatButton(
              child: Column(
                children: <Widget>[

                  IconButton(
                    icon: new Icon(Icons.settings_applications),
                    color: Colors.black38,
                    onPressed: (){
                      Navigator.push(context, new MaterialPageRoute(builder: (context) {
                        return new myRepair(model.user.username);
                      }));
                    }
                  ),
                  Text("物业维修")
                ],
              ),
              onPressed: (){
                Navigator.push(context, new MaterialPageRoute(builder: (context) {
                  return new myRepair(model.user.username);
                }));
              },
            ),
            FlatButton(
              child: Column(
                children: <Widget>[

                  IconButton(
                    icon: new Icon(Icons.shopping_cart),
                    color: Colors.black38,
                    onPressed: null,
                  ),
                  Text("超市送货")
                ],
              ),
              onPressed: null,
            ),
            FlatButton(
              child: Column(
                children: <Widget>[

                  IconButton(
                    icon: new Icon(Icons.computer),
                    color: Colors.black38,
                    onPressed: null,
                  ),
                  Text("电脑维修")
                ],
              ),
              onPressed: null,
            ),
            FlatButton(
              child: Column(
                children: <Widget>[

                  IconButton(
                    icon: new Icon(Icons.search),
                    color: Colors.black38,
                    onPressed: null,
                  ),
                  Text("周边")
                ],
              ),
              onPressed: null,
            ),
            FlatButton(
              child: Column(
                children: <Widget>[

                  IconButton(
                    icon: new Icon(Icons.free_breakfast),
                    color: Colors.black38,
                    onPressed: null,
                  ),
                  Text("团购")
                ],
              ),
              onPressed: _toGroup,
            ),
            FlatButton(
              child: Column(
                children: <Widget>[

                  IconButton(
                    icon: new Icon(Icons.monetization_on),
                    color: Colors.black38,
                    onPressed: null,
                  ),
                  Text("账单")
                ],
              ),
              onPressed: null,
            ),

          ],
        ),
      );
    });
  }
}
