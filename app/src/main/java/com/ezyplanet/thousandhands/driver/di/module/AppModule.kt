package com.ezyplanet.thousandhands.driver.di.module


import android.app.Application
import android.content.Context
import androidx.room.Room
import com.ezyplanet.thousandhands.core.di.qualifier.PreferenceInfo
import com.ezyplanet.thousandhands.core.util.CoreConstants
import com.ezyplanet.thousandhands.core.util.SchedulerProvider
import com.ezyplanet.thousandhands.driver.data.database.AppDatabase
import com.ezyplanet.thousandhands.driver.data.network.ApiHeader
import com.ezyplanet.thousandhands.driver.data.network.ApiHelper
import com.ezyplanet.thousandhands.driver.data.network.AppApiHelper
import com.ezyplanet.thousandhands.driver.data.network.AppDataManager
import com.ezyplanet.thousandhands.driver.data.preferences.AppPreferenceHelper
import com.ezyplanet.thousandhands.driver.di.builder.ViewModelBuilder
import com.irmansyah.catalogmoviekotlin.data.DataManager


import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Main App [Module] that provides default and public classes everywhere
 */
@Module(includes = [ViewModelBuilder::class])
class AppModule {

    /**
     * provides [Application] context as default context.
     */
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }
    @Provides
    @Singleton
    internal fun provideAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, CoreConstants.APP_DB_NAME).build()



    @Provides
    @PreferenceInfo
    internal fun provideprefFileName(): String = CoreConstants.PREF_NAME

    @Provides
    internal fun provideApiKey(): String = "test"

    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper = appApiHelper

    @Provides
    @Singleton
    internal fun provideProtectedApiHeader(preferenceHelper: AppPreferenceHelper)
            : ApiHeader.ProtectedApiHeader = ApiHeader.ProtectedApiHeader(

            accessToken = "bearer "+preferenceHelper.token)

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }



    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()
}
