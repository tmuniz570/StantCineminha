package com.tmuniz570.stantcineminha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.tmuniz570.stantcineminha.databinding.ActivityDetalheBinding
import com.tmuniz570.stantcineminha.model.Filmes

class DetalheActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalheBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalheBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvNome.text = Adapter.selectFilme.title
        binding.tvOverview.text = Adapter.selectFilme.overview
        binding.tvGenero.text = Adapter.selectFilme.genre_ids.toString()
        binding.tvLancamento.text = Adapter.selectFilme.release_date
        binding.tvLangOrigem.text = Adapter.selectFilme.original_language

        val url = "https://image.tmdb.org/t/p/original${Adapter.selectFilme.poster_path}"
        Glide.with(this).load(url).into(binding.ivPoster)

        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }
}