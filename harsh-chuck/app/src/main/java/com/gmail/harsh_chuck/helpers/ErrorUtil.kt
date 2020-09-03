package com.gmail.harsh_chuck.helpers

import timber.log.Timber

/**
 * функция выполняющая обработку исключения возникшего во время работы приложения
 */
val errorTimber: (Throwable) -> Unit = { error: Throwable -> Timber.e(error) }
