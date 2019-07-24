import 'package:flutter/material.dart';
import 'comsuphttp.dart';

class computer extends StatefulWidget {
  final communityId;
  computer(this.communityId);
  @override
  State<StatefulWidget> createState() {
    return new computerCenter(communityId);
  }
}

class computerCenter extends State<computer> with SingleTickerProviderStateMixin,NetListener {
  final communityId;
  computerCenter(this.communityId);

  List<Merchant> computerMerchantList=[];//存后端数据

  comsupHttp manager = comsupHttp();

  Widget _getComputerMerchant(int id,String name, String phone, String address,String detail,String type) {

    return new ListTile(
      leading: (type=="电脑维修")?new Icon(Icons.computer):new Icon(Icons.shopping_cart),
      title: new Text(id.toString()),
      subtitle: new Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          new Text(name),
          new Text(phone),
          new Text(address),
          new Text(detail),
        ],
      ),
      onTap: () {
        print(name);
      },
      dense: true,
    );
  }

  List<Widget> ComputerMerchants = [];
  Widget buildComputerMerchantBody(BuildContext ctxt, int index) {
    return ComputerMerchants[index];
  }

  @override
  Widget build(BuildContext context) {
    ComputerMerchants = [];
    if(computerMerchantList.length>0){
      for(int i=0;i<computerMerchantList.length;i++){
        Widget ComputerMerchant1 = _getComputerMerchant(computerMerchantList[i].id, computerMerchantList[i].name, computerMerchantList[i].phone,computerMerchantList[i].address,computerMerchantList[i].detail,computerMerchantList[i].type);
        ComputerMerchants.add(ComputerMerchant1);
      }
    }
//    Widget myrepair = _getMyRepair("2019/6/6", "我家厕所堵住了", 1);
//    myrepairs.add(myrepair);
    Widget myComputerSection = new Container(
      child: new ListView.builder(
          itemCount: ComputerMerchants.length,
          itemBuilder: (BuildContext ctxt, int index) =>
              buildComputerMerchantBody(ctxt, index)),
    );
    return Scaffold(
      appBar: AppBar(
        title: Text('商家列表'),
      ),
      body: Column(
        children: <Widget>[
          new Expanded(
            child: myComputerSection,
          )
        ],
      ),

    );
  }
  void _returnComputerMerchant()async{
    await manager.myComputer(this);
  }
  @override
  void initState() {

    super.initState();
    _returnComputerMerchant();

  }
  @override
  void onMyComputerResponse(List<Merchant> computer){
    computerMerchantList=computer;
    setState(() {

    });
  }
  @override
  void onMySupermarketResponse(List<Merchant> supermarket){}
  @override
  void onError(error){}
}
