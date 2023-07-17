package com.technical.task.core.mapper


interface InterfaceListMapper<in From, out To> : InterfaceItemMapper<From, To> {
    fun transformList(models: Collection<From>?): Collection<To>
}