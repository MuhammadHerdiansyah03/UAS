package id.ac.amikom.filmindonesia

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Film (
        @PrimaryKey(autoGenerate = true)
        var id:Int = 0,
        var nama: String = "",
        var sutradara: String = "",
        var produser: String = "",
        var pemain: String = "",
        var sinopsis: String = "",
        var genre: String = "",
        var tanggalliris : String = "",
        var gambar: String = "",
        var link:String = "",
    ) : Parcelable
