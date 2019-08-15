import 'package:flutter/material.dart';
import 'addRepair.dart';
import 'package:scoped_model/scoped_model.dart';
import 'package:e_life_flutter/user.dart';
import 'package:e_life_flutter/service/maintainhttp.dart';
import 'dart:convert';

import 'package:oktoast/oktoast.dart';

class myRepair extends StatefulWidget {
  final username;
  var session;
  myRepair(this.username,this.session);
  @override
  State<StatefulWidget> createState() {
    return new RepairCenter(username,session);
  }
}

class RepairCenter extends State<myRepair> with SingleTickerProviderStateMixin,NetListener {
  final username;
  var session;
  RepairCenter(this.username,this.session);

  List<Maintain> maintainList=[];
  maintainHttp manager = new maintainHttp();

  Widget _getMyRepair(String time, String content, int status) {
    String repairStatus;
    if (status == 0) {
      repairStatus = "未处理";
    }
    if (status == 1) {
      repairStatus = "处理中";
    }
    if (status == 2) {
      repairStatus = "已完成";
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
              leading: new Icon(Icons.settings),
              title: new Text(time),
              subtitle: new Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  new Text(content),
                  new Text(repairStatus),
                ],
              ),
              onTap: () {
                print(content);
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
  @override
  void initState() {

    super.initState();
    _getMaintain1();

  }

  List<Widget> myrepairs = [];
  Widget buildRepairBody(BuildContext ctxt, int index) {
    return myrepairs[index];
  }

  void _toaddRepair() {
    Navigator.push<String>(context, new MaterialPageRoute(builder: (context) {
      return new addRepair(username,session);
    })).then((String result) {
      print(result);
      showToast(result,textStyle: TextStyle(



      ));
      setState(() async{
        await new Future.delayed(new Duration(milliseconds: 1000));
        manager.myMaintain(this, username,session);

      });


    });
  }


  _getMaintain1() async {
    print(username);
    await manager.myMaintain(this, username,session);
    return true;
  }
  @override
  Widget build(BuildContext context) {

    myrepairs = [];
    if(maintainList.length>0){
      for(int i=0;i<maintainList.length;i++){
        Widget myrepair1 = _getMyRepair(maintainList[i].time, maintainList[i].content, maintainList[i].status);

        myrepairs.add(myrepair1);
      }
    }

    return ScopedModelDescendant<UserModel>(builder: (context, child, model) {



      return Scaffold(
        appBar: AppBar(
          title: Text('我的维修'),
        ),
        body: Column(
          children: <Widget>[
            new Expanded(
              child: new Container(
                child: new ListView.builder(
                    itemCount: myrepairs.length,
                    itemBuilder: (BuildContext ctxt, int index) {
                      return  buildRepairBody(ctxt, index);
                    }
                       ),
              ),
            )
          ],
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: _toaddRepair,
          tooltip: 'addRepair',
          child: Icon(Icons.add),
        ),
      );
    });
  }

  @override
  void onAddMaintainResponse(bool send){
    setState(() {

    });
  }
  @override
  void onMyMaintainResponse(List<Maintain> maintain){
    maintainList=maintain;
    //print("收到maintain "+maintain[0].content);
    setState(() {
      //manager.myMaintain(this, username);
    });

  }
  @override
  void onError(error){
    print("$error");
  }
}
