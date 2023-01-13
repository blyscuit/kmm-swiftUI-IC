//
//  SurveyScreen.swift
//  Survey
//
//  Created by Bliss on 21/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

final class SurveyScreen: GenericScreen {

    func waitForAppearance() {
        tester().waitForView(withAccessibilityIdentifier: ViewId.surveySelection(.mainImage)())
    }

    func navigateToAccount() {
        waitForAppearance()
        if !tester().tryFindingView(
            withAccessibilityIdentifier: ViewId.account(.profileImage)()
        ) {
            tester().tapView(withAccessibilityIdentifier: ViewId.surveySelection(.headerProfileImage)())
        }
    }

    func swipeRight() {
        tester().swipeView(
            withAccessibilityIdentifier: ViewId.surveySelection(.detailText)(),
            in: .left
        )
    }

    func logoutIfNeeded() {
        if tester().tryFindingView(withAccessibilityIdentifier: ViewId.login(.view)()) {
            return
        }
        if tester().tryFindingView(withAccessibilityIdentifier: ViewId.splash(.view)()) {
            tester().waitForAbsenceOfView(withAccessibilityIdentifier: ViewId.splash(.view)())
        }
        if tester().tryFindingView(withAccessibilityIdentifier: ViewId.surveySelection(.view)()) {
            navigateToAccount()
            tester().tapView(withAccessibilityIdentifier: ViewId.account(.logoutButton)())
        }
        tester().waitForAnimationsToFinish()
    }
}
