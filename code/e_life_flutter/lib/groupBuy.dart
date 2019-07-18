import 'package:flutter/material.dart';

class GroupBuy extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        actions: <Widget>[
          new IconButton(icon: new Icon(Icons.account_circle), onPressed: null),

        ],
        title: Text('团购'),
      ),
      body: Center(
        child: Text('小区团购'),
      ),
    );
  }
}
