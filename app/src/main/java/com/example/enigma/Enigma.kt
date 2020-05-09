package com.example.enigma

import android.widget.Button
import android.widget.TextView

private val ALPHABET = arrayListOf<String>("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
private var PLUGBOARD: Array<Array<String>> = arrayOf( arrayOf("Z", "W"), arrayOf("J", "Y"), arrayOf("C", "X"), arrayOf("A", "F"), arrayOf("V", "E"), arrayOf("U", "D"), arrayOf("T", "S"), arrayOf("H", "M"), arrayOf("R", "I"), arrayOf("B", "Q"), arrayOf("P", "K"), arrayOf("O", "L"), arrayOf("N", "G"))
private val REFLECTOR = arrayListOf<String>("A1", "J1", "F1", "B1", "H1", "D1", "C1", "E1", "L1", "G1", "M1", "K1", "I1", "F2", "C2", "L2", "K2", "D2", "I2", "M2", "A2", "G2", "J2", "B2", "E2", "H2")

class Enigma {

    private val firstRotor = arrayListOf<String>("A Z", "J H", "M Q", "O T", "L C", "P K", "U M", "E U", "Q D", "V W", "F I", "S R", "W B", "D X", "X G", "K J", "Y P", "G A", "N F", "I L", "C Y", "T O", "H S", "R V", "Z N", "B E")
    private val secondRotor = arrayListOf<String>("D E", "M G", "O U", "B C", "I J", "L Q", "H B", "R V", "A F", "J X", "V S", "N Y", "F A", "U P", "Z D", "C Z", "E H", "Y I", "G W", "S N", "K L", "W R", "Q T", "X M", "T O", "P K")
    private val thirdRotor = arrayListOf<String>("B C", "I K", "C Z", "D I", "H D", "Z R", "J T", "A S", "P J", "N A", "U M", "F P", "T V", "M B", "W Y", "E L", "Q X", "Y E", "L U", "X F", "V W", "O G", "R O", "S N", "K Q", "G H")
    private val maxCount = 26

    var counterRotor1 = 0 // this variable used to count the user input. When the variable reach maxCount(26) the rotor2 position will be updated
    var counterRotor2 = 0 // this variable used to count the user input. When the variable reach maxCount(26) the rotor3 position will be updated

    //This method takes the user input then compare it with the PLUGBOARD and start swapping
    private fun swapLetter(myArr: Array<Array<String>>, view: TextView): String {
        var swappedLetter = ""
        val letter = view.text.toString()
        for (element in myArr) {
            if (element.contains(view.text.toString())) {
                swappedLetter = swapping(letter, element[0].toString(), element[1].toString())
                break
            }
        }
        return swappedLetter
    }

    //This method used to swap the input alphabet based on the PLUGBOARD array
    private fun swapping(str: String, str1: String, str2: String): String {
        var returnedLetter = str1
        if (str == str1) returnedLetter = str2
        return returnedLetter
    }

    //once the input go through the rotor3 then it will change the position(index) based on the REFLECTOR
    private fun reflectorSwapping(alphabet: String, plugboard: Boolean) : String{
        var firstAlphabet = alphabet
        if(plugboard){
            for(i in PLUGBOARD){
                if(i.contains(alphabet)){
                    firstAlphabet = i.first().toString()
                    if(firstAlphabet == alphabet) firstAlphabet = i.last().toString()
                    break
                }
            }
        }
        return firstAlphabet
    }
    //This method takes the input and encrypt it through the rotors. Once, the input go through the rotor 3, it will reflecting back from rotor 3 to rotor 1.
    fun encryptingTheInput(view: Button, myArr: ArrayList<String>, myArr2: ArrayList<String>, myArr3: ArrayList<String>, plugboard: Boolean): String{
        val inputAlphabet: String = if(plugboard) {
            swapLetter(PLUGBOARD, view)
        } else view.text.toString()
        val rotor1 = convertingFromInputToRotor1(myArr, inputAlphabet)
        val rotor2 = false.convertingFromRotor1ToReflector(rotor1, myArr2)
        val rotor3 = false.convertingFromRotor1ToReflector(rotor2, myArr3)

        val reflector = reflector(rotor3)
        val refRotor3 = true.convertingFromRotor1ToReflector(reflector, thirdRotor)
        val refRotor2 = true.convertingFromRotor1ToReflector(refRotor3, secondRotor)
        val refRotor1 = true.convertingFromRotor1ToReflector(refRotor2, firstRotor)

        return reflectorSwapping(findAlphabet(refRotor1), plugboard)
    }

    //Takes the input then encrypt it through the Rotor 1
    private fun convertingFromInputToRotor1(myArr: ArrayList<String>, inputAlphabet: String): Int {
        var index = 0
        var leftSideRotor1Counting = 0
        var rightSideRotor1Counting = 0

        rearrangeArray(myArr, 1) // after every input from user, the rotor (myArr) will update the order of the elements.

        ALPHABET.forEach { txt ->
            leftSideRotor1Counting ++
            if (inputAlphabet == txt) {
                val leftLetterInRotor1 = myArr[leftSideRotor1Counting - 1].split(" ").first()
                for (z in myArr) {
                    rightSideRotor1Counting++
                    val rightLetterInRotor1 = z.split(" ").last()
                    if (leftLetterInRotor1 == rightLetterInRotor1) {
                        index = rightSideRotor1Counting
                        rightSideRotor1Counting = 0
                        counterRotor1 ++
                        break
                    }
                }
            }
        }
        updateRotorPosition()
        return index
    }

    //This method used to check that if any rotor reach maxCount(26) then it will update to the new position
    private fun updateRotorPosition(){
        if (counterRotor1 == maxCount) {
            rearrangeArray(secondRotor, 1)
            counterRotor2++
            if (counterRotor2 == maxCount) {
                rearrangeArray(thirdRotor, 1)
                counterRotor2 = 0
            }
            counterRotor1 = 0
        }
    }

    //This method used to encrypt alphabet from rotor1 to reflector
    private fun Boolean.convertingFromRotor1ToReflector(rotor1Index: Int, myArr: ArrayList<String>): Int {
        var counterValue1 = 0
        var counterValue2 = 0
        var value1 = ""
        var value2 = ""
        for (i in myArr) {
            counterValue1++
            if (rotor1Index == counterValue1) {
                if(!this){
                    value1 = i.split(" ").first()
                    for (z in myArr) {
                        value2 = z.split(" ").last()
                        counterValue2++
                        if (value2 == value1) {
                            return counterValue2
                            break
                        }
                    }
                }
                else{
                    value1 = i.split(" ").last()
                    for (z in myArr) {
                        value2 = z.split(" ").first()
                        counterValue2++
                        if (value2 == value1) {
                            return counterValue2
                            break
                        }
                    }
                }
            }
        }
        return counterValue2
    }

    //Used to re-arrange array
    fun rearrangeArray(myArr: ArrayList<String>, noOfRearrange: Int) {
        repeat(noOfRearrange) {
            var temp = myArr[0]
            myArr.removeAt(0)
            myArr.add(temp)
        }
    }
    //reflect the encrypted alphabet from rotor3
    private fun reflector(index: Int): Int {
        var result = 0
        var alphabet = REFLECTOR[validateIndex(index)]
        for(i in REFLECTOR){
            result ++
            var temp = i.first()
            if((i != alphabet) && (alphabet.contains(temp))) return result
        }
        return result
    }

    //make sure an index is not out of array.
    private fun validateIndex(index: Int) : Int{
        var result = index - 1
        if(result < 0) result = 25
        return result
    }

    private fun findAlphabet(index: Int): String{ return ALPHABET[validateIndex(index)] }
    fun getRotor1(): ArrayList<String> { return firstRotor }
    fun getRotor2(): ArrayList<String> { return secondRotor }
    fun getRotor3(): ArrayList<String> { return thirdRotor }
}
