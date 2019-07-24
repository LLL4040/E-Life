package com.example.mymapshow;

import android.os.Bundle;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.BaiduMap;
import io.flutter.app.FlutterActivity;
import io.flutter.plugins.GeneratedPluginRegistrant;
//import permissions.dispatcher.NeedsPermission;
//import permissions.dispatcher.OnNeverAskAgain;
//import permissions.dispatcher.OnPermissionDenied;
//import permissions.dispatcher.OnShowRationale;
//import permissions.dispatcher.PermissionRequest;
//import permissions.dispatcher.RuntimePermissions;


//@RuntimePermissions
public class MainActivity extends FlutterActivity {
    private MapView mapView;
    private BaiduMap baiduMap;

    public void initView(){
        mapView = new MapView(this);
    }

    public void initMap(){
        baiduMap = mapView.getMap();
        //baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //卫星地图
        //baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        baiduMap.setTrafficEnabled(true);
        mapView.showZoomControls(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initView();
        this.initMap();
        //GeneratedPluginRegistrant.registerWith(this);
        //MapView mapView = new MapView(this);
        MapRegistrant.registerWith(this, mapView);
    }

//    @Override
//    protected void onDestory(){
//        super.onDestory();
//        mapView.onDestory();
//    }
}
