package com.example.data.remote;


import com.example.data.BuildConfig;
import com.example.domain.entities.PostModel;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PostsClient {
    private PostInterface postInterface;
    private static PostsClient INSTANCE;


    public PostsClient() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        postInterface=retrofit.create(PostInterface.class);
    }

    public static PostsClient getINSTANCE() {
        if(INSTANCE==null)
        {
            INSTANCE=new PostsClient();
        }
        return INSTANCE;
    }
    public Single<List<PostModel>> getPosts()
    {
        return postInterface.getPosts();
    }
}
