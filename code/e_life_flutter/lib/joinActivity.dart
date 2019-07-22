import 'package:flutter/material.dart';
class joinActivity extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new joinActivityWidget();
  }
}
class joinActivityWidget extends State<joinActivity> with SingleTickerProviderStateMixin{
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(

        title: Text('活动报名'),
      ),
      body: Center(
        child: Text('活动报名'),
      ),
    );
  }
}
