package app.sato.ken.scrtch.database

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val realmConfig: RealmConfiguration = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfig)
    }
}