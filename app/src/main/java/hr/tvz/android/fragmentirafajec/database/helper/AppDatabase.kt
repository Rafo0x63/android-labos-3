package hr.tvz.android.fragmentirafajec

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Car::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao
}
