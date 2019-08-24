import 'package:flutter/material.dart';
import 'package:e_life_flutter/service/addRepair.dart';
import 'payhttp.dart';
class pay extends StatefulWidget {
  final username;
  var session;
  pay(this.username,this.session);
  @override
  State<StatefulWidget> createState() {
    return new payCenter(username,session);
  }
}

class payCenter extends State<pay> with SingleTickerProviderStateMixin,NetListener {
  final username;
  var session;
  payCenter(this.username,this.session);

  List<Bill> billList=[];//存后端数据

  payHttp manager = payHttp();
  Widget _getMyBill(var bill) {
    String repairStatus;
    if (bill.status == -1||bill.status == -2) {
      repairStatus = "未缴费";
    }
    else if (bill.status == 1||bill.status == 2) {
      repairStatus = "已缴费";
    }
    else if (bill.status == -11||bill.status== -12) {
      repairStatus = "支付中";
    }else{
      repairStatus ="其他";
    }

    return new SizedBox(
      //height: 210.0, //设置高度
      child: new Card(
//        elevation: 15.0, //设置阴影
        shape: const RoundedRectangleBorder(
            borderRadius: BorderRadius.all(Radius.circular(20.0))), //设置圆角
        child: new Column(
          // card只能有一个widget，但这个widget内容可以包含其他的widget
          children: [
            ListTile(
              leading: bill.status==-1||bill.status==1||bill.status==11?new Icon(Icons.departure_board):new Icon(Icons.settings),
              title: new Text(bill.bill.toString()),
              subtitle: new Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  new Text(bill.time.toString()),


                ],
              ),

              trailing: new RaisedButton(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(20.0)),
                color: repairStatus=="已缴费"?Colors.blue[300]:repairStatus=="支付中"?Colors.orangeAccent[200]:Colors.redAccent[200],
                textColor: Colors.white,
                child: Text(repairStatus,
                ),
                onPressed: () {
                  // ...
                },
              ),
              dense: true,
            )

          ],
        ),
      ),
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
      for(int i=0;i<billList.length-1;i++){
        Widget mybill1 = _getMyBill(billList[i]);
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
    await manager.myBill(this, username,session);
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
