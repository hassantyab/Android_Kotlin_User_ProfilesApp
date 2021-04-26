package com.example.userprofiles
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userprofiles.databinding.ActivityMainBinding
import com.example.userprofiles.db.AppDatabase
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException
import com.example.userprofiles.db.UserRepository
import com.example.userprofiles.user_data.DetailsActivity
import com.example.userprofiles.user_data.UserInfo

import com.example.userprofiles.user_data.Users
import com.example.userprofiles.vm.UserViewModel
import com.example.userprofiles.vm.UserViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var cellAdapter: CellAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = AppDatabase.getInstance(application).data_dao
        val repository = UserRepository(dao)
        val factory = UserViewModelFactory(repository)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
        binding.myViewModel = userViewModel
        binding.lifecycleOwner = this
        initialize()

        if (Util.isOnline(this))
            getUserData()
        else
            fetchDataFromRoom()
    }

    private fun getUserData() {
        val url = "https://reqres.in/api/users?per_page=10"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                val data = response.body()?.string().toString()
                Log.d("res", data)
                val gson = GsonBuilder().create()
                val users = gson.fromJson<Users>(data, Users::class.java)
                users?.data?.let {
                    runOnUiThread {
                        cellAdapter.addAll(it)
                        userViewModel.insertUsers(it)
                    }

                }

            }

            override fun onFailure(call: Call, e: IOException) {
                Log.d("res", "Failed to get")
            }
        })
    }

    private fun fetchDataFromRoom(){
        cellAdapter.addAll(userViewModel.getUserData())
    }

    private fun initialize() {
        binding.profileRecyclerView.layoutManager = LinearLayoutManager(this)
        cellAdapter = CellAdapter(this) { selectedItem: UserInfo -> listItemClicked(selectedItem) }
        binding.profileRecyclerView.adapter = cellAdapter
    }


    private fun listItemClicked(user: UserInfo) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("fname", user.first_name)
        intent.putExtra("lname", user.last_name)
        intent.putExtra("email", user.email)
        intent.putExtra("avatar", user.avatar)
        startActivity(intent)
    }

}
