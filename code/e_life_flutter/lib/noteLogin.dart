import 'package:flutter/material.dart';
import 'main.dart';
import 'package:e_life_flutter/userhttp.dart';
import 'bottom_navigation_widget.dart';
import 'package:scoped_model/scoped_model.dart';
import 'user.dart';
import 'package:oktoast/oktoast.dart';

class noteLogin extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new noteLoginWidget();
  }
}

class noteLoginWidget extends State<noteLogin>
    with SingleTickerProviderStateMixin ,NetListener{
  userHttp manager = new userHttp();


  @override
  void initState() {
    super.initState();
  }

  final TextEditingController _phoneNum =
      new TextEditingController.fromValue(new TextEditingValue(text: ""));
  final TextEditingController _authCode =
      new TextEditingController.fromValue(new TextEditingValue(text: ""));
  String role = "0";
  User user;
  bool loginSuccess = false;
  bool getNoteSuccess = false;
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: '手机快捷键登录',
      home: new Scaffold(
          appBar: new AppBar(
            title: new Text('手机快捷键登录'),
          ),
          body: new Container(
            child: new ListView(
              children: <Widget>[

                SizedBox(
                  height: 200,
                  child: new Image.asset(
                    'images/app.png',
                    scale: 4.0,
                  ),
                ),
                ListTile(
                  leading: Text("手机号 "),
                  subtitle: new TextField(
                    controller: _phoneNum,
                    decoration: new InputDecoration(
                      contentPadding:
                          const EdgeInsets.symmetric(vertical: 12.0),

                    ),
                  ),
                  trailing: new FlatButton(
                    color: Colors.blue[400],
                    child: new Padding(
                      padding: new EdgeInsets.all(10.0),
                      child: new Text(
                        '获取验证码',
                        style:
                            new TextStyle(color: Colors.white, fontSize: 16.0),
                      ),
                    ),
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(5.0)),
                    onPressed: ()async {
                      print(_phoneNum.text);
                      await manager.getNote(this, _phoneNum.text, role);
                      //manager.getNote(this, _phoneNum.text, role);
//                      print(manager.getNote(this, _phoneNum.text, role).toString());
                      //await new Future.delayed(new Duration(milliseconds: 2000));


                    },
                  ),
                ),
                ListTile(
                  leading: Text("验证码 "),
                  subtitle: new TextField(
                    controller: _authCode,
                    decoration: new InputDecoration(
                      contentPadding:
                          const EdgeInsets.symmetric(vertical: 12.0),
                    ),
                  ),
                ),
                Row(
                  children: <Widget>[
                    Flexible(
                      child: RadioListTile<String>(
                        value: '0',
                        title: Text('用户'),
                        groupValue: role,
                        onChanged: (value) {
                          setState(() {
                            role = value;
                          });
                        },
                      ),
                    ),
                    Flexible(
                      child: RadioListTile<String>(
                        value: '1',
                        title: Text('管理员'),
                        groupValue: role,
                        onChanged: (value) {
                          setState(() {
                            role = value;
                          });
                        },
                      ),
                    ),
                    Flexible(
                      child: RadioListTile<String>(
                        value: '2',
                        title: Text('商家'),
                        groupValue: role,
                        onChanged: (value) {
                          setState(() {
                            role = value;
                          });
                        },
                      ),
                    ),
                  ],
                ),
                new Container(
                  width: 320.0,
                  child: new Card(
                    color: Colors.blue[400],
                    elevation: 0.0,
                    child: new FlatButton(
                      child: new Padding(
                        padding: new EdgeInsets.all(10.0),
                        child: new Text(
                          '登录',
                          style: new TextStyle(color: Colors.white, fontSize: 16.0),
                        ),
                      ),
                      onPressed:()async{
                        manager.phoneLogin(this, _phoneNum.text, _authCode.text, role);
                        await new Future.delayed(new Duration(milliseconds: 1000));


                      } ,
                    ),
                  ),
                ),
                new Container(
                  width: 320.0,
                  child: new Card(
                    color: Colors.grey[200],
                    elevation: 0.0,
                    child: new FlatButton(
                      child: new Padding(
                        padding: new EdgeInsets.all(10.0),
                        child: new Text(
                          '账号密码登录',
                          style: new TextStyle(color: Colors.grey[900], fontSize: 16.0),
                        ),
                      ),
                      onPressed:(){
                        Navigator.push<String>(context,
                            new MaterialPageRoute(builder: (context) {
                             return new MyApp();;
                            }));
                      } ,
                    ),
                  ),
                ),
              ],
            ),
          )),
    );
  }
  @override
  onUserResponse(User body,bool login) {
    user = body;
    loginSuccess = login;
    setState(() {

    });
    if(loginSuccess!=false){
      Navigator.of(context).pushAndRemoveUntil(
          new MaterialPageRoute(
              builder: (context) => new BottomNavigationWidget(user.username,user.communityId.toString(),user.role.toString(),user.session)),
              (route) => route == null);
      showToast("登录成功");
    }else{
      showToast("登录失败");
    }
  }
  @override void onLogoutResponse(bool logout) {
    // TODO: implement onLogoutResponse
  }
  @override
  void onError(error) {
    // TODO: implement onError
  }
  @override
   onGetNoteResponse(bool ifGetNote){
    getNoteSuccess=ifGetNote;
    setState(() {

    });
    if(getNoteSuccess!=false){
      showToast("验证码已发送");
    }else{
      showToast("验证码发送失败");

    }
  }
}
