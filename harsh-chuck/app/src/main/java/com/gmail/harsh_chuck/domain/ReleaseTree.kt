package com.gmail.harsh_chuck.domain

import android.app.Application
import android.util.Log
import timber.log.Timber
import javax.inject.Inject

/**
 * класс может быть использльзован для определения логии вывода логов или работами над логами в
 * реализной сборке приложения. Являеться наследником Timber.Tree класса
 */
class ReleaseTree @Inject constructor() : Timber.Tree() {
    //метод отправляет данные на сервер, так же логирует все ошибки в базу данных (CashboxDatabase в таблицу: throwable_error)
    //на текущий момент не реализована логика выборки данных с базы ошибок для повторной отправки
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }

        // log your crash to your favourite
        // Sending crash report to Firebase CrashAnalytics

        // FirebaseCrash.report(message);
        // FirebaseCrash.report(new Exception(message));
    }
}
