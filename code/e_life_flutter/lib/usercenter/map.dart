import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:latlong/latlong.dart';
import '../service/comsuphttp.dart';
//import 'package:amap_base/amap_base.dart';

class myMap extends StatefulWidget {
  var session;
  myMap(this.session);
  @override
  State<StatefulWidget> createState() {
    return new myMapWidget(session);
  }
}

class myMapWidget extends State<myMap>
    with SingleTickerProviderStateMixin, NetListener {
  var session;
  myMapWidget(this.session);
  List<Merchant> merchantList = []; //存后端数据
  List<Bargain> bargainList = []; //存后端数据
  List<Bargain> searchList = []; //存后端数据
  List<Marker> markerList = [];
  final TextEditingController _bargain =
  new TextEditingController.fromValue(new TextEditingValue(text: ""));
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
  bargainButtonBuild(Bargain bargain){
    return IconButton(
      icon: Image.asset(
        'images/black.png',
      ),
      onPressed: (){
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
                            bargain.title,
                            textAlign: TextAlign.left,
                            textScaleFactor: 1.5,
                          ),
                        ),
                        Divider(),
                        ListTile(
                          title:Text(bargain.goods) ,
                        ),
                        Divider(),
                        ListTile(
                          title:Text(bargain.content) ,
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
  bargainBuild(Bargain bargain) {
    double lng=double.parse(bargain.address.substring(4,14));
    double lat=double.parse(bargain.address.substring(19));
    //print(lng);
    //print(lat);
    return Marker(
      height: 80,
      width: 80,
      point: new LatLng(lat, lng),
      builder: (ctx) => new Container(
        child: bargainButtonBuild(bargain),
      ),
    );
  }
  searchBargin(String text){
    List<Marker> learchMerchant=[];
    for(int i=0;i<bargainList.length;i++){
      print("hahah");
      if(bargainList[i].title==text){
        var marker1=bargainBuild(bargainList[i]);
        learchMerchant.add(marker1);
      }
    }

    setState(() {
      markerList=learchMerchant;
    });
  }
  Widget sizebox(){
    return new SizedBox(
      height: 150,
      child:new Card(
        child: ListView(
          children: <Widget>[
            ListTile(
              subtitle: new TextField(
                controller: _bargain,
                decoration: new InputDecoration(
                  contentPadding:
                  const EdgeInsets.symmetric(vertical: 10.0),
                  hintText: '搜索优惠商品',
                ),
                onSubmitted:(text){
                  searchList=[];
                  for(int i=0;i<bargainList.length;i++){
                    print("hahah");
                    if(bargainList[i].title==text){
                      searchList.add(bargainList[i]);
                    }
                  }

                },
              ),
              trailing: new FlatButton(
                color: Colors.blue[400],
                child: Text("全部优惠商品",style: TextStyle(
                  color: Colors.white,

                ),),
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(5.0)),
                onPressed: ()async {
                  await manager.allBargain(this, session);
                },
              ),
            ),
            ListTile(
              leading:FlatButton(
                color: Colors.blue[400],
                child: Text("全部商家",style: TextStyle(
                  color: Colors.white,
                ),),
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(5.0)),
                onPressed: ()async {
                  await manager.allMerchant(this, session);
                },
              ),

            ),
            Row(
              children: <Widget>[

                Container(
                  width: 70,
                  height: 25,
                  padding:EdgeInsets.only(right: 1.0),
                  decoration: new BoxDecoration(
                    borderRadius: new BorderRadius.all(new Radius.circular(20.0)),
                  ),
                  alignment: Alignment.center,
                  child: FlatButton(
                    color: Colors.blue[800],
                    child: Text("购物",style: TextStyle(
                      color: Colors.white,
                    ),),
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(5.0)),
                    onPressed: ()async {

                    },
                  ),
                ),
                Container(
                  width: 70,
                  height: 25,
                  padding:EdgeInsets.only(right: 1.0),
                  decoration: new BoxDecoration(
                    borderRadius: new BorderRadius.all(new Radius.circular(20.0)),
                  ),
                  alignment: Alignment.center,
                  child: FlatButton(
                    color: Colors.green[400],
                    child: Text("娱乐",style: TextStyle(
                      color: Colors.white,
                    ),),
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(5.0)),
                    onPressed: ()async {

                    },

                  ),
                ),
                Container(
                  width: 70,
                  height: 25,
                  padding:EdgeInsets.only(right: 1.0),
                  decoration: new BoxDecoration(
                    borderRadius: new BorderRadius.all(new Radius.circular(20.0)),
                  ),
                  alignment: Alignment.center,
                  child: FlatButton(
                    color: Colors.brown[400],
                    child: Text("餐饮",style: TextStyle(
                      color: Colors.white,
                    ),),
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(5.0)),
                    onPressed: ()async {

                    },

                  ),
                ),
                Container(
                  width: 70,
                  height: 25,
                  decoration: new BoxDecoration(
                    borderRadius: new BorderRadius.all(new Radius.circular(20.0)),
                  ),
                  alignment: Alignment.center,
                  child: FlatButton(
                    color: Colors.purple[400],
                    child: Text("服务",style: TextStyle(
                      color: Colors.white,
                    ),),
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(5.0)),
                    onPressed: ()async {

                    },
                  ),
                )
              ],
            )
          ],
        ),
      ) ,
    );
  }
  @override
  Widget build(BuildContext context) {

    if(merchantList.length>0){
      markerList=[];
      //bargainList=[];
      for(int i=0;i<merchantList.length;i++){
        var marker1=markerBuild(merchantList[i]);
        markerList.add(marker1);
      }

    }
    if(bargainList.length>0){
      markerList=[];
      //merchantList=[];
      for(int i=0;i<bargainList.length;i++){
        var marker1=bargainBuild(bargainList[i]);
        markerList.add(marker1);
      }
    }
    if(searchList.length>0){
      markerList=[];
      merchantList=[];
      for(int i=0;i<searchList.length;i++){
        var marker1=bargainBuild(searchList[i]);
        markerList.add(marker1);
      }
    }
    return Scaffold(
      appBar: AppBar(
        title: Text("我的周边"),
      ),
    body: Container(
      child: Stack(
            children: <Widget>[
              merchantList.length==0&&bargainList.length==0?Center(
                child: Text("地图加载中"),
              ):FlutterMap(
                options: new MapOptions(
//                  center: new LatLng(31.029766, 121.437648),
                  center: merchantList.length>0?new LatLng(double.parse(merchantList[0].address.substring(19)),double.parse(merchantList[0].address.substring(4,14))):new LatLng(double.parse(bargainList[0].address.substring(19)),double.parse(bargainList[0].address.substring(4,14))),
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
              ),
              sizebox(),

            ],

      ),
    ),
    );
  }

  void _returnSupermarketMerchant() async {
    await manager.allMerchant(this, session);

  }

  @override
  void initState() {
    super.initState();
    _returnSupermarketMerchant();
  }

  @override
  void onMyComputerResponse(List<Merchant> computer) {}
  @override
  void onMySupermarketResponse(List<Merchant> supermarket) {}
  @override
  void allMerchantResponse(List<Merchant> merchant) {
    bargainList=[];
    searchList=[];
    merchantList = merchant;
    setState(() {});
  }
  @override
  void allBargainResponse(List<Bargain> bargain){
    bargainList=bargain;
    merchantList=[];
    setState(() {

    });
  }
  @override
  void onError(error) {}
}
