package br.com.myshow.presenter.orders.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.myshow.R
import br.com.myshow.databinding.FragmentCartBinding
import br.com.myshow.databinding.FragmentOrderDetailsBinding
import br.com.myshow.presenter.cart.adapter.TicketAdapter
import br.com.myshow.presenter.cart.adapter.TicketListener
import br.com.myshow.presenter.model.OrderUi
import br.com.myshow.presenter.model.ShowUi
import br.com.myshow.presenter.model.TicketUi
import br.com.myshow.presenter.orders.details.adapter.OrderDetailsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderDetailsFragment : Fragment() {

    private lateinit var binding: FragmentOrderDetailsBinding
    private val viewModel: OrderDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentOrderDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val order = arguments?.getParcelable("order") as OrderUi?
        order?.let {
            viewModel.setOrder(order)
        }

        viewModel.tickets.observe(viewLifecycleOwner){
            setTicketsAdapter(it.toMutableList())
        }

        viewModel.updateCart.observe(viewLifecycleOwner){
            binding.textViewPriceTotal.text = it.totalPrice
            binding.textViewCountTicket.text = resources.getQuantityString(R.plurals.cart_total_ticket, it.totalTicket, it.totalTicket)
        }


        binding.constraintLayoutButtonFinish.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setTicketsAdapter(ticketList: MutableList<TicketUi>) {
        val ticket = ticketList.find { it.countTicket == 0 }
        ticketList.remove(ticket)
        if((binding.recyclerViewOrderDetails.adapter?.itemCount?:0) <= 0) {
            binding.recyclerViewOrderDetails.adapter = OrderDetailsAdapter(ticketList)
        }
    }
}