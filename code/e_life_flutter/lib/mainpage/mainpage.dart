import 'package:flutter/material.dart';
import '../bottom_navigation_widget.dart';
import 'package:flutter_swiper/flutter_swiper.dart';
import 'postDetail.dart';
import 'package:e_life_flutter/mainpage/estateforumhttp.dart';
import 'addPost.dart';
import 'news.dart';
import 'package:oktoast/oktoast.dart';
import 'estateforum.dart';

class mainpage extends StatefulWidget {
  final communityId;
  final username;
  var session;
  mainpage(this.communityId, this.username, this.session);
  @override
  State<StatefulWidget> createState() {
    return new mainpageWidget(communityId, username, session);
  }
}

class Choice {
  const Choice({this.title, this.icon, this.position});
  final String title;
  final int position;
  final IconData icon;
}

class mainpageWidget extends State<mainpage>
    with SingleTickerProviderStateMixin, NetListener {
  final communityId;
  final username;
  var session;
  mainpageWidget(this.communityId, this.username, this.session);
  estateforumHttp manager = new estateforumHttp();
  List<Choice> tabs = []; //导航栏
  TabController mTabController;
//  TabController fTabController;
  int mCurrentPosition = 0;
  //int fCurrentPosition = 0;
  List<forumTabs> forumTabsList =[];

  List<Widget> forums = [];
  // 从 itemBuilder 调用的独立函数
  Widget buildforumBody(BuildContext ctxt, int index) {
    return forums[index];
  }

  @override
  Widget build(BuildContext context) {

    //小区资讯界面
    Widget newsContain = new newswidget(communityId, username, session);
    //小区论坛界面

    Widget forumWidget = new estateforum(communityId, username, session,forumTabsList);
/**
 * 尤其注意，这个顺序变了之后可能导致数据显示不正常，不能及时渲染*/
    List<Widget> widgetList = []; //!!!要显示的下方组件
    widgetList.add(newsContain);
    widgetList.add(forumWidget);

    return Scaffold(
      body: NestedScrollView(
        headerSliverBuilder: (BuildContext context, bool innerBoxIsScrolled) {
          return [
            new SliverAppBar(
              //forceElevated :true,
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
                  preferredSize: new Size(double.infinity, 18)),
            )
          ];
        },
        body: new TabBarView(
          children: tabs.map((Choice choice) {
            return new Padding(
                padding: const EdgeInsets.all(1.0),
                child: widgetList[choice.position]);
          }).toList(),
          controller: mTabController,
        ),
      ),
      floatingActionButton: mCurrentPosition == 1
          ? RaisedButton(
              color: Colors.blue,
              highlightColor: Colors.blue[700],
              colorBrightness: Brightness.dark,
              splashColor: Colors.grey,
              child: Text("发布帖子"),
              shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(20.0)),
              onPressed: () async {
                Navigator.push<String>(context,
                    new MaterialPageRoute(builder: (context) {
                  return new addPost(username,communityId,session);
                  //return new estateforum(username, communityId, session);
                })).then((String result) {
                  print("报名收到的信息为:" + result);
                  showToast(result);
                  setState(() {
                    _getPost();
                  });
                });
              },
            )
          : null,
    );
  }

  _getPost() async {
    print("aaaaaaaaa");
    await manager.getTabs(this, communityId);
    return true;
  }

  @override
  void initState() {
    super.initState();
    _getPost();
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

  @override
  void onAllPostResponse(List<Post> body) {
    setState(() {});
  }

  @override
  void onAllCommentsResponse(List<Comment> commentList) {}
  @override
  void onAddComment(bool success) {}
  @override
  void onDeleteComment(bool success) {}
  @override
  void onAllTabsResponse(List<forumTabs> body){
    forumTabsList = body;
    setState(() {

    });
  }
  @override
  void onError(error) {}
}
