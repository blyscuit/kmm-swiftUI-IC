//
//  SurveyQuestionScreen.swift
//  Survey
//
//  Created by Bliss on 11/1/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

final class SurveyQuestionScreen: GenericScreen {

    func waitForAppearance() {
        tester().waitForView(withAccessibilityIdentifier: ViewId.surveyQuestion(.titleText)())
    }

    func tapCloseButton() {
        tester().tapView(withAccessibilityIdentifier: ViewId.surveyQuestion(.closeButton)())
    }

    func tapConfirmCloseDialog() {
        tester().tapView(withAccessibilityLabel: "Yes")
    }

    func tapCancelCloseDialog() {
        tester().tapView(withAccessibilityLabel: "Cancel")
    }
}
