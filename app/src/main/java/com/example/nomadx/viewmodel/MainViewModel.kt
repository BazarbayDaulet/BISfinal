package com.example.nomadx.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.nomadx.data.SessionManager
import com.example.nomadx.data.db.AppDatabase
import com.example.nomadx.data.db.PostEntity
import com.example.nomadx.data.db.UserEntity
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = AppDatabase.get(app).dao()
    private val session = SessionManager(app)

    val allPosts: LiveData<List<PostEntity>> = dao.getAllPosts().asLiveData()
    val loginResult = MutableLiveData<Boolean>()
    val registerResult = MutableLiveData<Boolean>()
    val errorMsg = MutableLiveData<String>()

    val userAvatar = MutableLiveData<String?>()
    fun login(email: String, pass: String) {
        viewModelScope.launch {
            val user = dao.login(email, pass)
            if (user != null) {
                session.saveSession(email)
                loginResult.postValue(true)
            } else {
                errorMsg.postValue("Неверный логин или пароль")
            }
        }
    }

    fun register(email: String, pass: String) {
        viewModelScope.launch {
            if (dao.checkUser(email) == null) {
                dao.register(UserEntity(email = email, pass = pass))
                registerResult.postValue(true)
            } else {
                errorMsg.postValue("Пользователь уже существует")
            }
        }
    }


    fun addPost(title: String, desc: String, price: Double) {
        val email = session.getEmail()
        viewModelScope.launch {
            dao.addPost(PostEntity(title = title, desc = desc, price = price, authorEmail = email))
        }
    }

    fun getCurrentUserEmail() = session.getEmail()
    fun logout() = session.logout()
    fun isLogged() = session.isLogged()
}