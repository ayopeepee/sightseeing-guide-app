package com.example.sigthseeingguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.sigthseeingguide.databinding.FragmentPlaceInfoBinding
import com.example.sigthseeingguide.databinding.FragmentPlaceMapBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView


class PlaceMapFragment : Fragment() {

    private lateinit var mapView: MapView
    private var _binding: FragmentPlaceMapBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MapKitFactory.initialize(requireContext())
        _binding = FragmentPlaceMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = binding.mapView

        binding.mapView.map.move(
            CameraPosition(
                Point(
            arguments?.getString("latitude")!!.toDouble(),
            arguments?.getString("longitude")!!.toDouble()),
            15.0f,
            0.0f,
            0.0f),
            Animation(Animation.Type.SMOOTH, 7f),
            null
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
    }
}