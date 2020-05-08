package com.example.enigma

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var arrEncryptedText = ArrayList<TextView>()
    private var arrInputLetter = ArrayList<TextView>()
    private var arrRotorTextViewSetup = ArrayList<TextView>()
    private var arrRotorButtonSetup = ArrayList<TextView>()
    private var enigma: Enigma? = null
    private val function = Functions()
    private var switch = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.onResume()
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        groupButtonInput()
        groupEncryptedText()
        groupRotorTextView()
        groupRotorButton()
        setup()
        run()
    }
    private fun setup() {
        //setup buttons
        btnApply.setOnClickListener { applyButton() }
        btnReset.setOnClickListener { resetButton() }
        txvEncryptedAs.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("my encrypted text", txvEncryptedAs.text)
            clipboard.setPrimaryClip(clip)
        }
        swtPlugboard.setOnClickListener { if (swtPlugboard.isChecked) switch = function.setupPlugboard(true) }
        rotorSetup()
    }
    private fun run() {
            btnLetterA.setOnClickListener { encrypting(btnLetterA, switch) }
            btnLetterB.setOnClickListener { encrypting(btnLetterB, switch) }
            btnLetterC.setOnClickListener { encrypting(btnLetterC, switch) }
            btnLetterD.setOnClickListener { encrypting(btnLetterD, switch) }
            btnLetterE.setOnClickListener { encrypting(btnLetterE, switch) }
            btnLetterF.setOnClickListener { encrypting(btnLetterF, switch) }
            btnLetterG.setOnClickListener { encrypting(btnLetterG, switch) }
            btnLetterH.setOnClickListener { encrypting(btnLetterH, switch) }
            btnLetterI.setOnClickListener { encrypting(btnLetterI, switch) }
            btnLetterJ.setOnClickListener { encrypting(btnLetterJ, switch) }
            btnLetterK.setOnClickListener { encrypting(btnLetterK, switch) }
            btnLetterL.setOnClickListener { encrypting(btnLetterL, switch) }
            btnLetterM.setOnClickListener { encrypting(btnLetterM, switch) }
            btnLetterN.setOnClickListener { encrypting(btnLetterN, switch) }
            btnLetterO.setOnClickListener { encrypting(btnLetterO, switch) }
            btnLetterP.setOnClickListener { encrypting(btnLetterP, switch) }
            btnLetterQ.setOnClickListener { encrypting(btnLetterQ, switch) }
            btnLetterR.setOnClickListener { encrypting(btnLetterR, switch) }
            btnLetterS.setOnClickListener { encrypting(btnLetterS, switch) }
            btnLetterT.setOnClickListener { encrypting(btnLetterT, switch) }
            btnLetterU.setOnClickListener { encrypting(btnLetterU, switch) }
            btnLetterV.setOnClickListener { encrypting(btnLetterV, switch) }
            btnLetterW.setOnClickListener { encrypting(btnLetterW, switch) }
            btnLetterX.setOnClickListener { encrypting(btnLetterX, switch) }
            btnLetterY.setOnClickListener { encrypting(btnLetterY, switch) }
            btnLetterZ.setOnClickListener { encrypting(btnLetterZ, switch) }
    }
    //take the input (e.g "a-z") then start encrypting
    private fun encrypting(btn: Button, switch: Boolean) {
        this.turnOffLight(arrEncryptedText)
        try {
            var temp =
                enigma?.encryptingTheInput(
                    btn,
                    enigma!!.getRotor1(), enigma!!.getRotor2(), enigma!!.getRotor3(), switch
                )
            txvEncryptedAs.append(temp)
            txvYourMessage.append(btn.text)
            temp?.let { turnOnLight(it) } // light up the encrypted letter
        } catch (e: NullPointerException) {
            function.displayNullPointerError(this)
        }
    }
    private fun groupEncryptedText() {
        arrEncryptedText.add(txvEW_A)
        arrEncryptedText.add(txvEW_B)
        arrEncryptedText.add(txvEW_C)
        arrEncryptedText.add(txvEW_D)
        arrEncryptedText.add(txvEW_E)
        arrEncryptedText.add(txvEW_F)
        arrEncryptedText.add(txvEW_G)
        arrEncryptedText.add(txvEW_H)
        arrEncryptedText.add(txvEW_I)
        arrEncryptedText.add(txvEW_J)
        arrEncryptedText.add(txvEW_K)
        arrEncryptedText.add(txvEW_L)
        arrEncryptedText.add(txvEW_M)
        arrEncryptedText.add(txvEW_N)
        arrEncryptedText.add(txvEW_O)
        arrEncryptedText.add(txvEW_P)
        arrEncryptedText.add(txvEW_Q)
        arrEncryptedText.add(txvEW_R)
        arrEncryptedText.add(txvEW_S)
        arrEncryptedText.add(txvEW_T)
        arrEncryptedText.add(txvEW_U)
        arrEncryptedText.add(txvEW_V)
        arrEncryptedText.add(txvEW_W)
        arrEncryptedText.add(txvEW_X)
        arrEncryptedText.add(txvEW_Y)
        arrEncryptedText.add(txvEW_Z)
    }
    private fun groupRotorTextView(){
        arrRotorTextViewSetup.add(txtViewRotor)
        arrRotorTextViewSetup.add(txtViewRotor1)
        arrRotorTextViewSetup.add(txtViewRotor2)
        arrRotorTextViewSetup.add(txtViewRotor3)
        arrRotorTextViewSetup.add(txtViewRotor4)
        arrRotorTextViewSetup.add(txtViewRotor5)
    }
    private fun groupRotorButton(){
        arrRotorButtonSetup.add(btnRotor1Next)
        arrRotorButtonSetup.add(btnRotor1Back)
        arrRotorButtonSetup.add(btnRotor2Next)
        arrRotorButtonSetup.add(btnRotor2Back)
        arrRotorButtonSetup.add(btnRotor3Next)
        arrRotorButtonSetup.add(btnRotor3Back)
        arrRotorButtonSetup.add(btnRotorPosition1Next)
        arrRotorButtonSetup.add(btnRotorPosition1Back)
        arrRotorButtonSetup.add(btnRotorPosition2Next)
        arrRotorButtonSetup.add(btnRotorPosition2Back)
        arrRotorButtonSetup.add(btnRotorPosition3Next)
        arrRotorButtonSetup.add(btnRotorPosition3Back)
    }
    private fun groupButtonInput() {
        arrInputLetter.add(btnLetterA)
        arrInputLetter.add(btnLetterB)
        arrInputLetter.add(btnLetterC)
        arrInputLetter.add(btnLetterD)
        arrInputLetter.add(btnLetterE)
        arrInputLetter.add(btnLetterF)
        arrInputLetter.add(btnLetterG)
        arrInputLetter.add(btnLetterH)
        arrInputLetter.add(btnLetterI)
        arrInputLetter.add(btnLetterJ)
        arrInputLetter.add(btnLetterK)
        arrInputLetter.add(btnLetterL)
        arrInputLetter.add(btnLetterM)
        arrInputLetter.add(btnLetterN)
        arrInputLetter.add(btnLetterO)
        arrInputLetter.add(btnLetterP)
        arrInputLetter.add(btnLetterQ)
        arrInputLetter.add(btnLetterR)
        arrInputLetter.add(btnLetterS)
        arrInputLetter.add(btnLetterT)
        arrInputLetter.add(btnLetterU)
        arrInputLetter.add(btnLetterV)
        arrInputLetter.add(btnLetterW)
        arrInputLetter.add(btnLetterX)
        arrInputLetter.add(btnLetterY)
        arrInputLetter.add(btnLetterZ)
    }
    //this method used to light off the buttons
    private fun turnOffLight(arr: ArrayList<TextView>) {
        for (key in arr)
            key.setBackgroundResource(R.drawable.original_text)
    }
    //this method used to light on the buttons
    private fun turnOnLight(alphabet: String) {
        when (alphabet) {
            "A" -> txvEW_A.setBackgroundResource(R.drawable.encrypted_text)
            "B" -> txvEW_B.setBackgroundResource(R.drawable.encrypted_text)
            "C" -> txvEW_C.setBackgroundResource(R.drawable.encrypted_text)
            "D" -> txvEW_D.setBackgroundResource(R.drawable.encrypted_text)
            "E" -> txvEW_E.setBackgroundResource(R.drawable.encrypted_text)
            "F" -> txvEW_F.setBackgroundResource(R.drawable.encrypted_text)
            "G" -> txvEW_G.setBackgroundResource(R.drawable.encrypted_text)
            "H" -> txvEW_H.setBackgroundResource(R.drawable.encrypted_text)
            "I" -> txvEW_I.setBackgroundResource(R.drawable.encrypted_text)
            "J" -> txvEW_J.setBackgroundResource(R.drawable.encrypted_text)
            "K" -> txvEW_K.setBackgroundResource(R.drawable.encrypted_text)
            "L" -> txvEW_L.setBackgroundResource(R.drawable.encrypted_text)
            "M" -> txvEW_M.setBackgroundResource(R.drawable.encrypted_text)
            "N" -> txvEW_N.setBackgroundResource(R.drawable.encrypted_text)
            "O" -> txvEW_O.setBackgroundResource(R.drawable.encrypted_text)
            "P" -> txvEW_P.setBackgroundResource(R.drawable.encrypted_text)
            "Q" -> txvEW_Q.setBackgroundResource(R.drawable.encrypted_text)
            "R" -> txvEW_R.setBackgroundResource(R.drawable.encrypted_text)
            "S" -> txvEW_S.setBackgroundResource(R.drawable.encrypted_text)
            "T" -> txvEW_T.setBackgroundResource(R.drawable.encrypted_text)
            "U" -> txvEW_U.setBackgroundResource(R.drawable.encrypted_text)
            "V" -> txvEW_V.setBackgroundResource(R.drawable.encrypted_text)
            "W" -> txvEW_W.setBackgroundResource(R.drawable.encrypted_text)
            "X" -> txvEW_X.setBackgroundResource(R.drawable.encrypted_text)
            "Y" -> txvEW_Y.setBackgroundResource(R.drawable.encrypted_text)
            "Z" -> txvEW_Z.setBackgroundResource(R.drawable.encrypted_text)
        }
    }
    //enable and disable the rotors setup
    //when user click on Apply button then the user cannot change the rotor setup unless they click on Reset button
    private fun enableRotorButtons(status: Boolean){
        btnRotor1Back.isEnabled = status
        btnRotor1Next.isEnabled = status
        btnRotor2Back.isEnabled = status
        btnRotor2Next.isEnabled = status
        btnRotor3Back.isEnabled = status
        btnRotor3Next.isEnabled = status

        btnRotorPosition1Back.isEnabled = status
        btnRotorPosition1Next.isEnabled = status
        btnRotorPosition2Back.isEnabled = status
        btnRotorPosition2Next.isEnabled = status
        btnRotorPosition3Back.isEnabled = status
        btnRotorPosition3Next.isEnabled = status
        for(i in arrRotorTextViewSetup){
            if(!status)i.setBackgroundResource(R.drawable.enable_button)
            else i.setBackgroundResource(R.drawable.disable_button)
        }
    }
    private fun applyButton(){
        enigma = Enigma()
        function.enableInputAlphabets(arrInputLetter, true)
        enableRotorButtons(false)
        btnApply.isEnabled = false
        btnReset.isEnabled = true
        swtPlugboard.isClickable = false
        changeEncryptedTextViewColour(true) // light on/off the encrypted alphabet
        btnReset.setBackgroundResource(R.drawable.enable_button)
        btnApply.setBackgroundResource(R.drawable.disable_button)
        btnRotor1Back.setBackgroundResource(R.drawable.enable_left)
        btnRotor2Back.setBackgroundResource(R.drawable.enable_left)
        btnRotor3Back.setBackgroundResource(R.drawable.enable_left)
        btnRotor1Next.setBackgroundResource(R.drawable.enable_right)
        btnRotor2Next.setBackgroundResource(R.drawable.enable_right)
        btnRotor3Next.setBackgroundResource(R.drawable.enable_right)
        btnRotorPosition1Back.setBackgroundResource(R.drawable.enable_left)
        btnRotorPosition2Back.setBackgroundResource(R.drawable.enable_left)
        btnRotorPosition3Back.setBackgroundResource(R.drawable.enable_left)
        btnRotorPosition1Next.setBackgroundResource(R.drawable.enable_right)
        btnRotorPosition2Next.setBackgroundResource(R.drawable.enable_right)
        btnRotorPosition3Next.setBackgroundResource(R.drawable.enable_right)
    }
    private fun resetButton(){
        turnOffLight(arrEncryptedText)
        txvEncryptedAs.text = ""
        txvYourMessage.text = ""
        function.enableInputAlphabets(arrInputLetter, false)
        swtPlugboard.isChecked = false
        function.setupPlugboard(false)
        enableRotorButtons(true)
        btnApply.isEnabled = true
        swtPlugboard.isClickable = true
        switch = false
        btnReset.isEnabled = false
        changeEncryptedTextViewColour(false)
        btnReset.setBackgroundResource(R.drawable.disable_button)
        btnApply.setBackgroundResource(R.drawable.enable_button)
        btnRotor1Back.setBackgroundResource(R.drawable.button_left)
        btnRotor2Back.setBackgroundResource(R.drawable.button_left)
        btnRotor3Back.setBackgroundResource(R.drawable.button_left)
        btnRotor1Next.setBackgroundResource(R.drawable.button_right)
        btnRotor2Next.setBackgroundResource(R.drawable.button_right)
        btnRotor3Next.setBackgroundResource(R.drawable.button_right)
        btnRotorPosition1Back.setBackgroundResource(R.drawable.button_left)
        btnRotorPosition2Back.setBackgroundResource(R.drawable.button_left)
        btnRotorPosition3Back.setBackgroundResource(R.drawable.button_left)
        btnRotorPosition1Next.setBackgroundResource(R.drawable.button_right)
        btnRotorPosition2Next.setBackgroundResource(R.drawable.button_right)
        btnRotorPosition3Next.setBackgroundResource(R.drawable.button_right)
    }
    private fun rotorSetup(){
        //setup rotors
        btnRotor1Back.setOnClickListener { function.rotorDecrease(txtViewRotor) }
        btnRotor1Next.setOnClickListener { function.rotorIncrease(txtViewRotor) }
        btnRotor2Back.setOnClickListener { function.rotorDecrease(txtViewRotor2) }
        btnRotor2Next.setOnClickListener { function.rotorIncrease(txtViewRotor2) }
        btnRotor3Back.setOnClickListener { function.rotorDecrease(txtViewRotor3) }
        btnRotor3Next.setOnClickListener { function.rotorIncrease(txtViewRotor3) }

        //setup rotor's positions
        btnRotorPosition1Back.setOnClickListener { function.alphabetDecrease(txtViewRotor1) }
        btnRotorPosition1Next.setOnClickListener { function.alphabetIncrease(txtViewRotor1) }
        btnRotorPosition2Back.setOnClickListener { function.alphabetDecrease(txtViewRotor4) }
        btnRotorPosition2Next.setOnClickListener { function.alphabetIncrease(txtViewRotor4) }
        btnRotorPosition3Back.setOnClickListener { function.alphabetDecrease(txtViewRotor5) }
        btnRotorPosition3Next.setOnClickListener { function.alphabetIncrease(txtViewRotor5) }
    }
    //this method used to light on/off encrypted alphabet
    private fun changeEncryptedTextViewColour(status: Boolean){
        if(status) {
            for (i in arrEncryptedText) {
                i.setTextColor(Color.parseColor("#EDE9E9"))
            }
        }
        else
        {
            for (i in arrEncryptedText) {
                i.setTextColor(Color.parseColor("#1E1716"))
            }
        }
    }
}

