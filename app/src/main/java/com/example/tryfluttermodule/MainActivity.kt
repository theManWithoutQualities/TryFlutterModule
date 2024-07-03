package com.example.tryfluttermodule

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.JSONMethodCodec
import io.flutter.plugin.common.MethodChannel

private const val engineId = "engineId"
private const val flutterTag = "flutter"

class MainActivity : FragmentActivity(R.layout.root) {

    private val flutterFragment get() =
        supportFragmentManager.findFragmentByTag(flutterTag) as? FlutterFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val engine = FlutterEngine(applicationContext)
        FlutterEngineCache.getInstance().put(engineId, engine)
        engine.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        val channel = MethodChannel(engine.dartExecutor, "methods", JSONMethodCodec.INSTANCE)
        findViewById<TextView>(R.id.button).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.fragment_container,
                    FlutterFragment.CachedEngineFragmentBuilder(
                        MyFlutterFragment::class.java,
                        engineId
                    ).shouldAutomaticallyHandleOnBackPressed(true)
                        .build(),
                    flutterTag
                )
                .addToBackStack(null)
                .commit()
            channel.invokeMethod("method", null)
        }
    }

    // forward OS signals from Activity as described in
    // https://docs.flutter.dev/add-to-app/android/add-flutter-fragment#add-a-flutterfragment-to-an-activity-with-a-new-flutterengine
    override fun onPostResume() {
        super.onPostResume()
        flutterFragment?.onPostResume()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        flutterFragment?.onNewIntent(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        flutterFragment?.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults
        )
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        flutterFragment?.onActivityResult(
            requestCode,
            resultCode,
            data
        )
    }

    override fun onUserLeaveHint() {
        flutterFragment?.onUserLeaveHint()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        flutterFragment?.onTrimMemory(level)
    }
}
