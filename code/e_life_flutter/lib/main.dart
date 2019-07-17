import 'package:flutter/material.dart';
import 'news.dart';
import 'bottom_navigation_widget.dart';

void main() => runApp(new MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(home: new Login());

  }
}

class Login extends StatelessWidget {


  @override
  Widget build(BuildContext context) {
    void _toNews(){
      Navigator.of(context).pushAndRemoveUntil(
          new MaterialPageRoute(builder: (context) => new BottomNavigationWidget()
          ), (route) => route == null);
    }
    Widget loginSection = Container(
      width: 320.0,
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
            onPressed:_toNews),
      ),
    );
    Widget quickLogibSession = new Container(
      width: 320.0,
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
    );
    Widget forgetSession = new Container(
      width: 320.0,
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
    );
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
                loginSection,
                quickLogibSession,
                forgetSession,
                new Padding(
                  padding: new EdgeInsets.fromLTRB(20.0, 15.0, 20.0, 40.0),

                ),
              ],
            ),
          ),
        ));
  }
}
