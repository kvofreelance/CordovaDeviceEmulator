CordovaEmulatorOnDevice
=====================

CordovaEmulatorOnDevice is application for easy testing your cordova(phonegap) projects.
Application consist of CordovaWbView which used Cordova framework and aLogcat app for view log messages in app(https://code.google.com/p/alogcat/).

CordovaDeviceEmulator support all standart cordova plugins:
org.apache.cordova.device
org.apache.cordova.network-information
org.apache.cordova.battery-status
org.apache.cordova.device-motion
org.apache.cordova.device-orientation
org.apache.cordova.geolocation
org.apache.cordova.camera
org.apache.cordova.media-capture
org.apache.cordova.media
org.apache.cordova.file
org.apache.cordova.file-transfer
org.apache.cordova.dialogs
org.apache.cordova.vibration
org.apache.cordova.contacts
org.apache.cordova.globalization
org.apache.cordova.splashscreen
org.apache.cordova.inappbrowser
org.apache.cordova.console

But you can add your plugins. It's easy.

Advantages:
1. You can fast develop your app.
2. You don't need to build and install every time your app
3. CordovaEmulatorOnDevice show your app as release app

Instruction:
1. Run applciation
2. Pess "Settings" button and click "GoTo"
3. Enter url adress in textedit of your project path. (Ex.: http://192.168.0.101/AppName/www)
4. Click OK and you can see your project

Prepare cordova project:
After creating project(This you have to do this once, and every time you add a new plugin):
1. Add all need plugins
2. Run command:
   cordova build android
3. Goto <AppName>/platforms/android/assets/www/
4. Copy cordova.js, cordova_plugins.js and plugins folder to <AppName>/www/

Add new plugins:
1. clone this project to your local pc
2. add your plugins
3. Run command
  cordova build android
4. Install new app CordovaEmulatorOnDevice on your phone.

