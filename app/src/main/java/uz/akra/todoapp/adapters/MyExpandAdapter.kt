package uz.akra.todoapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import uz.akra.todoapp.R
import uz.akra.todoapp.modes.MyToDo

class MyExpandAdapter (var titleList:ArrayList<String>, var map:HashMap<String, ArrayList<MyToDo>>) :BaseExpandableListAdapter()  {
    override fun getGroupCount(): Int {
        return titleList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return map[titleList[groupPosition]]?.size!!
    }

    override fun getGroup(groupPosition: Int): Any {
        return titleList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        val group = titleList[groupPosition]
        val child = map[group]!!
        return child[childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var itemView:View
        if (convertView == null){
            itemView = LayoutInflater.from(parent?.context).inflate(R.layout.item_parent, parent, false)
        }else{
            itemView = convertView
        }
        itemView.findViewById<TextView>(R.id.tv_item_parent)
            .text = titleList[groupPosition]
        return itemView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var itemView:View
        if (convertView  == null){
            itemView = LayoutInflater.from(parent?.context).inflate(R.layout.item_child, parent, false)
        }else{
            itemView = convertView
        }
        itemView.findViewById<TextView>(R.id.tv_item_child)
            .text = map[titleList[groupPosition]]!!.get(childPosition).title

        return itemView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}