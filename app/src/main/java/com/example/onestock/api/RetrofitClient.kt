import com.example.onestock.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private fun createRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val fmpApiService: ApiService.FMPApi by lazy {
        createRetrofit("https://financialmodelingprep.com/api/v3/").create(ApiService.FMPApi::class.java)
    }

    val marketauxApiService: ApiService.MarketauxApi by lazy {
        createRetrofit("https://api.marketaux.com/v1/").create(ApiService.MarketauxApi::class.java)
    }
}
