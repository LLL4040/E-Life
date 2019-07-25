import 'package:flutter/material.dart';
import 'demandhttp.dart';
class addDemand extends StatefulWidget {
  final username;
  final CommunityId;
  addDemand(this.username, this.CommunityId);
  @override
  State<StatefulWidget> createState() {
    return new addDemandCenter(username, CommunityId);
  }
}

class addDemandCenter extends State<addDemand>
    with SingleTickerProviderStateMixin ,NetListener{
  final username;
  final CommunityId;
  addDemandCenter(this.username, this.CommunityId);
  demandHttp manager = new demandHttp();
  var _date;
  var _time;
  _showDataPicker() async {
    Locale myLocale = Localizations.localeOf(context);
    var picker = await showDatePicker(
        context: context,
        initialDate: DateTime.now(),
        firstDate: DateTime(2016),
        lastDate: DateTime(2019),
        locale: myLocale);
    setState(() {
      _date = picker.toString();
    });
  }

  _showTimePicker() async {
    var picker =
    await showTimePicker(context: context, initialTime: TimeOfDay.now());
    setState(() {
      _time = picker.toString();
    });
  }
  @override
  Widget build(BuildContext context) {
    final TextEditingController _contentController =
    new TextEditingController.fromValue(new TextEditingValue(text: ""));



    Widget addDemandSection = new Container(
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
                    child: Text("留言: ",style: TextStyle(
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
                  onPressed: (){

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
                  onPressed: () => Navigator.pop(context, "取消发送请求"),
                )
              ],
            ),
          )
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
  void onAddDemandResponse(bool success){

}
  @override
  void onAllDemandResponse(List<Demand> demandList){}
  @override
  void onParticipateDemandResponse(bool success){}
  @override
  void onQuitDemandResponse(bool success){}
  @override
  void onAllDiscountResponse(List<Discount> discountList){}
  @override
  void onError(error){}
}
