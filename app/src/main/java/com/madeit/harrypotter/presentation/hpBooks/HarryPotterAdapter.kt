package com.madeit.harrypotter.presentation.hpBooks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.madeit.harrypotter.data.model.BookData
import com.madeit.harrypotter.databinding.ItemAllBooksBinding
import java.text.SimpleDateFormat
import java.util.Locale

class HarryPotterAdapter : RecyclerView.Adapter<HarryPotterAdapter.HarryPotterViewHolder>() {

    private var books: List<BookData> = emptyList()

    var onItemClick: (currentBook: BookData) -> Unit = {}

    fun updateBooksList(newBooksList: List<BookData>) {
        val bookCallBack = BookCallBack(books, newBooksList)
        val diffResult = DiffUtil.calculateDiff(bookCallBack)
        books = newBooksList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HarryPotterViewHolder {
        return HarryPotterViewHolder(
            ItemAllBooksBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HarryPotterViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size

    class BookCallBack(
        private val oldList: List<BookData>,
        private val newList: List<BookData>
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

    inner class HarryPotterViewHolder(private val binding: ItemAllBooksBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bookData: BookData) = with(binding) {
            val book = bookData.attributes
            Glide.with(bookImageView)
                .load(book?.coverUrl)
                .into(bookImageView)

            titleView.text = book?.title
            bookAuthorTv.text = book?.author
            releaseDateTv.text = book?.releaseDate?.let {
                try {
                    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                    val date = inputFormat.parse(it)
                    outputFormat.format(date)
                } catch (e: Exception) {
                    "Unknown Date"
                }
            } ?: "Unknown Date"

            binding.root.setOnClickListener {
                onItemClick.invoke(bookData)
            }

        }
    }

}