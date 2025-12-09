package com.example.nomadx.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nomadx.R
import com.example.nomadx.data.db.PostEntity
import com.example.nomadx.viewmodel.MainViewModel

class FeedFragment : Fragment(R.layout.fragment_feed) {
    private val vm: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val rv = view.findViewById<RecyclerView>(R.id.recyclerView)
        rv.layoutManager = LinearLayoutManager(context)

        vm.allPosts.observe(viewLifecycleOwner) { posts ->
            rv.adapter = PostAdapter(posts)
        }
    }

    class PostAdapter(private val list: List<PostEntity>) : RecyclerView.Adapter<PostAdapter.VH>() {
        class VH(v: View) : RecyclerView.ViewHolder(v) {
            val title: TextView = v.findViewById(R.id.itemTitle)
            val price: TextView = v.findViewById(R.id.itemPrice)
            val desc: TextView = v.findViewById(R.id.itemDesc)
            val author: TextView = v.findViewById(R.id.itemAuthor)
        }
        override fun onCreateViewHolder(parent: ViewGroup, t: Int) = VH(android.view.LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false))
        override fun onBindViewHolder(h: VH, pos: Int) {
            val p = list[pos]
            h.title.text = p.title
            h.price.text = "${p.price} ₸"
            h.desc.text = p.desc
            h.author.text = p.authorEmail
        }
        override fun getItemCount() = list.size
    }
}