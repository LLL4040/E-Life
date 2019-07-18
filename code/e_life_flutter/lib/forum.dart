import 'package:flutter/material.dart';

class forum extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        actions: <Widget>[
          new IconButton(icon: new Icon(Icons.account_circle), onPressed: null),

        ],
        title: Text('论坛'),
      ),
      body: Center(
        child: Text('小区论坛'),
      ),
    );
  }
}
