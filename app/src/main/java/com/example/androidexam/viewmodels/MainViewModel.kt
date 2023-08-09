package com.example.androidexam.viewmodels

import androidx.lifecycle.ViewModel
import com.example.androidexam.R
import com.example.androidexam.models.RecyclerItem

class MainViewModel : ViewModel() {

    var carousalResponse = ArrayList<Int>()
    var itemList = ArrayList<RecyclerItem>()

    fun getImagesForCarousal() : ArrayList<Int> {

        carousalResponse.add(R.drawable.viewpager_2)
        carousalResponse.add(R.drawable.viewpager_3)
        carousalResponse.add(R.drawable.viewpager_4)
        return carousalResponse
    }

    fun addCarousalImage(image : Int){
        carousalResponse.add(image)
    }

    fun getRecyclerItems():ArrayList<RecyclerItem>{
        val item1 = RecyclerItem("https://images.unsplash.com/photo-1540189549336-e6e99c3679fe?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8NXx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80","Pizza")
        val item2 = RecyclerItem("https://thumbs.dreamstime.com/b/heart-shape-various-vegetables-fruits-healthy-food-concept-isolated-white-background-140287808.jpg","Pasta")
        val item3 = RecyclerItem("https://www.foodiesfeed.com/wp-content/uploads/2021/01/fried-egg-and-guacamole-sandwiches.jpg","Burger")
        val item4 = RecyclerItem("https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=960,872","Macroni")
        val item5 = RecyclerItem("https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80","Momos")
        val item6 = RecyclerItem("https://burst.shopifycdn.com/photos/breakfast-from-above.jpg?width=1200&format=pjpg&exif=1&iptc=1","Pancake")
        val item7 = RecyclerItem("https://images.unsplash.com/photo-1540189549336-e6e99c3679fe?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8NXx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80","French Fries")
        val item8 = RecyclerItem("https://thumbs.dreamstime.com/b/heart-shape-various-vegetables-fruits-healthy-food-concept-isolated-white-background-140287808.jpg","Noodles")
        val item9 = RecyclerItem("https://www.foodiesfeed.com/wp-content/uploads/2021/01/fried-egg-and-guacamole-sandwiches.jpg","Chicken Soup")
        val item10 = RecyclerItem("https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=960,872","Sandwich")
        val item11 = RecyclerItem("https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80","Wrap")
        val item12 = RecyclerItem("https://burst.shopifycdn.com/photos/breakfast-from-above.jpg?width=1200&format=pjpg&exif=1&iptc=1","Taco")

        itemList.add(item1)
        itemList.add(item2)
        itemList.add(item3)
        itemList.add(item4)
        itemList.add(item5)
        itemList.add(item6)
        itemList.add(item7)
        itemList.add(item8)
        itemList.add(item9)
        itemList.add(item10)
        itemList.add(item11)
        itemList.add(item12)
        return itemList
    }

    fun addNewItems(item : RecyclerItem){
        itemList.add(item)
    }
}