package com.example.tictactoe

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TicTacToeAdapter(
    private var historyList: MutableList<TicTacToeState>,
    private val onRevertClick: (Int) -> Unit
) : RecyclerView.Adapter<TicTacToeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val boardTextView: TextView = view.findViewById(R.id.board_text)
        val messageTextView: TextView = view.findViewById(R.id.message_text)
        val revertButton: Button = view.findViewById(R.id.btn_revert)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tictactoe_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentState = historyList[position]
        holder.boardTextView.text = currentState.board.joinToString(" | ")  // 간단히 상태를 표시
        holder.messageTextView.text = currentState.currentPlayer.toString()

        holder.revertButton.setOnClickListener{
            onRevertClick(position)


        }
    }
    override fun getItemCount(): Int {
        return historyList.size
    }
    fun updateHistory(newHistoryList: MutableList<TicTacToeState>) {
        historyList = newHistoryList
        notifyDataSetChanged()  // 데이터 변경을 알리고 RecyclerView를 새로고침
    }
}
