package com.example.posts;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.data.remote.repositoriesimp.RepoisitoryImp;
import com.example.domain.entities.PostModel;
import com.example.domain.repositories.PostsRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PostViewModel extends ViewModel {
    private PostsRepository postsRepo;

    public PostViewModel() {
        postsRepo=new RepoisitoryImp();
    }

    public MutableLiveData<List<PostModel>> posts=new MutableLiveData<>();
    public static final String TAG="PostViewModel";

    CompositeDisposable compositeDisposable=new CompositeDisposable();
    public void getPosts(){
        Single<List<PostModel>> observable =postsRepo.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        compositeDisposable.add(observable.subscribe(postsList ->posts.setValue(postsList), error -> Log.d(TAG, "Error: "+error)));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
