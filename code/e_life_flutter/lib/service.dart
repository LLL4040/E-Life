import 'package:flutter/material.dart';
class Choice {
  const Choice({this.title, this.icon, this.position});
  final String title;
  final int position;
  final IconData icon;
}
class service extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        actions: <Widget>[
        ],
        title: Text('小区服务'),
      ),
      body: GridView(
        padding: EdgeInsets.all(5),
        gridDelegate: SliverGridDelegateWithMaxCrossAxisExtent(
            maxCrossAxisExtent: 150.0,
            childAspectRatio: 1.0 //宽高比为2
        ),
        children: <Widget>[
          Column(
            children: <Widget>[
              IconButton(icon:new Icon(Icons.event),onPressed: null,),
              Text("邮包")
            ],
          ),
          Column(
            children: <Widget>[
              IconButton(icon:new Icon(Icons.settings_applications),onPressed: null,),
              Text("维修")
            ],
          ),
          Column(
            children: <Widget>[
              IconButton(icon:new Icon(Icons.shopping_cart),onPressed: null,),
              Text("超市")
            ],
          ),
          Column(
            children: <Widget>[
              IconButton(icon:new Icon(Icons.computer),onPressed: null,),
              Text("电脑维修")
            ],
          ),
          Column(
            children: <Widget>[
              IconButton(icon:new Icon(Icons.search),onPressed: null,),
              Text("周边")
            ],
          ),
          Column(
            children: <Widget>[
              IconButton(icon:new Icon(Icons.free_breakfast),onPressed: null,),
              Text("团购")
            ],
          ),
          Column(
            children: <Widget>[
              IconButton(icon:new Icon(Icons.monetization_on),onPressed: null,),
              Text("账单")
            ],
          ),

        ],
      ),

    );
  }
}
