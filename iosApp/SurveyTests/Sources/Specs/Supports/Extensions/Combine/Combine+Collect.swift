//  swiftlint:disable:this file_name
//
//  Combine+Collect.swift
//  Survey
//
//  Created by Bliss on 28/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Combine

// From https://www.swiftbysundell.com/articles/unit-testing-combine-based-swift-code/

extension Published.Publisher {

    func collectNext(_ count: Int) -> AnyPublisher<[Output], Never> {
        dropFirst()
            .collect(count)
            .first()
            .eraseToAnyPublisher()
    }

    func coldCollectNext(_ count: Int) -> AnyPublisher<[Output], Never> {
        collect(count)
            .first()
            .eraseToAnyPublisher()
    }
}
