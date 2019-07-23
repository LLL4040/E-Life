import 'package:flutter/material.dart';
import 'addRepair.dart';
class group extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new groupCenter();
  }
}

class groupCenter extends State<group> with SingleTickerProviderStateMixin {
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

  List<Widget> myrepairs = [];
  Widget buildRepairBody(BuildContext ctxt, int index) {
    return myrepairs[index];
  }
  void _toaddRepair() {
    Navigator.push<String>(context, new MaterialPageRoute(builder: (context) {
      return new addRepair();
    })).then((String result){
      print(result);
    });
  }
  @override
  Widget build(BuildContext context) {
    myrepairs = [];
    Widget myrepair = _getMyRepair("2019/6/6", "我家厕所堵住了", 1);
    myrepairs.add(myrepair);
    Widget repairSection = new Container(
      child: new ListView.builder(
          itemCount: myrepairs.length,
          itemBuilder: (BuildContext ctxt, int index) =>
              buildRepairBody(ctxt, index)),
    );
    return Scaffold(
      appBar: AppBar(
        title: Text('团购'),
      ),
      body: Column(
        children: <Widget>[
          new Expanded(
            child: repairSection,
          )
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _toaddRepair,
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ),
    );
  }
}
