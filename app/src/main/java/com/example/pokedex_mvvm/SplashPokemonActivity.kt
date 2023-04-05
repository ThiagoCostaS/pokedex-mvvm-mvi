package com.example.pokedex_mvvm

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout

class SplashActivity : AppCompatActivity() {
    private val motionLayout by lazy { findViewById<MotionLayout>(R.id.motion_layout) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        motionLayout.transitionToEnd()

        // Definindo o layout da Splash Activity
        setContentView(R.layout.activity_splash)

        // Definindo um tempo de exibição para a Splash Activity
        Handler().postDelayed({
            startActivity(Intent(this, PokemonActivity::class.java))
            finish()
        }, 3000) // 3000 milissegundos = 3 segundos
    }
}