package hr.tvz.android.fragmentirafajec.database.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import hr.tvz.android.fragmentirafajec.Car

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context,
    DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "cars"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "cars"

        const val COLUMN_ID = "id"
        const val COLUMN_MAKE = "make"
        const val COLUMN_MODEL = "model"
        const val COLUMN_YEAR = "year"
        const val COLUMN_HORSE_POWER = "horsePower"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_TITLE_IMAGE = "titleImage"

        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY," +
                    "$COLUMN_MAKE TEXT," +
                    "$COLUMN_MODEL TEXT," +
                    "$COLUMN_YEAR INTEGER," +
                    "$COLUMN_HORSE_POWER INTEGER," +
                    "$COLUMN_DESCRIPTION TEXT," +
                    "$COLUMN_TITLE_IMAGE INTEGER)"

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    fun insertCar(car: Car): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ID, car.id)
            put(COLUMN_MAKE, car.make)
            put(COLUMN_MODEL, car.model)
            put(COLUMN_YEAR, car.year)
            put(COLUMN_HORSE_POWER, car.horsePower)
            put(COLUMN_DESCRIPTION, car.description)
            put(COLUMN_TITLE_IMAGE, car.titleImage)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun getAllCars(): ArrayList<Car> {
        val carList = ArrayList<Car>()
        val db = this.readableDatabase

        val projection = arrayOf(
            COLUMN_ID,
            COLUMN_MAKE,
            COLUMN_MODEL,
            COLUMN_YEAR,
            COLUMN_HORSE_POWER,
            COLUMN_DESCRIPTION,
            COLUMN_TITLE_IMAGE
        )

        val cursor = db.query(
            TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        cursor.use { cursor ->
            val idIndex = cursor.getColumnIndex(COLUMN_ID)
            val makeIndex = cursor.getColumnIndex(COLUMN_MAKE)
            val modelIndex = cursor.getColumnIndex(COLUMN_MODEL)
            val yearIndex = cursor.getColumnIndex(COLUMN_YEAR)
            val horsePowerIndex = cursor.getColumnIndex(COLUMN_HORSE_POWER)
            val descriptionIndex = cursor.getColumnIndex(COLUMN_DESCRIPTION)
            val titleImageIndex = cursor.getColumnIndex(COLUMN_TITLE_IMAGE)

            while (cursor.moveToNext()) {
                val id = cursor.getInt(idIndex)
                val make = cursor.getString(makeIndex)
                val model = cursor.getString(modelIndex)
                val year = cursor.getInt(yearIndex)
                val horsePower = cursor.getInt(horsePowerIndex)
                val description = cursor.getString(descriptionIndex)
                val titleImage = cursor.getInt(titleImageIndex)

                val car = Car(id, make, model, year, horsePower, description, titleImage)
                carList.add(car)
            }
        }

        return carList
    }

    fun getCarById(id: Int): Car? {
        val db = this.readableDatabase

        val projection = arrayOf(
            COLUMN_ID,
            COLUMN_MAKE,
            COLUMN_MODEL,
            COLUMN_YEAR,
            COLUMN_HORSE_POWER,
            COLUMN_DESCRIPTION,
            COLUMN_TITLE_IMAGE
        )

        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(id.toString())

        val cursor = db.query(
            TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        cursor.use { cursor ->
            if (cursor.moveToFirst()) {
                val idIndex = cursor.getColumnIndex(COLUMN_ID)
                val makeIndex = cursor.getColumnIndex(COLUMN_MAKE)
                val modelIndex = cursor.getColumnIndex(COLUMN_MODEL)
                val yearIndex = cursor.getColumnIndex(COLUMN_YEAR)
                val horsePowerIndex = cursor.getColumnIndex(COLUMN_HORSE_POWER)
                val descriptionIndex = cursor.getColumnIndex(COLUMN_DESCRIPTION)
                val titleImageIndex = cursor.getColumnIndex(COLUMN_TITLE_IMAGE)

                if (idIndex >= 0 && makeIndex >= 0 && modelIndex >= 0 && yearIndex >= 0 &&
                    horsePowerIndex >= 0 && descriptionIndex >= 0 && titleImageIndex >= 0
                ) {

                    val make = cursor.getString(makeIndex)
                    val model = cursor.getString(modelIndex)
                    val year = cursor.getInt(yearIndex)
                    val horsePower = cursor.getInt(horsePowerIndex)
                    val description = cursor.getString(descriptionIndex)
                    val titleImage = cursor.getInt(titleImageIndex)

                    return Car(id, make, model, year, horsePower, description, titleImage)
                }
            }
            return null
        }
    }


}