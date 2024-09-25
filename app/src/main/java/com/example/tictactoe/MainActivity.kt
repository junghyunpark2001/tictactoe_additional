package com.example.tictactoe

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()
    private lateinit var openDrawerButton: ImageButton
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        //lateinit var binding: ActivityMainBinding
        //계속 Binding 클래스가 없다고 오류가 뜸... gradle에 정상적으로 추가도해주었는데. 일단 없이 진행.


        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // 아래 설정 때문에 방향에 따라 글자,버튼 등 안보임 -> 왠진 모름
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        drawerLayout = findViewById(R.id.drawerLayout)
        openDrawerButton = findViewById((R.id.imageButton))

        openDrawerButton.setOnClickListener{
            if (!drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.openDrawer(GravityCompat.END)
            }
        }
//
//        findViewById<ImageButton>(R.id.imageButton).setOnClickListener {
//            if(drawerLayout.isDrawerOpen(GravityCompat.START)){
//                drawerLayout.closeDrawer(GravityCompat.START)
//            }
//            else{
//                drawerLayout.openDrawer(GravityCompat.START)
//            }
//        }
        viewModel.box.observe(this){ box ->
            updateUI(box)
            viewModel.CheckFinish()}

        viewModel.restart.observe(this){ res ->
            if (res == 0)
                findViewById<TextView>(R.id.button).text = "초기화"
            else
                findViewById<TextView>(R.id.button).text = "한판 더"
        }

        viewModel.player.observe(this){play ->
            when(play)
            {
                0 -> findViewById<TextView>(R.id.order).text = "O의 차례입니다"
                1 -> findViewById<TextView>(R.id.order).text ="X의 차례입니다"
                2 -> findViewById<TextView>(R.id.order).text ="게임 오버"
                3 -> findViewById<TextView>(R.id.order).text ="무승부"
            }
        }

        findViewById<TextView>(R.id.box_1).setOnClickListener { viewModel.PressBox(1) } // 클릭하면 pressbox 함수가 실행되어 데이터가 변한다.
        findViewById<TextView>(R.id.box_2).setOnClickListener { viewModel.PressBox(2) }
        findViewById<TextView>(R.id.box_3).setOnClickListener { viewModel.PressBox(3) }
        findViewById<TextView>(R.id.box_4).setOnClickListener { viewModel.PressBox(4) }
        findViewById<TextView>(R.id.box_5).setOnClickListener { viewModel.PressBox(5) }
        findViewById<TextView>(R.id.box_6).setOnClickListener { viewModel.PressBox(6) }
        findViewById<TextView>(R.id.box_7).setOnClickListener { viewModel.PressBox(7) }
        findViewById<TextView>(R.id.box_8).setOnClickListener { viewModel.PressBox(8) }
        findViewById<TextView>(R.id.box_9).setOnClickListener { viewModel.PressBox(9) }

        findViewById<TextView>(R.id.button).setOnClickListener {
            viewModel.restart()
        } // 클릭하면 restart 함수 실행되어 데이터 초기화.
    }

    fun updateUI(box : List<String>){
        findViewById<TextView>(R.id.box_1).text = box[1]
        findViewById<TextView>(R.id.box_2).text = box[2]
        findViewById<TextView>(R.id.box_3).text = box[3]
        findViewById<TextView>(R.id.box_4).text = box[4]
        findViewById<TextView>(R.id.box_5).text = box[5]
        findViewById<TextView>(R.id.box_6).text = box[6]
        findViewById<TextView>(R.id.box_7).text = box[7]
        findViewById<TextView>(R.id.box_8).text = box[8]
        findViewById<TextView>(R.id.box_9).text = box[9]
    }
}