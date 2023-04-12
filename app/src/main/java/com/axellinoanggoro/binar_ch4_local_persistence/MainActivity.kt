package com.axellinoanggoro.binar_ch4_local_persistence

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.axellinoanggoro.binar_ch4_local_persistence.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences("dataprefs", Context.MODE_PRIVATE)
        saveData()
        viewData()
        clearData()
    }

    fun saveData() {
        binding.btnsave.setOnClickListener {
            //mengambil isi editText
            val name = binding.etName.text.toString()
            val id = binding.etId.text.toString()
            //simpan data ke sharedPreferences
            val save = sharedPref.edit()
            save.putString("namauser", name)
            save.putString("iduser", id)
            save.apply()
            Toast.makeText(this, "save data berhasil", Toast.LENGTH_SHORT).show()
        }
    }

    fun viewData() {
        binding.btnView.setOnClickListener {

            val getNama = sharedPref.getString("namauser", "")
            val getId = sharedPref.getString("iduser", "")
            //set data dari sharedPreference ke text vier
            binding.tvId.text = getId
            binding.tvName.text = getNama
        }
    }

    fun clearData(){
        binding.btnClear.setOnClickListener {
            val hapus = sharedPref.edit()
            hapus.clear()
            hapus.apply()
            binding.tvId.setText("")
            binding.tvName.setText("")
        }
    }

}