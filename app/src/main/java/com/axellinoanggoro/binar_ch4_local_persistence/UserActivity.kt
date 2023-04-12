package com.axellinoanggoro.binar_ch4_local_persistence

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import androidx.lifecycle.asLiveData
import androidx.lifecycle.observe
import com.axellinoanggoro.binar_ch4_local_persistence.databinding.ActivityUserBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserBinding
    lateinit var userMan : UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userMan = UserManager(this)

        binding.btnSavePrefs.setOnClickListener{
            val nama = binding.etNama.text.toString()
            val umur = binding.etUmur.text.toString()

            GlobalScope.launch {
                userMan.saveData(nama, umur.toInt())
            }
        }

        binding.btnClearPrefs.setOnClickListener {
            GlobalScope.launch {
                userMan.deleteData()
            }
        }
    }

    fun setData(){
        userMan.userName.asLiveData().observe(this,{
            binding.resultUmur.text.toString()
        })

        userMan.userUmur.asLiveData().observe(this,{
            binding.resultUmur.text = it.toString()
        })
    }

//        fun setData(){
//            userMan.asLiveData().observe(this,{
//                binding.resultNama.text = it.toString()
//            })
//            userMan.userUmur.asLiveData().observe(this,{
//                binding.resultUmur.text = it.toString()
//            })
//        }
}