package before.forget.util

import android.content.Context
import android.widget.Toast
import kotlin.math.roundToInt

fun Context.dpToPixel(dp: Int): Int =
    (dp * resources.displayMetrics.density).roundToInt()

fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
