package com.demo.countriesdemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.demo.countriesdemo.databinding.ItemLayoutBinding
import com.demo.countriesdemo.model.countries.CountriesResponseItem
import com.demo.countriesdemo.model.countries.CustomCountriesResponseItem
import kotlin.collections.get

class CountriesAdapter : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    // DiffUtil for efficient updates
    private val diffCallback = object : DiffUtil.ItemCallback<CustomCountriesResponseItem>() {
        override fun areItemsTheSame(oldItem: CustomCountriesResponseItem, newItem: CustomCountriesResponseItem): Boolean =
            oldItem.longStableId == newItem.longStableId

        override fun areContentsTheSame(oldItem: CustomCountriesResponseItem, newItem: CustomCountriesResponseItem): Boolean =
            oldItem == newItem
    }
    private val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = differ.currentList[position]

        val countryNameRegion = item.name+", "+item.region
        holder.binding.countryName.text = countryNameRegion

        holder.binding.countryCode.text =  item.code+" |"

        holder.binding.countryCapital.text  = item.capital

    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun getItemId(position: Int): Long = differ.currentList[position].longStableId // as setHasStableIds set to true...

    // Update data
    fun submitList(newItems: List<CustomCountriesResponseItem>, isNewPage: Boolean = false) {
        differ.submitList(
            if (isNewPage) differ.currentList + newItems else newItems
        )
    }


}
