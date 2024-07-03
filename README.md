# Example for reproducing exception with camera plugin
Go to flutter fragment -> Close it with back button -> Go to flutter fragment again ->
Exception while trying to call availableCameras() for the second time

```
PlatformException(java.lang.NullPointerException, NullPointerException, Cause: null, Stacktrace: java.lang.NullPointerException
at java.util.Objects.requireNonNull(Objects.java:220)
at io.flutter.plugins.camerax.CameraSelectorHostApiImpl.filter(CameraSelectorHostApiImpl.java:49)
at io.flutter.plugins.camerax.GeneratedCameraXLibrary$CameraSelectorHostApi.lambda$setup$1(GeneratedCameraXLibrary.java:1039)
at io.flutter.plugins.camerax.GeneratedCameraXLibrary$CameraSelectorHostApi$$ExternalSyntheticLambda1.onMessage(D8$$SyntheticClass:0)
at io.flutter.plugin.common.BasicMessageChannel$IncomingMessageHandler.onMessage(BasicMessageChannel.java:261)
at io.flutter.embedding.engine.dart.DartMessenger.invokeHandler(DartMessenger.java:292)
at io.flutter.embedding.engine.dart.DartMessenger.lambda$dispatchMessageToQueue$0$io-flutter-embedding-engine-dart-DartMessenger(DartMessenger.java:319)
at io.flutter.embedding.engine.dart.DartMessenger$$ExternalSyntheticLambda0.run(D8$$SyntheticClass:0)
at android.os.Handler.handleCallback(Handler.java:938)
at android.os.Handler.dispatchMessage(Handler.java:99)
at android.os.Looper.loop(Looper.java:246)
at android.app.ActivityThread.main(ActivityThread.java:8653)
at java.lang.reflect.Method.invoke(Native Method)
at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:602)
at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1130)
, null)
```