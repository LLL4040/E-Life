import 'package:e_life_flutter/mainpage/estateforumhttp.dart';
import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';
import 'estateforumhttp.dart';
import 'package:oktoast/oktoast.dart';
import 'dart:convert';

class postDetail extends StatefulWidget {
  final pid;
  final username;
  final Post post;
  var session;
  postDetail(this.pid, this.username, this.post, this.session);
  @override
  State<StatefulWidget> createState() {
    return new postDetailCenter(pid, username, post, session);
  }
}

class postDetailCenter extends State<postDetail>
    with SingleTickerProviderStateMixin, NetListener {
  final pid;
  final username;
  final Post post;
  var session;
  postDetailCenter(this.pid, this.username, this.post, this.session);
  String success1;
  List<Comment> postCommentList = []; //存后端数据
  final TextEditingController _commentController =
      new TextEditingController.fromValue(new TextEditingValue(text: ""));
  estateforumHttp manager = new estateforumHttp();

  ScrollController _scrollController = ScrollController(); //listview的控制器
  int _page = 1; //加载的页数
  bool isLoading = false; //是否正在加载数据

  Widget _getpostComment(
      String name, String time, String comment, String location) {
    return new SizedBox(
      //height: 210.0, //设置高度
      child: new Card(
//        elevation: 15.0, //设置阴影
//        shape: const RoundedRectangleBorder(
//            borderRadius: BorderRadius.all(Radius.circular(0.0))), //设置圆角
        child: new Column(
          // card只能有一个widget，但这个widget内容可以包含其他的widget
          children: [
            ListTile(
              leading: new Text(name),
              title: new Text(time),
              subtitle: new Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  new Text(comment),
                ],
              ),
              onTap: () {},
              trailing: new Text(location),
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

  Widget _getpostCart(
      String name, String time, String title, String location, String content) {
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
              title: new Text(time),
              subtitle: new Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  new Text(title),
                  new Divider(),
                  new Text(content),
                ],
              ),
              onTap: () {},
              trailing: new Text(location),
              dense: true,
            ),
            //new Divider(),
          ],
        ),
      ),
    );
  }

  Widget _getPhoto1(String photo1) {
    return new SizedBox(
      //height: 210.0, //设置高度
      child: new Card(
        //elevation: 0.0, //设置阴影
//设置shape，这里设置成了R角
        shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.all(Radius.circular(10.0))),
        //对Widget截取的行为，比如这里 Clip.antiAlias 指抗锯齿
        clipBehavior: Clip.antiAlias,
        child: new Column(
          // card只能有一个widget，但这个widget内容可以包含其他的widget
          mainAxisSize: MainAxisSize.max,
          mainAxisAlignment: MainAxisAlignment.start,
          children: [
            new Padding(padding: new EdgeInsets.fromLTRB(0.0, 5.0, 0.0, 5.0)),
            new Row(
              children: <Widget>[
                new Padding(
                    padding: new EdgeInsets.fromLTRB(10.0, 0.0, 5.0, 0.0)),
                new SizedBox(
                  height: 100, //设置高度
                  width: 100, //设置宽度
                  child: new Card(
                    //elevation: 0.1, //设置阴影
                    //设置shape，这里设置成了R角
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.all(Radius.circular(10.0))),
                    //对Widget截取的行为，比如这里 Clip.antiAlias 指抗锯齿
                    clipBehavior: Clip.antiAlias,

                    child: photo1 == ""
                        ? Text("null")
                        : Image.memory(
                            base64.decode(photo1.split(',')[1]),

                            fit: BoxFit.fill, //填充
                            gaplessPlayback: true, //防止重绘
                          ),
                  ),
                ),
                new Padding(
                    padding: new EdgeInsets.fromLTRB(5.0, 0.0, 5.0, 0.0)),
              ],
            ),
            new Padding(padding: new EdgeInsets.fromLTRB(0.0, 5.0, 0.0, 5.0)),
          ],
        ),
      ),
    );
  }

  Widget _getPhoto2(String photo1, String photo2) {
    return new SizedBox(
      //height: 210.0, //设置高度
      child: new Card(
//        elevation: 15.0, //设置阴影
        //设置shape，这里设置成了R角
        shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.all(Radius.circular(10.0))),
        //对Widget截取的行为，比如这里 Clip.antiAlias 指抗锯齿
        clipBehavior: Clip.antiAlias,
        child: new Column(
          // card只能有一个widget，但这个widget内容可以包含其他的widget
          mainAxisSize: MainAxisSize.max,
          mainAxisAlignment: MainAxisAlignment.start,
          children: [
            new Padding(padding: new EdgeInsets.fromLTRB(0.0, 5.0, 0.0, 5.0)),
            new Row(
              children: <Widget>[
                new Padding(
                    padding: new EdgeInsets.fromLTRB(11.0, 0.0, 5.0, 0.0)),
                new SizedBox(
                  height: 100, //设置高度
                  width: 100, //设置宽度
                  child: new Card(
                    //elevation: 0.1, //设置阴影
                    //设置shape，这里设置成了R角
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.all(Radius.circular(10.0))),
                    //对Widget截取的行为，比如这里 Clip.antiAlias 指抗锯齿
                    clipBehavior: Clip.antiAlias,
                    child: photo1 == ""
                        ? Text("null")
                        : Image.memory(
                            base64.decode(photo1.split(',')[1]),

                            fit: BoxFit.fill, //填充
                            gaplessPlayback: true, //防止重绘
                          ),
                  ),
                ),
                new Padding(
                    padding: new EdgeInsets.fromLTRB(5.0, 0.0, 5.0, 0.0)),
                new SizedBox(
                  height: 100, //设置高度
                  width: 100, //设置宽度
                  child: new Card(
                    //elevation: 0.1, //设置阴影
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.all(Radius.circular(10.0)),
                    ),
                    //对Widget截取的行为，比如这里 Clip.antiAlias 指抗锯齿
                    clipBehavior: Clip.antiAlias,
                    //semanticContainer: false,
                    child: photo2 == ""
                        ? Text("null")
                        : Image.memory(
                            base64.decode(photo2.split(',')[1]),

                            fit: BoxFit.fill, //填充
                            gaplessPlayback: true, //防止重绘
                          ),
                  ),
                ),
                new Padding(
                    padding: new EdgeInsets.fromLTRB(5.0, 0.0, 5.0, 0.0)),
              ],
            ),
            new Padding(padding: new EdgeInsets.fromLTRB(0.0, 5.0, 0.0, 5.0)),
          ],
        ),
      ),
    );
  }

  Widget _getPhoto3(String photo1, String photo2, String photo3) {
    return new SizedBox(
      //height: 210.0, //设置高度
      child: new Card(
        //elevation: 5.0, //设置阴影
        //设置shape，这里设置成了R角
        shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.all(Radius.circular(10.0))),
        //对Widget截取的行为，比如这里 Clip.antiAlias 指抗锯齿
        clipBehavior: Clip.antiAlias,
        child: new Column(
          // card只能有一个widget，但这个widget内容可以包含其他的widget
          mainAxisSize: MainAxisSize.max,
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            new Padding(padding: new EdgeInsets.fromLTRB(0.0, 5.0, 0.0, 5.0)),
            new Row(
              mainAxisSize: MainAxisSize.max,
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                new SizedBox(
                  height: 100, //设置高度
                  width: 100, //设置宽度
                  child: new Card(
                    //elevation: 0.1, //设置阴影
                    //设置shape，这里设置成了R角
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.all(Radius.circular(10.0))),
                    //对Widget截取的行为，比如这里 Clip.antiAlias 指抗锯齿
                    clipBehavior: Clip.antiAlias,
                    child: photo1 == ""
                        ? Text("null")
                        : Image.memory(
                            base64.decode(photo1.split(',')[1]),

                            fit: BoxFit.fill, //填充
                            gaplessPlayback: true, //防止重绘
                          ),
                  ),
                ),
                new Padding(
                    padding: new EdgeInsets.fromLTRB(5.0, 0.0, 5.0, 0.0)),
                new SizedBox(
                  height: 100, //设置高度
                  width: 100, //设置宽度
                  child: new Card(
                    //elevation: 0.1, //设置阴影
                    //设置shape，这里设置成了R角
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.all(Radius.circular(10.0))),
                    //对Widget截取的行为，比如这里 Clip.antiAlias 指抗锯齿
                    clipBehavior: Clip.antiAlias,
                    child: photo2 == ""
                        ? Text("null")
                        : Image.memory(
                            base64.decode(photo2.split(',')[1]),

                            fit: BoxFit.fill, //填充
                            gaplessPlayback: true, //防止重绘
                          ),
                  ),
                ),
                new Padding(
                    padding: new EdgeInsets.fromLTRB(5.0, 0.0, 5.0, 0.0)),
                new SizedBox(
                  height: 100, //设置高度
                  width: 100, //设置宽度
                  child: new Card(
                    //elevation: 0.1, //设置阴影
                    //设置shape，这里设置成了R角
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.all(Radius.circular(10.0))),
                    //对Widget截取的行为，比如这里 Clip.antiAlias 指抗锯齿
                    clipBehavior: Clip.antiAlias,
                    child: photo3 == ""
                        ? Text("null")
                        : Image.memory(
                            base64.decode(photo3.split(',')[1]),

                            fit: BoxFit.fill, //填充
                            gaplessPlayback: true, //防止重绘
                          ),
                  ),
                ),
              ],
            ),
            new Padding(padding: new EdgeInsets.fromLTRB(0.0, 5.0, 0.0, 5.0)),
          ],
        ),
      ),
    );
  }

  _addComment(String username, String pid, String comment) async {
    print(pid + "帖子id号");
    manager.addComment(this, pid, username, comment, session);
    await new Future.delayed(new Duration(milliseconds: 1000));
    if (success1 == "true") {
      showToast("评论成功");
    }
  }

  List<Widget> postComments = [];
  Widget buildpostCommentsBody(BuildContext ctxt, int index) {
    return postComments[index];
  }

  @override
  Widget build(BuildContext context) {
    postComments = [];
    if (postCommentList.length >= 0) {
      Widget post1 = _getpostCart(
          post.posterName, post.postTime, post.title, "1", post.postContent);
      postComments.add(post1);
      if (post.photo != null) {
        if (post.photo.length <= 3) {
          if (post.photo.length == 3) {
            Widget photo1 =
                _getPhoto3(post.photo[0], post.photo[1], post.photo[2]);
            postComments.add(photo1);
          }
          if (post.photo.length == 2) {
            Widget photo1 = _getPhoto2(post.photo[0], post.photo[1]);
            postComments.add(photo1);
          } else {
            Widget photo1 = _getPhoto1(post.photo[0]);
            postComments.add(photo1);
          }
        }
        if (post.photo.length > 3) {
          int length = post.photo.length % 3;
          print(length.toString() + "jsjs");

          int length1 = post.photo.length - length;
          for (int i = 0; i < length1 / 3; i++) {
            Widget photo1 = _getPhoto3(post.photo[3 * i], post.photo[3 * i + 1],
                post.photo[3 * i + 2]);
            postComments.add(photo1);
          }
          if (length == 1) {
            Widget photo1 = _getPhoto1(post.photo[length1]);
            postComments.add(photo1);
          }
          if (length == 2) {
            Widget photo1 =
                _getPhoto2(post.photo[length1], post.photo[length1 + 1]);
            postComments.add(photo1);
          }
        }
      }

      if (postCommentList.length > 0) {
        for (int i = 0; i < postCommentList.length; i++) {
          Widget postComment = _getpostComment(
              postCommentList[i].commenterName,
              postCommentList[i].commentsTime,
              postCommentList[i].postComment,
              postCommentList[i].location);
          postComments.add(postComment);
        }
      }
    }

    Widget myComputerSection = new Container(
      child: new ListView.builder(
          itemCount: postComments.length,
          itemBuilder: (BuildContext ctxt, int index) =>
              buildpostCommentsBody(ctxt, index)),
    );
    Widget myPostSection = RefreshIndicator(
        onRefresh: _onRefresh,
        child: Container(
          child: ListView.builder(
            itemBuilder: (BuildContext ctxt, int index) {
              if (index < postComments.length) {
                return buildpostCommentsBody(ctxt, index);
              }
              return _getMoreWidget();
            },
            itemCount: postComments.length + 1,
            controller: _scrollController,
          ),
        ));
    return Scaffold(
      appBar: AppBar(
        title: Text('帖子详情'),
      ),
      body: Column(
        children: <Widget>[
          new Expanded(
            child: myPostSection,
          )
        ],
      ),
      floatingActionButton: RaisedButton(
        color: Colors.blue,
        highlightColor: Colors.blue[700],
        colorBrightness: Brightness.dark,
        splashColor: Colors.grey,
        child: Text("添加评论"),
        shape:
            RoundedRectangleBorder(borderRadius: BorderRadius.circular(20.0)),
        onPressed: () async {
          showDialog<Null>(
            context: context,
            builder: (BuildContext context) {
              return new SimpleDialog(
                title: new Text('输入评论'),
                children: <Widget>[
                  new Row(
                    mainAxisSize: MainAxisSize.max,
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: <Widget>[
                      new SizedBox(
                        height: 100,
                        width: 250,
                        child: new TextField(
                          maxLines: 5,
                          controller: _commentController,
                          decoration: new InputDecoration(
                            hintMaxLines: 6,
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
                          ),
                        ),
                      ),
                    ],
                  ),
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
                            _addComment(username, pid, _commentController.text);
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
                            showToast("取消评论");
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
      ),
    );
  }

  void _returnPostComment() async {
    print(pid);
    await manager.getCommentList(this, pid, "1", "5", session);
  }
  /**
   * 下拉刷新方法,为list重新赋值
   */
  Future<Null> _onRefresh() async {
    postCommentList=[];
    _page=1;

    print("bbbbbbbbb");
    await manager.getCommentList(this, pid, _page.toString(), "5", session);

  }
  /**
   * 上拉加载更多
   */
  Future _getMore()async {
    if (!isLoading) {
      setState(() {
        isLoading = true;
      });
    }

      await manager.getCommentList(this, pid, (_page+1).toString(), "5", session);

    _page++;
    print(_page.toString());
    //isLoading = false;

  }
  /**
   * 加载更多时显示的组件,给用户提示
   */
  _getMoreWidget(){
    if(isLoading==true){
      return Center(
        child: Padding(
          padding: EdgeInsets.all(10.0),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              Text(
                '加载中...',
                style: TextStyle(fontSize: 16.0),
              ),
              CircularProgressIndicator(
                strokeWidth: 1.0,
              )
            ],
          ),
        ),
      );
    }else{
      return Center(
        child: Padding(
          padding: EdgeInsets.all(10.0),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              Text(
                '已经到底了',
                style: TextStyle(fontSize: 16.0),
              ),
            ],
          ),
        ),
      );
    }

  }
  @override
  void initState() {
    super.initState();
    _scrollController.addListener(() {
      if (_scrollController.position.pixels ==
          _scrollController.position.maxScrollExtent) {
        print('滑动到了最底部');
        _getMore();
      }
    });
    _returnPostComment();
  }
  @override
  void dispose() {
    super.dispose();
    _scrollController.dispose();
  }
  @override
  void onAllPostResponse(List<Post> body) {}
  @override
  void onAllCommentsResponse(List<Comment> commentList) {
    //postCommentList = commentList;
    for(int i=1;i<commentList.length;i++){
      postCommentList.add(commentList[i]);
    }
    if(_page*5>postCommentList.length){
      isLoading=false;
    }
    setState(() {});
  }

  @override
  void onAddComment(bool success) {
    print("评论：" + success.toString());
    if (success.toString() == "true") {
      success1 = "true";
    } else {
      success1 = "false";
    }
    setState(() {
      _page=1;
      postCommentList=[];
      manager.getCommentList(this, pid, "1", "5", session);
    });
  }

  @override
  void onDeleteComment(bool success) {}
  @override
  void onAllTabsResponse(List<forumTabs> body) {}
  @override
  void onError(error) {}
}
