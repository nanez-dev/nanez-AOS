package com.nane.storage.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.nane.storage.R
import com.nane.storage.presentation.data.StorageViewType

class StorageActivity : AppCompatActivity() {
    private lateinit var wishlistFragment: WishlistFragment
    private lateinit var havinglistFragment: HavinglistFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.storage_activity)

        wishlistFragment = WishlistFragment()
        havinglistFragment = HavinglistFragment()

        switchFragmentByPosition(StorageViewType.STORAGE_WISH_POSITION)

        val tabs = findViewById<TabLayout>(R.id.tabs)
        tabs.addTab(tabs.newTab().setText(StorageViewType.STORAGE_WISH_TEXT))
        tabs.addTab(tabs.newTab().setText(StorageViewType.STORAGE_HAVING_TEXT))

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position
                position?.let { switchFragmentByPosition(it) }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun switchFragmentByPosition(position: Int) {
        val selectedFragment: Fragment = when (position) {
            StorageViewType.STORAGE_WISH_POSITION -> wishlistFragment
            StorageViewType.STORAGE_HAVING_POSITION -> havinglistFragment
            else -> Fragment()
        }

        showFragment(selectedFragment)
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}
