package before.forget.feature.myrecord.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import before.forget.data.remote.response.ResponseDetail
import before.forget.databinding.*

class DetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var dataList = mutableListOf<ResponseDetail.Additional>()

    override fun getItemViewType(position: Int): Int {
        return dataList[position].viewType
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding: ViewDataBinding
        return when (viewType) {
            multi_album -> {
                binding = ItemAlbumDetailBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                AlbumViewHolder(binding)
            }
            multi_genre -> {
                binding = ItemGenreDetailBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                GenreViewHolder(binding)
            }
            multi_image -> {
                binding = ItemImageDetailBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                ImageViewHolder(binding)
            }
            multi_link -> {
                binding = ItemLinkDetailBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                LinkViewHolder(binding)
            }
            multi_ost -> {
                binding = ItemOstDetailBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                OstViewHolder(binding)
            }
            multi_qutoe -> {
                binding = ItemQutoeDetailBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                QuetoViewHolder(binding)
            }
            multi_text -> {
                binding = ItemTextDetailBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                TextViewHolder(binding)
            }
            multi_textNoLine -> {
                binding = ItemTextnolineDataBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                TextNoLineViewHolder(binding)
            }
            else -> {
                binding = ItemTimestampDetailBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                TimeStampViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (dataList[0]?.viewType) {
            multi_album -> {
                (holder as AlbumViewHolder).onBind(dataList[position])
                holder.setIsRecyclable(false)
            }
            multi_genre -> {
                (holder as GenreViewHolder).onBind(dataList[position])
                holder.setIsRecyclable(false)
            }
            multi_image -> {
                (holder as ImageViewHolder).onBind(dataList[position])
                holder.setIsRecyclable(false)
            }
            multi_link -> {
                (holder as LinkViewHolder).onBind(dataList[position])
                holder.setIsRecyclable(false)
            }
            multi_ost -> {
                (holder as OstViewHolder).onBind(dataList[position])
                holder.setIsRecyclable(false)
            }
            multi_qutoe -> {
                (holder as QuetoViewHolder).onBind(dataList[position])
                holder.setIsRecyclable(false)
            }
            multi_text -> {
                (holder as TextViewHolder).onBind(dataList[position])
                holder.setIsRecyclable(false)
            }
            multi_timestamp -> {
                (holder as TimeStampViewHolder).onBind(dataList[position])
                holder.setIsRecyclable(false)
            }
            multi_textNoLine -> {
                (holder as TextNoLineViewHolder).onBind(dataList[position])
                holder.setIsRecyclable(false)
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    inner class AlbumViewHolder(val binding: ItemAlbumDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseDetail.Additional) {
            binding.albumData = data
        }
    }

    inner class GenreViewHolder(val binding: ItemGenreDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseDetail.Additional) {
            binding.genreData = data
        }
    }

    inner class ImageViewHolder(val binding: ItemImageDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseDetail.Additional) {
            binding.imageData = data
            // binding.ivCoverDetailBook.setImageURI(data.imgUrl1)
        }
    }

    inner class LinkViewHolder(val binding: ItemLinkDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseDetail.Additional) {
            binding.linkData = data
            // binding.tvLinkContentDetailMusic.text = data.imgUrl1.toString()
        }
    }

    inner class OstViewHolder(val binding: ItemOstDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseDetail.Additional) {
            binding.ostData = data
        }
    }

    inner class QuetoViewHolder(val binding: ItemQutoeDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseDetail.Additional) {
            binding.quotesData = data
        }
    }

    inner class TextViewHolder(val binding: ItemTextDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseDetail.Additional) {
            binding.textData = data
        }
    }

    inner class TimeStampViewHolder(val binding: ItemTimestampDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseDetail.Additional) {
            binding.timestampData = data
        }
    }

    inner class TextNoLineViewHolder(val binding: ItemTextnolineDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseDetail.Additional) {
            binding.textNoLineData = data
        }
    }
}
