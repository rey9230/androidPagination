package cu.nico.infinity.androidpagination

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cu.nico.infinity.androidpagination.ui.passangers.PassengersFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        openHomeFragment()
    }

    private fun openHomeFragment(){
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, PassengersFragment.newInstance())
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }
}
