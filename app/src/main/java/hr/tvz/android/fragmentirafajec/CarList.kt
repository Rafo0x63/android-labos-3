package hr.tvz.android.fragmentirafajec

import CarDetail
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import hr.tvz.android.fragmentirafajec.Car
import hr.tvz.android.fragmentirafajec.CarAdapter
import hr.tvz.android.fragmentirafajec.R
import hr.tvz.android.fragmentirafajec.database.helper.DatabaseHelper
import hr.tvz.android.fragmentirafajec.databinding.FragmentCarListBinding

class CarList : Fragment(R.layout.fragment_car_list) {

    private var _binding: FragmentCarListBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var carList: List<Car>

    private lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarListBinding.inflate(inflater, container, false)
        val view = binding.root

        db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "database-name"
        )
            .allowMainThreadQueries()
            .build()

        carList = db.carDao().getAllCars()

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        val adapter = CarAdapter(carList)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : CarAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val carDetailFragment = CarDetail().apply {
                    arguments = Bundle().apply {
                        putParcelable("car", carList[position])
                    }
                }
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.detail_fragment, carDetailFragment)
                        .commit()
                } else {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, carDetailFragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        })

        binding.addButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AddCar())
                .addToBackStack(null)
                .commit()
                binding.addButton.visibility = View.GONE
        }

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.addButton.visibility = View.GONE
        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
