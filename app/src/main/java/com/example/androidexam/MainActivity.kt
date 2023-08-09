package com.example.androidexam

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.androidexam.adapters.FeaturedAdapter
import com.example.androidexam.adapters.FoodAdapter
import com.example.androidexam.databinding.ActivityMainBinding
import com.example.androidexam.models.RecyclerItem
import com.example.androidexam.viewmodels.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private var binding: ActivityMainBinding? = null
    private var carousalImages = ArrayList<Int>()
    private val mainViewModel : MainViewModel = MainViewModel()
    private var featureViewPagerRunnable: Runnable? = null
    private var featureViewPagerTwoRunnable: Runnable? = null
    private lateinit var handler: Handler
    var trackPosition = 0
    var itemList = ArrayList<RecyclerItem>()
    private var itemAdapter : FoodAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        handler = Handler(Looper.getMainLooper())
        carousalImages = mainViewModel.getImagesForCarousal()
        itemList = mainViewModel.getRecyclerItems()
        init()
    }

    private fun init(){
        setAdapterFeaturedView(carousalImages)
        setAdapter()
        binding?.searchView?.apply {
            setOnQueryTextListener(this@MainActivity)
            setOnClickListener {
                isIconified = false
            }
        }

    }

    private fun setAdapter(){
        itemAdapter = FoodAdapter()
        binding?.recyclerView?.adapter = itemAdapter
        submitList()
    }

    private fun submitList(){
        itemAdapter?.addData(itemList)
    }

    private fun setAdapterFeaturedView(featuredDataResponse: ArrayList<Int>?) {
        val featuredList: ArrayList<Int> = ArrayList()
        featuredDataResponse?.let { featuredList.addAll(it) }
        setupFeaturedViewPager(featuredList)
    }
    private fun setupFeaturedViewPager(featuredList: ArrayList<Int>) {
        binding?.featuredViewPager?.adapter = FeaturedAdapter(featuredList)
        binding?.featuredViewPager?.let {
            binding?.tabLayout?.let { it1 ->
                TabLayoutMediator(
                    it1,
                    it
                ){ _, _ -> }.attach()
            }
        }
        startAutoSliderForFeatureFirst(featuredList.size)
    }

    private fun startAutoSliderForFeatureFirst(count: Int) {

        featureViewPagerRunnable?.let { handler.removeCallbacks(it) }

        binding?.let {
            it.featuredViewPager.currentItem = trackPosition
            featureViewPagerRunnable = Runnable {
                var pos: Int = it.featuredViewPager.currentItem
                pos += 1
                if (pos >= count) pos = 0
                it.featuredViewPager.currentItem = pos
                featureViewPagerRunnable?.let {
                    handler.postDelayed(it, 3000)
                }
            }

            it.featuredViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    //page scrolled
                }

                override fun onPageSelected(position: Int) {
                    trackPosition = position
                }

                override fun onPageScrollStateChanged(state: Int) {
                    //page scrolled state changed
                }
            })
            featureViewPagerRunnable?.let { handler.postDelayed(it, 3000) }
        }
    }

    override fun onResume() {
        super.onResume()
        handler = Handler(Looper.getMainLooper())
    }

    override fun onPause() {
        super.onPause()
        featureViewPagerRunnable?.let {
            handler.removeCallbacks(it)
        }
    }


    override fun onDestroy() {
        featureViewPagerRunnable?.let {
            handler.removeCallbacks(it)
        }
        featureViewPagerTwoRunnable?.let {
            handler.removeCallbacks(it)
        }
        super.onDestroy()
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        itemAdapter?.filter?.filter(p0)
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        itemAdapter?.filter?.filter(p0)
        return false
    }
}