# cts-react-native-barkod-reader
CTS barkod reader for Unitech - EA600

# cts-react-native-barcode-reader

## Getting started

`$ npm install git+https://github.com/cts-yazilim/cts-react-native-barcode-reader --save`

### Mostly automatic installation

`$ react-native link cts-react-native-barcode-reader`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.barcodereader.RNAndroidBarcodeBroadcastPackage;` to the imports at the top of the file
  - Add `new RNAndroidBarcodeBroadcastPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':cts-react-native-barcode-reader'
  	project(':cts-react-native-barcode-reader').projectDir = new File(rootProject.projectDir, 	'../node_modules/cts-react-native-barcode-reader/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':cts-react-native-barcode-reader')
  	```
4. Insert the following lines inside the AndroidManifest.xml in `android/app/src/main/AndroidManifest.xml`:
  	```
	  <manifest ....>
	  		.....
			<application ...>
					.....
			        <receiver
						android:name="com.androidbroadcastreceiverforreferrer.ReferrerBroadcastReceiver"
						android:enabled="true"
						android:exported="true">
						<intent-filter>
							<action android:name="android.intent.ACTION_DECODE_DATA"/>
						</intent-filter>
        			</receiver>
   			</application>
      </manifest>
  	```

## Usage
```javascript
import { DeviceEventEmitter } from "react-native";

//Add it in componentWillMount or somewhere where it will get executed at the start of app 
DeviceEventEmitter.addListener('BarcodeScanerReceiver', function (map) {
    console.log('BarcodeScanerReceiver: ' + map.referrer;
});;

//Do not forget to remove the listener at componentWillUnmount 
componentWillUnmount() {
    DeviceEventEmitter.removeListener('BarcodeScanerReceiver'); 
  }

//You can also get the referrer which is stored in the local variable by
import RNAndroidBarcodeBroadcast from 'cts-react-native-barcode-reader'; 

//This will return the referrer value if we have got it other will return "NOT AVAILABLE"
let referrerValue = await RNAndroidBarcodeBroadcast.getReferrerData();

```
  