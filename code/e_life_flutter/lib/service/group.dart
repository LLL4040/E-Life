import 'package:flutter/material.dart';
import 'demandhttp.dart';
import '../user.dart';
import 'dart:convert';
import 'package:scoped_model/scoped_model.dart';
import 'package:oktoast/oktoast.dart';
import 'addDemand.dart';

class groupWeight extends StatefulWidget {
  final username;
  final communityId;
  var session;
  groupWeight(this.username, this.communityId,this.session);
  @override
  State<StatefulWidget> createState() {
    return new groupWeightState(username, communityId,session);
  }
}

class groupWeightState extends State<groupWeight>
    with SingleTickerProviderStateMixin, NetListener {
  final username;
  final communityId;
  var session;
  groupWeightState(this.username, this.communityId,this.session);

  List<Demand> demandList = [];
  List<Discount> discountList = [];

  demandHttp manager = new demandHttp();

  List<Choice> tabs = []; //导航栏
  TabController mTabController;
  int mCurrentPosition = 0;

  @override
  void initState() {
    super.initState();
    _getGroup();
    tabs.add(Choice(title: '商家团购', icon: Icons.add_shopping_cart, position: 0));
    tabs.add(Choice(title: '自发需求', icon: Icons.people, position: 1));
    mTabController = new TabController(vsync: this, length: tabs.length);
    //判断TabBar是否切换
    mTabController.addListener(() {
      if (mTabController.indexIsChanging) {
        setState(() {
          mCurrentPosition = mTabController.index;
        });
      }
    });
  }

  List<bool> visibleList=[];
  Widget _getDiscount(Discount discount,int i) {
    if(visibleList.length>0){
      return new SizedBox(

        height: visibleList[i]?250.0:100, //设置高度
        child: new Card(
//        elevation: 15.0, //设置阴影
//        shape: const RoundedRectangleBorder(
//            borderRadius: BorderRadius.all(Radius.circular(0.0))), //设置圆角
          child: new Column(
            // card只能有一个widget，但这个widget内容可以包含其他的widget
            children: [
              ListTile(

                  leading:SizedBox(

                    child: new Card(
                      //elevation: 0.1, //设置阴影
                      //设置shape，这里设置成了R角
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.all(Radius.circular(10.0))),
                      //对Widget截取的行为，比如这里 Clip.antiAlias 指抗锯齿
                      clipBehavior: Clip.antiAlias,
                      child:Image.memory(
                        base64.decode( discount.photo.split(',')[1]),

                        fit: BoxFit.fill, //填充
                        gaplessPlayback: true, //防止重绘
                      ),
                    ),
                  ),
                  title: new Text(discount.title,style: TextStyle(
                    color: Colors.grey[600],
                  ),),
                  subtitle: new Text("人数上限:"+discount.num.toString()),
                  trailing: IconButton(
                    icon: Icon(visibleList[i]?Icons.keyboard_arrow_up:Icons.keyboard_arrow_down),
                    onPressed: (){

                      setState(() {
                        if(visibleList[i]){
                          visibleList[i]=false;
                        }else{
                          visibleList[i]=true;
                        }
                      });
                    },
                  ),
                  onTap: (){
                    setState(() {
                      if(visibleList[i]){
                        visibleList[i]=false;
                      }else{
                        visibleList[i]=true;
                      }
                    });
                  }
              ),
              visibleList[i]?new Divider():Padding(
                padding: EdgeInsets.only(),
              ),
              visibleList[i]?new ListTile(
                subtitle: Column(
                  crossAxisAlignment:CrossAxisAlignment.start,
                  children: <Widget>[
                    Text("开始时间：" + discount.start),
                    Text("结束时间：" + discount.end),
                  ],
                ),
              ):Padding(
                padding: EdgeInsets.only(),
              ),
              visibleList[i]?new Divider():Padding(
                padding: EdgeInsets.only(),
              ),
              visibleList[i]?new ListTile(
                subtitle: Text("团购内容: "+discount.content),
              ):Padding(
                padding: EdgeInsets.only(),
              ),
            ],
          ),
        ),
      );
    }


  }

  Widget _getDemand(Demand demand){
    return new ListTile(
      leading: new Icon(Icons.shopping_basket),
      title: new Text(demand.title),
    onTap: (){
      showDialog<Null>(
        context: context,
        builder: (BuildContext context) {
          return new SimpleDialog(
            children: <Widget>[
               Container(
                   child: Column(
                     children: <Widget>[
                       ListTile(
                         leading: Text(
                           demand.title,
                           textAlign: TextAlign.left,
                           textScaleFactor: 1.5,
                         ),
                         trailing:Text("发起人：" + demand.username),
                       ),
                       new Divider(),
                       ListTile(
                         //subtitle: Text("开始时间：" + demand.start),
                         subtitle: Column(
                            crossAxisAlignment:CrossAxisAlignment.start,
                           children: <Widget>[
                             Text("开始时间：" + demand.start),
                             Text("结束时间：" + demand.end),
                           ],
                         ),
                       ),
                       new Divider(),
                       ListTile(
                         title: Text("团购内容：" + demand.content),
                       ),


                     ],
                   )
               ),
            ],
          );
        }
      );
    },
    );
  }


  void _toaddDemand() {
    Navigator.push<String>(context,
        new MaterialPageRoute(builder: (BuildContext context) {
          return new addDemand(username,communityId,session);
        })).then((String result){
      print("报名收到的信息为:"+result);
      showToast(result);
      manager.getDemandList(this, communityId,session);
      setState(() async{
        await new Future.delayed(new Duration(milliseconds: 1000));
        manager.getDemandList(this, username,session);
      });


    });
  }

  _getGroup() async {
    print("我的团购" + username);
    await manager.getDemandList(this, communityId,session);
    await manager.getDiscountList(this, communityId,session);
    return true;
  }

  List<Widget> discounts = [];
  Widget buildDiscountBody(BuildContext buildContext, int index){
    return discounts[index];
  }

  List<Widget> demands = [];
  Widget buildDemandBody(BuildContext buildContext, int index){
    return demands[index];
  }

  @override
  Widget build(BuildContext context) {
    final TextEditingController _nameController =
    new TextEditingController.fromValue(new TextEditingValue(text: ""));

    discounts = [];
    demands = [];
    //visibleList=[];
    if(discountList.length > 0){
      //print(discountList.length);
      for(int i = 0; i < discountList.length; i++){
        Widget newDiscount = _getDiscount(discountList[i],i);

        discounts.add(newDiscount);
      }
    }
    if(demandList.length > 0){
      //print(demandList.length);
      for(int i = 0; i < demandList.length; i++){
        Widget newDemand = _getDemand(demandList[i]);
        demands.add(newDemand);
      }
    }

    Widget discountContain = new Container(
      child: new ListView.builder(
          itemCount: discounts.length,
          itemBuilder: (BuildContext context, int index) {
            return buildDiscountBody(context, index);
          }),
    );
    Widget demandContain = new Container(
      child: new ListView.builder(
          itemCount: demands.length,
          itemBuilder: (BuildContext context, int index) {
            return buildDemandBody(context, index);
          }),
    );

    List<Widget> widgetList = [];
    widgetList.add(discountContain);
    widgetList.add(demandContain);

    return ScopedModelDescendant<UserModel>(builder: (context, child, model) {
      return Scaffold(
        appBar: AppBar(
          actions: <Widget>[],
          title: Text('团购'),
        ),
        body: NestedScrollView(
          headerSliverBuilder: (BuildContext context, bool innerBoxIsScrolled) {
            return [
              new SliverAppBar(
                bottom: PreferredSize(
                    child: new Container(
                      color: Colors.white,
                      child: new TabBar(
                        indicatorSize: TabBarIndicatorSize.label,
                        indicatorColor: Colors.blueAccent,
                        labelColor: Colors.blueAccent,
                        unselectedLabelColor: Colors.black45,
                        tabs: tabs.map((Choice choice) {
                          return new Tab(
                            text: choice.title,
                            icon: new Icon(
                              choice.icon,
                            ),
                          );
                        }).toList(),
                        controller: mTabController,
                      ),
                    ),
                    preferredSize: new Size(double.infinity, 18.0)),
              )
            ];
          },
          body: new TabBarView(
            children: tabs.map((Choice choice) {
              return new Padding(
                  padding: const EdgeInsets.all(15.0),
                  child: widgetList[choice.position]);
            }).toList(),
            controller: mTabController,
          ),
        ),
        floatingActionButton: mCurrentPosition==0?null:FloatingActionButton(
          onPressed: _toaddDemand,
          tooltip: 'addDemand',
          child: Icon(Icons.add),
        ),
      );
    });
  }

  @override
  void dispose() {
    super.dispose();
    mTabController.dispose();
  }


  @override
  void onAllDemandResponse(List<Demand> demandList) {
    this.demandList = demandList;
    print("get demandList");
    setState(() {});
  }

  @override
  void onAllDiscountResponse(List<Discount> discountList) {
    this.discountList = discountList;
    for(int i=0;i<discountList.length;i++){
      visibleList.add(false);
    }
    print("get discountList");
    setState(() {});
  }

  @override
  void onAddDemandResponse(bool success) {
    // TODO: implement onAddDemandResponse
  }

  @override
  void onParticipateDemandResponse(bool success) {
    // TODO: implement onParticipateDemandResponse
  }

  @override
  void onQuitDemandResponse(bool success) {
    // TODO: implement onQuitDemandResponse
  }

  @override
  void onError(error) {
    print("$error");
  }

}

class Choice {
  const Choice({this.title, this.icon, this.position});
  final String title;
  final int position;
  final IconData icon;
}



