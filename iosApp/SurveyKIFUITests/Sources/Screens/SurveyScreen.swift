//
//  SurveyScreen.swift
//  Survey
//
//  Created by Bliss on 21/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

final class SurveyScreen: GenericScreen {

    func navigateToAccount() {
        tester.tapView(withAccessibilityIdentifier: ViewId.surveySelection(.headerProfileImage)())
    }
}
