package com.example.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _box: MutableLiveData<List<String>> = MutableLiveData(List(10){""})
    val box: LiveData<List<String>> get() = _box  // 외부에 공개하는 데이터. 여기서는 필요 없나?

    private var _player: MutableLiveData<Int> = MutableLiveData(0) // 0 : O의 차례입니다, 1 : X의 차례입니다, 2 : 게임 오버, 3 : 무승부
    val player: LiveData<Int> = _player
    // 얘로도 업데이트 되니까.

    private var _count : Int = 0

    private var _restart : MutableLiveData<Int> = MutableLiveData(0) // 한판 더 버튼 활성화 여부. restart 1 되면 '재시작'으로 활성화. (UI가 바뀜)
    val restart : LiveData<Int> = _restart


    fun PressBox(index: Int){
        if (_box.value?.get(index)?.isEmpty() == true && (_player.value == 0 || _player.value == 1)){
            val updatedBox = _box.value?.toMutableList() ?: return
            updatedBox[index] = if (_player.value == 0) "O" else "X" // 0이면 O를 추가.
            _box.value = updatedBox
            // Box 들의 리스트를 계속 바꾸는. 그리고 각 인덱스는 box의 아이디를 의미함.

            if (_restart.value==0&&_player.value == 0)
                _player.value = 1
            else if (_restart.value==0&&_player.value == 1)
                _player.value = 0
            else if (_restart.value == 2)
                _player.value = 2
            else if (_restart.value == 1)
                _player.value = 3



            _count = _count + 1
        }
    }
        // Box들을 눌렀을때 일어나는 일. 타일이 채워지고, 플레이어 O, X가 변하고(그에 따라 문구도 변하고), 게임 횟수가 늘어남.
    // 기존 보드가 채워지지 않았는데도 클릭이 더이상 안됨.
    fun CheckFinish(){
        if (_box.value?.get(1) == "X" && _box.value?.get(2) == "X" && _box.value?.get(3) == "X" ||
            _box.value?.get(4) == "X" && _box.value?.get(5) == "X" && _box.value?.get(6) == "X" ||
            _box.value?.get(7) == "X" && _box.value?.get(8) == "X" && _box.value?.get(9) == "X" ||
            _box.value?.get(1) == "X" && _box.value?.get(4) == "X" && _box.value?.get(7) == "X" ||
            _box.value?.get(2) == "X" && _box.value?.get(5) == "X" && _box.value?.get(8) == "X" ||
            _box.value?.get(3) == "X" && _box.value?.get(6) == "X" && _box.value?.get(9) == "X" ||
            _box.value?.get(1) == "X" && _box.value?.get(5) == "X" && _box.value?.get(9) == "X" ||
            _box.value?.get(3) == "X" && _box.value?.get(5) == "X" && _box.value?.get(7) == "X" ||

            _box.value?.get(1) == "O" && _box.value?.get(2) == "O" && _box.value?.get(3) == "O" ||
            _box.value?.get(4) == "O" && _box.value?.get(5) == "O" && _box.value?.get(6) == "O" ||
            _box.value?.get(7) == "O" && _box.value?.get(8) == "O" && _box.value?.get(9) == "O" ||
            _box.value?.get(1) == "O" && _box.value?.get(4) == "O" && _box.value?.get(7) == "O" ||
            _box.value?.get(2) == "O" && _box.value?.get(5) == "O" && _box.value?.get(8) == "O" ||
            _box.value?.get(3) == "O" && _box.value?.get(6) == "O" && _box.value?.get(9) == "O" ||
            _box.value?.get(1) == "O" && _box.value?.get(5) == "O" && _box.value?.get(9) == "O" ||
            _box.value?.get(3) == "O" && _box.value?.get(5) == "O" && _box.value?.get(7) == "O"
            ) {
            _player.value = 2 // 게임 오버
            _restart.value = 2
        }
        else if (_count == 8){
            _player.value = 3
            _restart.value = 1
        }// 다 채워지면 무승부
    }

    fun restart(){
        _restart.value = 0
        _count = 0
        _player.value = 0
        _box.value = List(10) { "" }
    }
}
    // 끝났는지 체크할 수 있도록. 항상 실행이 되어야함. 끝나면 "초기화"가 "한판더"로 바뀜.

// 어차피 콜백 ftn으로 할거니 계속해서 불려질듯?