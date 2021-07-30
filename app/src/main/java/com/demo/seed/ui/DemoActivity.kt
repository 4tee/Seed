package com.demo.seed.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.demo.seed.R
import com.demo.seed.databinding.ActivityDemoBinding
import com.demo.seed.extension.setDebounceClickedListener
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_demo.*
import kotlinx.android.synthetic.main.content_main.*

@AndroidEntryPoint
class DemoActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDemoBinding

    private val activityViewModel: DemoActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.apply {
            button_load.setDebounceClickedListener {
                activityViewModel.loadCurrencyList()
            }

            button_sort.setDebounceClickedListener {
                activityViewModel.sortCurrencyList()
            }
        }

        activityViewModel.currencyInfoSelected.observe(this) { currencyInfo ->
            Snackbar.make(
                layout_demo,
                "$currencyInfo at DemoActivity",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}