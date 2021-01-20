package id.ac.amikom.filmindonesia

import androidx.room.*


@Dao
interface FilmDao {
    @Insert
    fun insert(film: Film)

    @Update
    fun update(film: Film)

    @Delete
    fun delete(film: Film)

    @Query("SELECT * FROM film")
    fun selectALL(): List<Film>

    @Query("SELECT * FROM film WHERE id=:id")
    fun select(id: Int): Film

}