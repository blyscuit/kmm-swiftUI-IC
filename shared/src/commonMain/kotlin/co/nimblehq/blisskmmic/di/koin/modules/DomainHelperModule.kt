package co.nimblehq.blisskmmic.di.koin.modules

import co.nimblehq.blisskmmic.domain.platform.VersionCode
import co.nimblehq.blisskmmic.domain.platform.VersionCodeImpl
import co.nimblehq.blisskmmic.domain.platform.datetime.DateTimeFormatter
import co.nimblehq.blisskmmic.domain.platform.datetime.DateTimeFormatterImpl
import org.koin.dsl.module

val domainHelperModule = module {
    single<DateTimeFormatter> { DateTimeFormatterImpl() }
    single<VersionCode> { VersionCodeImpl() }
}
