import 'package:flutter/material.dart';

class mycenter extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        actions: <Widget>[
          new IconButton(icon: new Icon(Icons.account_circle), onPressed: null),

        ],
        title: Text('个人中心'),
      ),
      body: Center(
        child: Text('个人中心'),
      ),
    );
  }
}
