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
        if tester.tryFindingView(withAccessibilityIdentifier: ViewId.surveySelection(.view)()) {
            return
        }
        if tester.tryFindingView(withAccessibilityIdentifier: ViewId.splash(.view)()) {
            tester.waitForAbsenceOfView(withAccessibilityIdentifier: ViewId.splash(.view)())
        }
        if tester.tryFindingView(withAccessibilityIdentifier: ViewId.login(.view)()) {
            tester.waitForTappableView(withAccessibilityIdentifier: ViewId.login(.loginButton)())
            fillCredentialIfNeeded()
            tester.tapView(withAccessibilityIdentifier: ViewId.login(.loginButton)())
        }
    }

    func waitForAppearance() {
        tester.waitForView(withAccessibilityIdentifier: ViewId.login(.view)())
    }

    func navigateToResetPassword() {
        tester.clearText(
            fromAndThenEnterText: "",
            intoViewWithAccessibilityIdentifier: ViewId.login(.passwordField)()
        )
        tester.tapView(withAccessibilityIdentifier: ViewId.login(.forgotButton)())
    }

    private func fillCredentialIfNeeded() {
        let uiTestConfig = SharedBuildConfig.UITestConfig()
        tester.clearText(
            fromAndThenEnterText: uiTestConfig.password(),
            intoViewWithAccessibilityIdentifier: ViewId.login(.passwordField)()
        )
    }
}
