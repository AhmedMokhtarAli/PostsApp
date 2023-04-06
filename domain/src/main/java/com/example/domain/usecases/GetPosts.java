package com.example.domain.usecases;

import com.example.domain.entities.PostModel;
import com.example.domain.repositories.PostsRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class GetPosts {
    private PostsRepository repository;

    public GetPosts(PostsRepository repository) {
        this.repository = repository;
    }

    public Single<List<PostModel>> inVoke(){
        return repository.getPosts();
    }
}
