package com.technical.task.core.mapper


interface InterfaceItemMapper<in From, out To> {
    fun transform(model: From): To
}