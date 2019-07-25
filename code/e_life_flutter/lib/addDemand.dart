import 'package:flutter/material.dart';
import 'demandhttp.dart';

class addDemand extends StatefulWidget {
  final username;
  final communityId;
  addDemand(this.username, this.communityId);
  @override
  State<StatefulWidget> createState() {
    return new addDemandCenter(username, communityId);
  }
}

class addDemandCenter extends State<addDemand>
    with SingleTickerProviderStateMixin, NetListener {
  final username;
  final communityId;
  addDemandCenter(this.username, this.communityId);
  demandHttp manager = new demandHttp();
  String _startTime;
  String _endTime;
  _startTimePicker() async {
    var picker =
        await showTimePicker(context: context, initialTime: TimeOfDay.now());
    setState(() {
      _startTime = picker.toString();

    });
  }

  _startDataPicker() async {
    Locale myLocale = Localizations.localeOf(context);
    var picker = await showDatePicker(
        context: context,
        initialDate: DateTime.now(),
        firstDate: DateTime(2016),
        lastDate: DateTime(2030),
        locale: myLocale);
    _startTime = picker.toString();
    print("开始日期为" + _startTime);
  }

  _endTimePicker() async {
    var picker =
        await showTimePicker(context: context, initialTime: TimeOfDay.now());
    _endTime += picker.toString();

  }

  _endDataPicker() async {
    Locale myLocale = Localizations.localeOf(context);
    var picker = await showDatePicker(
        context: context,
        initialDate: DateTime.now(),
        firstDate: DateTime(2016),
        lastDate: DateTime(2030),
        locale: myLocale);
    _endTime =  picker.toString();
    print("结束日期为" + _endTime);
  }

  String title1;
  String content;
  @override
  Widget build(BuildContext context) {
    final TextEditingController _titleController =
        new TextEditingController.fromValue(new TextEditingValue(text: ""));

    final TextEditingController _contentController =
        new TextEditingController.fromValue(new TextEditingValue(text: ""));

    Widget addDemandSection = new Container(
      child: Column(
        mainAxisSize: MainAxisSize.max,
        mainAxisAlignment: MainAxisAlignment.start,
        children: <Widget>[
          new Container(
            padding: new EdgeInsets.fromLTRB(10.0, 0.0, 5.0, 0.0),
            child: Row(
              mainAxisSize: MainAxisSize.max,
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
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
                FlatButton(
                  color: Colors.black54,
                  highlightColor: Colors.black38,
                  colorBrightness: Brightness.dark,
                  splashColor: Colors.grey,
                  child: Text("选择结束时间"),
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(20.0)),
                  onPressed: _endDataPicker,
                )
              ],
            ),
          ),
          new Expanded(
            child: new Column(
                mainAxisAlignment: MainAxisAlignment.start,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                      "标题: ",
                      style: TextStyle(fontSize: 20.0, color: Colors.black87),
                    ),
                  new Expanded(
                      child: new TextField(
                        maxLines: 1,
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
                          hintMaxLines: 1,
                        ),

                      ),

                  ),
                  Text(
                    "留言: ",
                    style: TextStyle(fontSize: 20.0, color: Colors.black87),
                  ),

                  new Expanded(
                      child: new TextField(
                        maxLines: 5,
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
                                color: Colors.blue, //边框颜色为绿色
                                width: 2, //宽度为5
                              )),
                          hintMaxLines: 6,
                        ),
                      )),
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
                        onPressed: () {
                          print(_startTime);
                          print(_endTime);
                          print(_contentController.text);
                          print(_titleController.text);
                          print(username);
                          print(communityId);
                          manager.addDemand(
                              this,
                              _startTime,
                              _endTime,
                              _contentController.text,
                              username,
                              communityId,
                              _titleController.text);
                          Navigator.pop(context, "发布需求成功");
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
                ]),
          ),

        ],
      ),
    );

    return Scaffold(
      appBar: AppBar(
        title: Text('添加需求'),
      ),
      body: Column(
        children: <Widget>[
          new Expanded(
            child: addDemandSection,
          )
        ],
      ),
    );
  }

  @override
  void onAddDemandResponse(bool success) {}
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
