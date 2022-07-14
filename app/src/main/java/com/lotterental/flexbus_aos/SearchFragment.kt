package com.lotterental.flexbus_aos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lotterental.flexbus_aos.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        initSearchFragment()
        return binding.root
    }

    private fun initSearchFragment(){
        binding.ivBack.setOnClickListener{
            App.activity.onBackPressed()
        }
    }
}