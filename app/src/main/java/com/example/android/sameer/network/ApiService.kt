
package com.example.android.sameer.network

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.sameer.R
import com.example.android.sameer.adapter.PhotosAdapter
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import kotlinx.coroutines.Deferred

private const val BASE_URL = "https://www.evenidontknow.com/"


private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()



interface ApiService {
//
//      Returns a Coroutine [Deferred] [List] of [Images] which can be fetched with await() if
//     in a Coroutine scope.
//
    @GET("mars")
    fun getProperties():
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<List<Images>>
}



@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Images>?) {
    val adapter = recyclerView.adapter as PhotosAdapter
    adapter.submitList(data)
}


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
                .load(imgUri)
                .apply(RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(imgView)
    }
}


///
//   public Api object that exposes the lazy-initialized Retrofit service
//
object MarsApi {
    val retrofitService : ApiService by lazy { retrofit.create(ApiService::class.java) }
}
