package `in`.woloo.www.application_kotlin.utilities

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class AppLifecycleObserver(private val context: Context) : LifecycleEventObserver {

    private var isDialogNeeded = false

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_START) {
            if (isDialogNeeded) {
               // showNotificationPermissionDialog(context)
                isDialogNeeded = false
            }
        }
    }

    fun setDialogNeeded() {
        isDialogNeeded = true
    }
}