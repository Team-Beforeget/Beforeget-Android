package before.forget.feature.myrecord

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import before.forget.data.local.MyRecordData
import before.forget.databinding.ItemRecordBinding

class MyRecordAdapter : RecyclerView.Adapter<MyRecordAdapter.MyRecordViewHolder>() {

    val recordList = mutableListOf<MyRecordData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecordViewHolder {
        val binding = ItemRecordBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MyRecordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyRecordViewHolder, position: Int) {
        holder.onBind(recordList[position])
    }

    override fun getItemCount(): Int = recordList.size

    class MyRecordViewHolder(private val binding: ItemRecordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: MyRecordData) {
            binding.myrecord = data
        }
    }
}
