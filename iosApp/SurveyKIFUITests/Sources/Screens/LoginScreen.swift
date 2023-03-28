//
//  LoginScreen.swift
//  Survey
//
//  Created by Bliss on 21/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Shared

final class LoginScreen: GenericScreen {

    func loginIfNeeded() {
        if tester().tryFindingView(withAccessibilityIdentifier: ViewId.surveySelection(.view)()) {
            return
        }
        if tester().tryFindingView(withAccessibilityIdentifier: ViewId.splash(.view)()) {
            tester().waitForAbsenceOfView(withAccessibilityIdentifier: ViewId.splash(.view)())
        }
        if tester().tryFindingView(withAccessibilityIdentifier: ViewId.login(.view)()) {
            system().wait(forTimeInterval: 60.0)
        }
        while tester().tryFindingView(withAccessibilityIdentifier: ViewId.login(.view)()) {
            tester().waitForTappableView(withAccessibilityIdentifier: ViewId.login(.loginButton)())
            tester().tapView(withAccessibilityIdentifier: ViewId.login(.loginButton)())
        }
        tester().waitForAbsenceOfView(withAccessibilityIdentifier: ViewId.login(.view)())
    }

    func waitForAppearance() {
        tester().waitForView(withAccessibilityIdentifier: ViewId.login(.view)())
    }

    func navigateToResetPassword() {
        tester().clearTextFromView(withAccessibilityIdentifier: ViewId.login(.passwordField)())
        tester().tapView(withAccessibilityIdentifier: ViewId.login(.forgotButton)())
    }

    func fillCredentialIfNeeded() {
        let uiTestConfig = SharedBuildConfig.UITestConfig()
        tester().clearText(
            fromAndThenEnterText: uiTestConfig.password(),
            intoViewWithAccessibilityIdentifier: ViewId.login(.passwordField)()
        )
    }
}
