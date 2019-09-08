package com.example.telview.view.posts

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.telview.R
import com.example.telview.viewmodel.posts.PostFragmentViewModel

class PostsFragment : Fragment() {

    private lateinit var postListBinding: com.example.telview.databinding.FragmentPostsBinding
    private lateinit var postFragmentViewModel: PostFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        postFragmentViewModel = ViewModelProviders.of(this).get(PostFragmentViewModel::class.java)
        postListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_posts, container, false
        )
        postListBinding.postviewmodel = postFragmentViewModel
        return postListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postFragmentViewModel.uiEventLiveData.observe(this, Observer {
            startActivityfun(it)
        })
    }

    private fun startActivityfun(postId: Bundle?) {
        val intent = Intent(context, PostDetailActivity::class.java)
        intent.putExtra("postBundle", postId)
        startActivity(intent)
    }
}