package com.example.trackfit.model.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}
