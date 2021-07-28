package com.demo.seed.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.demo.seed.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [CurrencyInfo::class], version = 1)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

    class Callback @Inject constructor(
        private val database: Provider<CurrencyDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
        ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            // operations during the creation
            val dao = database.get().currencyDao()
            applicationScope.launch {
                dao.insert(CurrencyInfo("BTC", "Bitcoin", "BTC"))
                dao.insert(CurrencyInfo("ETH", "Ethereum", "ETH"))
                dao.insert(CurrencyInfo("XRP", "XRP", "XRP"))
                dao.insert(CurrencyInfo("BCH", "Bitcoin Cash", "BCH"))
                dao.insert(CurrencyInfo("LTC", "Litecoin", "LTC"))
                dao.insert(CurrencyInfo("EOS", "EOS", "EOS"))
                dao.insert(CurrencyInfo("BNB", "Binance Coin", "BNB"))
                dao.insert(CurrencyInfo("LINK", "Chainlink", "LINK"))
                dao.insert(CurrencyInfo("NEO", "NEO", "NEO"))
                dao.insert(CurrencyInfo("ETC", "Ethereum Classic", "ETC"))
                dao.insert(CurrencyInfo("ONT", "Ontology", "ONT"))
                dao.insert(CurrencyInfo("CRO", "Crypto.com Chain", "CRO"))
                dao.insert(CurrencyInfo("CUC", "Cucumber", "CUC"))
                dao.insert(CurrencyInfo("USDC", "USD Coin", "USDC"))
            }
        }
    }
}