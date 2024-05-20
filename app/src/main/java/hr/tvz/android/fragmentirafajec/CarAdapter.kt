package hr.tvz.android.fragmentirafajec

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class CarAdapter(private val carList: List<Car>) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.car_list_item,
            parent, false)
        return CarViewHolder(itemView, mListener)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val currentItem = carList[position]
        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.itemHeading.text = "${currentItem.make} ${currentItem.model}"
    }

    class CarViewHolder(carView : View, listener: onItemClickListener) : RecyclerView.ViewHolder(carView) {

        val titleImage : ShapeableImageView = itemView.findViewById(R.id.titleImage)
        val itemHeading : TextView = itemView.findViewById(R.id.itemHeading)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}