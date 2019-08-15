import 'package:flutter/material.dart';
import 'package:e_life_flutter/user.dart';
import 'friendhttp.dart';
import 'package:scoped_model/scoped_model.dart';
import 'package:e_life_flutter/friends/addFriend.dart';
import 'package:oktoast/oktoast.dart';
class friendWidget extends StatefulWidget {
  final username;
  var session;
  friendWidget(this.username,this.session);
  @override
  State<StatefulWidget> createState() {
    return new friendWidgetState(username,session);
  }
}

class friendWidgetState extends State<friendWidget>
    with SingleTickerProviderStateMixin, NetListener {
  final username;
  var session;
  String success2;
  friendWidgetState(this.username,this.session);

  List<Friend> myFriendList = [];
  List<friendRequest> friendRequestList = [];
  List<Friend> searchUserList = [];
  friendHttp manager = new friendHttp();

  List<Choice> tabs = []; //导航栏
  TabController mTabController;
  int mCurrentPosition = 0;

  @override
  void initState() {
    super.initState();
    _getMyFriend();
    tabs.add(Choice(title: '好友列表', icon: Icons.people, position: 0));
    tabs.add(Choice(title: '添加好友', icon: Icons.add_circle, position: 1));
    tabs.add(Choice(title: '申请列表', icon: Icons.account_circle, position: 2));

    mTabController = new TabController(vsync: this, length: tabs.length);
    //判断TabBar是否切换
    mTabController.addListener(() {
      if (mTabController.indexIsChanging) {
        setState(() {
          mCurrentPosition = mTabController.index;
          manager.getFriendList(this, username,session);
        });
      }
    });
  }

  Widget _getFriend(String name, String phone, String email) {
    return new ListTile(
      leading: new Icon(Icons.message),
      title: new Text(name),
      subtitle: new Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          new Text("手机: " + phone),
          new Text("邮箱: " + email),
        ],
      ),
      trailing: new Icon(
        Icons.delete,
        color: Colors.black54,
      ),
      onTap: ()async{
        print(username+"用户名");
        manager.deleteFriend(this, username,name,session);
        await new Future.delayed(new Duration(milliseconds: 1000));
        if (success2=="true"){
          showToast("删除好友成功");
        }
        showToast("删除好友成功");
      },
      dense: true,
    );
  }
  Widget _getUser(String user, String community) {
    return new ListTile(
      leading: new Icon(Icons.perm_identity),
      title: new Text(user),
      subtitle: new Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[

          new Text("小区: " + community.toString()),
        ],
      ),
      trailing: new Icon(
        Icons.add_circle,
        color: Colors.black54,
      ),
      onTap: () {
        Navigator.push<String>(context,
            new MaterialPageRoute(builder: (BuildContext context) {
              return new addFriend(username,user,session);
            })).then((String result){
          print(result);
          showToast(result);
        });
      },
      dense: true,
    );
  }
  Widget _getApply(String name, String status, String message, int id) {
    return new ListTile(
      leading: new Icon(Icons.account_circle),
      title: new Text(name),
      subtitle: new Row(
        mainAxisSize: MainAxisSize.max,
        mainAxisAlignment: MainAxisAlignment.start,
        //crossAxisAlignment:CrossAxisAlignment.start,
        //textDirection: TextDirection.rtl,
        children: <Widget>[
          SizedBox(
            width: 130.0,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                new Text('请求id:' + id.toString()),
                new Text('状态:' + status),
                new Text('留言:' + message),
              ],
            ),
          ),
          Row(
            //crossAxisAlignment:CrossAxisAlignment.start,
            textDirection: TextDirection.ltr,
            children: <Widget>[
              new IconButton(
                icon: new Icon(Icons.add),
                onPressed: () {
                  print("id是");
                  print(id);
                  manager.acceptRequest(this, id,session);
                  showToast("加好友成功");

                },
              ),
              new IconButton(
                icon: new Icon(Icons.delete),
                onPressed: () {
                  manager.rejectRequest(this, id,session);
                  print("拒绝成功");
                  showToast("拒绝成功");
                },
              ),
            ],
          ),
        ],
      ),
      dense: true,
    );
  }

  _getMyFriend() async {
    print("我的好友" + username);
    await manager.getFriendList(this, username,session);
    await manager.getRequestList(this, username,session);
    return true;
  }

  List<Widget> friends = [];
  Widget buildFriendBody(BuildContext ctxt, int index) {
    return friends[index];
  }
  List<Widget> users=[];
  Widget buildUserBody(BuildContext ctxt, int index) {
    return users[index];
  }
  @override
  Widget build(BuildContext context) {
    final TextEditingController _nameController =
    new TextEditingController.fromValue(new TextEditingValue(text: ""));
    friends = []; //好友列表
    users =[];
    List<Widget> applys = []; //申请列表

    Widget friend1 = _getFriend("小王", "18209805949", "1080@qq.com");

    //Widget apply1 = _getApply("猪猪", "未处理", "游泳健身了解一下");
    //Widget apply2 = _getApply("妞妞", "已拒绝", "江浙沪包邮");

    if (myFriendList.length > 0) {
      print(myFriendList.length.toString() + "长度");
      for (int i = 0; i < myFriendList.length; i++) {
        print(myFriendList[i].email);
        print(myFriendList[i].friend);
        print(myFriendList[i].phone);

        Widget friend5 = _getFriend(myFriendList[i].friend,
            myFriendList[i].phone, myFriendList[i].email);
        print("奇奇怪怪");

        friends.add(friend5);
      }
    }
    if (searchUserList.length > 0) {
      print(searchUserList.length.toString() + "长度");
      for (int i = 0; i < searchUserList.length; i++) {


        Widget user2 = _getUser(searchUserList[i].searchUser,
             searchUserList[i].communityId);
        print("奇奇怪怪oo");

        users.add(user2);
      }
    }
    if (friendRequestList.length > 0) {
      print(friendRequestList.length.toString() + "长度");
      for (int i = 0; i < friendRequestList.length; i++) {
        Widget apply1 = _getApply(
            friendRequestList[i].request,
            friendRequestList[i].status.toString(),
            friendRequestList[i].content,
            friendRequestList[i].id);
        print("奇奇怪ee怪");

        applys.add(apply1);
      }
    }

    //applys.add(apply1);
    //applys.add(apply2);

    // 从 itemBuilder 调用的独立函数

    // 从 itemBuilder 调用的独立函数
    Widget buildApplyBody(BuildContext ctxt, int index) {
      return applys[index];
    }

    //好友列表界面
    Widget friendContain = new Container(
      child: new ListView.builder(
          itemCount: friends.length,
          itemBuilder: (BuildContext ctxt, int index) {
            return buildFriendBody(ctxt, index);
          }),
    );
    //申请列表界面
    Widget applyMessage = new Container(
      child: new ListView.builder(
          itemCount: applys.length,
          itemBuilder: (BuildContext ctxt, int index) =>
              buildApplyBody(ctxt, index)),
    );
    //添加好友界面
    Widget addFriendContain = new Container(
      child: new Column(
        children: <Widget>[
          TextField(
            controller: _nameController,
            autofocus: true,
            decoration: InputDecoration(
                hintText: "输入用户名查找", prefixIcon: Icon(Icons.search)),

          ),
          FlatButton(
            color: Colors.blue,
            highlightColor: Colors.blue[700],
            colorBrightness: Brightness.dark,
            splashColor: Colors.grey,
            child: Text("确定"),
            shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(20.0)),
            onPressed: (){
              manager.getFriendSearchList(this, _nameController.text,session);

            },
          ),
          Expanded(
            child:new ListView.builder(
                itemCount: users.length,
                itemBuilder: (BuildContext ctxt, int index) =>
                    buildUserBody(ctxt, index)) ,
          )

        ],
      ),
    );
    List<Widget> widgetList = []; //要显示的下方组件
    widgetList.add(friendContain);
    widgetList.add(addFriendContain);
    widgetList.add(applyMessage);
    return ScopedModelDescendant<UserModel>(builder: (context, child, model) {
      return Scaffold(
        appBar: AppBar(
          actions: <Widget>[],
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
                  child: widgetList[choice.position]);
            }).toList(),
            controller: mTabController,
          ),
        ),
      );
    });
  }

  @override
  void dispose() {
    super.dispose();
    mTabController.dispose();
  }

  @override
  void onFriendResponse(List<Friend> friendList) {
    myFriendList = friendList;

    print("收到" + friendList[0].email);
    setState(() {});
  }

  @override
  void onFriendSearchResponse(List<Friend> friendSearchList) {
    searchUserList=friendSearchList;
    print(searchUserList[0].searchUser+"用户");
    setState(() {});
  }

  @override
  void onRequestListResponse(List<friendRequest> requestList) {
    friendRequestList = requestList;
    print(friendRequestList.toString());
    setState(() {});
  }

  @override
  void onResponseListResponse(List<friendRequest> responseList) {
    setState(() {});
  }

  @override
  void onSendRequestResponse(bool send) {
    setState(() {});
  }

  @override
  void onAcceptRequestResponse(bool accept) {
    setState(() {
      manager.getRequestList(this, username,session);
    });
  }

  @override
  void onRejectRequestResponse(bool reject) {
    setState(() {
      manager.getRequestList(this, username,session);
    });
  }

  @override
  void onDeleteFriendResponse(bool delete) {
    print("delete:"+delete.toString());
    if(delete==true){
      success2="true";
    }else{
      success2="false";
    }
    setState(() {
      manager.getFriendList(this, username,session);
    });
  }

  @override
  void onError(error) {
    print("$error");
  }
}

class Choice {
  const Choice({this.title, this.icon, this.position});
  final String title;
  final int position;
  final IconData icon;
}
