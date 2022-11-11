//
//  ScreenProtocol.swift
//  Survey
//
//  Created by Bliss on 19/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Nimble
import XCTest

protocol ScreenProtocol: AnyObject {

    associatedtype Identifier: RawRepresentable where Identifier.RawValue == String

    var application: XCUIApplication { get }
}

extension ScreenProtocol {

    var keyboard: KeyboardScreen { KeyboardScreen(in: application) }

    func find(
        _ elementKey: KeyPath<XCUIApplication, XCUIElementQuery>,
        with identifier: Identifier
    ) -> XCUIElement {
        return application[keyPath: elementKey][identifier.rawValue]
    }

    func find(
        _ elementKey: KeyPath<XCUIApplication, XCUIElementQuery>,
        with string: String
    ) -> XCUIElement {
        return application[keyPath: elementKey][string]
    }

    @discardableResult
    func waitForExistence(
        timeout: TimeInterval,
        _ elementKey: KeyPath<XCUIApplication, XCUIElementQuery> = \.otherElements,
        with identifier: Identifier,
        file: FileString = #file,
        line: UInt = #line
    ) -> Self {
        let isFound = application[keyPath: elementKey][identifier.rawValue]
            .waitForExistence(timeout: timeout)

        if !isFound {
            fail(
                "\(identifier.rawValue.capitalized) is not found after \(timeout) seconds delay",
                file: file,
                line: line
            )
        }
        return self
    }

    func fillInField(_ viewId: Identifier, with value: String) {
        let field = application.textFields[viewId.rawValue]
        field.tap()
        field.typeText(value)
    }

    func fillInSecuredField(_ viewId: Identifier, with value: String) {
        let field = application.secureTextFields[viewId.rawValue]
        field.tap()
        field.typeText(value)
    }

    func tapButton(_ viewId: Identifier) {
        let button = application.buttons[viewId.rawValue]
        button.tap()
    }
}
