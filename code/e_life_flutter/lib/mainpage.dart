import 'package:flutter/material.dart';
import 'bottom_navigation_widget.dart';
import 'package:flutter_swiper/flutter_swiper.dart';
import 'postDetail.dart';
import 'news.dart';

class mainpage extends StatefulWidget {
  final communityId;
  mainpage(this.communityId);
  @override
  State<StatefulWidget> createState() {
    return new mainpageWidget(communityId);
  }
}
class Choice {
  const Choice({this.title, this.icon, this.position});
  final String title;
  final int position;
  final IconData icon;
}

class mainpageWidget extends State<mainpage> with SingleTickerProviderStateMixin {
  final communityId;
  mainpageWidget(this.communityId);

  List<Choice> tabs = [];//导航栏
  TabController mTabController;
  int mCurrentPosition = 0;
  List<Widget> widgetList = [];//要显示的下方组件

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

    List<Widget> forums = [];

    Widget post1 = _getpost("小王", "2019/5/7", "有人一起赏月吗");
    Widget post2 = _getpost("老王", "2019/5/7", "有人一起逛街吗");

    forums.add(post1);
    forums.add(post2);

    // 从 itemBuilder 调用的独立函数
    Widget buildforumBody(BuildContext ctxt, int index) {
      return forums[index];
    }
    //小区资讯界面
    Widget newsContain = new newswidget(communityId);
    //小区论坛界面
    Widget forumConatin =new Container(
      child:new ListView.builder
        (
          itemCount: forums.length,
          itemBuilder: (BuildContext ctxt, int index) => buildforumBody(ctxt, index)
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
