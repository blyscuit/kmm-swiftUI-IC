//
//  KoinApplication.swift
//  Survey
//
//  Created by Bliss on 21/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Shared

extension KoinApplication {

    static let shared = companion.start()

    @discardableResult
    static func start() -> KoinApplication {
        shared
    }
}

extension KoinApplication {

    private static let keyPaths: [PartialKeyPath<Koin>] = [
        \.logInViewModel,
        \.resetPasswordViewModel,
        \.splashViewModel
        \.surveySelectionViewModel
    ]

    static func inject<T>() -> T {
        shared.inject()
    }

    func inject<T>() -> T {
        for partialKeyPath in Self.keyPaths {
            guard let keyPath = partialKeyPath as? KeyPath<Koin, T> else { continue }
            return koin[keyPath: keyPath]
        }

        fatalError("\(T.self) is not registered with KoinApplication")
    }
}
