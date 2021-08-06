package com.tmuniz570.stantcineminha.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmuniz570.stantcineminha.Adapter
import com.tmuniz570.stantcineminha.DetalheActivity
import com.tmuniz570.stantcineminha.databinding.FragmentFavoritosBinding
import com.tmuniz570.stantcineminha.model.Filmes

class FavoritosFragment : Fragment(), Adapter.OnClickListener {

    private lateinit var lista : Filmes
    private val adapter by lazy { Adapter(lista, this) }

    private var _binding: FragmentFavoritosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoritosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        lista = Filmes(emptyList())

        if (!lista.results.isNullOrEmpty()){
            binding.tvListaVazia.visibility = View.GONE
            setup()
            adapter.get(lista)
        }else{
            binding.tvListaVazia.visibility = View.VISIBLE
        }

        return root
    }

    private fun setup() {
        binding.rvFavoritos.layoutManager = LinearLayoutManager(context)
        binding.rvFavoritos.setHasFixedSize(true)
        binding.rvFavoritos.adapter = adapter
    }

    override fun onItemClick(item: Filmes, position: Int) {
        super.onItemClick(item, position)
        startActivity(Intent(context, DetalheActivity::class.java))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
