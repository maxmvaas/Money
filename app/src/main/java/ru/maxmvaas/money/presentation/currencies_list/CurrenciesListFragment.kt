package ru.maxmvaas.money.presentation.currencies_list

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import org.koin.androidx.viewmodel.ext.android.viewModel

import ru.maxmvaas.money.R
import ru.maxmvaas.money.data.model.Currency
import ru.maxmvaas.money.databinding.FragmentCurrenciesListBinding
import ru.maxmvaas.money.presentation.currencies_list.adapter.CurrenciesAdapter
import ru.maxmvaas.money.utils.CurrencyState
import ru.maxmvaas.money.utils.date_formatter.DateFormatter

import java.util.*

class CurrenciesListFragment : Fragment(R.layout.fragment_currencies_list) {

    private val viewModel by viewModel<CurrenciesListViewModel>()

    private var _binding: FragmentCurrenciesListBinding? = null
    private val binding get() = _binding!!

    private var currentDate = Date()

    private var items = arrayListOf<Currency>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val savedDateTimeMills = savedInstanceState?.getLong(DATE_KEY)
        val savedDate = Date()
        if (savedDateTimeMills != null) {
            savedDate.time = savedDateTimeMills
            currentDate = Date()
        }
        if (!savedInstanceState?.getParcelableArrayList<Currency>(DATA_KEY).isNullOrEmpty()) {
            items =
                savedInstanceState?.getParcelableArrayList<Currency>(DATA_KEY) as ArrayList<Currency>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrenciesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInterface()
        if (items.isEmpty()) {
            viewModel.loadCurrencies(DateFormatter.formatToParse(currentDate))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(DATE_KEY, currentDate.time)
        outState.putParcelableArrayList(DATA_KEY, items)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupInterface() {
        binding.editTextDate.hint = DateFormatter.formatToDefault(currentDate)

        var previousDate = currentDate

        val todayCalendar = Calendar.getInstance()
        todayCalendar.time = Date()

        val onDateChooseCallback =
            OnDateSetListener { _, year, monthOfYear, dayOfMonth ->

                val chosenDateCalendar = Calendar.getInstance()
                chosenDateCalendar.set(Calendar.YEAR, year)
                chosenDateCalendar.set(Calendar.MONTH, monthOfYear)
                chosenDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val chosenDate = chosenDateCalendar.time

                val previousDateCalendar = Calendar.getInstance()
                previousDateCalendar.time = previousDate

                if (
                    chosenDateCalendar.get(Calendar.DAY_OF_MONTH) != previousDateCalendar.get(
                        Calendar.DAY_OF_MONTH
                    )
                    || chosenDateCalendar.get(Calendar.MONTH) != previousDateCalendar.get(Calendar.MONTH)
                    || chosenDateCalendar.get(Calendar.YEAR) != previousDateCalendar.get(Calendar.YEAR)
                ) {
                    binding.editTextDate.hint = DateFormatter.formatToDefault(chosenDate)
                    viewModel.loadCurrencies(DateFormatter.formatToParse(chosenDate))
                }

                currentDate = chosenDate
                previousDate = chosenDate
            }

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            onDateChooseCallback,
            todayCalendar.get(Calendar.YEAR),
            todayCalendar.get(Calendar.MONTH),
            todayCalendar.get(Calendar.DAY_OF_MONTH)
        )

        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, 1995)
            set(Calendar.MONTH, 0)
            set(Calendar.DAY_OF_MONTH, 6)
        }

        val minDate = calendar.time

        datePickerDialog.datePicker.minDate = minDate.time
        datePickerDialog.datePicker.maxDate = Date().time

        binding.editTextDate.setOnClickListener {
            datePickerDialog.show()
        }

        val adapter = CurrenciesAdapter()

        adapter.onItemClick = {
            val action =
                CurrenciesListFragmentDirections.actionCurrenciesListFragmentToConvertationFragment(
                    it
                )
            findNavController().navigate(action)
        }

        viewModel.currencyListStateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CurrencyState.Data -> {
                    items.clear()
                    items.addAll(state.data)
                    adapter.setItems(items)
                    setStateData()
                }
                is CurrencyState.Error -> {
                    setStateError()
                }
                else -> {
                    setStateLoading()
                }
            }
        }

        if (items.isNotEmpty()) {
            adapter.setItems(items)
        }

        binding.recyclerViewCurrencies.adapter = adapter
    }

    private fun setStateLoading() = binding.apply {
        progressbar.visibility = View.VISIBLE
        recyclerViewCurrencies.visibility = View.GONE
        textViewErrorMessage.visibility = View.GONE
    }

    private fun setStateData() = binding.apply {
        recyclerViewCurrencies.visibility = View.VISIBLE
        progressbar.visibility = View.GONE
        textViewErrorMessage.visibility = View.GONE
    }

    private fun setStateError() = binding.apply {
        textViewErrorMessage.text = getString(R.string.default_error_msg)
        textViewErrorMessage.visibility = View.VISIBLE
        progressbar.visibility = View.GONE
        recyclerViewCurrencies.visibility = View.GONE
    }

    companion object {
        const val DATE_KEY = "saved_date"
        const val DATA_KEY = "saved_data"
    }
}