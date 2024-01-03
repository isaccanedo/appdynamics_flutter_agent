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
* Captura automática de capturas de tela e pontos de contato do usuário (somente iOS).
* Dados personalizados do usuário sobre solicitações de rede, relatórios de falhas ou sessões.
* Relatório [breadcrumbs](https://pub.dev/documentation/appdynamics_agent/latest/appdynamics_agent/Instrumentation/leaveBreadcrumb.html)
para rastrear widgets de UI ou interações personalizadas do usuário.
* [Temporizadores](https://pub.dev/documentation/appdynamics_agent/latest/appdynamics_agent/Instrumentation/startTimer.html)
   para rastrear eventos que abrangem vários métodos.
* Marcar execução do método
   como [pontos de informação](https://pub.dev/documentation/appdynamics_agent/latest/appdynamics_agent/Instrumentation/trackCall.html).
* Dividir instrumentação de aplicativos
   em [várias sessões](https://pub.dev/documentation/appdynamics_agent/latest/appdynamics_agent/Instrumentation/startNextSession.html).
* Relatar automaticamente métricas de dispositivos (memória, armazenamento, bateria) e eventos de transição de conexão.

# Começando

## Instalação

Você pode instalar o plugin Flutter via `flutter` — mais informações em
na [guia Instalação](https://pub.dev/packages/appdynamics_agent/install).

```
$ flutter pub add appdynamics_agent
```

## Configuração extra para aplicativos Android:

1. Adicione as seguintes alterações em `android/build.gradle`:

```groovy
dependencies {
    classpath "com.appdynamics:appdynamics-gradle-plugin:22.2.2"
    // ... other dependencies
}
```

2. Aplique o plugin `adeum` na parte inferior do arquivo `android/app/build.gradle`:

```groovy
dependencies {
    // ... project dependencies
}

// Parte inferior do arquivo
apply plugin: 'adeum'
```

3. Adicione as seguintes permissões ao seu `AndroidManifest.xml` (geralmente em `android/src/main/`):

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

## Iniciar instrumentação

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
