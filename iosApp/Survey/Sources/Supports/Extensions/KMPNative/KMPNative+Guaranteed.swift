//
//  KMPNative+Guaranteed.swift
//  Survey
//
//  Created by Bliss on 23/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Combine
import KMPNativeCoroutinesCombine
import KMPNativeCoroutinesCore
import Shared

public func createGuaranteedPublisher<Output, Failure: Error, Unit>(
    for nativeFlow: @escaping NativeFlow<Output, Failure, Unit>,
    fallback: Output
) -> AnyPublisher<Output, Just<Output>.Failure> {
    return createPublisher(for: nativeFlow)
        .catch { _ -> Just<Output> in
            Just(fallback)
        }
        .eraseToAnyPublisher()
}
