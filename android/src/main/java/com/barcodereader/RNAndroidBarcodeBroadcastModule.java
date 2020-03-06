package com.barcodereader;

import android.util.Log;
import android.content.IntentFilter;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RNAndroidBarcodeBroadcastModule extends ReactContextBaseJavaModule {

    public static ReactApplicationContext reactContext;
    private static final String ACTION_BARCODE_DATA = "android.intent.ACTION_DECODE_DATA";

    public RNAndroidBarcodeBroadcastModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        reactContext.registerReceiver(new BarcodeBroadcastReceiver(), new IntentFilter(ACTION_BARCODE_DATA));

    }

    @Override
    public String getName() {
        return "RNAndroidBarcodeBroadcast";
    }

  
}