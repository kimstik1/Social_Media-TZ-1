package com.kimstik.social_media.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kimstik.social_media.AppApplication
import com.kimstik.social_media.R
import com.kimstik.social_media.databinding.FragmentMainBinding
import com.kimstik.social_media.ui.MainActivity
import com.kimstik.social_media.ui.adapters.RecyclerBestAdapter
import com.kimstik.social_media.ui.adapters.RecyclerCarouselAdapter
import com.kimstik.social_media.ui.adapters.listeners.BestRecyclerItemListener
import com.kimstik.social_media.ui.fragments.viewmodel.MainViewModel
import com.kimstik.social_media.util.BUNDLE_KEY
import com.kimstik.social_media.util.factory.MainViewModelFactory
import javax.inject.Inject

class MainFragment: Fragment(), BestRecyclerItemListener {

    @Inject
    lateinit var vmFactory: MainViewModelFactory
    private lateinit var vm: MainViewModel

    private var carouselAdapter: RecyclerCarouselAdapter = RecyclerCarouselAdapter()
    private var bestAdapter: RecyclerBestAdapter = RecyclerBestAdapter(this)

    private var bind: FragmentMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (AppApplication.INSTANCE as AppApplication).appComponent.injectMainViewModel(this)
        vm = ViewModelProvider(this, vmFactory)[MainViewModel::class.java]
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentMainBinding.inflate(layoutInflater, container, false)

        bind?.apply {
            verticalRecycler.adapter = bestAdapter
            horizontalRecycler.adapter = carouselAdapter
        }

        return bind?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeFlow()
    }


    private fun subscribeFlow() {
        lifecycleScope.launchWhenStarted {
            vm.bestList.collect{
                bestAdapter.updateList(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            vm.carouselList.collect {
                carouselAdapter.updateList(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            vm.eventsFlow.collect {
                when(it) {
                    is MainViewModel.Event.ShowSnackBar -> showSnackBar(it.text)
                    is MainViewModel.Event.NavigateScopeFragment -> navigate(it.text)
                }
            }
        }
    }

    private fun showSnackBar(text: String) {
        Snackbar.make(requireView(), text, Snackbar.LENGTH_SHORT).show()
    }

    override fun onClick(position: Int) = vm.recyclerClickHandler(position)

    private fun navigate(text: String){
        val bundle = Bundle()
        bundle.putString(BUNDLE_KEY, text)
        (activity as MainActivity).navController?.navigate(R.id.scopeFragment, bundle)
    }
}