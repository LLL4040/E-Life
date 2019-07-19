import 'package:flutter/material.dart';
//资讯的widget
class newswidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new myWidget();
  }
}
class Choice {
  const Choice({this.title, this.icon, this.position});
  final String title;
  final int position;
  final IconData icon;
}
class myWidget extends State<newswidget> with SingleTickerProviderStateMixin {
  final Widget myDevider = Container(
    child: Divider(
      color: Colors.black12,
    ),
  );
  Widget urgentSection = Container(
    child: ListView(
      children: [

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
      ],
    ),
  );
  Widget newsSection = new Container(
    child: ListView(
      children: <Widget>[

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
  Widget activitySection = new Container(
    child: ListView(
      children: <Widget>[

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
  List<Choice> tabs = [];//导航栏
  TabController mTabController;
  int mCurrentPosition = 0;

  TabController controller;


  @override
  void initState() {
    super.initState();
    tabs.add(Choice(title: '紧急通知', icon: Icons.fiber_new, position: 0));
    tabs.add(Choice(title: '最新资讯', icon: Icons.message, position: 1));
    tabs.add(Choice(title: '活动管理', icon: Icons.message, position: 2));

    mTabController = new TabController(vsync: this, length: tabs.length);
    //判断TabBar是否切换
    mTabController.addListener(() {
      if (mTabController.indexIsChanging) {
        setState(() {
          mCurrentPosition = mTabController.index;
        });
      }
    });
  }


  @override
  Widget build(BuildContext context) {
    List<Widget> newsContain=[];
    newsContain.add(urgentSection);
    newsContain.add(newsSection);
    newsContain.add(activitySection);

    return DefaultTabController(
      length: tabs.length,
      child: MaterialApp(
        home: Scaffold(
          appBar:
          TabBar(
            tabs: tabs.map((Choice choice) {
              return new Tab(
                text: choice.title,
                icon: new Icon(
                  choice.icon,
                ),
              );
            }).toList(),
            controller: mTabController,
            //isScrollable: true,
            indicatorColor: Colors.black12,
            indicatorWeight: 1,
            indicatorSize: TabBarIndicatorSize.label,
            indicatorPadding: EdgeInsets.fromLTRB(5.0,1.0,5.0,1.0),
            labelPadding: EdgeInsets.all( 10),
            indicator: const BoxDecoration(),
            labelColor: Color(0xff333333),
//            labelStyle: TextStyle(
//
//              fontSize: 12.0,
//            ),
            unselectedLabelColor: Colors.black12,
            unselectedLabelStyle: TextStyle(
              fontSize: 12.0,
            ),
          ),

          body: TabBarView(
              controller:  mTabController,
              children: tabs
                  .map((Choice choice) {
                return new Padding(
                      padding: const EdgeInsets.all(1.0),
                      child:newsContain[choice.position] ) ;


              }).toList(),

          ),
        ),
      ),
    );
  }
  @override
  void dispose() {
    super.dispose();
    mTabController.dispose();
  }
}

