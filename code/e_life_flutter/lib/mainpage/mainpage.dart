import 'package:flutter/material.dart';
import '../bottom_navigation_widget.dart';
import 'package:flutter_swiper/flutter_swiper.dart';
import 'postDetail.dart';
import 'package:e_life_flutter/mainpage/estateforumhttp.dart';
import 'addPost.dart';
import 'news.dart';

class mainpage extends StatefulWidget {
  final communityId;
  final username;
  mainpage(this.communityId, this.username);
  @override
  State<StatefulWidget> createState() {
    return new mainpageWidget(communityId, username);
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
  mainpageWidget(this.communityId, this.username);
  estateforumHttp manager = new estateforumHttp();
  List<Choice> tabs = []; //导航栏
  TabController mTabController;
  int mCurrentPosition = 0;

  List<Post> postList = [];

  Widget _getpost(
      String name, String time, String title, String pid, Post post) {
    return new SizedBox(
      //height: 210.0, //设置高度
      child: new Card(
//        elevation: 15.0, //设置阴影
//        shape: const RoundedRectangleBorder(
//            borderRadius: BorderRadius.all(Radius.circular(0.0))), //设置圆角
        child: new Column(
          // card只能有一个widget，但这个widget内容可以包含其他的widget
          children: [
            new ListTile(
              leading: new Text(name),
              title: new Text(title),
              subtitle: new Row(
                children: <Widget>[
                  new Text('时间:' + time),
                ],
              ),
              trailing: new Icon(Icons.keyboard_arrow_right),
              onTap: () {
//        Navigator.push<String>(context, new MaterialPageRoute(builder: (context) {
//          return new HeadImageUploadPage();
//        }));
                Navigator.push<String>(context,
                    new MaterialPageRoute(builder: (context) {
                      return new postDetail(pid, username, post);
                    }));
              },
              dense: true,
            ),
          ],
        ),
      ),
    );

  }

  List<Widget> forums = [];
  // 从 itemBuilder 调用的独立函数
  Widget buildforumBody(BuildContext ctxt, int index) {
    return forums[index];
  }

  @override
  Widget build(BuildContext context) {
    forums = [];
//    Widget post1 = _getpost("小王", "2019/5/7", "有人一起赏月吗","1",null);
//    Widget post2 = _getpost("老王", "2019/5/7", "有人一起逛街吗","1",null);

    if (postList.length > 0) {
      print("加载帖子成功");
      for (int i = 1; i < postList.length; i++) {
//        print(postList[i].posterName);
//        print(postList[i].postTime);
//        print(postList[i].title);
        Widget post5 = _getpost(postList[i].posterName, postList[i].postTime,
            postList[i].title, postList[i].id, postList[i]);

        forums.add(post5);
      }
    }
//    forums.add(post1);
//    forums.add(post2);

    //小区资讯界面
    Widget newsContain = new newswidget(communityId, username);
    //小区论坛界面
    Widget forumConatin = new Container(
      child: new ListView.builder(
          itemCount: forums.length,
          itemBuilder: (BuildContext ctxt, int index) =>
              buildforumBody(ctxt, index)),
    );
/**
 * 尤其注意，这个顺序变了之后可能导致数据显示不正常，不能及时渲染*/
    List<Widget> widgetList = []; //!!!要显示的下方组件
    widgetList.add(newsContain);
    widgetList.add(forumConatin);

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
      floatingActionButton: mCurrentPosition==1?RaisedButton(
        color: Colors.blue,
        highlightColor: Colors.blue[700],
        colorBrightness: Brightness.dark,
        splashColor: Colors.grey,
        child: Text("发布帖子"),
        shape:
            RoundedRectangleBorder(borderRadius: BorderRadius.circular(20.0)),
        onPressed: () async {
          Navigator.push<String>(context,
              new MaterialPageRoute(builder: (context) {
            return new addPost();
          }));
        },
      ):null,
    );
  }

  _getPost() async {
    print("aaaaaaaaa");
    await manager.getPostList(this, communityId, "1", "100");
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
    postList = body;
    setState(() {});
  }

  @override
  void onAllCommentsResponse(List<Comment> commentList) {}
  @override
  void onAddComment(bool success) {}
  @override
  void onDeleteComment(bool success) {}
  @override
  void onError(error) {}
}
