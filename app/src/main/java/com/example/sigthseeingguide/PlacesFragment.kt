package com.example.sigthseeingguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sigthseeingguide.databinding.FragmentPlacesBinding
import com.google.firebase.firestore.FirebaseFirestore


class PlacesFragment : Fragment() {

    private var _binding: FragmentPlacesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlacesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = CardAdapter(emptyList(), requireContext())
        }
        fetchData()
    }

    private fun fetchData() {
        FirebaseFirestore.getInstance().collection("cards")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val card = documents.toObjects(CardModel::class.java)
                    binding.recyclerView.adapter = CardAdapter(card, requireContext())
                }
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