package co.nimblehq.blisskmmic.di.koin.extensions

import co.nimblehq.blisskmmic.data.network.core.NetworkClient
import co.nimblehq.blisskmmic.di.koin.initKoin
import org.koin.core.Koin
import org.koin.core.KoinApplication

fun KoinApplication.Companion.start(): KoinApplication = initKoin()

// TODO: Add injectable dependencies
