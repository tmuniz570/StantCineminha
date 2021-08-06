package com.tmuniz570.stantcineminha

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.tmuniz570.stantcineminha.databinding.ActivityDetalheBinding

class DetalheActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalheBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalheBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvNome.text = Adapter.selectFilme.title
        binding.tvOverview.text = Adapter.selectFilme.overview
        binding.tvGenero.text = Adapter.selectFilme.generos
        binding.tvLancamento.text = Adapter.selectFilme.release_date
        binding.tvLangOrigem.text = "Lang.: "+Adapter.selectFilme.original_language

        if (Adapter.selectFilme.favorito) binding.ivFavotitoYes.visibility = View.VISIBLE
        else binding.ivFavotitoYes.visibility = View.GONE

        binding.ivFavotitoNo.setOnClickListener {
            Adapter.selectFilme.favorito = true
            binding.ivFavotitoYes.visibility = View.VISIBLE
            binding.ivFavotitoNo.visibility = View.GONE
        }

        binding.ivFavotitoYes.setOnClickListener {
            Adapter.selectFilme.favorito = false
            binding.ivFavotitoNo.visibility = View.VISIBLE
            binding.ivFavotitoYes.visibility = View.GONE
        }

        val url = "https://image.tmdb.org/t/p/original${Adapter.selectFilme.poster_path}"
        Glide.with(this).load(url).into(binding.ivPoster)

        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }
}