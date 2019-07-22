import 'package:flutter/material.dart';

class Map extends StatefulWidget {
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
              child: AndroidView(viewType: "MyMap")
          )
        ],
      ),
    );
  }
}