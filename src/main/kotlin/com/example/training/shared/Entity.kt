package com.example.training.shared

import java.util.*
import java.util.Objects.requireNonNull


open class Entity<Id> {
    var id: Id? = null
        protected set

    constructor(id: Id) {
        requireNonNull(id)
        this.id = id
    }

    protected constructor()

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val entity = o as Entity<*>
        return id == entity.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }
}