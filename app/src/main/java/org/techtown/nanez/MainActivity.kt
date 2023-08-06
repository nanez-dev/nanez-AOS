package org.techtown.nanez

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabs = findViewById<TabLayout>(R.id.main_tab)
        tabs.setBackgroundColor(Color.parseColor("#EDEDED"))

        val pager = findViewById<ViewPager2>(R.id.main_pager)
        pager.adapter = MainPagerAdapter(this as FragmentActivity, 3)

        val homeTab = tabs.newTab()
        homeTab.customView = createTabView(R.drawable.bar_home, 30,30)
        tabs.addTab(homeTab)

        val listTab = tabs.newTab()
        listTab.customView = createTabView(R.drawable.bar_heart, 30,30)
        tabs.addTab(listTab)

        val myPageTab = tabs.newTab()
        myPageTab.customView = createTabView(R.drawable.bar_mypage, 30,30)
        tabs.addTab(myPageTab)




        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                pager.setCurrentItem(tab!!.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun createTabView(drawableRes: Int, widthDp: Int, heightDp: Int): View {
        val drawable = ContextCompat.getDrawable(this, drawableRes)
        val imageView = ImageView(this)
        imageView.setImageResource(drawableRes)

        val widthPx = dpToPx(this, widthDp.toFloat())
        val heightPx = dpToPx(this, heightDp.toFloat())

        val params = LinearLayout.LayoutParams(widthPx, heightPx)
        imageView.layoutParams = params

        return imageView
    }

    private fun dpToPx(context: Context, dp: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }
}

class MainPagerAdapter(
    fragmentActivity: FragmentActivity,
    val tabCount: Int,
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return tabCount
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            1 -> return FeedFragment()
            0 -> {
                return PostFragment()
            }
            else -> return ProfileFragment()
        }
    }
}