import 'package:flutter/material.dart';
import 'addRepair.dart';
import 'package:scoped_model/scoped_model.dart';
import 'user.dart';
import 'maintainhttp.dart';
import 'dart:convert';


class myRepair extends StatefulWidget {
  final username;
  myRepair(this.username);
  @override
  State<StatefulWidget> createState() {
    return new RepairCenter(username);
  }
}

class RepairCenter extends State<myRepair> with SingleTickerProviderStateMixin,NetListener {
  final username;
  RepairCenter(this.username);

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
    return new ListTile(
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
      return new addRepair();
    })).then((String result) {
      print(result);
    });
  }


  _getMaintain1() async {
    print(username);
    await manager.myMaintain(this, username);
    return true;
  }
  @override
  Widget build(BuildContext context) {

    //myrepairs = [];
    if(maintainList.length>0){
      for(int i=0;i<maintainList.length;i++){
        Widget myrepair1 = _getMyRepair(maintainList[i].time, maintainList[i].content, maintainList[i].status);

        myrepairs.add(myrepair1);
      }
    }

    return ScopedModelDescendant<UserModel>(builder: (context, child, model) {

      print("ppppppppp1" + model.user.username);

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
    setState(() {});
  }
  @override
  void onMyMaintainResponse(List<Maintain> maintain){
    maintainList=maintain;
    print("收到maintain "+maintain[0].content);
    setState(() {
    });

  }
  @override
  void onError(error){
    print("$error");
  }
}
