@file:Suppress("MatchingDeclarationName")
package co.nimblehq.blisskmmic.helpers.extensions.ios

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// For iOS Unit Test
class AnyFlow<T>(source: Flow<T>): Flow<T> by source {

    constructor(result: T) : this( flow { emit(result) } )
    constructor(errorMessage: String) : this( flow { error(errorMessage) } )
}
