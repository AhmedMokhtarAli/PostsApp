package com.example.posts.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.posts.R;
import com.example.posts.databinding.PostItemBinding;
import com.example.posts.pojo.PostModel;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<PostModel> posts=new ArrayList<>();

    public void setPosts(List<PostModel> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        PostItemBinding postItemBinding= PostItemBinding.inflate(inflater,parent,false);
        return new PostViewHolder(postItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        PostModel postModel=posts.get(position);
        holder.bind(postModel);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    protected class PostViewHolder extends RecyclerView.ViewHolder{
        private PostItemBinding postItemBinding;
        public PostViewHolder(PostItemBinding postItemBinding) {
            super(postItemBinding.getRoot());
            this.postItemBinding=postItemBinding;
        }
        public void bind(PostModel postModel){
            postItemBinding.body.setText(postModel.getBody());
            postItemBinding.title.setText(postModel.getTitle());
            postItemBinding.userId.setText(postModel.getUserId()+"");
        }
    }
}
