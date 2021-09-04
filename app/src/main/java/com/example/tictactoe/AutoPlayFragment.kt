package com.example.tictactoe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.tictactoe.databinding.FragmentAutoPlayBinding

class AutoPlayFragment : Fragment() {
    private lateinit var binding: FragmentAutoPlayBinding
    private var flag = 0
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
        binding = FragmentAutoPlayBinding.inflate(inflater)

        binding.autoBox1.setOnClickListener {
            playerGamePlay(binding.autoBox1, 0)
            if(count<8)
                androidsMove()
        }
        binding.autoBox2.setOnClickListener {
            playerGamePlay(binding.autoBox2, 1)
            if(count<8)
                androidsMove()
        }
        binding.autoBox3.setOnClickListener {
            playerGamePlay(binding.autoBox3, 2)
            if(count<8)
                androidsMove()
        }
        binding.autoBox4.setOnClickListener {
            playerGamePlay(binding.autoBox4, 3)
            if(count<8)
                androidsMove()
        }
        binding.autoBox5.setOnClickListener {
            playerGamePlay(binding.autoBox5, 4)
            if(count<8)
                androidsMove()
        }
        binding.autoBox6.setOnClickListener {
            playerGamePlay(binding.autoBox6, 5)
            if(count<8)
                androidsMove()
        }
        binding.autoBox7.setOnClickListener {
            playerGamePlay(binding.autoBox7, 6)
            if(count<8)
                androidsMove()
        }
        binding.autoBox8.setOnClickListener {
            playerGamePlay(binding.autoBox8, 7)
            if(count<8)
                androidsMove()
        }
        binding.autoBox9.setOnClickListener {
            playerGamePlay(binding.autoBox9, 8)
            if(count<8)
                androidsMove()
        }

        binding.autoPlayAgain.setOnClickListener {
            gameRefresh()
            val navController: NavController =
                requireActivity().findNavController(R.id.myNavHostFragment)
            navController.run {
                popBackStack()
                navigate(R.id.autoPlayFragment)
            }
        }

