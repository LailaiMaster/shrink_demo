import 'dart:async';

import 'package:flutter/services.dart';

class DemoPlugin {
  static const MethodChannel _channel = MethodChannel('demo_plugin');

  static Future<String?> get platformVersion async {
    final String? version = await _channel.invokeMethod('getPlatformVersion',
        <String, dynamic>{'res_path': 'ic_address_checked'});
    print('platformVersion: $version');
    return version;
  }
}
