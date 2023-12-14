package br.com.myshow.presenter

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.transition.Fade
import androidx.transition.TransitionManager
import br.com.myshow.R
import br.com.myshow.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val viewModel: MainViewModel by viewModels()
    private var idFragment: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_home, R.id.navigation_orders)
        )
        setupActionBarWithNavController(
            navController = navController,
            configuration = appBarConfiguration
        )
        binding.navView.setupWithNavController(navController)
        setSettingsButtonNavigation(navController)

        viewModel.updateCart.observe(this){
            binding.textViewPrice.text = it.totalPrice
            binding.textViewCount.text = resources.getQuantityString(R.plurals.cart_total_ticket, it.totalTicket, it.totalTicket)
        }

        viewModel.updateShowCart.observe(this){
            binding.groupCart.visibility = if(it && R.id.navigation_home == idFragment) View.VISIBLE else View.GONE
        }

        binding.constraintLayoutButtonCart.setOnClickListener {
            findNavController(R.id.fragmentContainerView).navigate(R.id.navigation_cart)
        }
    }

    private fun setSettingsButtonNavigation(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            TransitionManager.beginDelayedTransition(binding.navView, Fade())
            idFragment = destination.id
            if (destination.id == R.id.navigation_home) {
                binding.groupNavigation.visibility = View.VISIBLE
                if(((viewModel.updateCart.value?.totalTicket?:0) > 0)) {
                    binding.groupCart.visibility = View.VISIBLE
                }
            } else {
                binding.groupNavigation.visibility = View.GONE
                binding.groupCart.visibility = View.GONE
            }
            if (destination.id == R.id.navigation_orders){
                binding.groupNavigation.visibility = View.VISIBLE
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}