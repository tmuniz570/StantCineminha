package com.tmuniz570.stantcineminha.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmuniz570.stantcineminha.Adapter
import com.tmuniz570.stantcineminha.databinding.FragmentBuscaBinding
import com.tmuniz570.stantcineminha.extensions.text
import com.tmuniz570.stantcineminha.model.Filmes
import com.tmuniz570.stantcineminha.model.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuscaFragment : Fragment(), Adapter.OnClickListener {

    private var lista : MutableList<Filmes.Results> = ArrayList()
    private val adapter by lazy { Adapter(lista, this) }

    private var _binding: FragmentBuscaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBuscaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnBuscar.setOnClickListener {

            Toast.makeText(context, binding.tilBuscarFilme.text, Toast.LENGTH_SHORT).show()

            if (context != null){
                if (!isInternetAvailable(requireContext())){
                    Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()
                }
            }

            val callBusca = RetrofitInitializer().service().listBusca(
                "pt-BR",
                "f321a808e68611f41312aa8408531476",
                binding.tilBuscarFilme.text
            )

            callBusca.enqueue(object: Callback<Filmes> {
                override fun onResponse(call: Call<Filmes>?, response: Response<Filmes>?) {
                    response?.body()?.let {
                        val result: Filmes = it
                        lista = result.results.toMutableList()
                        setup()
                        adapter.get(lista)
                    }
                }

                override fun onFailure(call: Call<Filmes>?, t: Throwable?) {
                    Log.d("API Get Buscar", "FAIL !")
                }
            })
        }

        return root
    }

    private fun setup() {
        binding.rvBusca.layoutManager = LinearLayoutManager(context)
        binding.rvBusca.setHasFixedSize(true)
        binding.rvBusca.adapter = adapter
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}