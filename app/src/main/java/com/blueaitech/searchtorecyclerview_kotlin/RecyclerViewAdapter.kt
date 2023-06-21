package com.blueaitech.searchtorecyclerview_kotlin

import android.R
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class RecyclerViewAdapter(val arrayList: ArrayList<String>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() , Filterable {
    /**儲存最原先ArrayList的狀態(也就是充當回複RecyclerView最原先狀態的陣列) */
    private var arrayListFilter = ArrayList<String>()
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvItem: TextView = itemView.findViewById(R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /**這裡把初始陣列複製進去了 */
        holder.tvItem.text = arrayList!![position]
        arrayListFilter!!.addAll(arrayList!!)
    }

    override fun getItemCount(): Int {
        return arrayList!!.size
    }

    override fun getFilter(): Filter? {
        return mFilter
    }

    /**使用Filter濾除方法 */
    private var mFilter: Filter = object : Filter() {
        /**此處為正在濾除字串時所做的事 */
        override fun performFiltering(constraint: CharSequence): FilterResults {
            /**先將完整陣列複製過去 */
            val filteredList = ArrayList<String>()
            /**如果沒有輸入，則將原本的陣列帶入 */
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(arrayListFilter!!)
            } else {
                /**如果有輸入，則照順序濾除相關字串
                 * 如果你有更好的搜尋演算法，就是寫在這邊 */
                for (movie in arrayListFilter!!) {
                    if (movie.lowercase(Locale.getDefault()).contains(constraint.toString().lowercase(Locale.getDefault()))){
                        filteredList.add(movie)
                    }
                }
            }
            /**回傳濾除結果 */
            val filterResults = FilterResults()
            filterResults.values = filteredList
            return filterResults
        }

        /**將濾除結果推給原先 RecyclerView 綁定的陣列，並通知 RecyclerView刷新 */
        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            arrayList!!.clear()
            arrayList!!.addAll((results.values as Collection<String>))
            notifyDataSetChanged()
        }
    }
}