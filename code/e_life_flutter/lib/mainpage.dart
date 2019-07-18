import 'package:flutter/material.dart';
import 'bottom_navigation_widget.dart';
import 'package:flutter_swiper/flutter_swiper.dart';
import 'user.dart';
class mainpage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new mainpageWidget();
  }
}
class Choice {
  const Choice({this.title, this.icon, this.position});
  final String title;
  final int position;
  final IconData icon;
}

class mainpageWidget extends State<mainpage> with SingleTickerProviderStateMixin {
  List<Choice> tabs = [];//导航栏
  TabController mTabController;
  int mCurrentPosition = 0;
  List<Widget> widgetList = [];//要显示的下方组件

  List<Widget> noticeList=[];//要显示的物业通知列表



  Widget _getNotice(String title, String time,String content  ) {
    return  new ListTile(
      leading: new Icon(Icons.message),
      title: new Text(title),
      subtitle: new Column(
        crossAxisAlignment:CrossAxisAlignment.start,
        children: <Widget>[
          new Text(time),
          new Text(content),
        ],
      ),
      trailing: new Icon(Icons.delete,color:Colors.black54 ,),
      onTap: () {
        print(content);
      },
      dense: true,
    );
  }
  Widget _getpackage(String status, String time,String content  ) {
    return  new ListTile(
      leading: new Icon(Icons.markunread_mailbox),
      title: new Text(status),
      subtitle: new Row(
        children: <Widget>[
          new Text('时间:'+time),

        ],
      ),
      trailing: new Icon(Icons.keyboard_arrow_down),
      onTap: () {
        print(content);
      },
      dense: true,
    );
  }

  @override
  Widget build(BuildContext context) {
    List<Widget> notices = [];
    List<Widget> packages = [];

    Widget notice = _getNotice("欠费", "2019/7/12", "请尽快缴费");
    Widget notice2 = _getNotice("交钱", "2019/7/12", "请尽快缴纳房租你怎么还不交钱啊啊啊啊啊啊快交钱啊啊啊啊啊");
    Widget package1 = _getpackage("未提取", "2019/5/7", "您有一个邮包代取");
    Widget package2 = _getpackage("已签收", "2019/5/7", "您有一个邮包代取");
    notices.add(notice);
    notices.add(notice2);
    packages.add(package1);
    packages.add(package2);

    // 从 itemBuilder 调用的独立函数
    Widget buildNoticeBody(BuildContext ctxt, int index) {
      return notices[index];
    }
    // 从 itemBuilder 调用的独立函数
    Widget buildPackageBody(BuildContext ctxt, int index) {
      return packages[index];
    }
    //物业通知界面
    Widget noticeContain = new Container(
      child:new ListView.builder
        (
          itemCount: notices.length,
          itemBuilder: (BuildContext ctxt, int index) => buildNoticeBody(ctxt, index)
      ) ,
    );
    //邮包提醒界面
    Widget packageMessage = new Container(
      child:new ListView.builder
        (
          itemCount: notices.length,
          itemBuilder: (BuildContext ctxt, int index) => buildPackageBody(ctxt, index)
      ) ,
    );
    widgetList.add(noticeContain);
    widgetList.add(packageMessage);

    return Scaffold(

      body: NestedScrollView(
        headerSliverBuilder: (BuildContext context, bool innerBoxIsScrolled) {
          return [
            new SliverAppBar(
              forceElevated :true,
              bottom: PreferredSize(
                  child: new Container(
                    color: Colors.white,
                    child: new TabBar(
                      indicatorSize: TabBarIndicatorSize.label,
                      indicatorColor: Colors.blueAccent,
                      labelColor: Colors.blueAccent,
                      unselectedLabelColor: Colors.black45,
                      tabs: tabs.map((Choice choice) {
                        return new Tab(
                          text: choice.title,
                          icon: new Icon(
                            choice.icon,
                          ),
                        );
                      }).toList(),
                      controller: mTabController,
                    ),
                  ),
                  preferredSize: new Size(double.infinity, 18.0)),

            )
          ];
        },
        body: new TabBarView(
          children: tabs.map((Choice choice) {
            return new Padding(
                padding: const EdgeInsets.all(15.0),
                child: widgetList[choice.position] );
          }).toList(),
          controller: mTabController,
        ),
      ),
    );

  }

  @override
  void initState() {
    super.initState();
    tabs.add(Choice(title: '资讯', icon: Icons.fiber_new, position: 0));
    tabs.add(Choice(title: '论坛', icon: Icons.message, position: 1));

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
  void dispose() {
    super.dispose();
    mTabController.dispose();
  }
}
class myWidget extends State<mainpage> with SingleTickerProviderStateMixin {
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


    return new Scaffold(
      appBar: new AppBar(
        actions: <Widget>[
          new IconButton(
              icon: new Icon(Icons.account_circle), onPressed: null),
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
