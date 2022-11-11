//
//  LoginScreen.swift
//  Survey
//
//  Created by Bliss on 19/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import XCTest

final class LoginScreen: ScreenProtocol {

    typealias Identifier = ViewId.Login

    let application: XCUIApplication

    init(in application: XCUIApplication) {
        self.application = application
    }
}
