package com.example.openpaytest_network.di

import com.example.openpaytest_network.BuildConfig
import com.example.openpaytest_network.NetworkBuilder
import com.example.openpaytest_network.interactors.NetworkInteractor
import com.example.openpaytest_network.services.MoviesApiServices
import com.example.openpaytest_network.services.UserApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun retrofitProvider() = NetworkBuilder.build(BuildConfig.BASE_URL)

    @Provides
    @Singleton
    fun networkInteractor()  : NetworkInteractor = NetworkInteractor()

    @Provides
    @Singleton
    fun userApiProvider(retrofit: Retrofit)
    : UserApiServices = retrofit.create(UserApiServices::class.java)

    @Provides
    @Singleton
    fun moviesApiProvider(retrofit: Retrofit)
    : MoviesApiServices = retrofit.create(MoviesApiServices::class.java)

}