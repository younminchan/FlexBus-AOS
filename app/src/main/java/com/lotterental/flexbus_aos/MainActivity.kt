package com.lotterental.flexbus_aos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.lotterental.flexbus_aos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var m_fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        m_fragmentManager = supportFragmentManager

        initFragment()
    }

    private fun initFragment() {
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_main, MainFragment())
        fragmentTransaction.commit()
    }

    fun moveFragment(fActivity: FragmentActivity, fragment: Fragment, backStack: Boolean) {
        var tag = fragment.javaClass.simpleName
        var fragmentTransaction = fActivity.supportFragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragment_main, fragment)

        if (backStack) {
            fragmentTransaction.addToBackStack(tag)
        }

        fragmentTransaction.commit()
    }


    companion object {
        @Volatile private var instance: MainActivity? = null

        @JvmStatic fun getInstance(): MainActivity =
            instance ?: synchronized(this) {
                instance ?: MainActivity().also {
                    instance = it
                }
            }
    }
}