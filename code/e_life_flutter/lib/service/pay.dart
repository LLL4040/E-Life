import 'package:flutter/material.dart';
import 'package:e_life_flutter/service/addRepair.dart';
import 'payhttp.dart';
class pay extends StatefulWidget {
  final username;
  pay(this.username);
  @override
  State<StatefulWidget> createState() {
    return new payCenter(username);
  }
}

class payCenter extends State<pay> with SingleTickerProviderStateMixin,NetListener {
  final username;
  payCenter(this.username);

  List<Bill> billList=[];//存后端数据

  payHttp manager = payHttp();
  Widget _getMyBill(String time, double bill, int status) {
    String repairStatus;
    if (status == 0) {
      repairStatus = "未处理";
    }
    if (status == 1) {
      repairStatus = "处理中";
    }
    if (status == 2) {
      repairStatus = "已完成";
    }else{
      repairStatus ="其他";
    }
    return new ListTile(
      leading: new Icon(Icons.settings),
      title: new Text(bill.toString()),
      subtitle: new Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          new Text(time),
          new Text(repairStatus),
        ],
      ),
      onTap: () {
        print(bill);
      },
      dense: true,
    );
  }

  List<Widget> myMyBills = [];
  Widget buildMyBillBody(BuildContext ctxt, int index) {
    return myMyBills[index];
  }

  @override
  Widget build(BuildContext context) {
    myMyBills = [];
    if(billList.length>0){
      for(int i=0;i<billList.length;i++){
        Widget mybill1 = _getMyBill(billList[i].time, billList[i].bill, billList[i].status);
        myMyBills.add(mybill1);
      }
    }
//    Widget myrepair = _getMyRepair("2019/6/6", "我家厕所堵住了", 1);
//    myrepairs.add(myrepair);
    Widget myBillSection = new Container(
      child: new ListView.builder(
          itemCount: myMyBills.length,
          itemBuilder: (BuildContext ctxt, int index) =>
              buildMyBillBody(ctxt, index)),
    );
    return Scaffold(
      appBar: AppBar(
        title: Text('缴费账单'),
      ),
      body: Column(
        children: <Widget>[
          new Expanded(
            child: myBillSection,
          )
        ],
      ),

    );
  }
  void _returnMyBill()async{
    await manager.myBill(this, username);
  }
  @override
  void initState() {

    super.initState();
    _returnMyBill();

  }
  @override
  void onMyBillResponse(List<Bill> bill){
    billList=bill;
    setState(() {

    });
  }
  @override
  void onError(error){
    print("$error");
  }
}
