package com.example.marswebservicecontact

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.marswebservicecontact.network.MarsClass
import com.example.marswebservicecontact.photoGridAdapter.PhotoGridAdapter
import com.example.marswebservicecontact.titleFragment.MarsAPIStartus


@BindingAdapter("imageURL")
fun bindImage(imgView: ImageView, imgURL: String?){
    imgURL?.let {
        var imgURI = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgURI)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.error_img))
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data:List<MarsClass>?){
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("MarsAPIStatus")
fun bindStatus(statusImageView: ImageView, status: MarsAPIStartus?){
    when(status) {
        MarsAPIStartus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarsAPIStartus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.error_img)
        }
        MarsAPIStartus.DONE -> {
            statusImageView.visibility = View.GONE

        }
    }

}