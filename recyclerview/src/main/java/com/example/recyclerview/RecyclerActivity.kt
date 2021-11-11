package com.example.recyclerview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recycler.*
import kotlinx.android.synthetic.main.activity_recycler_item_earth.view.*
import kotlinx.android.synthetic.main.activity_recycler_item_mars.view.*

class RecyclerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        val data = arrayListOf(
            Data("Mars","")
        )
        data.add(0, Data("Header"))

        val adapter = RecyclerActivityAdapter(
            object : RecyclerActivityAdapter.OnListItemClickListener {
                override fun onItemClick(data: Data) {
                    Toast.makeText(this@RecyclerActivity, data.someText,Toast.LENGTH_SHORT).show()
                }
            },
            data
        )
        recyclerView.adapter = adapter
        recyclerActivityFAB.setOnClickListener { adapter.appendItem() }
    }

    class RecyclerActivityAdapter(
        private var onListItemClickListener: OnListItemClickListener,
        private var data: MutableList<Data>
    ) : RecyclerView.Adapter<BaseViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
                BaseViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return when (viewType) {
//                TYPE_EARTH -> EarthViewHolder(
//                    inflater.inflate(R.layout.activity_recycler_item_earth, parent,false
//                    ) as View
//                )
                TYPE_MARS ->
                    MarsViewHolder(
                        inflater.inflate(R.layout.activity_recycler_item_mars,parent, false
                        ) as View
                    )
                else -> HeaderViewHolder(
                    inflater.inflate(R.layout.activity_recycler_item_header, parent,
                        false) as View
                )
            }
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
            holder.bind(data[position])

        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun getItemViewType(position: Int): Int {
            return when {
                position == 0 -> TYPE_HEADER
                data[position].someDescription.isNullOrBlank() -> TYPE_MARS
                else -> TYPE_EARTH
            }
        }

        @SuppressLint("NotifyDataSetChanged")
        fun appendItem() {
            data.add(generateItem())
            notifyItemInserted(itemCount - 1)

        }

        private fun generateItem() = Data("Mars", "")

//        inner class EarthViewHolder(view: View) : BaseViewHolder(view) {

//            override fun bind(data: Data) {
//                if (layoutPosition != RecyclerView.NO_POSITION) {
//                    itemView.descriptionTextView.text = data.someDescription
//                    itemView.wikiImageView.setOnClickListener {
//                        onListItemClickListener.onItemClick(data) }
//                }
//            }
//        }

        inner class MarsViewHolder(view: View) : BaseViewHolder(view) {
            override fun bind(data: Data) {
                itemView.marsImageView.setOnClickListener {
                    onListItemClickListener.onItemClick(data)
                }
                itemView.addItemImageView.setOnClickListener { addItem() }
                itemView.removeItemImageView.setOnClickListener { removeItem() }
            }

            @SuppressLint("NotifyDataSetChanged")
            private fun addItem() {
                data.add(layoutPosition, generateItem())
                notifyItemInserted(layoutPosition)

            }

            @SuppressLint("NotifyDataSetChanged")
            private fun removeItem() {
                data.removeAt(layoutPosition)
                notifyItemRemoved(layoutPosition)

            }
        }


                inner class HeaderViewHolder(view: View) : BaseViewHolder(view) {
            override fun bind(data: Data) {
                itemView.setOnClickListener {
                    onListItemClickListener.onItemClick(data) }
            }
        }

        interface OnListItemClickListener {
            fun onItemClick(data: Data)
        }

        companion object {
            private const val TYPE_EARTH = 0
            private const val TYPE_MARS = 1
            private const val TYPE_HEADER = 2

        }
    }

    abstract class BaseViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        abstract fun bind(data: Data)
    }

}




