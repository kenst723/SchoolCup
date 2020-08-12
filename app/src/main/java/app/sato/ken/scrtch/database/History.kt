package app.sato.ken.scrtch.database

import io.realm.RealmObject

open class History(
    open var name: String = ""
) : RealmObject()