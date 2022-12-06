//
//  XCTestCase+Publisher.swift
//  Survey
//
//  Created by Bliss on 28/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Combine
import XCTest

// From https://www.swiftbysundell.com/articles/unit-testing-combine-based-swift-code/

extension XCTestCase {

    @discardableResult
    func awaitPublisher<T: Publisher>(
        _ publisher: T,
        timeout: TimeInterval = .default,
        file: StaticString = #file,
        line: UInt = #line
    ) throws -> T.Output {
        var result: Result<T.Output, Error>?
        let expectation = self.expectation(description: "Awaiting publisher")

        let cancellable = publisher.sink(
            receiveCompletion: { completion in
                switch completion {
                case let .failure(error):
                    result = .failure(error)
                case .finished:
                    break
                }

                expectation.fulfill()
            },
            receiveValue: { value in
                result = .success(value)
            }
        )
        waitForExpectations(timeout: timeout)
        cancellable.cancel()
        let unwrappedResult = try XCTUnwrap(
            result,
            "Awaited publisher did not produce any output",
            file: file,
            line: line
        )

        return try unwrappedResult.get()
    }
}
