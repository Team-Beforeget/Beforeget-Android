package before.forget.feature.dialog

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View

class BeforegetDialog(
    context: Context
) : AlertDialog(context) {
    private var onClickOkButton: ((View) -> Unit)? = null
    private var onClickCancelButton: ((View) -> Unit) = { dismiss() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun setOnClickOkButtonListener(listener: (View) -> Unit) {
        this.onClickOkButton = listener
    }

    fun setOnClickCancelButtonListener(listener: (View) -> Unit) {
        this.onClickCancelButton = listener
    }
}

data class BeforegetDialogUIState(
    private val onClickOkButton: ((View) -> Unit)?,
    private val onClickCancelButton: (View) -> Unit
)
