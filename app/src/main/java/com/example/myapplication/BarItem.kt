package com.example.myapplication

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class BarItem:LinearLayout {
    //回调状态
    var selectedCallback:((Int)->Unit)?=null
    //记录控件的序号
    var index = -1
    //正常图片
    var nonorImage = 0
    //被选中时的图片
     var selectImage = 0
    //被选中时的颜色,lateinit是稍后初始化，Int类型是没法稍后初始化的
    //初始化时必须要附一个初始值，而现在不想给它初始值就可以用稍后初始化
      var selectColor = 0
    //控件的标题 稍后初始化就表示待会儿一定会有
       var title = ""
    set(value) {
        field = value
        titleTextView?.text = field
    }
    //记录当前点击的状态
     var misSelected = false
    set(value) {
        field = value
        //更新状态
        upDateUI()
    }
    //图片与文本
    private  var iconImageView:ImageView? = null
    private  var titleTextView:TextView? = null
//更新状态图片
    private fun upDateUI() {
      if (misSelected){
          //被选中,切换为选中
          iconImageView?.setImageResource(selectImage)
          titleTextView?.setTextColor(selectColor)
      }else{
          //没有被选中， 切换为未选中状态
          iconImageView?.setImageResource(nonorImage)
          titleTextView?.setTextColor(resources.getColor(R.color.black))
      }
    }

    //构造函数,构造函数就是创建该控件的时候调用
    constructor(context: Context):super(context){
        initView()
    }
    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet){
        //解析
        val typedArray = context.obtainStyledAttributes(attributeSet,R.styleable.BarItem)
        //正常图片
        nonorImage = typedArray.getResourceId(R.styleable.BarItem_normalImage,R.drawable.home)
        //被选中时的图片
        selectImage = typedArray.getResourceId(R.styleable.BarItem_selectImage,R.drawable.home_selected)
        //被选中时的颜色
        //getColor得到的返回值是一个整形
        selectColor = typedArray.getColor(R.styleable.BarItem_selectColor,resources.getColor(R.color.red))
        //控件的标题 text是用的稍后初始化，所以这个是一定有的
        title = typedArray.getString(R.styleable.BarItem_title).toString()
        //控件的初始状态
        misSelected = typedArray.getBoolean(R.styleable.BarItem_misSelected,false)
        initView()
    }
    //初始化控件，并添加内容（图片和文字）
    private fun initView(){
        orientation = VERTICAL
        gravity = Gravity.CENTER
        //添加图片
     iconImageView = ImageView(context).apply {
        }.also {
            val lp = LinearLayout.LayoutParams(dp2px(32),dp2px(32))
            addView(it,lp)
        }
        //添加文字
    titleTextView = TextView(context).also {
            val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT )
            addView(it,lp)
        }
    }
    //将dp值转换为px值
    private fun dp2px(dp:Int) = (resources.displayMetrics.density*dp).toInt()
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN){
            if (!misSelected){
                misSelected = true
                //回调被点击的事件
                selectedCallback?.let {
                    it(index)
                }
            }
        }
        return true
    }
}