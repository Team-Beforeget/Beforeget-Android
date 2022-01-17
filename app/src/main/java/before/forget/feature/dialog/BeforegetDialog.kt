package before.forget.feature.dialog

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.databinding.ViewDataBinding
import before.forget.R
import before.forget.databinding.DialogBeforegetTwoButtonBinding

class BeforegetDialog(
    context: Context,
    private val subViewBinding: ViewDataBinding
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
        }
    }

    fun setOnClickOkButtonListener(
        text: String = "확인",
        @ColorRes textColor: Int = R.color.black200,
        listener: (View) -> Unit
    ) {
        this.okButtonText = text
        this.okButtonTextColor = textColor
        this.onClickOkButton = listener
    }

    fun setOnClickOkButtonListener(
        @StringRes text: Int = R.string.okText,
        @ColorRes textColor: Int = R.color.black200,
        listener: (View) -> Unit
    ) {
        this.okButtonText = context.resources.getString(text)
        this.okButtonTextColor = textColor
        this.onClickOkButton = listener
    }

    fun setOnClickCancelButtonListener(
        text: String = "취소",
        @ColorRes textColor: Int = R.color.gray200,
        listener: (View) -> Unit
    ) {
        this.cancelButtonText = text
        this.cancelButtonTextColor = textColor
        this.onClickCancelButton = listener
    }

    fun setOnClickCancelButtonListener(
        @StringRes text: Int = R.string.cancelText,
        @ColorRes textColor: Int = R.color.gray200,
        listener: (View) -> Unit
    ) {
        this.cancelButtonText = context.resources.getString(text)
        this.cancelButtonTextColor = textColor
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
