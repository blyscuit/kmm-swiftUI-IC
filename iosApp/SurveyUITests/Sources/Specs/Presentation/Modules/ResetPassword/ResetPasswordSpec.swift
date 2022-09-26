//
//  ResetPasswordSpec.swift
//  Survey
//
//  Created by Bliss on 26/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Nimble
import Quick

final class ResetPasswordSpec: QuickSpec {

    override func spec() {

        var app: XCUIApplication!
        var loginScreen: LoginScreen!
        var resetPasswordScreen: ResetPasswordScreen!

        describe("a Reset Password screen") {

            describe("its open") {

                beforeEach {
                    app = XCUIApplication()
                    loginScreen = LoginScreen(in: app)
                    app.launch()
                    loginScreen.tapButton(.forgotButton)
                    resetPasswordScreen = ResetPasswordScreen(in: app)
                }

                afterEach {
                    app.terminate()
                }

                it("it shows its ui components") {
                    let emailField = resetPasswordScreen.find(\.textFields, with: .emailField)
                    expect(emailField.exists) == true

                    let passwordField = loginScreen.find(\.secureTextFields, with: .passwordField)
                    expect(passwordField.exists) == true

                    let resetPassword = resetPasswordScreen.find(\.buttons, with: .resetButton)
                    expect(resetPassword.exists) == true
                }
            }
        }
    }
}
