import 'package:flutter/material.dart';
import 'bottom_navigation_widget.dart';
import 'package:flutter_swiper/flutter_swiper.dart';
import 'postDetail.dart';
import 'news.dart';

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
  Widget _getpost(String name, String time,String title  ) {
    return  new ListTile(
      leading: new Text(name),
      title: new Text(title),
      subtitle: new Row(
        children: <Widget>[
          new Text('时间:'+time),

        ],
      ),
      trailing: new Icon(Icons.keyboard_arrow_right),
      onTap: () {
        print(title);
      },
      dense: true,
    );
  }

  @override
  Widget build(BuildContext context) {
    List<Widget> notices = [];
    List<Widget> forums = [];
    List<Widget> minwidgetList = [];

    Widget notice = _getNotice("欠费", "2019/7/12", "请尽快缴费");
    Widget notice2 = _getNotice("交钱", "2019/7/12", "请尽快缴纳房租你怎么还不交钱啊啊啊啊啊啊快交钱啊啊啊啊啊");
    Widget post1 = _getpost("小王", "2019/5/7", "有人一起赏月吗");
    Widget post2 = _getpost("老王", "2019/5/7", "有人一起逛街吗");
    notices.add(notice);
    notices.add(notice2);
    forums.add(post1);
    forums.add(post2);

    // 从 itemBuilder 调用的独立函数
    Widget buildNoticeBody(BuildContext ctxt, int index) {
      return notices[index];
    }
    // 从 itemBuilder 调用的独立函数
    Widget buildforumBody(BuildContext ctxt, int index) {
      return forums[index];
    }
    //小区资讯界面
    Widget newsContain = new newswidget();
    //小区论坛界面
    Widget forumConatin =new Container(
      child:new ListView.builder
        (
          itemCount: notices.length,
          itemBuilder: (BuildContext ctxt, int index) => buildNoticeBody(ctxt, index)
      ) ,
    );
    widgetList.add(newsContain);
    widgetList.add(forumConatin);

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
                padding: const EdgeInsets.all(1.0),
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
