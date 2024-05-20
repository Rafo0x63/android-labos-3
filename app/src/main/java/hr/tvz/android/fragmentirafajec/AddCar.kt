package hr.tvz.android.fragmentirafajec

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.room.Room
import hr.tvz.android.fragmentirafajec.databinding.FragmentAddCarBinding

class AddCar : Fragment(R.layout.fragment_add_car) {

    lateinit var binding: FragmentAddCarBinding
    lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentAddCarBinding.inflate(layoutInflater)

        db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "database-name"
        )
            .allowMainThreadQueries()
            .build()

        binding.buttonAddCar.setOnClickListener {
            val id = binding.editTextCarId.text.toString().toInt()
            val make = binding.editTextCarMake.text.toString()
            val model = binding.editTextCarModel.text.toString()
            val year = binding.editTextCarYear.text.toString().toInt()
            val horsePower = binding.editTextCarHorsePower.text.toString().toInt()
            val description = binding.editTextCarDescription.text.toString()
            val titleImage = binding.editTextCarTitleImage.text.toString().toInt()

            val car = Car(id, make, model, year, horsePower, description, R.drawable.civic)

            val result = db.carDao().insertCar(car)

            if (result != -1L) {
                showToast("Car added successfully")
            } else {
                showToast("Failed to add car")
            }

            parentFragmentManager.popBackStack()
        }

        return binding.root
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    /*override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.hideAddButton()
    }

    override fun onPause() {
        super.onPause()
        (activity as? MainActivity)?.showAddButton()
    }*/
}