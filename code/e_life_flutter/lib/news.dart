import 'package:flutter/material.dart';
import 'bottom_navigation_widget.dart';
import 'package:flutter_swiper/flutter_swiper.dart';
import 'user.dart';

class newsPage extends StatelessWidget {
  final Widget myDevider = Container(
    child: Divider(
      color: Colors.black12,
    ),
  );
  Widget urgentSection = Expanded(
    child: ListView(
      children: [
        Container(
          padding: const EdgeInsets.only(bottom: 8),
          child: Text(
            '紧急通知',
            style: TextStyle(
              fontSize: 30.0,
              fontWeight: FontWeight.bold,

            ),
            textAlign: TextAlign.center,
          ),
        ),
        Container(
          child: Divider(),
        ),
        Container(
          padding: EdgeInsets.all(10),
          child: new Image.asset(
            'images/app.png',
            height: 100,
          ),
        ),

      ],
    ),
  );
  Widget newsSection = new Expanded(
    child: ListView(
      children: <Widget>[
        new Row(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            Row(
              children: <Widget>[
                Text(
                  "最新资讯",
                  textAlign: TextAlign.center,
                )
              ],
            ),
          ],
        ),
        new ListTile(
          leading: new Icon(Icons.nature_people),
          title: new Text('活动1'),
          subtitle: new Row(
            children: <Widget>[
              new Text('时间:2019/7/23'),
              new Icon(Icons.person)
            ],
          ),
          trailing: new Icon(Icons.keyboard_arrow_down),
          onTap: () {
            print('点击');
          },
          dense: true,
        ),
        new ListTile(
          leading: new Icon(Icons.nature_people),
          title: new Text('活动2'),
          subtitle: new Row(
            children: <Widget>[
              new Text('时间:2019/7/29'),
              new Icon(Icons.person)
            ],
          ),
          trailing: new Icon(Icons.keyboard_arrow_down),
          onTap: () {
            print('点击');
          },
          dense: true,
        ),
        new ListTile(
          leading: new Icon(Icons.nature_people),
          title: new Text('活动3'),
          subtitle: new Row(
            children: <Widget>[new Text('时间:2019/7/9'), new Icon(Icons.person)],
          ),
          trailing: new Icon(Icons.keyboard_arrow_down),
          onTap: () {
            print('点击');
          },
          dense: true,
        ),
      ],
    ),
  );
  Widget activitySection = new Expanded(
      child: ListView(
        children: <Widget>[
          new Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              Row(
                children: <Widget>[
                  Text(
                    "最新资讯",
                    textAlign: TextAlign.center,
                  )
                ],
              ),
            ],
          ),
          new ListTile(
            leading: new Icon(Icons.nature_people),
            title: new Text('活动1'),
            subtitle: new Row(
              children: <Widget>[
                new Text('时间:2019/7/23'),
                new Icon(Icons.person)
              ],
            ),
            trailing: new Icon(Icons.keyboard_arrow_down),
            onTap: () {
              print('点击');
            },
            dense: true,
          ),
          new ListTile(
            leading: new Icon(Icons.nature_people),
            title: new Text('活动2'),
            subtitle: new Row(
              children: <Widget>[
                new Text('时间:2019/7/29'),
                new Icon(Icons.person)
              ],
            ),
            trailing: new Icon(Icons.keyboard_arrow_down),
            onTap: () {
              print('点击');
            },
            dense: true,
          ),
          new ListTile(
            leading: new Icon(Icons.nature_people),
            title: new Text('活动3'),
            subtitle: new Row(
              children: <Widget>[new Text('时间:2019/7/9'), new Icon(Icons.person)],
            ),
            trailing: new Icon(Icons.keyboard_arrow_down),
            onTap: () {
              print('点击');
            },
            dense: true,
          ),
        ],
      ),
  );
  @override
  Widget build(BuildContext context) {
    void _toUser() {
      Navigator.of(context)
          .push(new MaterialPageRoute(builder: (context) => User()));
    }

    return new Scaffold(
      appBar: new AppBar(
        actions: <Widget>[
          new IconButton(
              icon: new Icon(Icons.account_circle), onPressed: _toUser),
        ],
        title: new Text("资讯"),
      ),
      body: Column(
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
    );
  }
}
