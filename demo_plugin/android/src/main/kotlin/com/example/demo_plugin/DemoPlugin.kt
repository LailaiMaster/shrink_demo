package com.example.demo_plugin

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** DemoPlugin */
class DemoPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel
  private lateinit var context: Context

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    context = flutterPluginBinding.applicationContext
    channel = MethodChannel(flutterPluginBinding.flutterEngine.dartExecutor, "demo_plugin")
    channel.setMethodCallHandler(this)
    this.context = flutterPluginBinding.applicationContext
  }


  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    Log.e("DemoPlugin", "onMethodCall ${call.arguments}--")
    if (call.method == "getPlatformVersion") {

      //获取argument
      val arguments = call.arguments as Map<String, Any>
      Log.e("DemoPlugin", "onMethodCall ${arguments["res_path"]}--")
      TestActivity.start(context,arguments["res_path"] as String)
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}
