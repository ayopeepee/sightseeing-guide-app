package com.example.sigthseeingguide

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sigthseeingguide.databinding.FragmentPlaceInfoBinding
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView


class PlaceInfoFragment : Fragment() {

    private var _binding: FragmentPlaceInfoBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlaceInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.textViewName.text = arguments?.getString("name")
        binding.textViewInfo.text = arguments?.getString("info")


        fetchData()
    }

    private fun fetchData() {
        FirebaseFirestore.getInstance().collection("tsoy")
            .get()
            .addOnSuccessListener { documents ->
                val images = documents.toObjects(CarouselModel::class.java)
                binding.carouselRecyclerView.adapter = CarouselAdapter(images, requireContext())
                binding.carouselRecyclerView.layoutManager = CarouselLayoutManager()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
            }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}