package ru.maxmvaas.money.presentation.convertation

import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import org.koin.androidx.viewmodel.ext.android.viewModel

import ru.maxmvaas.money.R
import ru.maxmvaas.money.databinding.FragmentConvertationBinding
import ru.maxmvaas.money.utils.convertation_input_filter.DecimalDigitsInputFilter

class ConvertationFragment : Fragment(R.layout.fragment_convertation) {

    private val viewModel by viewModel<ConvertationViewModel>()

    private val args: ConvertationFragmentArgs by navArgs()

    private var _binding: FragmentConvertationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConvertationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currency = args.currentCurrency

        binding.apply {
            toolbarConvertation.title = currency.name
            toolbarConvertation.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            textViewTitle.text = currency.fullName
            textInputLayoutConvertationToRubles.hint = currency.name
            textInputEditTextCurrentCurrency.hint = resources.getString(R.string.zero)
            textViewRate.text = getString(R.string.convertation_rate_format).format(
                currency.rate.toString().replace(',', '.')
            )
        }

        val rate = currency.rate
        val editCurrentCurrency = binding.textInputEditTextCurrentCurrency
        val editRubles = binding.textInputEditTextRubles

        viewModel.apply {
            valueLiveData.observe(viewLifecycleOwner) {
                editCurrentCurrency.setText(it)
            }
            valueRoublesLiveData.observe(viewLifecycleOwner) {
                editRubles.setText(it)
            }
        }

        editCurrentCurrency.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(12, 3))
        editCurrentCurrency.addTextChangedListener {
            if (editCurrentCurrency.hasFocus()) {
                editRubles.text?.clear()
                if (editCurrentCurrency.text.toString().isNotBlank()) {
                    viewModel.convertToRubles(it.toString(), rate)
                }
            }
        }

        editRubles.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(12, 3))
        editRubles.addTextChangedListener {
            if (editRubles.hasFocus()) {
                editCurrentCurrency.text?.clear()
                if (editRubles.text.toString().isNotBlank()) {
                    viewModel.convertFromRubles(it.toString(), rate)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}