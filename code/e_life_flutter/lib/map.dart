import 'package:flutter/material.dart';
//import 'package:amap_base/amap_base.dart';

class myMap extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new myMapWidget();
  }
}

class myMapWidget extends State<myMap> with SingleTickerProviderStateMixin {
  //AMapController _controller;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("我的周边"),
        ),
//        body: AMapView(
//          onAMapViewCreated: (controller) {
//            //_controller = controller;
//          },
//          amapOptions: AMapOptions(
//            compassEnabled: false,
//            zoomControlsEnabled: true,
//            logoPosition: LOGO_POSITION_BOTTOM_CENTER,
//            camera: CameraPosition(
//              target: LatLng(41.851827, 112.801637),
//              zoom: 4,
//            ),
//          ),
//        )
    body: Center(
      child: Text("地图"),
    ),
    );
  }
}
