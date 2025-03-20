package com.efkan.footballapp.viewmodal

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.efkan.footballapp.model.Footballer
import com.efkan.footballapp.service.FootballerAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FootballerViewModal : ViewModel() {
    private val BASE_URL = "https://www.thesportsdb.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FootballerAPI::class.java)

    val footballerList = mutableStateOf<List<Footballer>>(listOf())

    fun getFootballer(playerName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = retrofit.getData(playerName) // API'yi çağır
                footballerList.value = result.player // State güncellemesi
            } catch (e: Exception) {
                println("Hata oluştu: ${e.message}")
            }
        }
    }
    fun getFootballerByName(playerName: String): Footballer? {
        return footballerList.value.firstOrNull { it.strPlayer.replace(" ", "_") == playerName }
        //firstOrNull koşulu sağlayan ilk değeri döndürür.
    }
}
