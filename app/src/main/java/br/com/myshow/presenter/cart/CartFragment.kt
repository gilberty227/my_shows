package br.com.myshow.presenter.cart

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
import br.com.myshow.databinding.FragmentHomeBinding
import br.com.myshow.domain.model.Ticket
import br.com.myshow.presenter.cart.adapter.TicketAdapter
import br.com.myshow.presenter.cart.adapter.TicketListener
import br.com.myshow.presenter.home.HomeFragmentDirections
import br.com.myshow.presenter.home.adapter.ShowAdapter
import br.com.myshow.presenter.model.ShowUi
import br.com.myshow.presenter.model.TicketUi
import dagger.hilt.android.AndroidEntryPoint
import java.text.FieldPosition

@AndroidEntryPoint
class CartFragment : Fragment(), TicketListener {

    private lateinit var binding: FragmentCartBinding
    private val viewModel: CartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.tickets.observe(viewLifecycleOwner){
            setTicketsAdapter(it.toMutableList())
        }

        viewModel.updateCart.observe(viewLifecycleOwner){
            binding.textViewPriceTotal.text = it.totalPrice
            binding.textViewCountTicket.text = resources.getQuantityString(R.plurals.cart_total_ticket, it.totalTicket, it.totalTicket)
        }
    }

    private fun setTicketsAdapter(ticketList: MutableList<TicketUi>) {
        if((binding.recyclerViewTicket.adapter?.itemCount?:0) <= 0) {
            TicketAdapter(ticketList, this) {

            }.also { binding.recyclerViewTicket.adapter = it }
        }

    }

    override fun removeTicket(ticket: TicketUi, position: Int) {
        (binding.recyclerViewTicket.adapter as TicketAdapter).removeItem(position)
        viewModel.removeTicket(ticket)
    }

    override fun updateCart(ticket: TicketUi) {
        viewModel.updateTicket(ticket)
    }

    override fun maxTicket() {
        Toast.makeText(context, getString(R.string.max_items), Toast.LENGTH_LONG).show()
    }
}