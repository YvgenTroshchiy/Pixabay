package com.troshchii.pixabay.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.troshchii.pixabay.databinding.FragmentDetailsBinding
import com.troshchii.pixabay.utils.viewBinding

class DetailsFragment : Fragment() {

    private val safeArgs: DetailsFragmentArgs by navArgs()
    private var binding: FragmentDetailsBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Glide.with(this)
            .load(safeArgs.imageUrl)
            .into(binding.image)
    }
}
