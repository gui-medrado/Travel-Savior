package com.example.travelsavior.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.travelsavior.R
import com.example.travelsavior.databinding.ActivityMainBinding
import com.example.travelsavior.ui.viewmodels.TravelViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_content_nav_host) as? NavHostFragment
        navHostFragment?.navController;
    }

    private val viewModel: TravelViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding) {
            mainButton.setOnClickListener {


                when(navController?.currentDestination?.id) {
                     R.id.fuelPriceFragment -> {
                        if(!checkValue(viewModel.uiState.value.price, navController?.currentDestination?.label.toString())) return@setOnClickListener;

                        navController?.navigate(R.id.action_fuelPriceFragment_to_distanceFragment)
                    }
                    R.id.distanceFragment -> {
                        if(!checkValue(viewModel.uiState.value.distance, navController?.currentDestination?.label.toString())) return@setOnClickListener;

                        navController?.navigate(R.id.action_distanceFragment_to_avarageConsumptionFragment)
                    }
                    R.id.avarageConsumptionFragment -> {
                        if(!checkValue(viewModel.uiState.value.averageConsumption, navController?.currentDestination?.label.toString())) return@setOnClickListener;

                        viewModel.calculateTravel()
                        navController?.navigate(R.id.action_avarageConsumptionFragment_to_resultFragment)
                    }
                    R.id.resultFragment -> {

                        viewModel.reset()
                        navController?.popBackStack(navController!!.graph.startDestinationId, false)
                    }
                }
            }
        }
    }




    private fun checkValue (value: Double, currentFragment: String): Boolean {
        if(value != 0.0) return true;

        when (currentFragment) {
            "fragment_fuel_price" -> {
                createDialog(title=getString(R.string.title_warning_fuel_price_empty), message=getString(
                    R.string.description_warning_empty
                ))
                return false
            }
            "fragment_distance" -> {
                createDialog(title=getString(R.string.title_warning_distance_empty), message=getString(
                    R.string.description_warning_empty
                ))
                return false
            }
            "fragment_avarage_consumption" -> {
                createDialog(title=getString(R.string.title_warning_avarage_empty), message=getString(
                    R.string.description_warning_empty
                ))
                return false
            }
        }

        return false;
    }

    private fun createDialog (title: String, message: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        builder
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}