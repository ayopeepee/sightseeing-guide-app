package com.example.sigthseeingguide

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.example.sigthseeingguide.databinding.FragmentBottomNavigationBinding


class BottomNavigationFragment : Fragment() {

    private var _binding: FragmentBottomNavigationBinding? = null
    private val args: BottomNavigationFragmentArgs by navArgs()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBottomNavigationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val starterTransaction = parentFragmentManager.beginTransaction()
        val starterFragment = PlaceInfoFragment().apply {
            arguments = bundleOf(
                "name" to args.name,
                "info" to args.info
            )
        }
        starterTransaction.replace(R.id.child_container, starterFragment)
        starterTransaction.commit()


        binding.bottomNav.setOnItemSelectedListener { item ->
            val transaction = parentFragmentManager.beginTransaction()

            when (item.itemId) {
                R.id.item_info -> {
                    val fragment = PlaceInfoFragment().apply {
                        arguments = bundleOf(
                            "name" to args.name,
                            "info" to args.info
                        )
                    }
                    transaction.replace(R.id.child_container, fragment)
                }

                R.id.item_map -> {
                    val fragment = PlaceMapFragment().apply {
                        arguments = bundleOf(
                            "latitude" to args.latitude,
                            "longitude" to args.longitude
                        )
                    }
                    transaction.replace(R.id.child_container, fragment)
                }
            }
            transaction.commit()
            true
        }
    }


}