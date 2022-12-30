package co.nimblehq.blisskmmic.domain.platform.datetime

import platform.Foundation.*

class DateFormatter {

    companion object {

        var dateFormatters:MutableMap<DateFormat, NSDateFormatter> = mutableMapOf()

        fun get(dateFormat: DateFormat): NSDateFormatter {
            return initializeIfNeeded(dateFormat)
        }

        private fun dateFormatter(dataFormat: DateFormat): NSDateFormatter {
            return NSDateFormatter()
                .also {
                    it.dateFormat = dataFormat.value
                }
        }

        private fun initializeIfNeeded(dateFormat: DateFormat): NSDateFormatter {
            dateFormatters[dateFormat]?.let { return it }
            val dateFormatter = dateFormatter(dateFormat)
            dateFormatters[dateFormat] = dateFormatter
            return dateFormatter
        }
    }
}
