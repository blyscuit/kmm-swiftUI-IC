//
//  AA_ResetPasswordSpec.swift
//  SurveyKIFUITests
//
//  Created by Bliss on 11/1/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Nimble
import Quick

// Named to be the first test
final class AA_ResetPasswordSpec: QuickSpec {

    override func spec() {

        var loginScreen: LoginScreen!
        var surveyScreen: SurveyScreen!

        describe("a Reset Password screen") {

            beforeEach {
                loginScreen = LoginScreen(self)
                surveyScreen = SurveyScreen(self)
            }

            describe("its open") {

                beforeEach {
                    surveyScreen.logoutIfNeeded()
                    loginScreen.navigateToResetPassword()
                }

                afterEach {
                    loginScreen.pressBack()
                    loginScreen.waitForAppearance()
                    loginScreen.fillCredentialIfNeeded()
                }

                it("it shows its ui components") {
                    self.tester().waitForView(
                        withAccessibilityIdentifier: ViewId.resetPassword(.resetButton)()
                    )
                    self.tester().waitForView(
                        withAccessibilityIdentifier: ViewId.resetPassword(.emailField)()
                    )
                }
            }
        }
    }
}
