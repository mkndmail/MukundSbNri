package com.example.mukundsbnri

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mukundsbnri.databinding.LayoutGithubReposBinding
import com.example.mukundsbnri.entity.RepoEntity

/**
 * Created by Mukund, mkndmail@gmail.com on 30, May, 2020
 */

class RepoAdapter(private val itemClicked: ItemClicked):ListAdapter<RepoEntity,RecyclerView.ViewHolder>(RepoDiffCallBack()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val githubRepo=getItem(position)
        val myViewHolder=(holder) as ViewHolder
        myViewHolder.bind(githubRepo)

        holder.itemView.setOnClickListener {
           itemClicked.openUrl(githubRepo.url)
        }
    }

}

class ViewHolder private constructor(private val binding: LayoutGithubReposBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(repoEntity: RepoEntity) {
        binding.repoEntity = repoEntity
        binding.executePendingBindings()
    }
    companion object{
        fun from(parent: ViewGroup):RecyclerView.ViewHolder{
            val layoutInflater=LayoutInflater.from(parent.context)
            val binding=LayoutGithubReposBinding.inflate(layoutInflater,parent,false)
            return ViewHolder(binding)
        }
    }
}

class RepoDiffCallBack: DiffUtil.ItemCallback<RepoEntity>() {
    override fun areItemsTheSame(oldItem: RepoEntity, newItem: RepoEntity): Boolean {
       return  oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: RepoEntity, newItem: RepoEntity): Boolean {
        return oldItem==newItem
    }


}

interface ItemClicked{
    fun openUrl(url:String)
}

