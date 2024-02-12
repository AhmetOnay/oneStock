package com.example.onestock.data

import androidx.room.*
import com.example.onestock.models.Stock
import kotlinx.coroutines.flow.Flow

@Dao
interface StockDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(stock: Stock)

    @Update
    suspend fun update(stock: Stock)

    @Delete
    suspend fun delete(stock: Stock)

    @Query("DELETE FROM Stock")
    suspend fun deleteAll()

    @Query("SELECT * FROM Stock")
    fun getAllStocks(): Flow<List<Stock>>

    @Query("SELECT symbol FROM Stock")
    fun getAllStocksSymbols(): Flow<List<String>>

    @Query("SELECT * FROM Stock WHERE symbol=:symbol")
    fun getStockBySymbol(symbol: String): Flow<Stock?>


}