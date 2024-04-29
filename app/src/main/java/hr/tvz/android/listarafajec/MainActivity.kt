package hr.tvz.android.listarafajec

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.tvz.android.listarafajec.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var carArrayList: ArrayList<Car>
    lateinit var imageId : Array<Int>

    val receiver = AirplaneModeReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        val view = binding.root
        setContentView(view)

        registerReceiver(receiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))

        imageId = arrayOf(
            R.drawable.corolla,
            R.drawable.civic,
            R.drawable.focus,
            R.drawable.camaro,
            R.drawable.bmw3
        )

        carArrayList = ArrayList()
        carArrayList.add(
            Car(
                resources.getStringArray(R.array.corolla)[0], // Toyota
                resources.getStringArray(R.array.corolla)[1], // Corolla
                resources.getStringArray(R.array.corolla)[2].toInt(), // 2023
                resources.getStringArray(R.array.corolla)[3].toInt(), // 150
                resources.getStringArray(R.array.corolla)[4],
                imageId[0]
            )
        )
        carArrayList.add(
            Car(
                resources.getStringArray(R.array.civic)[0], // Honda
                resources.getStringArray(R.array.civic)[1], // Civic
                resources.getStringArray(R.array.civic)[2].toInt(), // 2023
                resources.getStringArray(R.array.civic)[3].toInt(), // 158
                resources.getStringArray(R.array.civic)[4],

                imageId[1]
            )
        )
        carArrayList.add(
            Car(
                resources.getStringArray(R.array.focus)[0], // Ford
                resources.getStringArray(R.array.focus)[1], // Focus
                resources.getStringArray(R.array.focus)[2].toInt(), // 2023
                resources.getStringArray(R.array.focus)[3].toInt(), // 123
                resources.getStringArray(R.array.focus)[4],
                imageId[2]
            )
        )
        carArrayList.add(
            Car(
                resources.getStringArray(R.array.camaro)[0], // Chevrolet
                resources.getStringArray(R.array.camaro)[1], // Camaro
                resources.getStringArray(R.array.camaro)[2].toInt(), // 2023
                resources.getStringArray(R.array.camaro)[3].toInt(), // 275
                resources.getStringArray(R.array.camaro)[4],
                imageId[3]
            )
        )
        carArrayList.add(
            Car(
                resources.getStringArray(R.array.bmw3)[0], // BMW
                resources.getStringArray(R.array.bmw3)[1], // 3 Series
                resources.getStringArray(R.array.bmw3)[2].toInt(), // 2023
                resources.getStringArray(R.array.bmw3)[3].toInt(), // 248
                resources.getStringArray(R.array.bmw3)[4],
                imageId[4]
            )
        )

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        var adapter = CarAdapter(carArrayList)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : CarAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                //Toast.makeText(this@MainActivity, "You clicked item no. ${position + 1}", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@MainActivity, CarDetail::class.java)
                /*intent.putExtra("heading", "${carArrayList[position].make} ${carArrayList[position].model}")
                intent.putExtra("imageId", carArrayList[position].titleImage)
                intent.putExtra("year", carArrayList[position].year)
                intent.putExtra("hp", carArrayList[position].horsePower)*/
                intent.putExtra("car", carArrayList[position])

                startActivity(intent)
            }
        })
    }
}