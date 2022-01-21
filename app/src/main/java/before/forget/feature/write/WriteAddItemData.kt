package before.forget.feature.write

import androidx.databinding.ObservableBoolean

data class WriteAddItemData(
    val item: String,
    val isSelected: ObservableBoolean,
    val onClickEventListener: (() -> Unit)? = null
) {
    fun onClick() {
        isSelected.set(!isSelected.get())
        onClickEventListener?.invoke()
    }
}
