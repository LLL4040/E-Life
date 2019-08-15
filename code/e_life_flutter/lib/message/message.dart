import 'package:e_life_flutter/message/noticehttp.dart';
import 'package:flutter/material.dart';
import '../user.dart';
import 'package:scoped_model/scoped_model.dart';
import 'package:oktoast/oktoast.dart';
import 'package:e_life_flutter/message/packagehttp.dart';

class message extends StatefulWidget {
  final username;
  var session;
  message(this.username,this.session);
  @override
  State<StatefulWidget> createState() {
    return new messageState(username,session);
  }
}

class messageState extends State<message>
    with SingleTickerProviderStateMixin, NetListener, packNetListener {
  final username;
  var session;
  messageState(this.username,this.session);
  String success1; //用于判断删除信息是否成功
  String success2;
  noticeHttp manager = new noticeHttp();
  packageHttp manager1 = new packageHttp();
  List<Notice> mynoticeList = []; //后端传的
  List<Package> mypackageList = [];
  List<Choice> tabs = []; //导航栏
  TabController mTabController;
  int mCurrentPosition = 0;

  void _returnNotice() async {
    print("获得的是用户的物业信息" + username);
    await manager.myNotice(this, username,session);
  }

  void _returnPackage() async {
    print("获得的是用户的邮件提醒" + username);
    await manager1.myPackage(this, username,session);
  }

  Widget _getNotice(String managerName, String time, String content, int id) {

    return new SizedBox(
      //height: 210.0, //设置高度
      child: new Card(
        elevation: 0.0, //设置阴影
        shape: const RoundedRectangleBorder(
            borderRadius: BorderRadius.all(Radius.circular(10.0))), //设置圆角
        semanticContainer: false,
        child: new Column(
          // card只能有一个widget，但这个widget内容可以包含其他的widget
          children: [
            ListTile(
              leading: new Icon(Icons.message),
              title: new Text(managerName),
              subtitle: new Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  new Text(time),
                  new Text(content),
                ],
              ),
              trailing: new Icon(
                Icons.delete,
                color: Colors.black54,
              ),
              onTap: () async {
                print(id.toString() + "id号");
                print(username + "用户名");
                manager.deleteNotice(this, username, id,session);
                await new Future.delayed(new Duration(milliseconds: 1000));
                if (success1 == "true") {
                  showToast("删除物业消息成功");
                }
              },
              dense: true,
            ),
            //new Divider(),

//            new ListTile(
//              title: new Text('图片区域'),
//              leading: new Icon(
//                Icons.photo,
//                color: Colors.blue[500],
//              ),
//
//            ),
          ],
        ),
      ),



    );
  }

  _takeOut(String id) async {
    print(id + "邮包id号");
    manager1.takeOut(this, id,session);
    await new Future.delayed(new Duration(milliseconds: 1000));
    if (success2 == "true") {
      showToast("提取邮包成功");
    }
  }

  Widget _getpackage(String status, String time, String manage, String id) {
    String statustmp = "";
    if (status == "0") {
      statustmp = "未提取";
    }

    return new SizedBox(
      //height: 210.0, //设置高度
      child: new Card(
        elevation: 0.0, //设置阴影
        shape: const RoundedRectangleBorder(
            borderRadius: BorderRadius.all(Radius.circular(10.0))), //设置圆角
        semanticContainer: false,
        child: new Column(
          // card只能有一个widget，但这个widget内容可以包含其他的widget
          children: [
            ListTile(
              leading: new Icon(Icons.settings_system_daydream),
              title: new Text('时间:' + time),
              subtitle: new Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  new Text("负责人: " + manage),
                  new Text(statustmp ,),
                ],
              ),
              trailing: new Icon(Icons.offline_pin),

              onTap: () {
                showDialog<Null>(
                  context: context,
                  builder: (BuildContext context) {
                    return new SimpleDialog(
                      title: new Text('确定提取吗'),
                      children: <Widget>[
                        new Row(
                          mainAxisSize: MainAxisSize.max,
                          mainAxisAlignment: MainAxisAlignment.end,
                          children: <Widget>[
                            new SimpleDialogOption(
                              child: FlatButton(
                                color: Colors.blue,
                                highlightColor: Colors.blue[700],
                                colorBrightness: Brightness.dark,
                                splashColor: Colors.grey,
                                child: Text("确定"),
                                shape: RoundedRectangleBorder(
                                    borderRadius: BorderRadius.circular(20.0)),
                                onPressed: () {
                                  _takeOut(id);
                                  Navigator.of(context).pop();
                                },
                              ),

                            ),
                            new SimpleDialogOption(
                              child: FlatButton(
                                color: Colors.black54,
                                highlightColor: Colors.black38,
                                colorBrightness: Brightness.dark,
                                splashColor: Colors.grey,
                                child: Text("取消"),
                                shape: RoundedRectangleBorder(
                                    borderRadius: BorderRadius.circular(20.0)),
                                onPressed: () {
                                  showToast("取消提取邮包");
                                  Navigator.of(context).pop();
                                },
                              ),

                            ),
                          ],
                        ),

                      ],
                    );
                  },
                ).then((val) {
                  print(val);
                });
              },
              dense: true,
            ),
            //new Divider(),

//            new ListTile(
//              title: new Text('图片区域'),
//              leading: new Icon(
//                Icons.photo,
//                color: Colors.blue[500],
//              ),
//
//            ),
          ],
        ),
      ),
    );
  }

  List<Widget> notices = [];
  List<Widget> packages = [];
  // 从 itemBuilder 调用的独立函数
  Widget buildNoticeBody(BuildContext ctxt, int index) {
    return notices[index];
  }

  // 从 itemBuilder 调用的独立函数
  Widget buildPackageBody(BuildContext ctxt, int index) {
    return packages[index];
  }

  @override
  Widget build(BuildContext context) {
    notices = [];
    packages = [];

    if (mynoticeList.length > 0) {
      for (int i = 0; i < mynoticeList.length; i++) {
        Widget notice4 = _getNotice(mynoticeList[i].managerName,
            mynoticeList[i].time, mynoticeList[i].content, mynoticeList[i].id);
        print("sjsjj");
        notices.add(notice4);
      }
    }
    if (mypackageList.length > 0) {
      for (int i = 0; i < mypackageList.length; i++) {
        Widget package4 = _getpackage(
            mypackageList[i].status.toString(),
            mypackageList[i].time,
            mypackageList[i].managerName,
            mypackageList[i].id.toString());
        print("jjjjssusu");
        packages.add(package4);
      }
    }
    //物业通知界面
    Widget noticeContain = new Container(
      child: new ListView.builder(
          itemCount: notices.length,
          itemBuilder: (BuildContext ctxt, int index) =>
              buildNoticeBody(ctxt, index)),
    );
    //邮包提醒界面
    Widget packageMessage = new Container(
      child: new ListView.builder(
          itemCount: packages.length,
          itemBuilder: (BuildContext ctxt, int index) =>
              buildPackageBody(ctxt, index)),
    );

    List<Widget> widgetList = []; //要显示的下方组件
    widgetList.add(noticeContain);
    widgetList.add(packageMessage);
    return ScopedModelDescendant<UserModel>(builder: (context, child, model) {
      return Scaffold(
        body: NestedScrollView(
          headerSliverBuilder: (BuildContext context, bool innerBoxIsScrolled) {
            return [
              new SliverAppBar(
                //forceElevated: true, //是否显示阴影
                //pinned: true,//是否固定在顶部
                expandedHeight: 200.0,
                bottom: PreferredSize(
                    child: new Container(
                      color: Colors.white,
                      child: new TabBar(
                        labelPadding: EdgeInsets.all(0),
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
                    preferredSize: new Size(double.infinity, 18.0)
                ),
                flexibleSpace: new Container(
                  child: new Column(
                    children: <Widget>[
                      new Expanded(
                        child: new Container(
                          child: Image.asset(
                            "images/app.png",
                            fit: BoxFit.cover,
                          ),
                          width: double.infinity,
                        ),
                      )
                    ],
                  ),
                ),
              )
            ];
          },
          body: new TabBarView(
            children: tabs.map((Choice choice) {
              return new Padding(
                  padding: const EdgeInsets.all(15.0),
                  child: widgetList[choice.position]);
            }).toList(),
            controller: mTabController,
          ),
        ),
      );
    });
  }

  @override
  void initState() {
    super.initState();
    _returnNotice();
    _returnPackage();
    tabs.add(Choice(title: '物业通知', icon: Icons.monetization_on, position: 0));
    tabs.add(Choice(title: '邮包通知', icon: Icons.local_post_office, position: 1));

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
  void onMyNoticeResponse(List<Notice> notice) {
    mynoticeList = notice;
    setState(() {});
  }

  @override
  void onDeleteNoticeResponse(bool success) {
    if (success == true) {
      success1 = "true";
    } else {
      success1 = "false";
    }
    setState(() {
      manager.myNotice(this, username,session);
    });
  }

  @override
  void onError(error) {}
  @override
  void onMyPackageResponse(List<Package> package) {
    mypackageList = package;
    print("我的邮包提醒");
    setState(() {});
  }

  @override
  void onTakeOutResponse(String bool) {
    print("邮包提取：" + bool);
    if (bool == "true") {
      success2 = "true";
    } else {
      success2 = "false";
    }
    setState(() {
      manager1.myPackage(this, username,session);
    });
  }

  @override
  void onDeleteResponse(String bool) {}
  @override
  void onError1(error) {}
}

class Choice {
  const Choice({this.title, this.icon, this.position});
  final String title;
  final int position;
  final IconData icon;
}
