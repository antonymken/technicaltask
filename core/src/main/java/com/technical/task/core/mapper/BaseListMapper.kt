package com.technical.task.core.mapper


import java.util.Collections.emptyList


abstract class BaseListMapper<From, To> : InterfaceListMapper<From, To> {

    override fun transformList(models: Collection<From>?): Collection<To> {
        if (models.isNullOrEmpty()) return emptyList()
        val toList = mutableListOf<To>()
        for (from in models) {
            toList.add(transform(from))
        }
        return toList
    }
}