        return binding.root
    }

    private fun gameRefresh() {
        for (i in 0..8) {
            tickcheck[i] = false
            crosscheck[i] = false
        }
        count = 0
        flag = 0
    }

    private fun gameEnd() {
        binding.apply {
            autoBox1.isClickable = false
            autoBox2.isClickable = false
            autoBox3.isClickable = false
            autoBox4.isClickable = false
            autoBox5.isClickable = false
            autoBox6.isClickable = false
            autoBox7.isClickable = false
            autoBox8.isClickable = false
            autoBox9.isClickable = false
        }
    }

    private fun winCheck(temp: List<Boolean>): Boolean {
        if (temp[0] && temp[1] && temp[2])
            return true
        else if (temp[3] && temp[4] && temp[5])
            return true
        else if (temp[6] && temp[7] && temp[8])
            return true
        else if (temp[0] && temp[3] && temp[6])
            return true
        else if (temp[1] && temp[4] && temp[7])
            return true
        else if (temp[2] && temp[5] && temp[8])
            return true
        else if (temp[0] && temp[4] && temp[8])
            return true
        else if (temp[2] && temp[4] && temp[6])
            return true
        return false
    }

    private fun drawCheck(): Boolean {
        if (count == 8 && !winCheck(tickcheck) && !winCheck(crosscheck))
            return true
        return false
    }

    private fun playerGamePlay(view: ImageButton, num: Int) {
        binding.apply {
            view.setImageResource(R.drawable.tic)
            autoGameText.visibility = View.INVISIBLE
            view.isClickable = false
            tickcheck[num] = true
            if (winCheck(tickcheck)) {
                gameEnd()
                autoGameText.isVisible = true
                binding.autoGameText.text = getString(R.string.player_winner_text)
                binding.autoPlayAgain.visibility = View.VISIBLE
            }
            if (drawCheck()) {
                gameEnd()
                autoGameText.visibility = View.VISIBLE
                autoGameText.setText(R.string.match_draw)
                autoPlayAgain.visibility = View.VISIBLE
            }
            count++
        }
    }

    private fun autoGamePlay(temp: Int) {
        binding.apply {
            var view: ImageButton = autoBox1
            when (temp) {
                0 -> view = autoBox1
                1 -> view = autoBox2
                2 -> view = autoBox3
                3 -> view = autoBox4
                4 -> view = autoBox5
                5 -> view = autoBox6
                6 -> view = autoBox7
                7 -> view = autoBox8
                8 -> view = autoBox9
            }
            view.setImageResource(R.drawable.tac)
            crosscheck[temp] = true
            if (winCheck(crosscheck)) {
                gameEnd()
                autoGameText.isVisible = true
                autoGameText.text = getString(R.string.android_won_text)
                autoPlayAgain.visibility = View.VISIBLE
            }
            if (drawCheck()) {
                gameEnd()
                autoGameText.visibility = View.VISIBLE
                autoGameText.setText(R.string.match_draw)
                autoPlayAgain.visibility = View.VISIBLE
            }
            count++
        }
    }

    private fun androidsMove() {
        if (!winCheck(tickcheck)) {
            flag = if (winningMove() >= 0) {
                winningMove()
            } else if (stoppingMove() >= 0)
                stoppingMove()
            else
                randomMove()
            autoGamePlay(flag)
        }
    }

    private fun randomMove(): Int {
        val temp = (0..8).random()
        if (tickcheck[temp] || crosscheck[temp])
            randomMove()
        return temp
    }

    private fun stoppingMove(): Int {
        if ((!tickcheck[0] && !crosscheck[0] && tickcheck[1] && tickcheck[2]) || (!tickcheck[0] && !crosscheck[0] && tickcheck[4] && tickcheck[8]) || (!tickcheck[0] && !crosscheck[0] && tickcheck[3] && tickcheck[6]))
            return 0
        else if ((tickcheck[0] && !tickcheck[1] && !crosscheck[1] && tickcheck[2]) || (!tickcheck[1] && !crosscheck[1] && tickcheck[4] && tickcheck[7]))
            return 1
        else if ((tickcheck[0] && tickcheck[1] && !tickcheck[2] && !crosscheck[2]) || (!tickcheck[2] && !crosscheck[2] && tickcheck[4] && tickcheck[6]) || (!tickcheck[2] && !crosscheck[2] && tickcheck[5] && tickcheck[8]))
            return 2
        else if ((!tickcheck[3] && !crosscheck[3] && tickcheck[4] && tickcheck[5]) || (!tickcheck[3] && !crosscheck[3] && tickcheck[0] && tickcheck[6]))
            return 3
        else if ((!tickcheck[4] && !crosscheck[4] && tickcheck[0] && tickcheck[8]) || (!tickcheck[4] && !crosscheck[4] && tickcheck[1] && tickcheck[7]) || (!tickcheck[4] && !crosscheck[4] && tickcheck[2] && tickcheck[6]))
            return 4
        else if ((!tickcheck[5] && !crosscheck[5] && tickcheck[2] && tickcheck[8]) || (!tickcheck[5] && !crosscheck[5] && tickcheck[4] && tickcheck[3]))
            return 5
        else if ((!tickcheck[6] && !crosscheck[6] && tickcheck[3] && tickcheck[0]) || (!tickcheck[6] && !crosscheck[6] && tickcheck[4] && tickcheck[2]) || (!tickcheck[6] && !crosscheck[6] && tickcheck[7] && tickcheck[8]))
            return 6
        else if ((!tickcheck[7] && !crosscheck[7] && tickcheck[6] && tickcheck[8]) || (!tickcheck[7] && !crosscheck[7] && tickcheck[4] && tickcheck[1]))
            return 7
        else if ((!tickcheck[8] && !crosscheck[8] && tickcheck[7] && tickcheck[6]) || (!tickcheck[8] && !crosscheck[8] && tickcheck[4] && tickcheck[0]) || (!tickcheck[8] && !crosscheck[8] && tickcheck[5] && tickcheck[2]))
            return 8
        else
            return -1
    }

    private fun winningMove(): Int {
        if ((!crosscheck[0] && !tickcheck[0] && crosscheck[1] && crosscheck[2]) || (!crosscheck[0] && !tickcheck[0] && crosscheck[4] && crosscheck[8]) || (!crosscheck[0] && !tickcheck[0] && crosscheck[3] && crosscheck[6]))
            return 0
        else if ((crosscheck[0] && !crosscheck[1] && !tickcheck[1] && crosscheck[2]) || (!crosscheck[1] && !tickcheck[1] && crosscheck[4] && crosscheck[7]))
            return 1
        else if ((crosscheck[0] && crosscheck[1] && !crosscheck[2] && !tickcheck[2]) || (!crosscheck[2] && !tickcheck[2] && crosscheck[4] && crosscheck[6]) || (!crosscheck[2] && !tickcheck[2] && crosscheck[5] && crosscheck[8]))
            return 2
        else if ((!crosscheck[3] && !tickcheck[3] && crosscheck[4] && crosscheck[5]) || (!crosscheck[3] && !tickcheck[3] && crosscheck[0] && crosscheck[6]))
            return 3
        else if ((!crosscheck[4] && !tickcheck[4] && crosscheck[0] && crosscheck[8]) || (!crosscheck[4] && !tickcheck[4] && crosscheck[1] && crosscheck[7]) || (!crosscheck[4] && !tickcheck[4] && crosscheck[2] && crosscheck[6]))
            return 4
        else if ((!crosscheck[5] && !tickcheck[5] && crosscheck[2] && crosscheck[8]) || (!crosscheck[5] && !tickcheck[5] && crosscheck[4] && crosscheck[3]))
            return 5
        else if ((!crosscheck[6] && !tickcheck[6] && crosscheck[3] && crosscheck[0]) || (!crosscheck[6] && !tickcheck[6] && crosscheck[4] && crosscheck[2]) || (!crosscheck[6] && !tickcheck[6] && crosscheck[7] && crosscheck[8]))
            return 6
        else if ((!crosscheck[7] && !tickcheck[7] && crosscheck[6] && crosscheck[8]) || (!crosscheck[7] && !tickcheck[7] && crosscheck[4] && crosscheck[1]))
            return 7
        else if ((!crosscheck[8] && !tickcheck[8] && crosscheck[7] && crosscheck[6]) || (!crosscheck[8] && !tickcheck[8] && crosscheck[4] && crosscheck[0]) || (!crosscheck[8] && !tickcheck[8] && crosscheck[5] && crosscheck[2]))
            return 8
        else
            return -1
    }

}