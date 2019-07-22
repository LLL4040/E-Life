import 'package:flutter/material.dart';


class friendWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new friendWidgetState();
  }
}

class friendWidgetState extends State<friendWidget> with SingleTickerProviderStateMixin {
  List<Choice> tabs = [];//导航栏
  TabController mTabController;
  int mCurrentPosition = 0;
  List<Widget> widgetList = [];//要显示的下方组件

  Widget _getFriend(String name, String phone,String email  ) {
    return  new ListTile(
      leading: new Icon(Icons.message),
      title: new Text(name),
      subtitle: new Column(
        crossAxisAlignment:CrossAxisAlignment.start,
        children: <Widget>[
          new Text("手机: "+phone),
          new Text("邮箱: "+email),
        ],
      ),
      trailing: new Icon(Icons.delete,color:Colors.black54 ,),
      onTap: () {
        print("删除功能未实现");
      },
      dense: true,
    );
  }
  Widget _getApply(String name, String status,String message  ) {
    return  new ListTile(
      leading: new Icon(Icons.account_circle),
      title: new Text(name),
      subtitle: new Row(

        mainAxisSize :MainAxisSize.max,
        mainAxisAlignment:MainAxisAlignment.start,
        //crossAxisAlignment:CrossAxisAlignment.start,
        //textDirection: TextDirection.rtl,
        children: <Widget>[
          SizedBox(
          width: 165.0,

            child: Column(
              crossAxisAlignment:CrossAxisAlignment.start,
              children: <Widget>[
              new Text('状态:'+status),
              new Text('留言:'+message),
            ],
          ),
      ),
          Row(
            //crossAxisAlignment:CrossAxisAlignment.start,
            textDirection: TextDirection.ltr,
            children: <Widget>[
              new IconButton(icon: new Icon(Icons.add),onPressed: (){
                print("hhh");
              },),
              new IconButton(icon: new Icon(Icons.delete),onPressed: null,),
            ],
          ),

        ],
      ),


      dense: true,
    );
  }

  @override
  Widget build(BuildContext context) {
    List<Widget> friends = [];
    List<Widget>  myapplys=[];//我的申请
    List<Widget> applys = [];//申请列表


    Widget friend1 = _getFriend("小王", "18209805949", "1080@qq.com");
    Widget friend2 = _getFriend("小黑", "18209805949", "1080@qq.com");
    Widget apply1 = _getApply("猪猪", "未处理", "游泳健身了解一下");
    Widget apply2 = _getApply("妞妞", "已拒绝", "江浙沪包邮");
    friends.add(friend1);
    friends.add(friend2);
    applys.add(apply1);
    applys.add(apply2);

    // 从 itemBuilder 调用的独立函数
    Widget buildNoticeBody(BuildContext ctxt, int index) {
      return friends[index];
    }
    // 从 itemBuilder 调用的独立函数
    Widget buildApplyBody(BuildContext ctxt, int index) {
      return applys[index];
    }
    //好友列表界面
    Widget friendContain = new Container(
      child:new ListView.builder
        (
          itemCount: friends.length,
          itemBuilder: (BuildContext ctxt, int index) => buildNoticeBody(ctxt, index)
      ) ,
    );
    //申请列表界面
    Widget applyMessage = new Container(
      child:new ListView.builder
        (
          itemCount: applys.length,
          itemBuilder: (BuildContext ctxt, int index) => buildApplyBody(ctxt, index)
      ) ,
    );
    //添加好友界面
    Widget addFriendContain = new Container(
      child:new Column(
        children: <Widget>[
          TextField(
            autofocus: true,
            decoration: InputDecoration(
                hintText: "输入用户名查找",
                prefixIcon: Icon(Icons.search)
            ),
          ),

        ],
      ),
    );
    widgetList.add(friendContain);
    widgetList.add(addFriendContain);
    widgetList.add(applyMessage);

    return Scaffold(
      appBar: AppBar(
        actions: <Widget>[
        ],
        title: Text('好友列表'),
      ),
      body: NestedScrollView(
        headerSliverBuilder: (BuildContext context, bool innerBoxIsScrolled) {
          return [
            new SliverAppBar(
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
    tabs.add(Choice(title: '好友列表', icon: Icons.people, position: 0));
    tabs.add(Choice(title: '添加好友', icon: Icons.add_circle, position: 1));
    tabs.add(Choice(title: '申请列表', icon: Icons.account_circle, position: 2));


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

class Choice {
  const Choice({this.title, this.icon, this.position});
  final String title;
  final int position;
  final IconData icon;
}

