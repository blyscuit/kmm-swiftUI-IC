//
//  LazyKoin.swift
//  Survey
//
//  Created by Bliss on 21/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

@propertyWrapper
struct LazyKoin<T> {

    lazy var wrappedValue: T = KoinApplication.shared.inject()

    init() {}
}
