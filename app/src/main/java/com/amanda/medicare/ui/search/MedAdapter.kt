package com.amanda.medicare.ui.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amanda.medicare.data.response.DataItem
import com.amanda.medicare.databinding.ItemMedicineBinding
import com.amanda.medicare.ui.detail.DetailActivity
import com.squareup.picasso.Picasso

class MedAdapter : ListAdapter<DataItem, MedAdapter.MedicineViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val binding = ItemMedicineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MedicineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        val medicineData = getItem(position)
        holder.bind(medicineData)
    }

    class MedicineViewHolder(val binding: ItemMedicineBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(medicineData: DataItem) {
            binding.tvMedicineName.text = medicineData.nama
            binding.tvMedicineDesc.text = medicineData.deskripsi

            // Use the Picasso library to display an image from the URL
            Picasso.get().load(medicineData.linkGambar).into(binding.ivItemMedicine)

            binding.root.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("keyword", medicineData.nama)
                intent.putExtra(DetailActivity.EXTRA_DESC, medicineData.deskripsi)
                intent.putExtra(DetailActivity.EXTRA_URL, medicineData.linkGambar)
                itemView.context.startActivity(intent)
            }
        }
    }


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}