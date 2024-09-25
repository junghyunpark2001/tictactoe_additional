package com.example.tictactoe//package com.example.tictactoe
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//
//class TurnAdapter(
//    private val turns: List<TurnRecord>,
//    private val revertListener:(Int)->Unit
//    ): RecyclerView.Adapter<TurnAdapter.TurnViewHolder>() {
//        inner class TurnViewHolder(view: View) : RecyclerView.ViewHolder(view){
//            val boardTextView: TextView = view.findViewById(R.id.board_state)
//            val revertButton: Button = view.findViewById(R.id.revert_button)
//        }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TurnViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.turn_item, parent, false)
//        return TurnViewHolder(view)
//    }
//    override fun onBindViewHolder(holder: TurnViewHolder, position: Int){
//        val turn = turnList[position]
//
//    }
//
//}