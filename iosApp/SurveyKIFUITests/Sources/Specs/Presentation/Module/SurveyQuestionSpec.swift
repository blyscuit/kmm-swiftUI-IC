//
//  SurveyQuestionSpec.swift
//  Survey
//
//  Created by Bliss on 11/1/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Nimble
import Quick

final class SurveyQuestionSpec: QuickSpec {

    override func spec() {

        var loginScreen: LoginScreen!
        var surveyScreen: SurveyScreen!
        var surveyDetailScreen: SurveyDetailScreen!
        var surveyQuestionScreen: SurveyQuestionScreen!

        describe("a Survey Question screen") {

            beforeEach {
                loginScreen = LoginScreen(self)
                surveyScreen = SurveyScreen(self)
                surveyDetailScreen = SurveyDetailScreen(self)
                surveyQuestionScreen = SurveyQuestionScreen(self)
            }

            describe("its open") {

                beforeEach {
                    loginScreen.loginIfNeeded()
                    surveyScreen.waitForAppearance()
                    surveyScreen.tapNextButton()
                    surveyDetailScreen.waitForAppearance()
                    surveyDetailScreen.tapNextButton()
                }

                afterEach {
                    surveyQuestionScreen.tapCloseButton()
                    surveyScreen.waitForAppearance()
                }

                it("shows its ui components") {
                    surveyQuestionScreen.waitForAppearance()

                    self.tester().waitForView(
                        withAccessibilityIdentifier: ViewId.surveyQuestion(.titleText)()
                    )
                    self.tester().waitForView(
                        withAccessibilityIdentifier: ViewId.surveyQuestion(.detailText)()
                    )
                    self.tester().waitForView(
                        withAccessibilityIdentifier: ViewId.surveyQuestion(.closeButton)()
                    )
                    self.tester().waitForView(
                        withAccessibilityIdentifier: ViewId.surveyQuestion(.nextButton)()
                    )
                }
            }
        }
    }
}
