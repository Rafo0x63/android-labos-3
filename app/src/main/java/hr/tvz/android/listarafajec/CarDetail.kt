package hr.tvz.android.listarafajec

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
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

        val bundle : Bundle?= intent.extras
        /*
        val heading = bundle!!.getString("heading")
        val imageId = bundle.getInt("imageId")
        val year = bundle.getInt("year")
        val hp = bundle.getInt("hp")*/

        val car = bundle!!.getParcelable("car", Car::class.java)
        println("CAR IMAGE ID ------------------------ ${car!!.titleImage} ${car.make} ${car.model} ${car.year} ${car.horsePower}")

        carHeading.text = "${car!!.make} ${car.model}"
        carImage.setImageResource(car.titleImage)
        carYear.text = car.year.toString()
        carHp.text = car.horsePower.toString()
    }
}
