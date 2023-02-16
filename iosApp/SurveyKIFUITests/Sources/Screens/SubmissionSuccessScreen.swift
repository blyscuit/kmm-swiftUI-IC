//
//  SubmissionSuccessScreen.swift
//  SurveyKIFUITests
//
//  Created by Bliss on 16/2/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

final class SubmissionSuccessScreen: GenericScreen {

    func waitForAppearance() {
        tester()
            .usingAnimationWaitingTimeout(.long)
            .waitForView(withAccessibilityIdentifier: ViewId.submissionSuccess(.animation)())
    }

    func waitForDisappearance() {
        tester().waitForAbsenceOfView(withAccessibilityIdentifier: ViewId.submissionSuccess(.animation)())
    }
}
