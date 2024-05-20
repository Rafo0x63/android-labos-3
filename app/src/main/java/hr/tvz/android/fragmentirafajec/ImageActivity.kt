package hr.tvz.android.fragmentirafajec

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ImageActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_activity)

        val imageId = intent.getIntExtra("imageId", 0)

        val imageView = findViewById<ImageView>(R.id.image)
        imageView.setImageResource(imageId)
    }
}