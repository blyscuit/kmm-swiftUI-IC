//
//  SurveyDetailScreen.swift
//  Survey
//
//  Created by Bliss on 6/1/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

final class SurveyDetailScreen: GenericScreen {

    func waitForAppearance() {
        tester.waitForView(withAccessibilityIdentifier: ViewId.surveyDetail(.view)())
    }
}
