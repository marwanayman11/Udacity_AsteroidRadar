package com.example.asteroidradar.main
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.asteroidradar.AsteroidAdapter
import com.example.asteroidradar.R
import com.example.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application
        val viewModelFactory = MainViewModelFactory(application)
        val viewModel = ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]
        val binding = FragmentMainBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = AsteroidAdapter(AsteroidAdapter.OnClickListener {
            viewModel.displayAsteroidDetails(it)
        })
        binding.asteroids.adapter=adapter

        viewModel.navigateToSelectedAsteroid.observe(viewLifecycleOwner) {
            if (null != it) {
                this.findNavController()
                    .navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(it))
                viewModel.displayAsteroidDetailsComplete()
            }

        }
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.filter, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    when (menuItem.itemId) {
                        R.id.view_today -> viewModel.updateFilter(Filter.TODAY)
                        R.id.view_week -> viewModel.updateFilter(Filter.WEEK)
                        else -> viewModel.updateFilter(Filter.All)

                    }

                    return true
                }
            }, viewLifecycleOwner, Lifecycle.State.RESUMED
        )
        return binding.root
    }


}