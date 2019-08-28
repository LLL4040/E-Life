import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:latlong/latlong.dart';
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
      child:FlutterMap(
        options: new MapOptions(
          center: new LatLng(31.029766,121.437648),
          zoom: 13.0,
        ),
        layers: [
          new TileLayerOptions(
            urlTemplate: "https://api.tiles.mapbox.com/v4/"
                "{id}/{z}/{x}/{y}@2x.png?access_token={accessToken}",
            additionalOptions: {
              'accessToken': 'pk.eyJ1IjoiemhpbW8iLCJhIjoiY2p6dm4yZXJ2MDM3ajNtbzFmd3o5d3J4dCJ9.t184EEBZpri3Ds4HOHn6pw',
              'id': 'mapbox.streets',
            },
          ),
          new MarkerLayerOptions(
            markers: [
              new Marker(
                width: 80.0,
                height: 80.0,
                point: new LatLng(31.029766, 121.437648),
                builder: (ctx) =>
                new Container(
                  child: new Image.asset(

                    'images/pin.png',
                    scale: 1.0,
                  ),
                ),
              ),
            ],
          ),
        ],
      ),
    ),
    );

  }
}
