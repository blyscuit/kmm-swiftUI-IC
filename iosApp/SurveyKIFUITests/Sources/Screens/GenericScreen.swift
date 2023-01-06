//
//  GenericScreen.swift
//  Survey
//
//  Created by Bliss on 21/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import KIF
import XCTest

class GenericScreen {

    weak var testCase: XCTestCase!

    var tester: KIFUITestActor {
        testCase.tester()
    }

    init(_ testCase: XCTestCase!) {
        self.testCase = testCase
    }

    func tapBackButton() {
        tester.tapView(withAccessibilityIdentifier: ViewId.general(.backButton)())
    }
}
