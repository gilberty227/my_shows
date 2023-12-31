package br.com.myshow.presenter.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.myshow.databinding.FragmentHomeBinding
import br.com.myshow.presenter.home.adapter.ShowAdapter
import br.com.myshow.presenter.model.ShowUi
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

    private fun setShowAdapter(showList: MutableList<ShowUi>) {
        if((binding.recyclerViewShows.adapter?.itemCount?:0) <= 0) {
            ShowAdapter(showList) {
                val action = HomeFragmentDirections.actionNavigationHomeToNavigationShowDetails(
                    it.title.orEmpty(),
                    it
                )
                findNavController().navigate(action)
            }.also { binding.recyclerViewShows.adapter = it }
        }
    }
}