//
//  LoginFlow.swift
//  Survey
//
//  Created by Bliss on 28/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Shared
import XCTest

final class LoginFlow {

    let app: XCUIApplication

    init(in application: XCUIApplication) {
        app = application
    }

    func execute() {
        let loginScreen = LoginScreen(in: app)
        loginScreen.waitForExistence()
        loginScreen.tapButton(.loginButton)
    }
}
