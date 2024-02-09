package com.example.onestock.repositories

import com.example.onestock.data.StockDao
import com.example.onestock.models.Stock

class StockRepository(private val stockDao: StockDao) {

    suspend fun addStock(stock: Stock) = stockDao.add(stock)

    suspend fun deleteStock(stock: Stock) = stockDao.delete(stock)

    suspend fun updateStock(stock: Stock) = stockDao.update(stock)

    suspend fun deleteAllStocks() = stockDao.deleteAll()

    fun getAllStocks() = stockDao.getAllStocks()

    fun getStockBySymbol(symbol: String) = stockDao.getStockBySymbol(symbol)

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: StockRepository? = null

        fun getInstance(dao: StockDao) =
            instance ?: synchronized(this) {
                instance ?: StockRepository(dao).also { instance = it }
            }
    }
}
