package com.example.tictactoe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.tictactoe.databinding.Fragment1v1Binding

class OneVsOneFragment: Fragment() {
    private lateinit var binding : Fragment1v1Binding
    private var count = 0
    private var tickcheck =
        arrayListOf(false, false, false, false, false, false, false, false, false)
    private val crosscheck =
        arrayListOf(false, false, false, false, false, false, false, false, false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Fragment1v1Binding.inflate(inflater)

        binding.box1.setOnClickListener {
            if (count % 2 == 0) {
                gamePlay(binding.box1, 0, R.drawable.tic)
            } else {
                gamePlay(binding.box1, 0, R.drawable.tac)
            }
            gameEnd()
        }
        binding.box2.setOnClickListener {
            if (count % 2 == 0) {
                gamePlay(binding.box2, 1, R.drawable.tic)
            } else {
                gamePlay(binding.box2, 1, R.drawable.tac)
            }
            gameEnd()
        }
        binding.box3.setOnClickListener {
            if (count % 2 == 0) {
                gamePlay(binding.box3, 2, R.drawable.tic)
            } else {
                gamePlay(binding.box3, 2, R.drawable.tac)
            }
            gameEnd()
        }
        binding.box4.setOnClickListener {
            if (count % 2 == 0) {
                gamePlay(binding.box4, 3, R.drawable.tic)
            } else {
                gamePlay(binding.box4, 3, R.drawable.tac)
            }
            gameEnd()
        }
        binding.box5.setOnClickListener {
            if (count % 2 == 0) {
                gamePlay(binding.box5, 4, R.drawable.tic)
            } else {
                gamePlay(binding.box5, 4, R.drawable.tac)
            }
            gameEnd()
        }
        binding.box6.setOnClickListener {
            if (count % 2 == 0) {
                gamePlay(binding.box6, 5, R.drawable.tic)
            } else {
                gamePlay(binding.box6, 5, R.drawable.tac)
            }
            gameEnd()
        }
        binding.box7.setOnClickListener {
            if (count % 2 == 0) {
                gamePlay(binding.box7, 6, R.drawable.tic)
            } else {
                gamePlay(binding.box7, 6, R.drawable.tac)
            }
            gameEnd()
        }
        binding.box8.setOnClickListener {
            if (count % 2 == 0) {
                gamePlay(binding.box8, 7, R.drawable.tic)
            } else {
                gamePlay(binding.box8, 7, R.drawable.tac)
            }
            gameEnd()
        }
        binding.box9.setOnClickListener {
            if (count % 2 == 0) {
                gamePlay(binding.box9, 8, R.drawable.tic)
            } else {
                gamePlay(binding.box9, 8, R.drawable.tac)
            }
            gameEnd()
        }
        binding.playAgain.setOnClickListener {
            gameRefresh()
            val navController: NavController =
                requireActivity().findNavController(R.id.myNavHostFragment)
            navController.run {
                popBackStack()
                navigate(R.id.oneVsOneFragment)
            }
        }

        return binding.root
    }
    private fun gameRefresh() {
        count = 0
        for (i in 0..8) {
            tickcheck[i] = false
            crosscheck[i] = false
        }
    }

    private fun drawCheck(): Boolean {
        if (count == 8 && !winCheck(tickcheck) && !winCheck(crosscheck))
            return true
        return false
    }

    private fun gameEnd() {
        binding.apply {
            if (winCheck(tickcheck) || winCheck(crosscheck)) {
                box1.isClickable = false
                box2.isClickable = false
                box3.isClickable = false
                box4.isClickable = false
                box5.isClickable = false
                box6.isClickable = false
                box7.isClickable = false
                box8.isClickable = false
                box9.isClickable = false
            }
        }
    }

    private fun winCheck(temp: List<Boolean>): Boolean {
        if (temp[0] && temp[1] && temp[2])
            return true
        if (temp[3] && temp[4] && temp[5])
            return true
        if (temp[6] && temp[7] && temp[8])
            return true
        if (temp[0] && temp[3] && temp[6])
            return true
        if (temp[1] && temp[4] && temp[7])
            return true
        if (temp[2] && temp[5] && temp[8])
            return true
        if (temp[0] && temp[4] && temp[8])
            return true
        if (temp[2] && temp[4] && temp[6])
            return true
        return false
    }

    private fun gamePlay(view: ImageButton, num: Int, id: Int) {
        binding.apply {
            view.setImageResource(id)
            view.isClickable = false
            if (count % 2 == 0) {
                tickcheck[num] = true
                if (winCheck(tickcheck)) {
                    gameText.setText(R.string.player_X_won)
                    playAgain.visibility = View.VISIBLE
                } else if (drawCheck()) {
                    gameText.setText(R.string.match_draw)
                    playAgain.visibility = View.VISIBLE
                } else
                    gameText.setText(R.string.player_Os_turn)
            } else {
                crosscheck[num] = true
                if (winCheck(crosscheck)) {
                    gameText.setText(R.string.player_O_won)
                    playAgain.visibility = View.VISIBLE
                } else if (drawCheck()) {
                    gameText.setText(R.string.match_draw)
                    playAgain.visibility = View.VISIBLE
                } else
                    gameText.setText(R.string.player_Xs_turn)
            }
            count++
        }
    }

}