package com.example.historicalfigures

import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.historicalfigures.adapter.HistoricalFigureAdapter
import com.example.historicalfigures.databinding.ActivityMainBinding
import com.example.historicalfigures.model.entity.HistoricalFigure
import com.example.historicalfigures.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HistoricalFigureAdapter
    private var allFigures: MutableList<HistoricalFigure> = ArrayList()
    private lateinit var searchEditText: EditText
    private lateinit var helloTextView: TextView
    private lateinit var descTextView: TextView
    private lateinit var searchButton: Button

    companion object {
        private const val API_KEY = "SNFNkTTxEhBVdg/dCYi4UQ==LCCXxMqV6tf1dEeT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView

        adapter = HistoricalFigureAdapter(allFigures)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        searchEditText = binding.searchEditText
        searchButton = binding.actionButton
        helloTextView = binding.helloTextView
        descTextView = binding.descriptionTextView

        adapter.submitList(allFigures)

        searchButton.setOnClickListener {
            val searchQuery = searchEditText.text.toString().trim()
            if (searchQuery.isNotEmpty()) {
                fetchHistoricalFigures(searchQuery)
                helloTextView.text = "Results"
                descTextView.visibility = View.GONE
            }
        }
    }

    private fun fetchHistoricalFigures(name: String) {
        val apiKeyEncoded = Uri.encode(API_KEY)
        Log.d("API_REQUEST", "Encoded API Key: $apiKeyEncoded")
        val client = ApiClient.HistoricalFigureServiceInstance.fetchHistoricalFigureList(name, API_KEY)
        Log.d("API_REQUEST", "URL: ${client.request().url()}")
        client.enqueue(object: Callback<List<HistoricalFigure>> {

            override fun onResponse(
                call: Call<List<HistoricalFigure>>,
                response: Response<List<HistoricalFigure>>
            ) {
                if (response.isSuccessful) {
                    val figureList = response.body()
                    if (figureList != null) {
                        if (figureList.isNotEmpty()) {
                            adapter.submitList(figureList)
                        } else {
                            Log.e(ContentValues.TAG, "Response body is empty")
                        }
                    } else {
                        Log.e(ContentValues.TAG, "Response body is null")
                    }
                } else {
                    Log.e(ContentValues.TAG, "Failed to fetch data: ${response.code()}")
                    Log.e(ContentValues.TAG, "Error Body: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<HistoricalFigure>>, t: Throwable) {
                Log.e(ContentValues.TAG, "Error fetching offer list: ${t.message}")
            }
        })
    }
}
