package com.example.myapplication

import android.widget.ImageView

data class ItemModel (
    //data class 数据类，没有任何的计算，仅仅是封装，将散零的数据封装成一个整体
    var normalImageView: Int,
    var selectImageView: Int,
    var selectColor: Int,
    var title: String,
    var misSelected :Boolean
        )