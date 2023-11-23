package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Flavor(var name: String, private var colorList: List<Int>) {

    private val _quantity = MutableLiveData(0)
    var quantity: LiveData<Int> = _quantity

    private val colorsMap: MutableMap<String, Int> = mutableMapOf(Pair("main_color", 0),
        Pair("shade_color", 0),
        Pair("light_color1", 0),
        Pair("light_color2", 0)
        )

    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
    }

    fun getColorMap(): MutableMap<String, Int> {
        return colorsMap
    }

    fun setColorMap(colorList: List<Int>){
        colorsMap["main_color"] = colorList[0]
        colorsMap["shade_color"] = colorList[1]
        colorsMap["light_color1"] = colorList[2]
        colorsMap["light_color2"] = colorList[3]
    }

    init{
        colorsMap["main_color"] = colorList[0]
        colorsMap["shade_color"] = colorList[1]
        colorsMap["light_color1"] = colorList[2]
        colorsMap["light_color2"] = colorList[3]
    }
}
