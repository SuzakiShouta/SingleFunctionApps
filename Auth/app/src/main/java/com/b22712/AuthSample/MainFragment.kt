package com.b22712.AuthSample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.b22712.AuthSample.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    lateinit var app: MainApplication
    lateinit var activity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        app = requireActivity().application as MainApplication
        activity = getActivity() as MainActivity
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFavorite.setOnClickListener {
            app.getToken()
        }

        app.token.observe(viewLifecycleOwner) {
            binding.tokenTextView.text = it
        }

    }
}