import 'package:flutter/material.dart';
import 'package:e_life_flutter/friends/friendhttp.dart';
class addFriend extends StatefulWidget {
  final username;
  final friend;
  var session;
  addFriend(this.username, this.friend,this.session);
  @override
  State<StatefulWidget> createState() {
    return new addFriendCenter(username, friend,session);
  }
}

class addFriendCenter extends State<addFriend>
    with SingleTickerProviderStateMixin ,NetListener{
  final username;
  final friend;
  var session;
  addFriendCenter(this.username, this.friend,this.session);
  friendHttp manager = new friendHttp();
  @override
  Widget build(BuildContext context) {
    final TextEditingController _contentController =
        new TextEditingController.fromValue(new TextEditingValue(text: ""));

    void _addRequest() {
      //print(_contentController.text);
      manager.sendRequest(this, username, friend, _contentController.text,session);

    }

    Widget addFriendSection = new Container(
      child: Column(
        mainAxisSize: MainAxisSize.max,
        mainAxisAlignment: MainAxisAlignment.start,
        children: <Widget>[
          new Padding(
            padding: new EdgeInsets.fromLTRB(10.0, 0.0, 10.0, 10.0),
            child: new Column(
                mainAxisAlignment: MainAxisAlignment.start,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  new Padding(
                    padding: new EdgeInsets.fromLTRB(0.0, 0.0, 5.0, 0.0),
                    child: Text("留言: ",style: TextStyle(
                        fontSize: 20.0,
                        color: Colors.black87

                    ),),
                  ),
                  new Padding(
                      padding: new EdgeInsets.fromLTRB(0.0, 0.0, 5.0, 0.0),
                      child: new TextField(
                        maxLines: 5,
                        controller: _contentController,
                        decoration: new InputDecoration(
                          enabledBorder: OutlineInputBorder(

                            borderSide: BorderSide(
                              color: Colors.black38,
                              width: 1, //边线宽度为2
                            ),
                          ),
                          focusedBorder: OutlineInputBorder(
                              borderSide: BorderSide(
                                color: Colors.blue, //边框颜色为绿色
                                width: 2, //宽度为5
                              )),
                          hintMaxLines: 6,
                        ),
                      ))
                ]),
          ),
          new Container(
            padding: new EdgeInsets.fromLTRB(10.0, 0.0, 5.0, 0.0),
            child: Row(
              mainAxisSize: MainAxisSize.max,
              mainAxisAlignment: MainAxisAlignment.end,
              children: <Widget>[
                FlatButton(
                  color: Colors.blue,
                  highlightColor: Colors.blue[700],
                  colorBrightness: Brightness.dark,
                  splashColor: Colors.grey,
                  child: Text("确定"),
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(20.0)),
                  onPressed: _addRequest,
                ),
                FlatButton(
                  color: Colors.black54,
                  highlightColor: Colors.black38,
                  colorBrightness: Brightness.dark,
                  splashColor: Colors.grey,
                  child: Text("取消"),
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(20.0)),
                  onPressed: () => Navigator.pop(context, "取消发送请求"),
                )
              ],
            ),
          )
        ],
      ),
    );
    return Scaffold(
      appBar: AppBar(
        title: Text('添加好友'),
      ),
      body: Column(
        children: <Widget>[
          new Expanded(
            child: addFriendSection,
          )
        ],
      ),
    );
  }
  @override
  void onFriendResponse(List<Friend> friendList) {

    print("收到" + friendList[0].email);
    setState(() {});
  }

  @override
  void onFriendSearchResponse(List<Friend> friendSearchList) {

    setState(() {});
  }

  @override
  void onRequestListResponse(List<friendRequest> requestList) {

    setState(() {});
  }

  @override
  void onResponseListResponse(List<friendRequest> responseList) {
    setState(() {});
  }

  @override
  void onSendRequestResponse(bool send) {
    if(send){
      Navigator.pop(context, "发送添加好友请求成功");
    }else{
      Navigator.pop(context, "发送添加好友请求失败");
    }
    setState(() {});
  }

  @override
  void onAcceptRequestResponse(bool accept) {
    setState(() {});
  }

  @override
  void onRejectRequestResponse(bool reject) {
    setState(() {});
  }

  @override
  void onDeleteFriendResponse(bool delete) {
    setState(() {});
  }

  @override
  void onError(error) {
    print("$error");
  }
}
