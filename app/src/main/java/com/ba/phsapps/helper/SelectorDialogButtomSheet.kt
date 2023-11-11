package com.ba.phsapps.helper

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import com.ba.phsapps.R
import com.ba.phsapps.databinding.FragmentSpinerBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ssoft.common.util.LogUtil


interface SlideChangeListener {

    fun onSlideChangeListener(
        filterOption: SelectBottomOptions,
        position: Int,
        value: String,
        valueNumber: Int
    )

}

class SelectorDialogButtomSheet(val filterOption: SelectBottomOptions, var arr: List<String>) :
    BottomSheetDialogFragment() {


    private lateinit var binding: FragmentSpinerBinding
    var selected: Int = 0

    var onSlideChangeListener: SlideChangeListener? = null


    var position = 0
    var strTitle = ""

    init {
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSpinerBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)


        LogUtil.showLogError("js", "${arr.toString()}")

        binding.selectSP.setDisplayedValues(arr.toTypedArray());
        initTitle()

        binding.selectSP.minValue = (0);
        binding.selectSP.setMaxValue(arr.size - 1);


//        selectSP.selectionDividerHeight = 0
        binding.selectSP.value = this.selected

        position = 0
        Log.e("position", "${this.selected}")

        binding.selectSP.setOnValueChangedListener(object : NumberPicker.OnValueChangeListener {
            override fun onValueChange(picker: NumberPicker?, oldVal: Int, newVal: Int) {
                // do something here
                this@SelectorDialogButtomSheet.strTitle = arr[newVal]
                this@SelectorDialogButtomSheet.position = newVal
//                Log.e("position---", "${this.selected}")

            }
        })
        binding.doneTV.setOnClickListener {

            Log.e("position type", "${this.position}")

            onSlideChangeListener?.onSlideChangeListener(filterOption, position, strTitle, 0)

            dismiss()
        }


    }






    private fun initTitle() {


//        when (filterOption) {
//            SelectBottomOptions.SALETYPE -> {
//                titleTV.text = getString(R.string.sale_type)
//                LogUtil.showLogError("dd", "${getString(R.string.property_categoory)}")
//            }
//            SelectBottomOptions.TYPE -> {
//                titleTV.text = getString(R.string.property_categoory)
//                LogUtil.showLogError("dd", "${getString(R.string.property_categoory)}")
//            }
//            SelectBottomOptions.SALE_TYPE -> {
//                titleTV.text = ""
//                LogUtil.showLogError("dd", "${getString(R.string.property_categoory)}")
//            }
//            SelectBottomOptions.DECORATION -> {
//                titleTV.text = "การตกแต่ง"
////                LogUtil.showLogError("dd", "${getString(R.string.property_categoory)}")
//            }
//            else ->   titleTV.text = getString(R.string.property_categoory)
//
//        }

    }


//    fun selected(position: Int) {
//        this.selected = position
//        selectSP?.value = this.selected
//    }


}


enum class SelectBottomOptions {
    Gender,Title,Type,Service
}