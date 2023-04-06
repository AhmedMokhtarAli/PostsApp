package com.example.data.remote.repositoriesimp;

import com.example.data.remote.PostsClient;
import com.example.domain.entities.PostModel;
import com.example.domain.repositories.PostsRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class RepoisitoryImp implements PostsRepository {

    private PostsClient postsClient;

    public RepoisitoryImp() {
        postsClient = PostsClient.getINSTANCE();
    }

    @Override
    public Single<List<PostModel>> getPosts() {
        return postsClient.getPosts();
    }
}
