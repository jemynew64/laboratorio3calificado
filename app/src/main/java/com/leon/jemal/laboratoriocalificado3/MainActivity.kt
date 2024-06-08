package com.leon.jemal.laboratoriocalificado3

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.leon.jemal.laboratoriocalificado3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var listProfesor: List<ProfesorResponse> = emptyList()
    private val adapter by lazy {ProfesorAdapter(listProfesor)}
    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {MainViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvProfesor.adapter = adapter
        observeValues()
    }

    private fun observeValues() {
        viewModel.isLoading.observe(this) { isLoading ->
            //depende del boleano a pasar cambiar mostrar o ocultar
        }

        viewModel.profesorList.observe(this) { profesorList ->
            adapter.list = profesorList
            adapter.notifyDataSetChanged()
        }

        viewModel.errorApi.observe(this) { errorMessage ->
            showMessage(errorMessage)
        }
    }

    private fun showMessage(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}

