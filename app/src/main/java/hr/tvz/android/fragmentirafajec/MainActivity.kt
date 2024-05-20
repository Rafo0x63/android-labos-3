package hr.tvz.android.fragmentirafajec

import CarDetail
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import hr.tvz.android.fragmentirafajec.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var MOJ_KANAL = "mojKanal"

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this@MainActivity)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_land)
            supportFragmentManager.beginTransaction()
                .replace(R.id.list_fragment, CarList())
                .commit()

            supportFragmentManager.beginTransaction()
                .replace(R.id.detail_fragment, CarDetail())
                .commit()
        } else {
            setContentView(R.layout.activity_main)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CarList())
                .commit()
        }

        if (intent != null && intent.hasExtra("fragmentToLoad")) {
            val fragmentToLoad = intent.getStringExtra("fragmentToLoad")
            if (fragmentToLoad == "AddCar") {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, AddCar())
                    .commit()
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                MOJ_KANAL,
                "Kanal 1",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "Description"
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }

            val token = task.result

            Log.d("Main activity token: ", token)
            Toast.makeText(this@MainActivity, token, Toast.LENGTH_SHORT).show()
        })
    }
}
