import 'package:flutter/material.dart';

class addRepair extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new addRepairCenter();
  }
}

class addRepairCenter extends State<addRepair>
    with SingleTickerProviderStateMixin {


  @override
  Widget build(BuildContext context) {


    final TextEditingController _phoneController =
        new TextEditingController.fromValue(new TextEditingValue(text: ""));
    final TextEditingController _contentController =
        new TextEditingController.fromValue(new TextEditingValue(text: ""));
    void _addRepair(){
      print(_phoneController.text);
      print(_contentController.text);
      Navigator.pop(context,"发送请求成功");
    }
    Widget addrepairSection = new Container(
      child: Column(
        mainAxisSize: MainAxisSize.max,
        mainAxisAlignment: MainAxisAlignment.start,
        children: <Widget>[
          new Padding(
            padding: new EdgeInsets.fromLTRB(10.0, 0.0, 10.0, 10.0),
            child: new Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  new Padding(
                    padding: new EdgeInsets.fromLTRB(0.0, 0.0, 5.0, 0.0),
                    child: Text("联系方式: "),
                  ),
                  new Expanded(
                      child: new TextField(
                    controller: _phoneController,
                    decoration: new InputDecoration(
                      hintText: 'xxx-xxx-xxx',
                    ),
                  ))
                ]),
          ),
          new Padding(
            padding: new EdgeInsets.fromLTRB(10.0, 0.0, 10.0, 10.0),
            child: new Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  new Padding(
                    padding: new EdgeInsets.fromLTRB(0.0, 0.0, 5.0, 0.0),
                    child: Text("问题描述: "),
                  ),
                  new Expanded(
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
                  onPressed: _addRepair,
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
    );
    return Scaffold(
      appBar: AppBar(
        title: Text('增加维修'),
      ),
      body: Column(
        children: <Widget>[
          new Expanded(
            child: addrepairSection,
          )
        ],
      ),
    );
  }
}
