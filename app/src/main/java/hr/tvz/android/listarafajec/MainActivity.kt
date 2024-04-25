package hr.tvz.android.listarafajec

import android.os.Bundle
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
    lateinit var heading : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        val view = binding.root
        setContentView(view)

        imageId = arrayOf(
            R.drawable.corolla,
            R.drawable.civic,
            R.drawable.focus,
            R.drawable.camaro,
            R.drawable.bmw3
        )

        carArrayList = ArrayList()
        carArrayList.add(Car("Toyota", "Corolla", 2020, 150, imageId[0]))
        carArrayList.add(Car("Honda", "Civic", 2019, 140, imageId[1]))
        carArrayList.add(Car("Ford", "Focus", 2018, 160, imageId[2]))
        carArrayList.add(Car("Chevrolet", "Camaro", 2017, 300, imageId[3]))
        carArrayList.add(Car("BMW", "3 Series", 2021, 200, imageId[4]))

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        recyclerView.adapter = CarAdapter(carArrayList)
    }


}