package com.example.travelsavior.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.travelsavior.R
import com.example.travelsavior.databinding.FragmentResultBinding
import com.example.travelsavior.ui.viewmodels.TravelViewModel
import kotlinx.coroutines.launch
import java.util.Locale

class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null;
    private val binding get() = _binding!!;

    private val viewModel: TravelViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { currentUi ->
                val currentTravel = String.format(Locale.US,"%.2f", currentUi.travelPrice)
                binding.fragResultValue.text = getString(R.string.frag_result_value, currentTravel)
            }
        }

    }
}