package id.ac.amikom.filmindonesia

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Film::class,], version = 1)
abstract class FilmDatabase : RoomDatabase(){
    abstract  fun filmDao(): FilmDao

     companion object{
         private var instance: FilmDatabase? = null
         private var callback = object: Callback(){
             override fun onCreate(db: SupportSQLiteDatabase) {
                 super.onCreate(db)

             }

         }
         private  fun populate(dp: FilmDatabase){
             val filmDao = dp.filmDao()
         }
     }
}