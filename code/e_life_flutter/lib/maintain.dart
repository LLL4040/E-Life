import 'package:flutter/material.dart';

class maintain extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        actions: <Widget>[
          new IconButton(icon: new Icon(Icons.account_circle), onPressed: null),

        ],
        title: Text('维修'),
      ),
      body: Center(
        child: Text('物业维修'),
      ),
    );
  }
}
