package com.demo.seed.di

import android.app.Application
import androidx.room.Room
import com.demo.seed.data.CurrencyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /* This will provide Room database */
    @Provides
    @Singleton
    fun provideDatabase(
        app: Application,
        callback: CurrencyDatabase.Callback
    ) = Room.databaseBuilder(app, CurrencyDatabase::class.java, "currency_database")
        .fallbackToDestructiveMigration()
        .addCallback(callback) // we want to pre-populate some data upon creation
        .build()


    /* This will provide Currency Dao */
    @Provides
    fun provideCurrencyDao(db: CurrencyDatabase) = db.currencyDao()


    /* This will provide Application Scope. SupervisorJob() ensures that one child failure
    * will not affect other siblings. */
    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())
}

/* To avoid ambiguity, we will create a qualifier called ApplicationScope */
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope