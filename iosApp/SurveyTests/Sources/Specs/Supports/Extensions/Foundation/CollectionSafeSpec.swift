//
//  CollectionSafeSpec.swift
//  SurveyTests
//
//  Created by Bliss on 6/2/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Nimble
import Quick

@testable import Survey

final class CollectionSafeSpec: QuickSpec {

    override func spec() {

        describe("a string array") {
            var values: [String]!

            context("when there is 1 value") {
                beforeEach {
                    values = ["hello"]
                }

                context("when getting value at index 0 with safe") {

                    it("returns correct value") {
                        expect(values[safe: 0]) == "hello"
                    }
                }

                context("when getting value at index 1 with safe") {

                    it("returns nil") {
                        expect(values[safe: 1]) == nil
                    }
                }
            }
        }
    }
}
