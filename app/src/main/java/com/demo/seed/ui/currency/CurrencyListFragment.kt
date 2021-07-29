package com.demo.seed.ui.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.seed.databinding.FragmentCurrencyListBinding
import com.demo.seed.ui.DemoActivityViewModel
import com.demo.seed.ui.model.CurrencyInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyListFragment : Fragment(), CurrencyListAdapter.OnItemClickListener {

    private val activityViewModel: DemoActivityViewModel by viewModels({ requireActivity() })

    private var _binding: FragmentCurrencyListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currencyListAdapter = CurrencyListAdapter(this)

        binding.apply {
            recyclerViewCurrencyList.apply {
                adapter = currencyListAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        activityViewModel.currencyList.observe(viewLifecycleOwner) {
            currencyListAdapter.submitList(it)
        }
    }

    override fun onItemClicked(currencyInfo: CurrencyInfo) {
        activityViewModel.updateSelectedCurrencyInfo(currencyInfo)
    }
}