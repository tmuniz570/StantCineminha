package com.tmuniz570.stantcineminha

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tmuniz570.stantcineminha.databinding.ActivityMainBinding
import com.tmuniz570.stantcineminha.model.Generos
import com.tmuniz570.stantcineminha.model.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Adapter.allGenres = generos()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_cartaz, R.id.navigation_busca, R.id.navigation_favoritos
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        if (applicationContext != null){
            if (!isInternetAvailable(applicationContext)){
                Toast.makeText(applicationContext, "Sem Conexão", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun generos(): Map<Int, String> {
        val callGenres = RetrofitInitializer().service().listGenres(
            "pt-BR",
            "f321a808e68611f41312aa8408531476"
        )

        val genres = mutableMapOf<Int, String>()

        callGenres.enqueue(object: Callback<Generos> {
            override fun onResponse(call: Call<Generos>?, response: Response<Generos>?) {
                response?.body()?.let {
                    it.genres.forEach {
                        genres[it.id] = it.name
                    }
                }
            }
            override fun onFailure(call: Call<Generos>?, t: Throwable?) {
                Log.d("API Get Gêneros", "FAIL !")
            }
        })
        return genres
    }

    private fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
            result = when {
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        }
        return result
    }
}