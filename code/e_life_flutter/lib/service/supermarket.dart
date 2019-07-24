import 'package:flutter/material.dart';
import 'comsuphttp.dart';

class surpermarket extends StatefulWidget {
  final communityId;
  surpermarket(this.communityId);
  @override
  State<StatefulWidget> createState() {
    return new surpermarketCenter(communityId);
  }
}

class surpermarketCenter extends State<surpermarket> with SingleTickerProviderStateMixin,NetListener {
  final communityId;
  surpermarketCenter(this.communityId);

  List<Merchant> supermarketMerchantList=[];//存后端数据

  comsupHttp manager = comsupHttp();

  Widget _getSurpermarketMerchant(int id,String name, String phone, String address,String detail,String type) {

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

  List<Widget> supermarketMerchants = [];
  Widget buildSupermarketMerchantBody(BuildContext ctxt, int index) {
    return supermarketMerchants[index];
  }

  @override
  Widget build(BuildContext context) {
    supermarketMerchants = [];
    if(supermarketMerchantList.length>0){
      for(int i=0;i<supermarketMerchantList.length;i++){
        Widget supermarketMerchant1 = _getSurpermarketMerchant(supermarketMerchantList[i].id, supermarketMerchantList[i].name, supermarketMerchantList[i].phone,supermarketMerchantList[i].address,supermarketMerchantList[i].detail,supermarketMerchantList[i].type);
        supermarketMerchants.add(supermarketMerchant1);
      }
    }

    Widget supermarketSection = new Container(
      child: new ListView.builder(
          itemCount: supermarketMerchants.length,
          itemBuilder: (BuildContext ctxt, int index) =>
              buildSupermarketMerchantBody(ctxt, index)),
    );
    return Scaffold(
      appBar: AppBar(
        title: Text('商家列表'),
      ),
      body: Column(
        children: <Widget>[
          new Expanded(
            child: supermarketSection,
          )
        ],
      ),

    );
  }
  void _returnSupermarketMerchant()async{
    await manager.mySupermarket(this);
  }
  @override
  void initState() {

    super.initState();
    _returnSupermarketMerchant();

  }
  @override
  void onMyComputerResponse(List<Merchant> computer){

  }
  @override
  void onMySupermarketResponse(List<Merchant> supermarket){
    supermarketMerchantList=supermarket;
    print("超市送货");
    setState(() {

    });
  }
  @override
  void onError(error){}
}
