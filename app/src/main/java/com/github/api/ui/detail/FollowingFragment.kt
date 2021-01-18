package com.github.api.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.api.R
import com.github.api.adapter.MainAdapter
import com.github.api.databinding.FragmentFollowBinding

class FollowingFragment : Fragment(R.layout.fragment_follow) {

    private var _binding : FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowingViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFollowBinding.bind(view)

        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        _binding = FragmentFollowBinding.bind(view)

        adapter = MainAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            recycleMain.setHasFixedSize(true)
            recycleMain.layoutManager = LinearLayoutManager(activity)
            recycleMain.adapter = adapter
        }
        showLoading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowingViewModel::class.java)
        viewModel.setListFollowing(username)
        viewModel.getListFollowing().observe(viewLifecycleOwner, {
            if (it != null)
                adapter.setList(it)
            showLoading(false)
        })

    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE

        }else{

            binding.progressBar.visibility = View.GONE
        }
    }
}