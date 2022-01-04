package com.example.mrshopercapstone.view.adaptersimport

import androidx.recyclerview.widget.RecyclerView
import com.example.mrshopercapstone.view.main.CartViewModel


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.mrshopercapstone.R
import com.example.mrshopercapstone.models.items.CartModel
import com.squareup.picasso.Picasso



class CartAdapter(var viewModel: CartViewModel) :

    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CartModel>(){
        override fun areItemsTheSame(oldItem: CartModel, newItem: CartModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CartModel, newItem: CartModel): Boolean {
            return oldItem == newItem
        }

    }


    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.CartViewHolder {
        return CartViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cart_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = differ.currentList[position]

        holder.titleCartTextView.text = item.title
        holder.qunitityTextview.text = item.count.toString()
        Picasso.get().load(item.image).into(holder.cartImageView)
        ////////////////////////////////////////////
        var countermiun = item.count
        holder.minusToggleButton.setOnClickListener {
            if (item.count >0 ){
        holder.priceCartTextView.text = "${item.price} SAR"
         countermiun--
         holder.qunitityTextview.text = countermiun.toString()
        }
            item.count = countermiun
        viewModel.editMyCart(item)
        }
        //////////////////////////////////////////
        var counter = item.count
        holder.plusToggleButton.setOnClickListener {
        counter++
        holder.qunitityTextview.text = counter.toString()
        item.count = counter
        viewModel.editMyCart(item)
        }
        //////////////////////////////////////////////////
        holder.deleteToggleButton.setOnClickListener {
            var list = mutableListOf<CartModel>()
            list.addAll(differ.currentList)
            list.remove(item)
            differ.submitList(list.toList())

            viewModel.deleteMyCart(item)
        }
    }



    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun sumbitList(list: List<CartModel>){
        differ.submitList(list)
    }
    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleCartTextView: TextView = itemView.findViewById(R.id.cartItemTextView)
        val priceCartTextView: TextView = itemView.findViewById(R.id.qunitiy_TextView)
        val cartImageView: ImageView = itemView.findViewById(R.id.cartImage)
        val minusToggleButton: ImageButton = itemView.findViewById(R.id.minus_imageButton)
        val plusToggleButton: ImageButton = itemView.findViewById(R.id.plus_imageButton)
        val qunitityTextview: TextView = itemView.findViewById(R.id.quntitycart_textView)
        val deleteToggleButton: ImageButton = itemView.findViewById(R.id.delete_image_button)
    }
}