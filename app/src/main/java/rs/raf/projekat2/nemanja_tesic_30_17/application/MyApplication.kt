package rs.raf.projekat2.nemanja_tesic_30_17.application

import android.app.Application
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import rs.raf.projekat2.nemanja_tesic_30_17.modules.beleskaModule
import rs.raf.projekat2.nemanja_tesic_30_17.modules.coreModule
import rs.raf.projekat2.nemanja_tesic_30_17.modules.korisnikModule
import rs.raf.projekat2.nemanja_tesic_30_17.modules.predavanjaModule
import timber.log.Timber

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initTimber()
        initKoin()
        initStetho()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        val modules = listOf(
            coreModule,
            predavanjaModule,
            beleskaModule,
            korisnikModule
        )
        startKoin {
            androidLogger(Level.DEBUG)
            // Use application context
            androidContext(this@MyApplication)
            // Use properties from assets/koin.properties
            androidFileProperties()
            // Use koin fragment factory for fragment instantiation
            fragmentFactory()
            // modules
            modules(modules)
        }
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }
}