package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import java.lang.reflect.Array
import kotlin.jvm.internal.Intrinsics

class TableBar:LinearLayout {
    //记录先前点击的序号
    private var currunt = -1
    //共有多少个item
    var number = 0
    //保存所有item的所有模型数据
    var itemModel:kotlin.Array<ItemModel> = emptyArray<ItemModel>()
    set(value) {
        field = value
        //数据一来就调用某个方法，就在数据来的路上添加set方法，在set方法里面调用，
        upDataUI()
    }
    //保存创建的对象
    val items = mutableListOf<BarItem>()


    //在调用一个控件时，是先调用xml里面的配置，根据配置调用xml构造方法，然后在调用代码的构造方法
    constructor(context: Context):super(context){
        initView()
    }
    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet){
        //解析整体
        val typedArray = context.obtainStyledAttributes(attributeSet,R.styleable.TableBar)
        //解析单个
        number = typedArray.getInt(R.styleable.TableBar_number,4)
        typedArray.recycle()
        initView()
    }
    //初始化整体
    private fun initView(){
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
                for(i in 0 until number){
                    BarItem(context).also {
                        //创建对象但是没有添加内容
                        //lp是设置空间的宽高，加的哪个控件就对哪个控件的宽高进行设置
                        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                                weight = 1f
                        }
                        addView(it,lp)
                        //为控件添加监听事件
                        it.selectedCallback = {
                            //还原之前的控件
                            items[currunt].misSelected = false
                            //保存索引
                            currunt = it
                        }
                        items.add(it)
                    }
                }
    }
    //将传过来的内容添加到一个个的创建好的控件上
    private fun upDataUI() {
        for ((i,item) in items.withIndex()){
            item.apply {
                val model = itemModel[i]
                index = i
                nonorImage = model.normalImageView
                selectImage = model.selectImageView
                selectColor = model.selectColor
                title = model.title
                misSelected = model.misSelected
                if (misSelected){
                    currunt = i
                }
            }
        }
    }
}