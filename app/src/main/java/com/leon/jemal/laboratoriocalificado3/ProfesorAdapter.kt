package com.leon.jemal.laboratoriocalificado3

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leon.jemal.laboratoriocalificado3.databinding.ItemProfesorBinding

class ProfesorAdapter(
    var list: List<ProfesorResponse>
) : RecyclerView.Adapter<ProfesorAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemProfesorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val profesor = list[adapterPosition]
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${profesor.phone_number}")
                itemView.context.startActivity(intent)
            }

            binding.root.setOnLongClickListener {
                val profesor = list[adapterPosition]
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:${profesor.email}")
                    putExtra(Intent.EXTRA_SUBJECT, "Consulta")
                }
                itemView.context.startActivity(intent)
                true
            }
        }

        fun bind(profesor: ProfesorResponse) {
            with(binding) {
                tvProfesornombre.text = profesor.name
                tvProfesorapellido.text = profesor.last_name
                Glide.with(itemView)
                    .load(profesor.image_url)
                    .into(rvProfesores)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProfesorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
