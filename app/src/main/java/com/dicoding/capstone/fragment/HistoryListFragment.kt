package com.dicoding.capstone.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.Capstone.databinding.FragmentHistoryListBinding
import com.dicoding.capstone.adapter.HistoryListAdapter
import com.dicoding.capstone.viewmodel.MainViewModel

class HistoryListFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: FragmentHistoryListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: HistoryListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryListBinding.inflate(inflater, container, false)
        getHistoryData()
        adapter = HistoryListAdapter()
        binding.rvListHistori.layoutManager = LinearLayoutManager(context)
        binding.rvListHistori.adapter = adapter
        return binding.root
    }

    private fun getHistoryData() {
        viewModel.getLastQuiz().observe(viewLifecycleOwner){ listData ->
            if(listData.status =="success" && listData.data!=null){
                var data = listData.data.histories.sortedByDescending { it.timestamp }
                binding.placeholderData.visibility = View.INVISIBLE
                adapter.submitList(data)
            }else if(listData.status =="fail"){
                binding.placeholderData.text = "${listData.message}"
            }else{
                binding.placeholderData.text = "Belum ada Data Quiz"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}