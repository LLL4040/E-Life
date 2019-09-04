import 'package:flutter/material.dart';
import 'demandhttp.dart';
import 'package:oktoast/oktoast.dart';
class addDemand extends StatefulWidget {
  final username;
  final communityId;
  var session;
  addDemand(this.username, this.communityId,this.session);
  @override
  State<StatefulWidget> createState() {
    return new addDemandCenter(username, communityId,session);
  }
}

class addDemandCenter extends State<addDemand>
    with SingleTickerProviderStateMixin, NetListener {
  final username;
  final communityId;
  var session;
  String success1;
  addDemandCenter(this.username, this.communityId,this.session);
  demandHttp manager = new demandHttp();
  String _startTime = "";
  String _endTime = "";
  _startTimePicker() async {
    var picker =
        await showTimePicker(context: context, initialTime: TimeOfDay.now());
    setState(() {
      _startTime =_startTime+" "+ picker.toString().substring(10,15);
    });
    print("开始时间为" + _startTime);
  }

  _startDataPicker() async {
    Locale myLocale = Localizations.localeOf(context);
    var picker = await showDatePicker(
        context: context,
        initialDate: DateTime.now(),
        firstDate: DateTime(2016),
        lastDate: DateTime(2030),
        locale: myLocale);
    setState(() {
      _startTime = picker.toString().split(" ")[0];
    });
    print("开始日期为" + _startTime);
    _startTimePicker();
  }

  _endTimePicker() async {
    var picker =
        await showTimePicker(context: context, initialTime: TimeOfDay.now());
    setState(() {
      _endTime = _endTime+" "+picker.toString().substring(10,15);
    });
    print("结束时间为" + _endTime);
  }

  _endDataPicker() async {
    Locale myLocale = Localizations.localeOf(context);
    var picker = await showDatePicker(
        context: context,
        initialDate: DateTime.now(),
        firstDate: DateTime(2016),
        lastDate: DateTime(2030),
        locale: myLocale);
    setState(() {
      _endTime = picker.toString().split(" ")[0];
    });
    print("结束日期为" + _endTime);
    _endTimePicker();
  }
   String title;
  String content;
  TextEditingController _titleController =
  new TextEditingController.fromValue(new TextEditingValue());

  TextEditingController _contentController =
  new TextEditingController.fromValue(new TextEditingValue());
  @override
  Widget build(BuildContext context) {


    Widget addDemandSection = new Container(
      child: ListView(
        children: <Widget>[
          Column(
            mainAxisSize: MainAxisSize.max,
            mainAxisAlignment: MainAxisAlignment.start,
            children: <Widget>[
              Row(
                children: <Widget>[
                  FlatButton(
                    color: Colors.blue,
                    highlightColor: Colors.blue[700],
                    colorBrightness: Brightness.dark,
                    splashColor: Colors.grey,
                    child: Text("选择开始时间"),
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(20.0)),
                    onPressed: _startDataPicker,
                  ),
                  Container(
                    padding: new EdgeInsets.fromLTRB(20.0, 10.0, 10.0, 10.0),
                    child: new Text('$_startTime'),
                  ),
                ],
              ),
              Row(
                mainAxisSize: MainAxisSize.max,
                mainAxisAlignment: MainAxisAlignment.start,
                children: <Widget>[
                  FlatButton(
                    color: Colors.black54,
                    highlightColor: Colors.black38,
                    colorBrightness: Brightness.dark,
                    splashColor: Colors.grey,
                    child: Text("选择结束时间"),
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(20.0)),
                    onPressed: _endDataPicker,
                  ),
                  Container(
                    padding: new EdgeInsets.fromLTRB(20.0, 10.0, 10.0, 10.0),
                    child: new Text('$_endTime'),
                  ),
                ],
              ),
            ],
          ),
          Text(
            "标题: ",
            style: TextStyle(fontSize: 20.0, color: Colors.black87),
          ),
          Container(
            child: new TextField(
              autofocus: false,
              maxLines: 1,
              onChanged: (v) {
                title = v;

                print("onChange: $v");
              },
              controller: _titleController,
              decoration: new InputDecoration(
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
                // hintMaxLines: 1,
              ),
            ),
          ),
          Text(
            "留言: ",
            style: TextStyle(fontSize: 20.0, color: Colors.black87),
          ),
          Container(
            child: new TextField(
              autofocus: false,
              onChanged: (v) {

                print("onChange: $v");
              },

              maxLines: 3,
              controller: _contentController,
              decoration: new InputDecoration(
                enabledBorder: OutlineInputBorder(
                  borderSide: BorderSide(
                    color: Colors.black38,
                    width: 1, //边线宽度为2
                  ),
                ),
                focusedBorder: OutlineInputBorder(
                    borderSide: BorderSide(
                  color: Colors.blue, //边框颜色为蓝色
                  width: 2, //宽度为5
                )),
                //hintMaxLines: 6,
              ),
            ),
          ),
          Row(
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
                onPressed: () async {
                  print(_startTime);
                  print(_endTime);
                  print(title);
                  print(content);
                  print(username);
                  print(communityId);
                  manager.addDemand(
                      this,
                      _startTime,
                      _endTime,
                      _contentController.text,
                      username,
                      communityId,
                      _titleController.text,
                      session);
                  //await new Future.delayed(new Duration(milliseconds: 1000));

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
                onPressed: () => Navigator.pop(context, "取消发布需求请求"),
              )
            ],
          ),
          new Padding(
            padding: new EdgeInsets.fromLTRB(20.0, 15.0, 20.0, 40.0),
          ),
        ],
      ),
    );

    return Scaffold(
      appBar: AppBar(
        title: Text('添加需求'),
      ),
      body: addDemandSection,
    );
  }

  @override
  void onAddDemandResponse(bool success) {
    print(success);

    if (success) {
      Navigator.pop(context, "发布需求成功");
    }else{
      showToast("请不要频繁操作");
    }
    setState(() {});
  }

  @override
  void onAllDemandResponse(List<Demand> demandList) {}
  @override
  void onParticipateDemandResponse(bool success) {}
  @override
  void onQuitDemandResponse(bool success) {}
  @override
  void onAllDiscountResponse(List<Discount> discountList) {}
  @override
  void onError(error) {}
}
