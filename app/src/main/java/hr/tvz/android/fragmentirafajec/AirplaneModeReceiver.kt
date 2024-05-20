package hr.tvz.android.fragmentirafajec

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneModeReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
                val isAirplaneModeOn = intent.getBooleanExtra("state", false)
                if (isAirplaneModeOn) {
                    println("Airplane mode turned on")
                    Toast.makeText(context, "Airplane mode turned on", Toast.LENGTH_LONG).show()
                } else {
                    println("Airplane mode turned off")
                    Toast.makeText(context, "Airplane mode turned off", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
