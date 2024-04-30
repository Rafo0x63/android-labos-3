package hr.tvz.android.listarafajec

import android.animation.ObjectAnimator
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import hr.tvz.android.listarafajec.databinding.ActivityMainBinding
import hr.tvz.android.listarafajec.databinding.CarDetailsBinding

class CarDetail : AppCompatActivity() {

    lateinit var binding: CarDetailsBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CarDetailsBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        val view = binding.root
        setContentView(view)


        val carHeading : TextView = binding.heading
        val carImage : ImageView = binding.image
        val carYear : TextView = binding.year
        val carHp : TextView = binding.horsepower
        val carDesc : TextView = binding.description

        val bundle : Bundle?= intent.extras
        val car = bundle!!.getParcelable("car", Car::class.java)
        println("CAR IMAGE ID ------------------------ ${car!!.titleImage} ${car.make} ${car.model} ${car.year} ${car.horsePower}")

        carHeading.text = "${car!!.make} ${car.model}"
        carImage.setImageResource(car.titleImage)
        carYear.text = car.year.toString()
        carHp.text = car.horsePower.toString()
        carDesc.text = car.description

        val imageView: ImageView = binding.image

        carHeading.setOnClickListener {
            val duration = 1500L
            val amplitude = 15f

            val animator = ObjectAnimator.ofFloat(carHeading, "rotation", 0f, amplitude, -amplitude, amplitude, -amplitude, 0f)
            animator.duration = duration
            animator.start()
        }

        imageView.setOnClickListener {
            val intent = Intent(this@CarDetail, ImageActivity::class.java)
            intent.putExtra("imageId", car.titleImage)
            startActivity(intent)
        }

        val ytBtn = binding.youtubeBtn
        ytBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.youtube.com/")
            startActivity(intent)
        }

        val shareBtn = binding.shareBtn

        shareBtn.setOnClickListener {
            showShareDialog()
        }
    }

    private fun showShareDialog() {
        AlertDialog.Builder(this)
            .setTitle(resources.getString(R.string.shareContent))
            .setMessage(resources.getString(R.string.confirm))
            .setPositiveButton(resources.getString(R.string.share)) { dialog, _ ->
                sendCustomBroadcast()
                dialog.dismiss()
            }
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun sendCustomBroadcast() {
        val intent = Intent("com.example.SHARE_ACTION")
        sendBroadcast(intent)
    }
}
