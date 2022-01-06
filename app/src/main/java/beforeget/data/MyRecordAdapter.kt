package beforeget.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beforeget.databinding.ItemRecordBinding

class MyRecordAdapter : RecyclerView.Adapter<MyRecordAdapter.MyRecordViewHolder>() {

    val recordlist = mutableListOf<MyrecordData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecordViewHolder {
        val binding = ItemRecordBinding.inflate(
            LayoutInflater.from(
                parent.context
            ), parent, false
        )
        return MyRecordViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyRecordViewHolder, position: Int) {
        holder.onBind(recordlist[position])

    }

    override fun getItemCount(): Int = recordlist.size

    class MyRecordViewHolder(private val binding: ItemRecordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: MyrecordData) {
            binding.myrecord = data
        }

    }
}