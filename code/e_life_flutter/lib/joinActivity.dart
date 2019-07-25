import 'package:flutter/material.dart';
import 'package:scoped_model/scoped_model.dart';
import 'user.dart';
import 'newsHttp.dart';
import 'package:oktoast/oktoast.dart';

class joinActivity extends StatefulWidget {

  final id;
  final username;
  joinActivity(this.id,this.username);
  @override
  State<StatefulWidget> createState() {
    return new joinActivityWidget(id,username);
  }
}
class joinActivityWidget extends State<joinActivity> with SingleTickerProviderStateMixin,NetListener{
  String joinResult="";
  final id;
  final username;
  joinActivityWidget(this.id,this.username);
  httpManager manager = new httpManager();
  @override
  Widget build(BuildContext context) {

    final TextEditingController _contentController =
    new TextEditingController.fromValue(new TextEditingValue(text: ""));

    return  ScopedModelDescendant<UserModel>(
        builder: (context, child, model) {

//            if(joinResult=="true"){
//              print("joinresult=true");
//              joinResult="false";
//              Navigator.pop(context,"发送请求成功");
//              return null;
//            }else{
//              Fluttertoast.showToast(
//                  msg: "您已参加过该活动",
//                  toastLength: Toast.LENGTH_SHORT,
//                  gravity: ToastGravity.CENTER,
//                  timeInSecForIos: 1,
//                  backgroundColor: Colors.redAccent,
//                  textColor: Colors.black54,
//                  fontSize: 16.0
//              );
//
//              print("您已参加过该活动");
//            }

    return Scaffold(

      appBar: AppBar(

        title: Text('参加活动报名'),
      ),
      body: Column(
        children: <Widget>[
          new Expanded(
            child: new Container(
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
                            child: Text("留言: "
                            ,style: TextStyle(
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
                          onPressed: ()async{
                            //print(model.user.username);
                            //print(id);
                            if(_contentController.text==""){
                              showToast("留言不应该为空",position: ToastPosition.top);
                              return;
                            }
                            manager.saveParticipator(this, id, _contentController.text, username);
                            String tmp;
                            await new Future.delayed(new Duration(milliseconds: 1000));
                            tmp =  joinResult;
                            if(tmp=="true"){
                              print("joinresult=true");
                              joinResult="false";
                              Navigator.pop(context,"发送报名请求成功");

                            }else{
                              showToast("您已参加过该活动",position: ToastPosition.top);
                            }


                            //Navigator.pop(context,joinResult);
                          },
                        ),
                        FlatButton(
                          color: Colors.black54,
                          highlightColor: Colors.black38,
                          colorBrightness: Brightness.dark,
                          splashColor: Colors.grey,
                          child: Text("取消"),
                          shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(20.0)),
                          onPressed: () => Navigator.pop(context,"取消发送请求"),
                        )
                      ],
                    ),
                  )
                ],
              ),
            ),
          )
        ],
      ),
    );

        }
        );
  }
  @override
  void onError(error) {print("$error");}
  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
  }
  @override
  void onUrgentResponse(List<urgent> body) {

    setState(() {});
  }
  @override
  void onNewsResponse(List<News> body){

    setState(() {});
  }
  @override
  void onActivityResponse(List<Activity> body){

    setState(() {});
  }
  @override
  void onSaveParticipantResponse(String body){
    joinResult =   body;
    print("response"+joinResult);
    setState(() {

    });

  }
}
