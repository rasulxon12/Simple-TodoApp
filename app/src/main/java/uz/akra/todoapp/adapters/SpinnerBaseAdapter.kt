package uz.akra.todoapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import uz.akra.todoapp.R
import uz.akra.todoapp.modes.Degree

class SpinnerBaseAdapter(var list: List<Degree>):BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView: View
        if (convertView == null) {
            itemView =
                LayoutInflater.from(parent?.context).inflate(R.layout.spin_item, parent, false)
        } else {
            itemView = convertView
        }

        itemView.findViewById<TextView>(R.id.spin_text).text = list[position].name
        if (list[position].flag != -1) {
            itemView.findViewById<ImageView>(R.id.spin_image).setImageResource(list[position].flag)

        }
        return itemView
    }
}