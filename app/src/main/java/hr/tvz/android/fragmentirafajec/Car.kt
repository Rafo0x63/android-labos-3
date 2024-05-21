package hr.tvz.android.fragmentirafajec

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "cars")
class Car(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "make") val make: String,
    @ColumnInfo(name = "model") val model: String,
    @ColumnInfo(name = "year") val year: Int,
    @ColumnInfo(name = "horsePower") val horsePower: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "titleImage") val titleImage: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt()
    )

    constructor(make: String, model: String, year: Int, horsePower: Int, description: String, titleImage: Int) :
            this(0, make, model, year, horsePower, description, titleImage) // Call the primary constructor with default id


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(make)
        parcel.writeString(model)
        parcel.writeInt(year)
        parcel.writeInt(horsePower)
        parcel.writeString(description)
        parcel.writeInt(titleImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Car> {
        override fun createFromParcel(parcel: Parcel): Car {
            return Car(parcel)
        }

        override fun newArray(size: Int): Array<Car?> {
            return arrayOfNulls(size)
        }
    }
}
