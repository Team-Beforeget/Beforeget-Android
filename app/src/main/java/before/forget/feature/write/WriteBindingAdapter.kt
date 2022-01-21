package before.forget.feature.write

import android.view.View
import androidx.databinding.BindingAdapter

object WriteBindingAdapter {
    @JvmStatic
    @BindingAdapter("isSelected")
    fun isSelectedView(view: View, isSelected: Boolean) {
        view.isSelected = isSelected
    }
}
