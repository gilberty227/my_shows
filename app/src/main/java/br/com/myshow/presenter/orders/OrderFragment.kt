package br.com.myshow.presenter.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.myshow.databinding.FragmentOrdersBinding
import br.com.myshow.presenter.model.OrderUi
import br.com.myshow.presenter.orders.adapter.OrderAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : Fragment() {

    private lateinit var binding: FragmentOrdersBinding
    private val viewModel: OrderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservables()
        viewModel.getOrders()
    }

    private fun setObservables(){
        viewModel.orders.observe(viewLifecycleOwner){
            if(it.isEmpty()){
                showEmpty(true)
            } else {
                setTicketsAdapter(it.toMutableList())
                showEmpty(false)
            }
        }
    }

    private fun showEmpty(show: Boolean) {
        binding.includeEmpty.constraintLayoutEmpty.visibility = if(show) View.VISIBLE else View.GONE
        if(show){
            binding.includeEmpty.animationEmpty.playAnimation()
        } else {
            binding.includeEmpty.animationEmpty.pauseAnimation()
        }

    }

    private fun setTicketsAdapter(orderList: MutableList<OrderUi>) {
        if((binding.recyclerViewOrder.adapter?.itemCount?:0) <= 0) {
            OrderAdapter(orderList){

            }.also { binding.recyclerViewOrder.adapter = it }
        }
    }
}