package com.example.tictactoe

data class TicTacToeState(
    val board: List<String>, // 보드 상태 (각 타일의 값)
    val currentPlayer: Int? // 현재 플레이어 (0: O, 1: X, 2: 게임 오버, 3: 무승부)
)
