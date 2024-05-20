import android.animation.ObjectAnimator
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.room.Room
import hr.tvz.android.fragmentirafajec.AppDatabase
import hr.tvz.android.fragmentirafajec.Car
import hr.tvz.android.fragmentirafajec.ImageActivity
import hr.tvz.android.fragmentirafajec.R
import hr.tvz.android.fragmentirafajec.databinding.FragmentCarDetailBinding

class CarDetail : Fragment(R.layout.fragment_car_detail) {
    lateinit var binding: FragmentCarDetailBinding
    private lateinit var db: AppDatabase
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentCarDetailBinding.inflate(layoutInflater)

        val carHeading : TextView = binding.heading
        val carImage : ImageView = binding.image
        val carYear : TextView = binding.year
        val carHp : TextView = binding.horsepower
        val carDesc : TextView = binding.description

        val car = arguments?.getParcelable("car", Car::class.java)

        val defaultCar = car ?: getDefaultCar()

        carHeading.text = "${defaultCar.make} ${defaultCar.model}"
        carImage.setImageResource(defaultCar.titleImage)
        carYear.text = defaultCar.year.toString()
        carHp.text = defaultCar.horsePower.toString()
        carDesc.text = defaultCar.description

        carHeading.setOnClickListener {
            val duration = 1500L
            val amplitude = 15f

            val animator = ObjectAnimator.ofFloat(carHeading, "rotation", 0f, amplitude, -amplitude, amplitude, -amplitude, 0f)
            animator.duration = duration
            animator.start()
        }

        carImage.setOnClickListener {
            val intent = Intent(requireContext(), ImageActivity::class.java)
            intent.putExtra("imageId", defaultCar.titleImage)
            startActivity(intent)
        }

        binding.youtubeBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.youtube.com/")
            startActivity(intent)
        }

        binding.shareBtn.setOnClickListener {
            showShareDialog()
        }

        return binding.root
    }

    private fun getDefaultCar(): Car {
        db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "database-name"
        )
            .allowMainThreadQueries()
            .build()

        val carList: List<Car> = db.carDao().getAllCars()

        return carList[0]
    }

    private fun showShareDialog() {
        AlertDialog.Builder(requireContext())
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
        requireContext().sendBroadcast(intent)
    }
}
