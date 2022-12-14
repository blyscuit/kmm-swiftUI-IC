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
                    app = ArgumentedXCUIApplication()
                    app.launch()
                    loginScreen = LoginScreen(in: app)
                }

                afterEach {
                    app.terminate()
                }

                it("it shows its ui components") {
                    loginScreen.waitForExistence()

                    let emailField = loginScreen.find(\.textFields, with: .emailField)
                    expect(emailField.exists) == true

                    let passwordField = loginScreen.find(\.secureTextFields, with: .passwordField)
                    expect(passwordField.exists) == true

                    let loginButton = loginScreen.find(\.buttons, with: .loginButton)
                    expect(loginButton.exists) == true

                    loginScreen.showResetPasswordButton()
                    let forgotButton = loginScreen.find(\.buttons, with: .forgotButton)
                    expect(forgotButton.exists) == true
                }

                context("when fill in valid credentials") {

                    beforeEach {
                        let loginFlow = LoginFlow(in: app)
                        loginFlow.execute()
                    }

                    it("shows home screen") {
                        let surveySelectionScreen = SurveySelectionScreen(in: app)
                        surveySelectionScreen.waitForExistence(timeout: .default, with: .view)
                    }
                }

                context("when fill in invalid credentials") {

                    beforeEach {
                        loginScreen.waitForExistence()
                        loginScreen.replaceInInField(.emailField, with: "test@nimblehq.co")
                        loginScreen.replaceInSecuredField(.passwordField, with: "123456")
                        loginScreen.tapButton(.loginButton)
                    }

                    it("shows an alert after the request fails") {
                        expect(loginScreen.wrongCredentialAlert().exists)
                            .toEventually(beTrue(), timeout: .default)
                    }
                }
            }
        }
    }
}
