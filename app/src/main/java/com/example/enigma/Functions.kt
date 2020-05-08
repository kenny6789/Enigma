package com.example.enigma

import android.app.AlertDialog
import android.content.Context
import android.widget.TextView

private const val MAX_ROTOR = 3
private const val MIN_ROTOR = 1
private const val MAX_CHAR = 90
private const val MIN_CHAR = 65


class Functions {

    fun rotorIncrease(view: TextView) {
        var temp = view.text.toString().toInt()
        if (temp < MAX_ROTOR) view.text = (temp + 1).toString()
    }
    fun rotorDecrease(view: TextView) {
        var temp = view.text.toString().toInt()
        if (temp > MIN_ROTOR) view.text = (temp - 1).toString()
    }
    fun alphabetIncrease(view: TextView) {
        var charCode = view.text.single().toInt()
        if ((charCode + 1) <= MAX_CHAR) view.text = ((charCode + 1).toChar().toString())
    }
    fun alphabetDecrease(view: TextView) {
        var charCode = view.text.single().toInt()
        if ((charCode - 1) >= MIN_CHAR) view.text = ((charCode - 1).toChar().toString())
    }

    fun setupPlugboard(switch: Boolean) : Boolean{
        var bool = false
        if(switch) bool = true
        return bool
    }

    fun displayNullPointerError(context: Context){
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Please, setup your rotors and click on Apply!")
            .setPositiveButton("Proceed") { _, _ ->}
        val alert = builder.create()
        alert.setTitle("Need to setup the rotors!")
        alert.show()
    }

    fun enableInputAlphabets(myArrayList: ArrayList<TextView>,enable: Boolean){
        if(enable) {
            for (i in myArrayList) {
                i.setBackgroundResource(R.drawable.enable_button)
                i.isEnabled = true
            }
        }
        else{
            for (i in myArrayList){
                i.setBackgroundResource(R.drawable.disable_button)
                i.isEnabled = false
            }
        }
    }
    //    fun setupRunProgram(enigma: Enigma, textView1: TextView, textView2: TextView, textView3: TextView, textView4: TextView, textView5: TextView, textView6: TextView){
//
//        enigma.rearrangeArray(setupRotor(setupRotorLocation(textView1),enigma), setupPosition(textView4))
//        enigma.rearrangeArray(setupRotor(setupRotorLocation(textView2),enigma), setupPosition(textView5))
//        enigma.rearrangeArray(setupRotor(setupRotorLocation(textView3),enigma), setupPosition(textView6))
//    }
//
//    private fun setupRotorLocation(txtView: TextView): Int {
//        var location = 0
//        location = (txtView.text.toString()).toInt()
//        return location
//    }
//    private fun setupRotor(rotor: Int, enigma: Enigma) : ArrayList<String>{
//        var myArr = arrayListOf<String>()
//        when(rotor){
//            1 -> myArr = enigma.getRotor1()
//            2 -> myArr = enigma.getRotor2()
//            3 -> myArr = enigma.getRotor3()
//        }
//        return myArr
//    }
//    private fun setupPosition(textView: TextView) : Int {
//        var alphabet = textView.text.toString()
//        var position = 0
//        when(alphabet) {
//            "A" -> position = 1
//            "B" -> position = 2
//            "C" -> position = 3
//            "D" -> position = 4
//            "E" -> position = 5
//            "F" -> position = 6
//            "G" -> position = 7
//            "H" -> position = 8
//            "I" -> position = 9
//            "J" -> position = 10
//            "K" -> position = 11
//            "L" -> position = 12
//            "M" -> position = 13
//            "N" -> position = 14
//            "O" -> position = 15
//            "P" -> position = 16
//            "Q" -> position = 17
//            "R" -> position = 18
//            "S" -> position = 19
//            "T" -> position = 20
//            "U" -> position = 21
//            "V" -> position = 22
//            "W" -> position = 23
//            "X" -> position = 24
//            "Y" -> position = 25
//            "Z" -> position = 26
//        }
//        return position
//    }
}