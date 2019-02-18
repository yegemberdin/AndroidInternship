package com.example.myapplication

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.list_item.view.*
import java.io.IOException

class ListAdapter(val items : ArrayList<Data>, val hashMap: HashMap<Int, Uri?>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    private  var recyclerListener:RecyclerListener?=null;

    fun setListener(listener: RecyclerListener) {
        this.recyclerListener = listener
    }

    override fun getItemCount(): Int {
        return items.size
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data: Data=items.get(position);

        if(hashMap.containsKey(position)){
            if(hashMap.get(position)!=null){
                val receivedUri: Uri?=hashMap.get(position)
                try {
                    Log.d("receiveurl","ok");

                    val bitmap=MediaStore.Images.Media.getBitmap(this.context.contentResolver,receivedUri);
                    holder?.img?.setImageBitmap(bitmap)
                    Log.d("setimg","ok");

                }
                catch (e: IOException) {
                    e.printStackTrace();
                }

            }
           }

        else holder?.img?.setImageResource(data.getImage_drawables())

        holder?.txtName?.setText(data.getNames())
        holder?.txtSurname?.setText(data.getSurnames())
        holder.itemView.setOnClickListener { recyclerListener!!.openSecondActivity(data,holder?.adapterPosition) }

    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//edits
    public var txtName: TextView?=null
    public var txtSurname: TextView?=null
    public var img: ImageView?=null
//    var position:Int?=null

    init {
        this.txtName = itemView?.findViewById<TextView>(R.id.nameTxt) as TextView
        this.txtSurname = itemView?.findViewById<TextView>(R.id.surnameTxt) as TextView
        this.img = itemView?.findViewById<ImageView>(R.id.itemImage) as ImageView
//        this.position=adapterPosition

    }
}