//
//  AccountFlow.swift
//  Survey
//
//  Created by Bliss on 20/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import KIF

extension KIFUITestActor {

    func navigateToAccount() {
        tapView(withAccessibilityIdentifier: ViewId.surveySelection(.headerProfileImage)())
    }

    func navigateToHome() {
        tapScreen(at: .init(x: 10.0, y: 100.0))
    }
}
