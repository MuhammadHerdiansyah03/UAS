package id.ac.amikom.filmindonesia

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    lateinit var filmDao: FilmDao
    var film:Film?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        filmDao = AppDataBase.getInstance(this).filmDao()
        film = intent.getParcelableExtra<Film>(MainActivity.KEY_FILM)
        populate(film)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

    }

    fun populate(film:Film?){
        film?.apply {
            Glide.with(this@DetailActivity).load(gambar).into(detailimage)
            detailName.text = nama
            detailSutradara.text = sutradara
            detailProduser.text = produser
            detailPemain.text = pemain
            detailSinopsis.text = sinopsis
            detailGenre.text = genre
            detailTanggalliris.text = tanggalliris
            detailLink.text = link
        }
        supportActionBar?.apply {
            title = film?.nama
            subtitle = film?.genre
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuEdit ->{
                val intent = Intent(this, AddEditActivity::class.java)
                intent.putExtra(MainActivity.KEY_FILM, film)
                startActivityForResult(intent, MainActivity.REQUEST_EDIT)
            }

            R.id.menuRemove -> {
                AlertDialog.Builder(this)
                    .setMessage("apakah anda yakin menghapus ini?")
                    .setPositiveButton("ya") { _, _ ->
                        film?.apply {
                            filmDao.delete(this)
                        }
                        Toast.makeText(this , "Data berhasil di hapus", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .setNegativeButton("Tidak", null)
                    .show()
            }
            else ->{
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MainActivity.REQUEST_EDIT &&resultCode == Activity.RESULT_OK && data != null){
            film = data.getParcelableExtra(MainActivity.KEY_FILM)
            populate(film)
            film?.apply {
                filmDao.update(this )
            }
            Toast.makeText(this , "Data berhasil di edit", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
