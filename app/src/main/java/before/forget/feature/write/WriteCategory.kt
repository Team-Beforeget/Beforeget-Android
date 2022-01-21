package before.forget.feature.write

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WriteCategory(
    val id: Int,
    val category: String,
    var isSelected: Boolean
) : Parcelable
