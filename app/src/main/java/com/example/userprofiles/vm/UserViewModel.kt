package com.example.userprofiles.vm


import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.userprofiles.db.UserRepository
import com.example.userprofiles.user_data.UserInfo
import com.example.userprofiles.user_data.Users

class UserViewModel(private val repository: UserRepository) : ViewModel(), Observable {


    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val fname = MutableLiveData<String>()

    @Bindable
    val lname = MutableLiveData<String>()

    @Bindable
    val email = MutableLiveData<String>()


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

    fun insertUsers(list: List<UserInfo>) {
        list.forEach {
            repository.insert(it)
        }
    }

    fun getUserData():List<UserInfo> = repository.getAllUser()


}