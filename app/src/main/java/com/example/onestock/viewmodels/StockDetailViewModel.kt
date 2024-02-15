package com.example.onestock.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onestock.common.NoteSerialization
import com.example.onestock.models.BalanceSheet
import com.example.onestock.models.Note
import com.example.onestock.models.Quote
import com.example.onestock.models.Stock
import com.example.onestock.repositories.DataRepository
import com.example.onestock.repositories.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StockDetailViewModel(
    private val dataRepository: DataRepository,
    private val stockRepository: StockRepository, private val symbol: String = ""
) : ViewModel() {
    val balanceSheetData: LiveData<BalanceSheet> = dataRepository.balanceSheetData
    val quoteData: LiveData<Quote> = dataRepository.quoteData
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes

    var stock by mutableStateOf(Stock())
        private set

    init {
        viewModelScope.launch {
          loadStockAndNotes()
        }
    }

    private suspend fun loadStockAndNotes() {
        if (symbol.isNotEmpty()) {
            stockRepository.getStockBySymbol(symbol).collect { stock ->
                this.stock = stock ?: Stock()
                _notes.value = NoteSerialization.deserializeNotes(stock?.note ?: "")
            }
        }
    }

    fun getBalanceSheetInfo(txt: String) {
        dataRepository.fetchBalanceSheet(txt)
    }

    fun getQuoteInfo(txt: String) {
        return dataRepository.fetchQuote(txt)
    }

    suspend fun saveStock(symbol: String) {
        var newStock = Stock(symbol, "", "")
        stockRepository.addStock(newStock);
        stock = newStock
    }

    private suspend fun updateStock(stock: Stock) {
        stockRepository.updateStock(stock);
    }

    suspend fun addNewNote(text: String){
        val currentNotes = try {
            NoteSerialization.deserializeNotes(stock.note)
        } catch (e: Exception) {
            emptyList<Note>()
        }
        val nextId = (currentNotes.maxByOrNull { it.id }?.id ?: 0L) + 1
        val newNote = Note(id= nextId, text = text, timestamp = System.currentTimeMillis())
        val updatedNotes = currentNotes + newNote
        val updatedNotesJson = NoteSerialization.serializeNotes(updatedNotes)
        val updatedStock = stock.copy(note = updatedNotesJson)
        updateStock(updatedStock)
    }

    suspend fun deleteNote(noteId: Long){
        val currentNotes = try {
            NoteSerialization.deserializeNotes(stock.note)
        } catch (e: Exception) {
            emptyList<Note>()
        }
        val updatedNotes = currentNotes.filter { it.id != noteId }
        val updatedNotesJson = NoteSerialization.serializeNotes(updatedNotes)
        val updatedStock = stock.copy(note = updatedNotesJson)
        updateStock(updatedStock)
    }


    fun deleteStock(symbol: String) {

    }

    fun getStock(symbol: String): Flow<Stock?> {
        return stockRepository.getStockBySymbol(symbol)
    }

}