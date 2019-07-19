import 'package:flutter/material.dart';
//资讯的widget
class newswidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new myWidget();
  }
}

class myWidget extends State<newswidget> with SingleTickerProviderStateMixin {
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


  TabController controller;
  var tabs = <Tab>[];

  @override
  void initState() {
    super.initState();
    tabs = <Tab>[
      Tab(text: "紧急通知",),
      Tab(text: "最新资讯",),
      Tab(text: "活动安排",),


    ];

    //initialIndex初始选中第几个
    controller =
        TabController(initialIndex: 0, length: tabs.length, vsync: this);
  }


  @override
  Widget build(BuildContext context) {

    return DefaultTabController(
      length: tabs.length,
      child: MaterialApp(
        home: Scaffold(
          appBar:
          TabBar(
            controller: controller,//可以和TabBarView使用同一个TabController
            tabs: tabs,
            isScrollable: true,
            indicatorColor: Colors.black12,
            indicatorWeight: 1,
            indicatorSize: TabBarIndicatorSize.tab,
            indicatorPadding: EdgeInsets.fromLTRB(5.0,1.0,5.0,1.0),
            labelPadding: EdgeInsets.only( left: 20),
            indicator: const BoxDecoration(),
            labelColor: Color(0xff333333),
            labelStyle: TextStyle(

              fontSize: 12.0,
            ),
            unselectedLabelColor: Colors.black12,
            unselectedLabelStyle: TextStyle(
              fontSize: 12.0,
            ),
          ),

          body: TabBarView(
              controller: controller,
              children: tabs
                  .map((Tab tab) =>
                  Container(child: Center(child: Text(tab.text),),))
                  .toList()),
        ),
      ),
    );
  }
  @override
  void dispose() {
    super.dispose();
    controller.dispose();
  }
}
