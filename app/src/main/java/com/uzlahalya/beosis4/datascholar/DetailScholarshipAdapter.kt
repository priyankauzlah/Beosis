//package com.uzlahalya.beosis4.datascholar
//
//import android.app.Activity
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.uzlahalya.beosis4.R
//
//class DetailScholarshipAdapter (var data : ArrayList<Scholarship>, var context: Activity?, var clickListener: ScholarshipAdapter.onScholarshipItemClickListener) : RecyclerView.Adapter<ScholarshipAdapter.MyViewHolder>() {
//
//    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){
//        val scholarshipLogo = view.findViewById<ImageView>(R.id.iv_image_detailscholarship)
//        val scholarshipName = view.findViewById<TextView>(R.id.tv_title_scholarship_detailscholarship)
//        val scholarshipUniversity = view.findViewById<TextView>(R.id.tv_uni_detailscholarship)
//        val scholarshipCountry = view.findViewById<TextView>(R.id.tv_country_detailscholarship)
//        val scholarshipDegree = view.findViewById<TextView>(R.id.tv_degree_detail_scholarship)
//        val scholarshipOpenRegistration = view.findViewById<TextView>(R.id.tv_date_openregistration_detailscholarship)
//        val scholarshipCloseregistration = view.findViewById<TextView>(R.id.tv_date_deadline_detailscholarship)
//
//
//
//        fun initialize(item: Scholarship,  action: ScholarshipAdapter.onScholarshipItemClickListener){
//            scholarshipLogo.setImageResource(item.logo)
//            scholarshipName.text = item.name
//            scholarshipUniversity.text = item.university
//            scholarshipCountry.text = item.country
//            scholarshipDegree.text = item.degree
//            scholarshipOpenRegistration.text = item.openregistration
//            scholarshipCloseregistration.text = item.closeregistration
//
//            itemView.setOnClickListener {
//                action.onItemClick(item, adapterPosition)
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_scholarship, parent, false)
//        return MyViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
////        holder.chatName.text = data[position].nameChat
////        holder.chatPreview.text = data[position].previewChat
////        holder.chatImage.setImageResource(data[position].imagePhoto)
//        holder.initialize(data.get(position),clickListener)
//    }
//
//    override fun getItemCount(): Int {
//        return data.size
//    }
//
//    interface onDetailScholarshipItemClickListener{
//        fun onItemClick(item: Scholarship, position: Int)
//    }
//
//}