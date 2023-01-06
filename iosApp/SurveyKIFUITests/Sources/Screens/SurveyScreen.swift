//
//  SurveyScreen.swift
//  Survey
//
//  Created by Bliss on 21/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

final class SurveyScreen: GenericScreen {

    func waitForAppearance() {
        tester.waitForView(withAccessibilityIdentifier: ViewId.surveySelection(.view)())
    }

    func navigateToAccount() {
        waitForAppearance()
        tester.tapView(withAccessibilityIdentifier: ViewId.surveySelection(.headerProfileImage)())
    }

    func swipeRight() {
        tester.swipeView(
            withAccessibilityIdentifier: ViewId.surveySelection(.detailText)(),
            in: .left
        )
    }

    func tapNextButton() {
        tester.tapView(withAccessibilityIdentifier: ViewId.surveySelection(.nextButton)())
    }
}
