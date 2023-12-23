[![Pub Version](https://img.shields.io/pub/v/appdynamics_agent)](https://pub.dev/packages/appdynamics_agent)

# AppDynamics Flutter Plugin

Extensão do AppDynamics SDK que permite instrumentar aplicativos Flutter e receber análises.

Este plug-in envolve os SDKs nativos e requer uma licença móvel válida do AppDynamics.

## Características

O agente Flutter incorpora os seguintes recursos:

* Rastreamento de solicitação de rede via
  o Rastreamento de solicitação de rede via
   o [TrackedHTTPClient](https://pub.dev/documentation/appdynamics_agent/latest/appdynamics_agent/TrackedHttpClient-class.html)
   e [RequestTracker](https://pub.dev/documentation/appdynamics_agent/latest/appdynamics_agent/RequestTracker-class.html)
   Classes.
* Relatório automático de falhas
   e [CrashReportCallback](https://pub.dev/documentation/appdynamics_agent/latest/appdynamics_agent/CrashReportCallback.html)
   para configuração extra do relatório de falhas.
*Rastreamento de tela
   via [NavigationObserver](https://pub.dev/documentation/appdynamics_agent/latest/appdynamics_agent/NavigationObserver-class.html)
   e [WidgetTracker](https://pub.dev/documentation/appdynamics_agent/latest/appdynamics_agent/WidgetTracker-class.html).
* Detecção e relatório automáticos de casos de aplicativo que não responde (ANR).
* [SessionFrame](https://pub.dev/documentation/appdynamics_agent/latest/appdynamics_agent/SessionFrame-class.html)
 mecanismo para rastrear fluxos de usuários personalizados no aplicativo.
* [Erros](https://pub.dev/documentation/appdynamics_agent/latest/appdynamics_agent/Instrumentation/errorHandler.html)
   e [métricas personalizadas](https://pub.dev/documentation/appdynamics_agent/latest/appdynamics_agent/Instrumentation/reportMetric.html)
   comunicando.
* Automatic capture of screenshots and user touch-points (iOS only).
* Custom user data on network requests, crash reports, or sessions.
* Report [breadcrumbs](https://pub.dev/documentation/appdynamics_agent/latest/appdynamics_agent/Instrumentation/leaveBreadcrumb.html)
to track UI widgets or custom user interactions.
* [Timers](https://pub.dev/documentation/appdynamics_agent/latest/appdynamics_agent/Instrumentation/startTimer.html)
  to track events that span across multiple methods.
* Mark method execution
  as [info points](https://pub.dev/documentation/appdynamics_agent/latest/appdynamics_agent/Instrumentation/trackCall.html).
* Split app instrumentation
  into [multiple sessions](https://pub.dev/documentation/appdynamics_agent/latest/appdynamics_agent/Instrumentation/startNextSession.html).
* Automatically report device metrics (memory, storage, battery) and connection transition events.

# Getting started

## Installation

You can install the Flutter plugin via `flutter` — more info on
the [Installation tab](https://pub.dev/packages/appdynamics_agent/install).

```
$ flutter pub add appdynamics_agent
```

## Extra configuration for Android apps:

1. Add the following changes to `android/build.gradle`:

```groovy
dependencies {
    classpath "com.appdynamics:appdynamics-gradle-plugin:22.2.2"
    // ... other dependencies
}
```

2. Apply the `adeum` plugin to the bottom of the `android/app/build.gradle` file:

```groovy
dependencies {
    // ... project dependencies
}

// Bottom of file
apply plugin: 'adeum'
```

3. Add the following permissions to your `AndroidManifest.xml` (usually in `android/src/main/`):

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.myawesomepackage">

    <!-- add these two permissions -->
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

    <!-- other permissions -->

    <application>
        <!-- other settings -->
    </application>
</manifest>
```

## Start instrumentation

> **_NOTE:_** Replace `<EUM_APP_KEY>` with your app key.

```dart
import 'package:appdynamics_agent/appdynamics_agent.dart';
import 'package:flutter/material.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();

  final config = AgentConfiguration(
      appKey: "<EUM_APP_KEY>",
      loggingLevel: LoggingLevel.verbose, // optional, for better debugging.
      collectorURL: "<COLLECTOR_URL>", // optional, mostly on-premises. 
      screenshotURL: "<SCREENSHOT_URL>" // optional, mostly on-premises.
  );
  await Instrumentation.start(config);

  runApp(const MyApp());
}
 ```

# Docs

You can access [pub.dev docs](https://pub.dev/documentation/appdynamics_agent/latest/) or check
the [official docs](https://docs.appdynamics.com/22.3/en/end-user-monitoring/mobile-real-user-monitoring/instrument-flutter-applications/customize-the-flutter-instrumentation)
for extra customization of the agent.
