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
                    app = ArgumentedXCUIApplication()
                    loginScreen = LoginScreen(in: app)
                    app.launch()
                    loginScreen.waitForExistence(timeout: .default, \.images, with: .view)
                    loginScreen.tapButton(.forgotButton)
                    resetPasswordScreen = ResetPasswordScreen(in: app)
                    resetPasswordScreen.waitForExistence(timeout: .default, \.images, with: .view)
                }

                afterEach {
                    app.terminate()
                }

                it("shows its ui components") {
                    let emailField = resetPasswordScreen.find(\.textFields, with: .emailField)
                    expect(emailField.exists) == true

                    let resetPasswordButton = resetPasswordScreen.find(\.buttons, with: .resetButton)
                    expect(resetPasswordButton.exists) == true
                }

                describe("its reset button") {

                    context("when email is valid") {

                        beforeEach {
                            resetPasswordScreen.fillInField(.emailField, with: "email@email.com")
                            resetPasswordScreen.tapButton(.resetButton)
                            resetPasswordScreen.grantNotificationPermissionIfNeeded()
                        }

                        it("shows notification and hides loading indicator") {
                            expect(resetPasswordScreen.notification.exists)
                                .toEventually(beTrue(), timeout: .default)
                            expect(resetPasswordScreen.loadingSpinner.exists)
                                .toEventually(beFalse(), timeout: .default)
                        }
                    }

                    context("when email is not valid") {

                        beforeEach {
                            resetPasswordScreen.fillInField(.emailField, with: "notemail")
                            resetPasswordScreen.tapButton(.resetButton)
                        }

                        it("shows error") {
                            let textQuery = app.staticTexts["Email is invalid"]
                            expect(textQuery.exists)
                                .toEventually(beTrue(), timeout: .default)
                        }
                    }
                }
            }
        }
    }
}
