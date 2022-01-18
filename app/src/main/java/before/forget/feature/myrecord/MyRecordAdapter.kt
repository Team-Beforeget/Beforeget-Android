package before.forget.feature.myrecord

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import before.forget.data.remote.response.ResponseMyRecordAll
import before.forget.databinding.ItemRecordBinding
import before.forget.feature.myrecord.detail.MyRecordDetailActivity

class MyRecordAdapter : RecyclerView.Adapter<MyRecordAdapter.MyRecordViewHolder>() {

    // val recordList = mutableListOf<ResponseMyRecordAll>()
    val recordList = mutableListOf<ResponseMyRecordAll>()

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

        fun onBind(data: ResponseMyRecordAll) {
            binding.myrecord = data
            itemView.setOnClickListener {
                // 1: Movie , 2: Book, 3:  TV , 4: Music, 5: Webtoon, 6: Youtube
                val media = 6
                val recordToDetailIntent =
                    Intent(itemView.context, MyRecordDetailActivity::class.java)
                recordToDetailIntent.putExtra("media", media)
                ContextCompat.startActivity(itemView.context, recordToDetailIntent, null)
            }
        }
    }
}
