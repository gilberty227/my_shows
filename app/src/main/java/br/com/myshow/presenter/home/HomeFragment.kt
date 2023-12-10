package br.com.myshow.presenter.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.myshow.databinding.FragmentHomeBinding
import br.com.myshow.presenter.model.ShowDto
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showsDtoList.observe(viewLifecycleOwner) { showList ->
            setShowAdapter(showList.toMutableList())
        }

        viewModel.getShows()
    }

    private fun setShowAdapter(showList: MutableList<ShowDto>) {
        var oi = showList
    }
}