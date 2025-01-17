package ru.agafonovilya.daybook.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.agafonovilya.daybook.Injection
import ru.agafonovilya.daybook.R
import ru.agafonovilya.daybook.databinding.ItemHomeworkBinding
import ru.agafonovilya.daybook.model.entity.Homework

class HomeworkListRvAdapter(private val onItemClickListener: (Homework) -> Unit) :
    RecyclerView.Adapter<HomeworkListRvAdapter.HomeworkViewHolder>() {

    var homeworkList: List<Homework> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeworkViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_homework, parent, false)
        //Устанавливаю ширину элемента = 0.7 ширины экрана
        view.layoutParams.width = (parent.width * 0.7).toInt()
        return HomeworkViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: HomeworkViewHolder, position: Int) {
        holder.bind(homeworkList[position])
    }

    override fun getItemCount() = homeworkList.size

    inner class HomeworkViewHolder(itemView: View, private val onItemClickListener: (Homework) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val imageLoader = Injection.provideImageLoader()
        private val binding = ItemHomeworkBinding.bind(itemView)

        fun bind(homework: Homework) {
            binding.tvLesson.text = homework.lesson
            binding.tvDeadline.text = homework.deadline
            binding.tvDescription.text = homework.description
            imageLoader.loadInto(homework.image, binding.ivImage)

            itemView.setOnClickListener {
                onItemClickListener(homework)
            }
        }
    }

}