package com.example.finalapplication5.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.finalapplication5.R
import com.example.finalapplication5.model.api.PostRetrofit
import com.example.finalapplication5.model.model.StateFragments
import com.example.finalapplication5.viewmodel.PostViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ViewFragment : Fragment(R.layout.fragment_view) {

    val viewModel: PostViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val postApi = PostRetrofit.getRetrofit()

        viewModel.getPostsFromServer(postApi, requireContext())

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPostsFromDb(requireContext())
        val adapter = Adapter(requireContext(), emptyList())
        viewModel.postLiveData.observe(viewLifecycleOwner) {
            adapter.list = it
            adapter.notifyDataSetChanged()
        }
        view.findViewById<RecyclerView>(R.id.postList).adapter = adapter
        view.findViewById<FloatingActionButton>(R.id.add)
            .setOnClickListener {
                viewModel.changeFragment(StateFragments.Add)
            }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ViewFragment()
    }
}