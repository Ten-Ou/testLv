package com.oshx.myapplication;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<ScanResult> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyWifiManager wifiManager1 = new MyWifiManager();


        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        list = wifiManager.getScanResults();
        int wifiState = wifiManager.getWifiState();
        android.net.wifi.WifiInfo info = wifiManager.getConnectionInfo();
        String wifiId = info != null ? info.getSSID() : null;

        try {
            List<WifiInfo> wifiInfos = wifiManager1.Read();
            for (int i = 0;i<wifiInfos.size();i++){
                if (wifiId.equals("\""+wifiInfos.get(i).Ssid+"\"")){
                    Log.d("test"," wifiinfo ssid "+wifiInfos.get(i).Ssid
                            +"    wifiinfo password :"+wifiInfos.get(i).Password);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


//        Log.d("test",wifiId);


//        for (int i=0;i<list.size();i++){
//            Log.d("test"," ssid :"+list.get(i).SSID);
//        }
    }



}
