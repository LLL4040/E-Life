import 'package:flutter/material.dart';

var androidView = new AndroidView(viewType: "MyMap");

class Map extends StatefulWidget {
  Wechat({
    Key key,
    this.title,
    this.name
  }) : super(key: key);
  @override
  _Map createState() => _Map();
}

class _Map extends State<Map> {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Map"),
      ),
      body: Stack(
        children: <Widget>[
          SizedBox(
              width: MediaQuery
                  .of(context)
                  .size
                  .width,
              height: MediaQuery
                  .of(context)
                  .size
                  .height,
              child: androidView //new AndroidView(viewType: "MyMap")
          )
        ],
      ),
    );
  }
}

//class Map extends StatelessWidget {
//  @override
//  Widget build(BuildContext context) {
//    return new MaterialApp(
//      title: 'Flutter Demo',
//      theme: new ThemeData(
//        primarySwatch: Colors.blue,
//      ),
//      home: new Scaffold(
//        appBar: AppBar(title: Text("Map"),),
//        body: new Column(
//          mainAxisAlignment: MainAxisAlignment.center,
//          children: <Widget>[
//            new Expanded(child: new AndroidView(viewType: 'MyMap'))
//          ],
//        ),
//      ),
//    );
//  }
//}