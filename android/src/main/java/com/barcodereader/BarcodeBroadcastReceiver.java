package com.barcodereader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.Set;

public class BarcodeBroadcastReceiver extends BroadcastReceiver {
    //Unitech EA600 barkod intent
    public static final String INSTALL_ACTION = "android.intent.ACTION_DECODE_DATA";
    public static String Barcode_Data;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (!INSTALL_ACTION.equals(intent.getAction())) {
            return;
        }

        Barcode_Data = intent.getStringExtra("barcode_string");
        Log.w("ReactNativeJS", "BarcodeScanerReceiver referrer: " + Barcode_Data);

        WritableMap map = new WritableNativeMap();
        map.putString("barcode_data", Barcode_Data);
        sendEvent("BarcodeScanerReceiver" ,map);
    }

    private void sendEvent(String eventName, WritableMap map) {
        try{
            ReactContext reactContext = RNAndroidBarcodeBroadcastModule.reactContext;

            reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit(eventName, map);
        }
        catch(Exception e){
            Log.d("ReactNativeJS","Exception in sendEvent in BarcodeScanerReceiver is:"+e.toString());
        }

    }
}
