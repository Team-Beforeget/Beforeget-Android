package before.forget.feature.dialog

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.viewbinding.ViewBinding
import before.forget.R
import before.forget.databinding.DialogBeforegetTwoButtonBinding

class BeforegetDialog(
    context: Context,
    private val subViewBinding: ViewBinding
) : AlertDialog(context) {
    private var onClickOkButton: ((View) -> Unit)? = null
    private var onClickCancelButton: ((View) -> Unit) = { dismiss() }

    private var okButtonText: String = "확인"
    private var cancelButtonText: String = "취소"

    @ColorRes
    private var okButtonTextColor: Int = R.color.black200

    @ColorRes
    private var cancelButtonTextColor: Int = R.color.gray200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DialogBeforegetTwoButtonBinding.inflate(layoutInflater).apply {
            dialogTopContainer.addView(subViewBinding.root)
            uiState = BeforegetDialogUIState(
                okButtonText,
                cancelButtonText,
                okButtonTextColor,
                cancelButtonTextColor,
                onClickOkButton,
                onClickCancelButton
            )
        }.also {
            setContentView(it.root)
            setTransparentWindowBackground()
        }
    }

    private fun setTransparentWindowBackground() {
        window?.setBackgroundDrawableResource(R.color.transparent)
    }

    fun setOkButtonText(
        @StringRes text: Int? = null,
        @ColorRes textColor: Int? = null,
    ) {
        this.okButtonText = context.resources.getString(text ?: R.string.okText)
        this.okButtonTextColor = textColor ?: R.color.black200
    }

    fun setOkButtonText(
        text: String? = null,
        @ColorRes textColor: Int? = null,
    ) {
        this.okButtonText = text ?: "확인"
        this.okButtonTextColor = textColor ?: R.color.black200
    }

    fun setOnClickOkButtonListener(listener: (View) -> Unit) {
        this.onClickOkButton = listener
    }

    fun setCancelButtonText(
        @StringRes text: Int? = null,
        @ColorRes textColor: Int? = null
    ) {
        this.cancelButtonText = context.resources.getString(text ?: R.string.cancelText)
        this.cancelButtonTextColor = textColor ?: R.color.gray200
    }

    fun setCancelButtonText(
        text: String? = null,
        @ColorRes textColor: Int? = null,
    ) {
        this.cancelButtonText = text ?: "취소"
        this.cancelButtonTextColor = textColor ?: R.color.gray200
    }

    fun setOnClickCancelButtonListener(listener: (View) -> Unit) {
        this.onClickCancelButton = listener
    }
}

data class BeforegetDialogUIState(
    val okButtonText: String,
    val cancelButtonText: String,
    @ColorRes val okButtonTextColor: Int,
    @ColorRes val cancelButtonTextColor: Int,
    val onClickOkButton: ((View) -> Unit)?,
    val onClickCancelButton: (View) -> Unit
)
