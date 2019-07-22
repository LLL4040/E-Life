import 'package:flutter/material.dart';
import 'repair.dart';

class Choice {
  const Choice({this.title, this.icon, this.position});
  final String title;
  final int position;
  final IconData icon;
}
class service  extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new serviceWidget();
  }
}
class serviceWidget extends State<service> with SingleTickerProviderStateMixin {
  @override
  Widget build(BuildContext context) {
    void _torepair() {
      Navigator.push(context, new MaterialPageRoute(builder: (context) {
        return new myRepair();
      }));
    }

    return Scaffold(
      appBar: AppBar(
        actions: <Widget>[],
        title: Text('小区服务'),
      ),
      body: GridView(
        padding: EdgeInsets.all(5),
        gridDelegate: SliverGridDelegateWithMaxCrossAxisExtent(
            maxCrossAxisExtent: 150.0, childAspectRatio: 1.0 //宽高比为2
            ),
        children: <Widget>[
          FlatButton(
            child: Column(
              children: <Widget>[

                IconButton(
                  icon: new Icon(Icons.event),
                  color: Colors.black38,
                  onPressed: _torepair,
                ),
                Text("上门寄件")
              ],
            ),
            onPressed: _torepair,
          ),


          FlatButton(
            child: Column(
              children: <Widget>[

                IconButton(
                  icon: new Icon(Icons.settings_applications),
                  color: Colors.black38,
                  onPressed: _torepair,
                ),
                Text("物业维修")
              ],
            ),
            onPressed: _torepair,
          ),
          FlatButton(
            child: Column(
              children: <Widget>[

                IconButton(
                  icon: new Icon(Icons.shopping_cart),
                  color: Colors.black38,
                  onPressed: _torepair,
                ),
                Text("超市送货")
              ],
            ),
            onPressed: _torepair,
          ),
          FlatButton(
            child: Column(
              children: <Widget>[

                IconButton(
                  icon: new Icon(Icons.computer),
                  color: Colors.black38,
                  onPressed: _torepair,
                ),
                Text("电脑维修")
              ],
            ),
            onPressed: _torepair,
          ),
          FlatButton(
            child: Column(
              children: <Widget>[

                IconButton(
                  icon: new Icon(Icons.search),
                  color: Colors.black38,
                  onPressed: _torepair,
                ),
                Text("周边")
              ],
            ),
            onPressed: _torepair,
          ),
          FlatButton(
            child: Column(
              children: <Widget>[

                IconButton(
                  icon: new Icon(Icons.free_breakfast),
                  color: Colors.black38,
                  onPressed: _torepair,
                ),
                Text("团购")
              ],
            ),
            onPressed: _torepair,
          ),
          FlatButton(
            child: Column(
              children: <Widget>[

                IconButton(
                  icon: new Icon(Icons.monetization_on),
                  color: Colors.black38,
                  onPressed: _torepair,
                ),
                Text("账单")
              ],
            ),
            onPressed: _torepair,
          ),

        ],
      ),
    );
  }
}
