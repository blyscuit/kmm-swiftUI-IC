//
//  LoginScreen.swift
//  Survey
//
//  Created by Bliss on 21/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

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
            tester.tapView(withAccessibilityIdentifier: ViewId.login(.loginButton)())
        }
    }
}
