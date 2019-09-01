import 'package:flutter/material.dart';
import 'package:e_life_flutter/service/addRepair.dart';
import 'payhttp.dart';
import 'package:tobias/tobias.dart' as tobias;
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
  Widget _getMyBill(String id,String time, double bill, int status) {
    String repairStatus;
    if (status == -1 || status==-2) {
      repairStatus = "未处理";
    }
    if (status == -11 || status==-12) {
      repairStatus = "处理中";
    }
    if (status == 2 || status==1) {
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
          OutlineButton(child: Text("获取订单"), onPressed: () {
            print(id);
            getOrderInfo(id,bill.toString(),time);
          }),
          OutlineButton(child: Text("支付"), onPressed: () {
            print(":"+_payInfo+":1222");
            doPayExec(_payInfo);
          }),
        ],
      ),
      onTap: () {
        print(bill);
      },
      dense: true,
    );
  }

  List<Widget> myMyBills = [];
  var  _payInfo;
  Orders order;
  Map _payresult = {};
  String msg = "";
  Widget buildMyBillBody(BuildContext ctxt, int index) {
    return myMyBills[index];
  }

  @override
  Widget build(BuildContext context) {
    myMyBills = [];

    if(billList.length>0){
      for(int i=0;i<billList.length-1;i++){
        Widget mybill1 = _getMyBill(billList[i].id.toString(),billList[i].time, billList[i].bill, billList[i].status);
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
  void doPayExec(String apyInfo) async {
    Map payResult;
    try {
      payResult = await tobias.pay(_payInfo.toString());
    }
    on Exception catch (e) {
      payResult = {};
      print("hhh");
      return;
    }
    finally{
      print("hhha");
    }
    if (!mounted) return;

    setState(() {
      _payresult = payResult;
    });

  }
  getOrderInfo(String id,String bill,String time) async {
    print(id);
    await manager.myOrders(this, username, id, bill);
    await new Future.delayed(new Duration(milliseconds: 1000));
    await manager.pay(this, order.id.toString(), order.bill.toString(), order.time);

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
  void onOrderResponse(Orders myOrder){
    order = myOrder;
    setState(() {

    });
  }
  @override
  void onAliResponse(String pay){
    _payInfo = pay;
    setState(() {

    });

  }
  @override
  void onError(error){
    print("$error");
  }
}
