package id.ac.amikom.filmindonesia

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_add_edit.*
import java.net.URL

class AddEditActivity : AppCompatActivity() {
    var film: Film? = null
    companion object{
        const val REQUEST_IMAGE = 400

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)
        film = intent.getParcelableExtra(MainActivity.KEY_FILM)
        supportActionBar?.apply{
            title= if (film == null)"Add Film" else "Edit film"
            film?.apply {
                subtitle = nama
            }
            setDisplayHomeAsUpEnabled(true)
        }

        film?.apply{
            addEditNama.setText(nama)
            addEditGenre.setText(genre)
            addEditSipnopsis.setText(sinopsis)
            addEditLink.setText(link)
            addEditGambar.setText(gambar)

        }
        buttonBrowse.setOnClickListener {
            val intent = Intent()
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), REQUEST_IMAGE)
        }
        buttonSave.setOnClickListener {
            if(film == null) film = Film()
            film?.apply {
                nama = addEditNama.text.toString()
                genre = addEditGenre.text.toString()
                sinopsis = addEditSipnopsis.text.toString()
                link = addEditLink.text.toString()
                gambar = addEditGambar.text.toString()
            }
            val intent = Intent()
            intent.putExtra(MainActivity.KEY_FILM,film)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE && resultCode == Activity.RESULT_OK && data != null){
            val imageUrl = data.data?.toString()
            addEditGambar.setText(imageUrl)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onContextItemSelected(item)
    }
}