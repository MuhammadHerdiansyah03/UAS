package id.ac.amikom.filmindonesia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_film.view.*

class FilmAdapter(var list: List<Film>, val sharedPref: SharedPref) : RecyclerView.Adapter<FilmAdapter.ViewHolder>() {
    var onItemClickListener: ((Film) -> Unit)? = null

    class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(film: Film){
            with(itemView){
                Glide.with(this).load(film.gambar).into(itemImage)
                itemNama.text = film.nama
                itemGenre.text = film.genre
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_film,parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film = list.get(position)
        holder.bind(film)
        holder.itemView.setOnClickListener{
            onItemClickListener?.invoke(film)
        }
        holder.itemView.itemGenre.visibility = if (sharedPref.latin) View.VISIBLE else View.GONE
    }

    override fun getItemCount(): Int {
        return list.size
    }
}