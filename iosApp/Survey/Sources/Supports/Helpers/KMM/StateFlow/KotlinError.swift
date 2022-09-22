//
//  KotlinError.swift
//  Survey
//
//  Created by Bliss on 22/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Shared

class KotlinError: LocalizedError {

    let throwable: KotlinThrowable

    var errorDescription: String? {
        throwable.message
    }

    init(_ throwable: KotlinThrowable) {
        self.throwable = throwable
    }
}
