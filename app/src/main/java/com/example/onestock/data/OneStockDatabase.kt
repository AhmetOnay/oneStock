package com.example.onestock.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.onestock.models.Stock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        Stock::class,
    ],
    version = 2,
    exportSchema = false
)

abstract class OneStockDatabase : RoomDatabase() {
    abstract fun stockDao(): StockDao


    companion object{
        @Volatile
        private var Instance: OneStockDatabase? = null

        fun getDatabase(context: Context): OneStockDatabase {
            return Instance ?: synchronized(this) {
                val database = Room.databaseBuilder(context, OneStockDatabase::class.java, "mobira_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(object : Callback() {
                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                /*val seedDatabaseWorker = SeedDatabaseWorker()
                                seedDatabaseWorker.seedDatabase(getDatabase(context), context)*/
                            }
                        }
                    })
                    .build()
                Instance = database
                database
            }
        }
    }
}
