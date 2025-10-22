package com.example.travelsavior.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import com.example.travelsavior.databinding.FragmentDistanceBinding
import com.example.travelsavior.ui.viewmodels.TravelViewModel

class DistanceFragment : Fragment() {
    private var _binding: FragmentDistanceBinding? = null;
    private val binding get() = _binding!!;

    private val viewModel: TravelViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDistanceBinding.inflate(inflater, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editText = binding.fragDistanceEditText
        editText.addTextChangedListener {
            viewModel.setDistance(it.toString().toDoubleOrNull() ?: 0.0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}