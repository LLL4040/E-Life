import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'newsHttp.dart';
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

class myWidget extends State<newswidget> with SingleTickerProviderStateMixin, NetListener{
  List<urgent> urgentList=[];
  List<News> newsList=[];
  List<Activity> activityList=[];
  httpManager manager = new httpManager();

  final Widget myDevider = Container(
    child: Divider(
      color: Colors.black12,
    ),
  );

  //紧急通知初始化的函数
  Widget _getUrgent(String time, String managerName,String content) {
    return  new ListTile(
      leading: new Icon(Icons.nature_people),
      title: new Text(managerName),
      subtitle: new Column(
        crossAxisAlignment:CrossAxisAlignment.start,
        children: <Widget>[

          new Text(time),
          new Text(content),
        ],
      ),

      onTap: () {
        print(content);
      },
      dense: true,
    );
  }

  //活动通知初始化的函数
  Widget _getActivity(String title, String photo,String content,String startTime,String endTime,int status) {
    String activity = status==0?"报名中":"已结束";
    return  new ListTile(
      leading: Image.memory(
        base64.decode(
            photo.split(',')[1]),
        height:80,    //设置高度
        width:80,    //设置宽度
        fit: BoxFit.fill,    //填充
        gaplessPlayback:true, //防止重绘

      ),
      title: new Text(title),
      subtitle: new Column(
        crossAxisAlignment:CrossAxisAlignment.start,
        children: <Widget>[
          new Text(startTime),
          new Text(endTime),
          new Text(content),
          new Text(activity),
        ],
      ),
      trailing: new Icon(Icons.add,color:Colors.black54 ,),
      onTap: () {
        print(content);
      },
      dense: true,
    );
  }
//最新资讯初始化的函数
  Widget _getNews(String photo, String title,String time,String content) {

    return new ListTile(
      leading: Image.memory(

        base64.decode(

            photo.split(',')[1]),

        height:80,    //设置高度

        width:80,    //设置宽度

        fit: BoxFit.fill,    //填充

        gaplessPlayback:true, //防止重绘

      ),
      //title: new Text(photo),
      subtitle: new Column(
        crossAxisAlignment:CrossAxisAlignment.start,
        children: <Widget>[
          new Text(title),
          new Text(time),
          new Text(content),

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
    _getMessage();
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
  List<Widget> urgents = [];
  Widget buildUrgentBody(BuildContext ctxt, int index) {
    return urgents[index];
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

    urgents =[];
    activitys =[];
    news=[];
    if(urgentList.length>0){
      for(int i=0;i<urgentList.length-1;i++){
        Widget urgent = _getUrgent(urgentList[i].time, urgentList[i].managerName, urgentList[i].content);
        urgents.add(urgent);
      }
    }
    if(newsList.length>0){
      for(int i=0;i<newsList.length;i++){
        Widget news1 = _getNews(newsList[i].photo, newsList[i].title, newsList[i].time, newsList[i].content);
        news.add(news1);
      }
    }
    if(activityList.length>0){
      for(int i=0;i<activityList.length;i++){
        Widget activity1 = _getActivity(activityList[i].title,activityList[i].photo, activityList[i].content, activityList[i].startTime, activityList[i].endTime,activityList[i].status);
        activitys.add(activity1);
      }
    }
    //Widget activity2 = _getActivity("暂无图片", "2019/7/18 18:00", "2019/7/19 23:59", "一起去赏月");

    //activitys.add(activity2);

    Widget urgentSection = Container(
      child: Column(
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
          new Expanded(
                child: ListView.builder
          (
             itemCount: urgents.length,
              itemBuilder: (BuildContext ctxt, int index) => buildUrgentBody(ctxt, index)
           ) ,
            )

        ],
      ),
    );
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
  _getMessage() async {
    await manager.getNews(this, 1);
    await manager.getUrgent(this, 1);
    await manager.getActivity(this, 1);
    return true;
  }
  @override
  void dispose() {
    super.dispose();
    mTabController.dispose();
  }
  @override
  void onError(error) {print("$error");}

  @override
  void onUrgentResponse(List<urgent> body) {
    urgentList = body;
    setState(() {});
  }
  @override
  void onNewsResponse(List<News> body){
    newsList = body;
    setState(() {});
  }
  @override
  void onActivityResponse(List<Activity> body){
    activityList = body;
    setState(() {});
  }
}



