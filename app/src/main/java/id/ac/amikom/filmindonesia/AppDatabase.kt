package id.ac.amikom.filmindonesia

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@Database(entities = [Film::class,], version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun filmDao(): FilmDao

    companion object{
        private var instance: AppDataBase? = null

        private fun populate(db: AppDataBase){
            val filmDao = db.filmDao()
            FilmData.list.forEach {
                filmDao.insert(it)
            }

        }
        private  val callback = object :RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Executors.newSingleThreadExecutor().execute {
                    populate(instance as AppDataBase)
                }
            }
        }

        fun getInstance(context: Context) : AppDataBase{
            if(instance==null){
                instance = Room.databaseBuilder(context, AppDataBase::class.java, "appdb")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build()
            }
            return instance!!
        }
    }
}
