import 'package:flutter/material.dart';

class User extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        actions: <Widget>[
          new IconButton(icon: new Icon(Icons.account_circle), onPressed: null),

        ],
        title: Text('帖子详情'),
      ),
      body: Center(
        child: Text('帖子'),
      ),
    );
  }
}
