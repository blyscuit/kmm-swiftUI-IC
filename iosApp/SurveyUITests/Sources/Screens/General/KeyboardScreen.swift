//
//  KeyboardScreen.swift
//  Survey
//
//  Created by Bliss on 19/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import XCTest

final class KeyboardScreen: ScreenProtocol {

    typealias Identifier = ViewId.General

    let application: XCUIApplication

    init(in application: XCUIApplication) {
        self.application = application
    }
}
