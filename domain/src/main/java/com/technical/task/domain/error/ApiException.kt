package com.technical.task.domain.error

import java.lang.Exception

class ApiException(message: String) : Exception(message)