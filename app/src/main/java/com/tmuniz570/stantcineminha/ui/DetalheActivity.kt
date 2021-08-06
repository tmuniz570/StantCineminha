package com.tmuniz570.stantcineminha.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.tmuniz570.stantcineminha.Adapter.Companion.selectFilme
import com.tmuniz570.stantcineminha.databinding.ActivityDetalheBinding
import com.tmuniz570.stantcineminha.favotite.dao.FilmesDao
import com.tmuniz570.stantcineminha.favotite.database.FilmesDatabase
import com.tmuniz570.stantcineminha.favotite.model.FilmesEntity
import java.io.File

class DetalheActivity : AppCompatActivity() {

    private lateinit var dao: FilmesDao
    private lateinit var binding: ActivityDetalheBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalheBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dao = FilmesDatabase.getInstance(this).filmesDao()

        binding.tvNome.text = selectFilme.title
        binding.tvOverview.text = selectFilme.overview
        binding.tvGenero.text = selectFilme.generos
        binding.tvLancamento.text = selectFilme.release_date
        binding.tvLangOrigem.text = "Lang.: "+ selectFilme.original_language
        val url = "https://image.tmdb.org/t/p/original${selectFilme.poster_path}"
        Glide.with(this).load(url).into(binding.ivPoster)

        if (selectFilme.favorito) binding.ivFavotitoYes.visibility = View.VISIBLE
        else binding.ivFavotitoYes.visibility = View.GONE

        binding.ivFavotitoNo.setOnClickListener {
            dao.insert(FilmesEntity(
                selectFilme.id,
                selectFilme.original_language,
                selectFilme.overview,
                selectFilme.poster_path,
                selectFilme.release_date,
                selectFilme.title,
                selectFilme.generos
            ))
            selectFilme.favorito = true
            binding.ivFavotitoYes.visibility = View.VISIBLE
            binding.ivFavotitoNo.visibility = View.GONE
        }

        binding.ivFavotitoYes.setOnClickListener {
            dao.delete(FilmesEntity(
                selectFilme.id,
                selectFilme.original_language,
                selectFilme.overview,
                selectFilme.poster_path,
                selectFilme.release_date,
                selectFilme.title,
                selectFilme.generos
            ))
            selectFilme.favorito = false
            binding.ivFavotitoNo.visibility = View.VISIBLE
            binding.ivFavotitoYes.visibility = View.GONE
        }

        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }
}