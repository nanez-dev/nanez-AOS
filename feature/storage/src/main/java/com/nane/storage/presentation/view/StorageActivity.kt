package com.nane.storage.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.nane.storage.R

class StorageActivity : AppCompatActivity() {
    private lateinit var wishlistFragment: WishlistFragment
    private lateinit var havinglistFragment: HavinglistFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.storage_activity)

        wishlistFragment = WishlistFragment()
        havinglistFragment = HavinglistFragment()

        supportFragmentManager.beginTransaction().replace(R.id.container, wishlistFragment).commit()

        val tabs = findViewById<TabLayout>(R.id.tabs)
        tabs.addTab(tabs.newTab().setText("위시리스트"))
        tabs.addTab(tabs.newTab().setText("보유리스트"))

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position

                var selected: Fragment = Fragment()
                when (position) {
                    0 -> selected = wishlistFragment
                    1 -> selected = havinglistFragment
                }

                supportFragmentManager.beginTransaction().replace(R.id.container, selected).commit()

            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

    }
}