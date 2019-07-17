import 'package:flutter/material.dart';
import 'User.dart';

void main() => runApp(new MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Login()
    );
  }
}


class Login extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
        title: '小区登录',
        home: new Scaffold(
          appBar: new AppBar(
            title: new Text('小区登录'),
          ),
          body: new SingleChildScrollView(
            child: new Column(
              mainAxisSize: MainAxisSize.max,
              mainAxisAlignment: MainAxisAlignment.start,
              children: <Widget>[
                new Padding(
                  padding: new EdgeInsets.all(30.0),
                  child: new Image.asset(
                    'images/app.png',
                    scale: 4.0,
                  ),
                ),
                new Padding(
                  padding: new EdgeInsets.fromLTRB(20.0, 0.0, 20.0, 15.0),
                  child: new Row(
                      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                      children: [
                        new Padding(
                          padding: new EdgeInsets.fromLTRB(0.0, 0.0, 5.0, 0.0),
                          child: Icon(Icons.account_circle),
                        ),
                        new Expanded(
                            child: new TextField(
                          decoration: new InputDecoration(
                            hintText: '请输入用户名',
                          ),
                        ))
                      ]),
                ),
                new Padding(
                  padding: new EdgeInsets.fromLTRB(20.0, 15.0, 20.0, 40.0),
                  child: new Row(
                      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                      children: [
                        new Padding(
                          padding: new EdgeInsets.fromLTRB(0.0, 0.0, 5.0, 0.0),
                          child: Icon(Icons.lock),
                        ),
                        new Expanded(
                            child: new TextField(
                          decoration: new InputDecoration(
                            hintText: '请输入密码',
                          ),
                          obscureText: true,
                        ))
                      ]),
                ),
                new Container(
                  width: 340.0,
                  child: new Card(
                    color: Colors.blue,
                    elevation: 16.0,
                    child: new FlatButton(
                        child: new Padding(
                          padding: new EdgeInsets.all(10.0),
                          child: new Text(
                            '登录',
                            style: new TextStyle(
                                color: Colors.white, fontSize: 16.0),
                          ),
                        ),
                        onPressed: () {
                          //跳转到新的 页面我们需要调用 navigator.push方法 这个和eactNative的方式相同
                          Navigator.push(
                              context,
                              new MaterialPageRoute(
                                  builder: (context) => new SecondPage()));
                        }),
                  ),
                ),
                new Container(
                  width: 340.0,
                  child: new Card(
                    color: Colors.white,
                    elevation: 16.0,
                    child: new FlatButton(
                      child: new Padding(
                        padding: new EdgeInsets.all(10.0),
                        child: new Text(
                          '手机号快捷登录',
                          style: new TextStyle(
                              color: Colors.black, fontSize: 16.0),
                        ),
                      ),
                    ),
                  ),
                ),
                new Container(
                  width: 340.0,
                  child: new Card(
                    color: Colors.white,
                    elevation: 16.0,
                    child: new FlatButton(
                        child: new Padding(
                      padding: new EdgeInsets.all(10.0),
                      child: new Text(
                        '忘记密码',
                        style:
                            new TextStyle(color: Colors.black, fontSize: 16.0),
                      ),
                    )),
                  ),
                ),
              ],
            ),
          ),
        ));
  }
}


