import 'package:flutter/material.dart';
import 'package:e_life_flutter/service/maintainhttp.dart';

class addRepair extends StatefulWidget {
  final username;
  addRepair(this.username);
  @override
  State<StatefulWidget> createState() {
    return new addRepairCenter(username);
  }
}

class addRepairCenter extends State<addRepair>
    with SingleTickerProviderStateMixin, NetListener {
  final username;
  addRepairCenter(this.username);
  maintainHttp manager = new maintainHttp();
  final TextEditingController _phoneController =
  new TextEditingController.fromValue(new TextEditingValue(text: ""));
  final TextEditingController _contentController =
  new TextEditingController.fromValue(new TextEditingValue(text: ""));
  @override
  Widget build(BuildContext context) {

    void _addRepair() {
      print(_phoneController.text);
      print(_contentController.text);
      manager.addMaintain(
          this, username, _phoneController.text, _contentController.text);
      Navigator.pop(context, "发送维修请求成功");
    }

    Widget addrepairSection =
    new Container(
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
                //crossAxisAlignment: CrossAxisAlignment.stretch,
                mainAxisAlignment: MainAxisAlignment.start,
                children: [
                  Column(
                    children: <Widget>[
                      new Padding(
                        padding: new EdgeInsets.fromLTRB(0.0, 0.0, 5.0, 0.0),
                        child: Text("问题描述: "),
                      ),
                    ],
                  ),
                  new Expanded(
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
                  onPressed: () => Navigator.pop(context, "取消发送维修请求"),
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

  @override
  void onAddMaintainResponse(bool send) {
    setState(() {});
  }

  @override
  void onMyMaintainResponse(List<Maintain> maintain) {
    setState(() {});
  }

  @override
  void onError(error) {
    print("$error");
  }
}
