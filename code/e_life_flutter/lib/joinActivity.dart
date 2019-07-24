import 'package:flutter/material.dart';
import 'package:scoped_model/scoped_model.dart';
import 'user.dart';
import 'newsHttp.dart';


class joinActivity extends StatefulWidget {

  final id;
  joinActivity(this.id);
  @override
  State<StatefulWidget> createState() {
    return new joinActivityWidget(id);
  }
}
class joinActivityWidget extends State<joinActivity> with SingleTickerProviderStateMixin,NetListener{
  String joinResult="";
  final id;
  joinActivityWidget(this.id);
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
                            child: Text("留言: "),
                          ),
                          new Padding(
                              padding: new EdgeInsets.fromLTRB(0.0, 0.0, 5.0, 0.0),
                              child: new TextField(
                                maxLines: 5,
                                controller: _contentController,
                                decoration: new InputDecoration(
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
                          onPressed: ()async{
                            print(model.user.username);
                            print(id);
                            manager.saveParticipator(this, id, _contentController.text, model.user.username);
                            setState(() {

                            });
                            if(joinResult=="true"){
                              print("joinresult=true");
                              joinResult="false";
                              Navigator.pop(context,"发送请求成功");
                              return null;
                            }else{
                              print("请求失败");
                            }
                            Navigator.pop(context,joinResult);
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
    joinResult = body;
    print("response"+joinResult);
    setState(() {

    });

  }
}
