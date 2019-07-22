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


  //活动通知初始化的函数
  Widget _getActivity(String photo, String startTime,String endTime,String detail) {
    return  new ListTile(
      leading: new Icon(Icons.nature_people),
      title: new Text(photo),
      subtitle: new Column(
        crossAxisAlignment:CrossAxisAlignment.start,
        children: <Widget>[
          new Text(startTime),
          new Text(endTime),
          new Text(detail),
        ],
      ),
      trailing: new Icon(Icons.add,color:Colors.black54 ,),
      onTap: () {
        print(detail);
      },
      dense: true,
    );
  }
//最新资讯初始化的函数
  Widget _getNews(String photo, String title,String time,String detail) {
    return new ListTile(
      leading: new Icon(Icons.near_me),
      title: new Text(photo),
      subtitle: new Column(
        crossAxisAlignment:CrossAxisAlignment.start,
        children: <Widget>[
          new Text(title),
          new Text(time),
          new Text(detail),

        ],
      ),

      onTap: () {
        print('点击');
      },
      dense: true,
    );
  }

  List<Choice> tabs = [];//导航栏
  TabController mTabController;
  int mCurrentPosition = 0;

  TabController controller;


  @override
  void initState() {
    super.initState();
    tabs.add(Choice(title: '紧急通知', icon: Icons.fiber_new, position: 0));
    tabs.add(Choice(title: '最新资讯', icon: Icons.message, position: 1));
    tabs.add(Choice(title: '活动管理', icon: Icons.nature, position: 2));

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

  List<Widget> activitys = [];
  Widget buildActivityBody(BuildContext ctxt, int index) {
    return activitys[index];
  }
  List<Widget> news = [];
  Widget buildNewsBody(BuildContext ctxt, int index) {
    return news[index];
  }
  @override
  Widget build(BuildContext context) {
    activitys =[];
    news=[];
    Widget activity1 = _getActivity("暂无图片", "2019/7/13 19:00", "2019/7/14 23:59", "一起去钓鱼 ");
    Widget activity2 = _getActivity("暂无图片", "2019/7/18 18:00", "2019/7/19 23:59", "一起去赏月");
    Widget news1 = _getNews("暂无图片", "放风筝今晚", "2019/7/14 23:59", "有人吗有人吗哈哈哈哈");
    Widget news2 = _getNews("暂无图片", "放风筝结束的结束今晚", "2019/7/14 23:59", "有人吗有人吗哈哈哈哈");
    activitys.add(activity1);
    activitys.add(activity2);
    news.add(news1);
    news.add(news2);
    //展示活动安排的组件
    Widget activitySection = new Container(
      child:new ListView.builder
        (
          itemCount: activitys.length,
          itemBuilder: (BuildContext ctxt, int index) => buildActivityBody(ctxt, index)
      ) ,
    );
    //展示最新资讯的组件
    Widget newsSection = new Container(
      child:new ListView.builder
        (
          itemCount: news.length,
          itemBuilder: (BuildContext ctxt, int index) => buildNewsBody(ctxt, index)
      ) ,

    );

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

              );
            }).toList(),
            controller: mTabController,
            //isScrollable: true,//加了之后就不是中间对齐了
            indicatorColor: Colors.black54,
            indicatorWeight: 1,
            indicatorSize: TabBarIndicatorSize.label,
            indicatorPadding: EdgeInsets.fromLTRB(5.0,1.0,5.0,1.0),
            labelPadding: EdgeInsets.all(15),
            //indicator: const BoxDecoration(),//加了之后指示条消失
            labelColor: Color(0xff333333),
            labelStyle: TextStyle(

              fontSize: 12.0,
            ),
            unselectedLabelColor: Colors.black54,
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

