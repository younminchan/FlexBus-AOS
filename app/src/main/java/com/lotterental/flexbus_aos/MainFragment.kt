package com.lotterental.flexbus_aos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lotterental.flexbus_aos.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        initMainFragment()

        return binding.root
    }

    private fun initMainFragment() {
        binding.tvSearch.setOnClickListener {
            MainActivity.getInstance().moveFragment(requireActivity(), SearchFragment(), true)
        }
    }
}