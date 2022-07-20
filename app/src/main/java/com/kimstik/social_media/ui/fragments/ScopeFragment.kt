package com.kimstik.social_media.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.kimstik.social_media.AppApplication
import com.kimstik.social_media.R
import com.kimstik.social_media.data.model.best.NetworkBook
import com.kimstik.social_media.databinding.FragmentScopeBinding
import com.kimstik.social_media.ui.adapters.RecyclerSimilarAdapter
import com.kimstik.social_media.ui.fragments.viewmodel.MainViewModel
import com.kimstik.social_media.ui.fragments.viewmodel.ScopeViewModel
import com.kimstik.social_media.util.BUNDLE_KEY
import com.kimstik.social_media.util.factory.MainViewModelFactory
import com.kimstik.social_media.util.factory.ScopeViewModelFactory
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class ScopeFragment: Fragment() {

    @Inject
    lateinit var vmFactory: ScopeViewModelFactory
    private lateinit var vm: ScopeViewModel

    private var bind: FragmentScopeBinding? = null

    private val adapter = RecyclerSimilarAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (AppApplication.INSTANCE as AppApplication).appComponent.injectScopeViewModel(this)
        vm = ViewModelProvider(this, vmFactory)[ScopeViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentScopeBinding.inflate(layoutInflater, container, false)
        bind?.btnBack?.setOnClickListener {activity?.onBackPressed()}
        bind?.cartActivityRecycler?.adapter = adapter
        return bind?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments?.getString(BUNDLE_KEY)
        dataToView(Gson().fromJson(data, NetworkBook::class.java))

        subscribeDataFlow()
    }

    private fun subscribeDataFlow(){
        lifecycleScope.launchWhenStarted {
            vm.similarList.collect{
                adapter.updateList(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            vm.eventsFlow.collect{
                when(it){
                    is ScopeViewModel.Event.ShowSnackBar -> showSnackBar(it.text)
                }
            }
        }
    }

    private fun showSnackBar(text: String) {
        Snackbar.make(requireView(), text, Snackbar.LENGTH_SHORT).show()
    }

    private fun dataToView(data: NetworkBook){
        bind?.apply {
            Glide.with(this@ScopeFragment)
                    .load(data.image)
                    .into(this.imageItemBook)

            tvItemTitle.text = data.author
            tvItemAuthor.text = data.author
            tvItemScore.text = data.rate.score.toString()
            tvItemAmount.text = "(" + data.rate.amount.toString() + ")"
            tvItemPrice.text = data.price.toString() + "$"
        }
    }
}