//
//  LoginFlow.swift
//  Survey
//
//  Created by Bliss on 20/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import KIF

extension KIFUITestActor {

    func loginIfNeeded() {
        if tryFindingView(withAccessibilityIdentifier: ViewId.login(.view)()) {
            tester().tapView(withAccessibilityIdentifier: ViewId.login(.loginButton)())
        }
    }
}
