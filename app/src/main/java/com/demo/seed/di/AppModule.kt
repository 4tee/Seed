package com.demo.seed.di

import android.app.Application
import androidx.room.Room
import com.demo.seed.data.CurrencyRepository
import com.demo.seed.data.cache.CacheDataSource
import com.demo.seed.data.cache.CacheDataSourceImpl
import com.demo.seed.data.cache.CurrencyListDaoService
import com.demo.seed.data.cache.CurrencyListDaoServiceImpl
import com.demo.seed.data.mapper.CurrencyMapper
import com.demo.seed.data.model.CurrencyInfoEntity
import com.demo.seed.data.room.CurrencyDao
import com.demo.seed.data.room.CurrencyDatabase
import com.demo.seed.ui.model.CurrencyInfo
import com.demo.seed.util.EntityMapper
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

    @Provides
    @Singleton
    fun provideCurrencyListDaoService(currencyDao: CurrencyDao): CurrencyListDaoService {
        return CurrencyListDaoServiceImpl(currencyDao)
    }

    @Provides
    @Singleton
    fun provideCacheDataSource(
        currencyListDaoService: CurrencyListDaoService,
        currencyMapper: CurrencyMapper
    ): CacheDataSource {
        return CacheDataSourceImpl(currencyListDaoService, currencyMapper)
    }

    @Singleton
    @Provides
    fun provideCurrencyRepository(
        cacheDataSource: CacheDataSource
    ): CurrencyRepository {
        return CurrencyRepository(cacheDataSource)
    }

    @Singleton
    @Provides
    fun provideCacheMapper(): EntityMapper<CurrencyInfoEntity, CurrencyInfo> {
        return CurrencyMapper()
    }

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