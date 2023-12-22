package com.example.marionumber

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.marionumber.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Random

class MainActivity() : AppCompatActivity(), Parcelable {

    private lateinit var binding: ActivityMainBinding
    private val listnumber: MutableList<Int> = mutableListOf()
    private var progresso = 0

    private val listaImgs: MutableList<Int> = mutableListOf(
        R.drawable.n0, R.drawable.n1, R.drawable.n2, R.drawable.n3,
        R.drawable.n4, R.drawable.n5, R.drawable.n6, R.drawable.n7,
        R.drawable.n8, R.drawable.n9, R.drawable.n10, R.drawable.bloco)

    constructor(parcel: Parcel) : this() {
        progresso = parcel.readInt()
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN // Esconder StatusBar da App

        binding.SurpriseNumber.setBackgroundResource(R.drawable.bloco)

        binding.btnPlay.setOnClickListener {view ->
            val numeroDigitado = binding.EditNumber.text.toString()

            if (numeroDigitado.isEmpty()) {
                mensagem(view, "Digite um número", "#FF0000")
            }else {
                gerarNumerosAleatorios(view, numeroDigitado.toInt())
            }
        }

        binding.btnReset.setOnClickListener {
            binding.EditNumber.setText("")
            progresso = 0
            binding.LinearProgressIndicator.setProgress(progresso, true)
        }

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(progresso)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun gerarNumerosAleatorios(view: View, numeroDigitado: Int){

        for (n in 0..11){
            listnumber.add(n)
        }

        val numeroAleatorio = Random().nextInt(11)

        val imgNumero = when(numeroAleatorio){
            0 -> {
               listaImgs[0]
            }
            1 -> {
                listaImgs[1]
            }
            2 -> {
                listaImgs[2]
            }
            3 -> {
                listaImgs[3]
            }
            4 -> {
                listaImgs[4]
            }
            5 -> {
                listaImgs[5]
            }
            6 -> {
                listaImgs[6]
            }
            7 -> {
                listaImgs[7]
            }
            8 -> {
                listaImgs[8]
            }
            9 -> {
                listaImgs[9]
            }

            10 -> {
                listaImgs[10]
            }

            else -> {
                listaImgs[11]
            }

        }

        if (numeroDigitado != numeroAleatorio){
            binding.SurpriseNumber.setBackgroundResource(R.drawable.bloco)
            mensagem(view,"Você errou! Tente novamente", "#FF0000")
            progresso = progresso.plus(30)
            binding.LinearProgressIndicator.setProgress(progresso, true)
        }else{
            mensagem(view,"Parabéns, você acertou!", "#209031")
            progresso = progresso.minus(120)
            binding.SurpriseNumber.setBackgroundResource(imgNumero)
            binding.EditNumber.setText("")
            binding.LinearProgressIndicator.setProgress(progresso, true)
            progresso = 0
        }

        if (progresso > 90){
            //navegar para a tela de Game Over
            val intent = Intent(this, GamerOver::class.java)
            startActivity(intent)
        }
    }

    private fun mensagem(view: View, mensagem: String, cor: String) {
        val snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor(cor))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()
    }

}




