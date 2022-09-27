//
//  LoginSpec.swift
//  Survey
//
//  Created by Bliss on 19/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Nimble
import Quick

final class LoginSpec: QuickSpec {

    override func spec() {

        var app: XCUIApplication!
        var loginScreen: LoginScreen!

        describe("a Login screen") {

            describe("its open") {

                beforeEach {
                    app = XCUIApplication()
                    loginScreen = LoginScreen(in: app)
                    app.launch()
                }

                afterEach {
                    app.terminate()
                }

                it("it shows its ui components") {
                    let emailField = loginScreen.find(\.textFields, with: .emailField)
                    expect(emailField.exists) == true

                    let passwordField = loginScreen.find(\.secureTextFields, with: .passwordField)
                    expect(passwordField.exists) == true

                    let loginButton = loginScreen.find(\.buttons, with: .loginButton)
                    expect(loginButton.exists) == true

                    let forgotButton = loginScreen.find(\.buttons, with: .forgotButton)
                    expect(forgotButton.exists) == true
                }

                context("when fill in valid credentials") {

                    beforeEach {
                        loginScreen.fillInField(.emailField, with: "dev@nimblehq.co")
                        loginScreen.fillInSecuredField(.passwordField, with: "123456")
                        loginScreen.tapButton(.loginButton)
                    }

                    it("shows home screen") {
                        // TODO: - Implement later
                        expect(true) == true
                    }
                }

                context("when fill in invalid credentials") {

                    beforeEach {
                        loginScreen.fillInField(.emailField, with: "test@nimblehq.co")
                        loginScreen.fillInSecuredField(.passwordField, with: "123456")
                        loginScreen.tapButton(.loginButton)
                    }

                    it("shows an alert after the request fails") {
                        // TODO: - Implement later
                        expect(true) == true
                    }
                }
            }
        }
    }
}
