package com.example.sqlitewithroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqlitewithroom.adapter.UserAdapter
import com.example.sqlitewithroom.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding

    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(layoutInflater,container,false)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        val adapter = UserAdapter()
        binding.rvlUser.layoutManager = LinearLayoutManager(context)
        binding.rvlUser.adapter = adapter
        binding.rvlUser.setHasFixedSize(true)

        mainViewModel.selectUsers.observe(viewLifecycleOwner) {
            response -> adapter.setList(response)
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        return binding.root
    }
}
