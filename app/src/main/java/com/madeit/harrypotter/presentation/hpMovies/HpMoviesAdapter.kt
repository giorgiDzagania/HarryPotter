package com.madeit.harrypotter.presentation.hpMovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.madeit.harrypotter.data.model.Movies
import com.madeit.harrypotter.databinding.ItemAllMoviesBinding

class HpMoviesAdapter : RecyclerView.Adapter<HpMoviesAdapter.HpMoviesViewHolder>() {

    private var hpMovies = listOf<Movies>()

    var onDetailBtnClick: (movie: Movies) -> Unit = {}

    fun updateMoviesList(newHarryPotter: List<Movies>) {
        val movieCallBack = HpMoviesCallBack(hpMovies, newHarryPotter)
        val diffResult = DiffUtil.calculateDiff(movieCallBack)
        hpMovies = newHarryPotter
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HpMoviesViewHolder {
        return HpMoviesViewHolder(
            ItemAllMoviesBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HpMoviesViewHolder, position: Int) {
        holder.bind(hpMovies[position])
    }

    override fun getItemCount(): Int = hpMovies.size

    class HpMoviesCallBack(
        private val oldList: List<Movies>,
        private val newList: List<Movies>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oList = oldList[oldItemPosition]
            val nList = newList[newItemPosition]
            return oList.id == nList.id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oList = oldList[oldItemPosition]
            val nList = newList[newItemPosition]
            return oList == nList
        }
    }

    inner class HpMoviesViewHolder(private val binding: ItemAllMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieData: Movies) = with(binding) {
            val movie = movieData.attributes
            Glide.with(movieCoverImage)
                .load(movie?.poster)
                .into(movieCoverImage)

            movieTitle.text = movie?.title
            movieRating.text = movie?.rating.toString()
            movieReleaseDate.text = movie?.releaseDate

            movieBtnDetails.setOnClickListener {
                onDetailBtnClick.invoke(movieData)
            }

        }
    }
}