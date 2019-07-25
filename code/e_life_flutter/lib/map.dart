import 'package:flutter/material.dart';

//var androidView = new AndroidView(viewType: "MyMap");

class Map extends StatefulWidget {
  final androidView;
  Map({Key key,this.androidView}) : super(key : key);
  @override
  _Map createState() => _Map();
}

class _Map extends State<Map> with WidgetsBindingObserver {

  Future<bool> _onWillPop() {
    return showDialog(
      context: context,
      builder: (context) => new AlertDialog(
        title: new Text('Are you sure?'),
        content: new Text('Do you want to exit map?'),
        actions: <Widget>[
          new FlatButton(
            onPressed: () => Navigator.of(context).pop(false),
            child: new Text('No'),
          ),
          new FlatButton(
            onPressed: () => Navigator.of(context).pop(true),
            child: new Text('Yes'),
          ),
        ],
      ),
    ) ?? false;
  }

  @override
  void dispose() {
    WidgetsBinding.instance.removeObserver(this);
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return new WillPopScope(
        child: Scaffold(
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
                  child: widget.androidView //new AndroidView(viewType: "MyMap")
              )
            ],
          ),
        ),
      onWillPop: _onWillPop,
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