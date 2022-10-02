package com.example.asteroidradar.detail

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.asteroidradar.R
import com.example.asteroidradar.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailBinding.inflate(inflater)
        val asteroid = DetailFragmentArgs.fromBundle(requireArguments()).asteroid
        val viewModelFactory = DetailViewModelFactory(asteroid)
        binding.viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]
        binding.help.setOnClickListener {
            AlertDialog.Builder(requireContext()).setMessage(R.string.dialog)
                .setPositiveButton("ACEPTAR", null).show()
        }
        return binding.root
    }


}