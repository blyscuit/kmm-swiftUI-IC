//
//  LoginFlow.swift
//  Survey
//
//  Created by Bliss on 28/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import XCTest

final class LoginFlow {

    let app: XCUIApplication

    init(in application: XCUIApplication) {
        app = application
    }

    func execute() {
        let loginScreen = LoginScreen(in: app)
        loginScreen.fillInField(.emailField, with: "dev@nimblehq.co")
        loginScreen.fillInSecuredField(.passwordField, with: "12345678")
        loginScreen.tapButton(.loginButton)
    }
}
