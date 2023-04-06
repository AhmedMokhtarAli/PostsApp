package com.example.domain.repositories;

import com.example.domain.entities.PostModel;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface PostsRepository {
    public Single<List<PostModel>> getPosts();
}
