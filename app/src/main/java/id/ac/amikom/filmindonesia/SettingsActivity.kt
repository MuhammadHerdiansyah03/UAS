package id.ac.amikom.filmindonesia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportActionBar?.apply {
            title = "Settings"
            setDisplayHomeAsUpEnabled(true)
        }
        val sharedPref = SharedPref(this)
        settingGrid.isChecked = sharedPref.gridLayout
        settingGrid.setOnCheckedChangeListener { buttonView, isChecked ->
            sharedPref.gridLayout = isChecked
        }

        settingGenre.isChecked = sharedPref.latin
        settingGrid.setOnCheckedChangeListener { buttonView, isChecked ->
            sharedPref.latin =isChecked
        }

        settingAppName.setText(sharedPref.appName)
        settingAppName.addTextChangedListener {
            sharedPref.appName = it.toString()

        }


        settingColumn.setText(sharedPref.column.toString())
        settingColumn.addTextChangedListener {
            var cols = if(it.toString().length==0)1 else it.toString().toInt()
            cols = if (cols > 3){
                3
            } else if (cols < 1){
                1
            }else{
                cols
            }
            sharedPref.column = cols
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}