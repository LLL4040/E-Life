import 'package:flutter/material.dart';
import 'bottom_navigation_widget.dart';
import 'package:flutter_swiper/flutter_swiper.dart';

class newsPage extends StatelessWidget {
  final Widget myDevider = Container(
    child: Divider(
      color: Colors.black,
    ),
  );
  Widget urgentSection = Container(
    padding: const EdgeInsets.all(10),
    child: Row(
      children: [
        Expanded(
          /*1*/
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              /*2*/
              Container(
                padding: const EdgeInsets.only(bottom: 8),
                child: Text(
                  '紧急通知',
                  style: TextStyle(
                    fontSize: 30.0,
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ),
              Container(
                child: Divider(),
              ),
              Container(
                padding: EdgeInsets.all(10),
                child: new Image.asset(
                  'images/app.png',
                  height: 200,
                ),
              ),
            ],
          ),
        ),
        /*3*/
//        new FavoriteWidget(),
      ],
    ),
  );
  Widget newsSection = new Padding(
    padding: new EdgeInsets.fromLTRB(20.0, 15.0, 20.0, 40.0),
  );
  Widget activitySection = new Padding(
    padding: new EdgeInsets.fromLTRB(20.0, 15.0, 20.0, 40.0),
  );
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        actions: <Widget>[
          new IconButton(icon: new Icon(Icons.account_circle), onPressed: null),
        ],
        title: new Text("资讯"),
      ),
      body: new SingleChildScrollView(
        child: Column(
          children: <Widget>[
            urgentSection,
            myDevider,
            newsSection,
            myDevider,
            activitySection,
            myDevider,
            //new BottomNavigationWidget(),
          ],
        ),
      ),
    );
  }
}
