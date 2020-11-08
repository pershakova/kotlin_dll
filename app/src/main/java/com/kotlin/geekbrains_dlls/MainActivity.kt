package com.kotlin.geekbrains_dlls

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.geekbrains_dlls.mvp.presenter.MainPresenter
import com.kotlin.geekbrains_dlls.mvp.view.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView{
    val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listener1 = View.OnClickListener {
            setButtonText1(presenter.counterClick1(0))
        }

        val listener2 = View.OnClickListener {
            setButtonText2(presenter.counterClick1(1))
        }

        val listener3 = View.OnClickListener {
            setButtonText3(presenter.counterClick1(2))
        }

        btn_counter1.setOnClickListener(listener1)
        btn_counter2.setOnClickListener(listener2)
        btn_counter3.setOnClickListener(listener3)
    }

    override fun setButtonText1(text: String) {
        btn_counter1.text = text
    }

    override fun setButtonText2(text: String) {
        btn_counter2.text = text
    }

    override fun setButtonText3(text: String) {
        btn_counter3.text = text
    }
}