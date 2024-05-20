package hr.tvz.android.fragmentirafajec

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CarDao {

    @Insert
    fun insertCar(car: Car): Long

    @Update
    fun updateCar(car: Car)

    @Delete
    fun deleteCar(car: Car)

    @Query("SELECT * FROM cars WHERE id = :id")
    fun getCarById(id: Int): Car?

    @Query("SELECT * FROM cars")
    fun getAllCars(): List<Car>
}
