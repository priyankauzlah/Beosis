package com.uzlahalya.beosis4.mvvm.database

import android.content.Context
import androidx.room.*

@Database(
    entities = [ScholarshipEntity::class],
    version = 1,
    exportSchema = false
)

abstract class ScholarshipDatabase: RoomDatabase() {

    abstract fun scholarshipDao(): ScholarshipDao

    companion object {
        const val DATABASE_NAME = "scholarship_database"

        @Volatile
        private var instance: ScholarshipDatabase? = null

        fun getDatabase(context: Context): ScholarshipDatabase {
            val tempInstance = instance
            if (tempInstance != null) {
                instance = tempInstance
            }
            synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext, ScholarshipDatabase::class.java, DATABASE_NAME
                ).build()

                instance = newInstance
                return  newInstance
            }
        }
    }
}