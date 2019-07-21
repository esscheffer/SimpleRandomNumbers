package com.scheffer.erik.simplerandomnumbers.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.scheffer.erik.simplerandomnumbers.R
import com.scheffer.erik.simplerandomnumbers.retrofit.RandomOrgService
import com.scheffer.erik.simplerandomnumbers.retrofit.models.Params
import com.scheffer.erik.simplerandomnumbers.retrofit.models.RandomOrgGenerateIntegerPostModel
import com.scheffer.erik.simplerandomnumbers.retrofit.models.RandomOrgGenerateIntegerReturnModel
import kotlinx.android.synthetic.main.activity_dice_roll.*
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiceRollActivity : AppCompatActivity() {

    private val randomOrgService: RandomOrgService by inject()

    private var diceButtons: MutableList<ImageButton> = mutableListOf()
    private var currentRolledDices: MutableList<Int> = mutableListOf()
    private var selectedDice = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice_roll)

        diceButtons.add(d4_image_button)
        diceButtons.add(d6_image_button)
        diceButtons.add(d8_image_button)
        diceButtons.add(d10_image_button)
        diceButtons.add(d12_image_button)
        diceButtons.add(d20_image_button)

        d6_image_button.setBackgroundResource(R.drawable.round_corner)

        diceButtons.forEach { availableDiceButton ->
            availableDiceButton.setOnClickListener { clickedButton ->
                diceButtons.forEach { otherButton ->
                    otherButton.setBackgroundResource(android.R.color.transparent)
                }
                selectedDice = (clickedButton.tag as String).toInt()
                (clickedButton as ImageButton).setBackgroundResource(R.drawable.round_corner)
            }
        }

        roll_button.setOnClickListener {
            getRandomOrgCall().enqueue(object : Callback<RandomOrgGenerateIntegerReturnModel> {
                override fun onResponse(
                        call: Call<RandomOrgGenerateIntegerReturnModel>?,
                        response: Response<RandomOrgGenerateIntegerReturnModel>?) {
                    val body = response?.body()
                    if (body == null) {
                        dice_roll_list_text.text = resources.getString(R.string.nothing_returned)
                    } else {
                        currentRolledDices = body.result.random.data.toMutableList()
                        setUiTexts()
                    }
                }

                override fun onFailure(
                        call: Call<RandomOrgGenerateIntegerReturnModel>?,
                        t: Throwable?) {
                    dice_roll_list_text.text = resources.getString(R.string.error_getting_numbers)
                }
            })
        }

        add_button.setOnClickListener {
            getRandomOrgCall().enqueue(object : Callback<RandomOrgGenerateIntegerReturnModel> {
                override fun onResponse(
                        call: Call<RandomOrgGenerateIntegerReturnModel>?,
                        response: Response<RandomOrgGenerateIntegerReturnModel>?) {
                    val body = response?.body()
                    if (body == null) {
                        dice_roll_list_text.text = resources.getString(R.string.nothing_returned)
                    } else {
                        currentRolledDices.addAll(body.result.random.data)
                        setUiTexts()
                    }
                }

                override fun onFailure(
                        call: Call<RandomOrgGenerateIntegerReturnModel>?,
                        t: Throwable?) {
                    dice_roll_list_text.text = resources.getString(R.string.error_getting_numbers)
                }
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUiTexts() {
        dice_roll_list_text.text =
                "${currentRolledDices.joinToString()} + ${extra_value_seekBar.progress}"
        total_text.text = (currentRolledDices.sum() + extra_value_seekBar.progress).toString()
    }

    private fun getRandomOrgCall() = randomOrgService.invoke(RandomOrgGenerateIntegerPostModel(
            Params(n = dice_count_seekBar.progress, max = selectedDice)))
}
