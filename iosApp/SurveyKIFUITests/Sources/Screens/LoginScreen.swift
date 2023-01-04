//
//  LoginScreen.swift
//  Survey
//
//  Created by Bliss on 21/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

final class LoginScreen: GenericScreen {

    func loginIfNeeded() {
        if tester.tryFindingView(withAccessibilityIdentifier: ViewId.login(.view)()) {
            tester.tapView(withAccessibilityIdentifier: ViewId.login(.loginButton)())
        }
    }
}
