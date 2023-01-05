package com.example.posts.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.posts.R;
import com.example.posts.data.PostsClient;
import com.example.posts.databinding.ActivityMainBinding;
import com.example.posts.pojo.PostModel;
import com.example.posts.ui.PostViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
   PostViewModel postViewModel;
   ActivityMainBinding binding;
   PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        postViewModel= ViewModelProviders.of(this).get(PostViewModel.class);
        postViewModel.getPosts();

        initPostsRecyclerView();


    }
    @Override
    protected void onStart() {
        super.onStart();
        binding.shimmer.startShimmerAnimation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.shimmer.stopShimmerAnimation();
    }

    private void initPostsRecyclerView(){
        adapter=new PostAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        postViewModel.posts.observe(this, new Observer<List<PostModel>>() {
            @Override
            public void onChanged(List<PostModel> postModels) {
                if (!postModels.isEmpty()) {
                    addPostsListToPostsRecyclerView(postModels);
                }
            }
        });
        binding.recyclerView.setAdapter(adapter);
    }

    private void addPostsListToPostsRecyclerView(List<PostModel> postModels){
        adapter.setPosts(postModels);
        binding.shimmer.stopShimmerAnimation();
        binding.shimmer.setVisibility(View.GONE);
        binding.recyclerView.setVisibility(View.VISIBLE);
    }


}