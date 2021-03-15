package com.ajax.ajaxtestassignment.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ajax.ajaxtestassignment.api.UserService
import com.ajax.ajaxtestassignment.db.AppDatabase
import com.ajax.ajaxtestassignment.db.UserDao
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
public class ApplicationModule(val appContext: Context) {

    @Provides
    fun providesRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun userService(retrofit: Retrofit) : UserService = retrofit.create(UserService::class.java)

    @Provides
    fun room() : AppDatabase = Room.databaseBuilder(appContext, AppDatabase::class.java, "the-database").build()

    @Provides
    fun userDao(db: AppDatabase) : UserDao = db.userDao()
}