package com.example.myapplications

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.findViewTreeViewModelStoreOwner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val edtext = findViewById<EditText>(R.id.editTextText)
        val tvtext = findViewById<TextView>(R.id.tvtxt)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val btn = findViewById<Button>(R.id.button3)
        val tvname = findViewById<TextView>(R.id.tvname)
        val tvwinner = findViewById<TextView>(R.id.tvwinner)
        val tvmyguess = findViewById<TextView>(R.id.tvmy)
        val tvcomputerguess = findViewById<TextView>(R.id.tvcomputer)

        btn.setOnClickListener(){
            if (edtext.text.isEmpty()) {
                tvtext.text="請輸入玩家姓名"
                return@setOnClickListener
            }
            val playername = edtext.text.toString()
            val computerguess=(0..2).random()
            val myguess = when(radioGroup.checkedRadioButtonId){
                R.id.radioButton2 -> 0
                R.id.radioButton -> 1
                R.id.radioButton3 -> 2
                else-> 3
            }
            tvname.text="$playername"
            tvmyguess.text="${getstring(myguess)}"
            tvcomputerguess.text="${getstring(computerguess)}"
            when{
                myguess==computerguess->{
                    tvwinner.text="平手"
                    tvtext.text="平局，請在試一次"
                }
                (myguess ==0 && computerguess == 2)||
                        (myguess == 1 && computerguess == 0)||
                        (myguess == 2 && computerguess == 1)->{
                    tvwinner.text="$playername"
                    tvtext.text="恭喜你獲勝了"
                }
                else->{
                    tvwinner.text="電腦"
                    tvtext.text="可惜，電腦獲勝了"
                }
            }
        }
    }
    private fun getstring(txt:Int):String{
        return when (txt){
            0->"剪刀"
            1->"石頭"
            2->"布"
            else ->"你還沒出拳"
        }
    }
}