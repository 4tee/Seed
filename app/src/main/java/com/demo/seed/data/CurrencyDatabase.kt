package com.demo.seed.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.demo.seed.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [CurrencyInfoEntity::class], version = 1)
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
                dao.insert(CurrencyInfoEntity("BTC", "Bitcoin", "BTC"))
                dao.insert(CurrencyInfoEntity("ETH", "Ethereum", "ETH"))
                dao.insert(CurrencyInfoEntity("XRP", "XRP", "XRP"))
                dao.insert(CurrencyInfoEntity("BCH", "Bitcoin Cash", "BCH"))
                dao.insert(CurrencyInfoEntity("LTC", "Litecoin", "LTC"))
                dao.insert(CurrencyInfoEntity("EOS", "EOS", "EOS"))
                dao.insert(CurrencyInfoEntity("BNB", "Binance Coin", "BNB"))
                dao.insert(CurrencyInfoEntity("LINK", "Chainlink", "LINK"))
                dao.insert(CurrencyInfoEntity("NEO", "NEO", "NEO"))
                dao.insert(CurrencyInfoEntity("ETC", "Ethereum Classic", "ETC"))
                dao.insert(CurrencyInfoEntity("ONT", "Ontology", "ONT"))
                dao.insert(CurrencyInfoEntity("CRO", "Crypto.com Chain", "CRO"))
                dao.insert(CurrencyInfoEntity("CUC", "Cucumber", "CUC"))
                dao.insert(CurrencyInfoEntity("USDC", "USD Coin", "USDC"))
            }
        }
    }
}