package com.uzlahalya.beosis4.mvvm.database

import android.content.Context
import androidx.room.*

@Database(
    entities = [ScholarshipEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(ScholarshipTypeConverter::class)
abstract class ScholarshipDatabase: RoomDatabase() {

    abstract fun scholarshipDao(): ScholarshipDao

    companion object{
        @Volatile
        private var instance: ScholarshipDatabase? = null

        fun getDatabase(context: Context): ScholarshipDatabase {
            val tempInstance = instance
            if (tempInstance != null){
                instance = tempInstance
            }
            synchronized(this){
                val newInstance = Room.databaseBuilder(
                    context.applicationContext, ScholarshipDatabase::class.java,
                    "scholarship_database"
                    ).build()

                instance = newInstance
                return  newInstance
            }
        }
    }
}