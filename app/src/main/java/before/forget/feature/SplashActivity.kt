package before.forget.feature

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import before.forget.R
import before.forget.feature.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Beforeget)
        super.onCreate(savedInstanceState)
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
        }
        finish()
    }
}
