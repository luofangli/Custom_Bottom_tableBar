package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tableBar.itemModel = arrayOf(
            ItemModel(
                R.drawable.home,
                R.drawable.home_selected,
                R.color.red,
                "主页",
                true
            ),
            ItemModel(
                R.drawable.circle,
                R.drawable.circle_selected,
                R.color.red,
                "朋友圈",
                false
            ),
            ItemModel(
                R.drawable.me,
                R.drawable.me_selected,
                R.color.red,
                "我",
                false
            ),
            ItemModel(
                R.drawable.video,
                R.drawable.video_selected,
                R.color.red,
                "视频",
                false
            )
        )
    }
}