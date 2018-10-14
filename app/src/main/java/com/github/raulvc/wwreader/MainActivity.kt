package com.github.raulvc.wwreader

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.github.raulvc.wwreader.dashboard.HomeFragment
import com.github.raulvc.wwreader.dashboard.NovelsFragment
import com.github.raulvc.wwreader.dashboard.PreferencesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currentFragment : Fragment? = null

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceFragment(HomeFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_novels -> {
                replaceFragment(NovelsFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_preferences -> {
                replaceFragment(PreferencesFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putString("lastActivity", MainActivity::class.simpleName)
        outState.putString("lastFragment", this.currentFragment!!::class.simpleName)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            val lastActivity = savedInstanceState.getString("last_activity")
            val lastFragment = savedInstanceState.getString("last_fragment")
        } else {
            setContentView(R.layout.activity_main)
            addFragment(PreferencesFragment.newInstance())
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun replaceFragment(newFragment: Fragment) {
        // same fragment
        if (this.currentFragment == newFragment) {
            return
        }

        supportFragmentManager.inTransaction {
            replace(R.id.root_layout, newFragment)
        }
        this.currentFragment = newFragment
    }

    private fun addFragment(newFragment: Fragment) {
        supportFragmentManager.inTransaction {
            add(R.id.root_layout, newFragment)
        }
        this.currentFragment = newFragment
    }

}
