package com.electro.essential.exception.mapper

interface ExceptionToMessageMapper {
    fun getLocalizedMessage(exception: Exception): String

    companion object : ExceptionToMessageMapper {
        private var instance: ExceptionToMessageMapper? = null

        override fun getLocalizedMessage(exception: Exception): String {
            return instance?.getLocalizedMessage(exception) ?: ""
        }

        fun init(mapper: ExceptionToMessageMapper) { instance = mapper }

        fun reset() { instance = null }
    }
}