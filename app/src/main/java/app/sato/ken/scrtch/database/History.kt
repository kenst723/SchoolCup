package app.sato.ken.scrtch.database

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class History(
    @PrimaryKey var id: Int? = null,
    @Required var name: String = ""
) : RealmObject()