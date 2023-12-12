package br.com.myshow.presenter.showdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.myshow.R
import br.com.myshow.databinding.FragmentShowDetailsBinding
import br.com.myshow.domain.utils.getMoney
import br.com.myshow.domain.utils.loadImage
import br.com.myshow.domain.utils.loadImageBlur
import br.com.myshow.presenter.model.ShowDto
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowDetailsFragment : Fragment() {

    private lateinit var binding: FragmentShowDetailsBinding
    private val viewModel: ShowDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentShowDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val show = arguments?.getParcelable("show") as ShowDto?
        show?.let {
            viewModel.setShow(show)
        }
        viewsSetOnClickLister()
        setObservables()
    }

    private fun setObservables(){
        viewModel.show.observe(viewLifecycleOwner){
            setShow(it)
        }

        viewModel.updatePrice.observe(viewLifecycleOwner){
            updatePrice(it)
        }

        viewModel.message.observe(viewLifecycleOwner){
            Toast.makeText(context, getString(it), Toast.LENGTH_LONG).show()
        }

        viewModel.updateStatusButton.observe(viewLifecycleOwner){
            binding.imageViewMinus.isEnabled = it.first
            binding.imageViewMinus.drawable.setTint(ContextCompat.getColor(requireContext(),
                if(it.first) R.color.colorTextBuy else R.color.colorTextTitle))

            binding.imageViewMore.isEnabled = it.second
            binding.imageViewMore.drawable.setTint(ContextCompat.getColor(requireContext(),
                if(it.second) R.color.colorTextBuy else R.color.colorTextTitle))
        }
    }

    private fun updatePrice(price: Int){
        binding.textViewTotalValue.text = price.getMoney()
    }

    private fun viewsSetOnClickLister(){
        binding.imageViewMinus.setOnClickListener {
            val numberTicket = binding.textViewNumber.text.toString().toInt()
            viewModel.changeNumberItem(numberTicket - 1)
            binding.textViewNumber.text = (numberTicket - 1).toString()
        }
        binding.imageViewMore.setOnClickListener {
            val numberTicket = binding.textViewNumber.text.toString().toInt()
            viewModel.changeNumberItem(numberTicket + 1)
            binding.textViewNumber.text = (numberTicket + 1).toString()
        }
        binding.constraintLayoutBuy.setOnClickListener {  }
    }

    private fun setShow(show: ShowDto){
        binding.imageViewShow.loadImage(show.imageUrl.orEmpty())
        binding.imageViewBackgroundShow.loadImageBlur(show.imageUrl.orEmpty())
        binding.textViewShowTitle.text = show.title
        binding.textViewShowDesc.text = show.desc
        binding.textViewShowLocale.text = show.location
        binding.textViewShowHour.text = show.hour
        binding.textViewShowDate.text = show.day
        binding.textViewNumber.text = "1"
        binding.textViewShowValue.text = show.price?.getMoney()
        binding.textViewTotalValue.text = (show.price?.getMoney())
    }


}