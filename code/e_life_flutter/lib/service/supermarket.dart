import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:latlong/latlong.dart';
import 'comsuphttp.dart';
import 'package:url_launcher/url_launcher.dart';
import 'package:oktoast/oktoast.dart';
class surpermarket extends StatefulWidget {
  final communityId;
  var session;
  surpermarket(this.communityId,this.session);
  @override
  State<StatefulWidget> createState() {
    return new surpermarketCenter(communityId,session);
  }
}

class surpermarketCenter extends State<surpermarket> with SingleTickerProviderStateMixin,NetListener {
  final communityId;
  var session;
  surpermarketCenter(this.communityId,this.session);
  _launchURL(String phone) async {
    String url="tel: "+phone;
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }
  List<Merchant> supermarketMerchantList=[];//存后端数据

  comsupHttp manager = comsupHttp();
  merchantButtonBuild(Merchant merchant){
    return IconButton(
      icon: merchant.type=="周边餐饮"?new Image.asset(
        'images/brown.png',
      ):merchant.type=="休闲娱乐"?new Image.asset(
        'images/green.png',
      ):merchant.type=="电脑维修"?new Image.asset(
        'images/purple.png',
      ):new Image.asset(
        'images/blue.png',
      ),
      onPressed:(){
        showDialog<Null>(
            context: context,
            builder: (BuildContext context) {
              return new SimpleDialog(
                children: <Widget>[
                  Card(
                    elevation: 0.0, //设置阴影
                    child: Column(

                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: <Widget>[
                        ListTile(
                          title: Text(
                            merchant.name,
                            textAlign: TextAlign.left,
                            textScaleFactor: 1.5,
                          ),
                        ),
                        Divider(),

                        ListTile(
                          title:Text(merchant.detail) ,
                        ),
                        Divider(),
                        ListTile(
                          subtitle:Text("地址: "+merchant.address) ,
                        ),
                      ],
                    ),
                  ),
                ],
              );
            }
        );
      },
    );
  }
  markerBuild(Merchant merchant) {
    double lng=double.parse(merchant.address.substring(4,14));
    double lat=double.parse(merchant.address.substring(19));
    //print(lng);
    //print(lat);
    return Marker(
      height: 80,
      width: 80,
      point: new LatLng(lat, lng),
      builder: (ctx) => new Container(
        child: merchantButtonBuild(merchant),
      ),
    );
  }
  List<Marker> markerList = [];
  Widget _getSurpermarketMerchant(int id,String name, String phone, String address,String detail,String type) {


    return new SizedBox(
      //height: 210.0, //设置高度
      child: new Card(
//        elevation: 15.0, //设置阴影
//        shape: const RoundedRectangleBorder(
//            borderRadius: BorderRadius.all(Radius.circular(0.0))), //设置圆角
        child: new Column(
          // card只能有一个widget，但这个widget内容可以包含其他的widget
          children: [
            new ListTile(
              leading: (type=="电脑维修")?new Icon(Icons.computer):new Icon(Icons.shopping_cart),
              title: new Text(name),
              subtitle: new Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[

                  new Text(phone),

                ],
              ),
              onTap: () {
                showDialog<Null>(
                  context: context,
                  builder: (BuildContext context) {
                    return new SimpleDialog(
                      title: new Text('是否拨打电话'),
                      children: <Widget>[
                        new Row(
                          mainAxisSize: MainAxisSize.max,
                          mainAxisAlignment: MainAxisAlignment.end,
                          children: <Widget>[
                            new SimpleDialogOption(
                              child: FlatButton(
                                color: Colors.blue,
                                highlightColor: Colors.blue[700],
                                colorBrightness: Brightness.dark,
                                splashColor: Colors.grey,
                                child: Text("确定"),
                                shape: RoundedRectangleBorder(
                                    borderRadius: BorderRadius.circular(20.0)),
                                onPressed: () {
                                  _launchURL(phone);
                                  Navigator.of(context).pop();
                                },
                              ),

                            ),
                            new SimpleDialogOption(
                              child: FlatButton(
                                color: Colors.black54,
                                highlightColor: Colors.black38,
                                colorBrightness: Brightness.dark,
                                splashColor: Colors.grey,
                                child: Text("取消"),
                                shape: RoundedRectangleBorder(
                                    borderRadius: BorderRadius.circular(20.0)),
                                onPressed: () {
                                  showToast("取消拨打电话");
                                  Navigator.of(context).pop();
                                },
                              ),

                            ),
                          ],
                        ),

                      ],
                    );
                  },
                ).then((val) {
                  print(val);
                });

              },
              dense: true,
            ),
            //new Divider(),
          ],
        ),
      ),
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
    if(supermarketMerchantList.length>0){
      markerList=[];
      //bargainList=[];
      for(int i=0;i<supermarketMerchantList.length;i++){
        var marker1=markerBuild(supermarketMerchantList[i]);
        markerList.add(marker1);
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
      body: Stack(
        children: <Widget>[

          supermarketMerchantList.length>0?FlutterMap(
            options: new MapOptions(
                  //center: new LatLng(31.029766, 121.437648),
              center: new LatLng(double.parse(supermarketMerchantList[0].address.substring(19)),double.parse(supermarketMerchantList[0].address.substring(4,14))),
              zoom: 13.0,
            ),
            layers: [
              new TileLayerOptions(
                urlTemplate: "https://api.tiles.mapbox.com/v4/"
                    "{id}/{z}/{x}/{y}@2x.png?access_token={accessToken}",
                additionalOptions: {
                  'accessToken':
                  'pk.eyJ1IjoiemhpbW8iLCJhIjoiY2p6dm4yZXJ2MDM3ajNtbzFmd3o5d3J4dCJ9.t184EEBZpri3Ds4HOHn6pw',
                  'id': 'mapbox.streets',
                },
              ),
              new MarkerLayerOptions(
                markers: markerList,
              ),

            ],
          ):Center(
            child: Text("地图加载中"),
          ),

          new SizedBox(
            height: 150,
            child:new Card(
              child: supermarketSection,
            ) ,
          ),
        ],
      ),

    );
  }
  void _returnSupermarketMerchant()async{
    await manager.mySupermarket(this,session);
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
  void allMerchantResponse(List<Merchant> merchant){}
  @override
  void allBargainResponse(List<Bargain> bargain){}
  @override
  void onError(error){}
}
