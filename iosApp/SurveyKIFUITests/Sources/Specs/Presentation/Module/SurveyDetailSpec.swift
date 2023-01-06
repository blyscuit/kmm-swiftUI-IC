//
//  SurveyDetailSpec.swift
//  Survey
//
//  Created by Bliss on 6/1/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Nimble
import Quick

final class SurveyDetailSpec: QuickSpec {

    override func spec() {

        var loginScreen: LoginScreen!
        var surveyScreen: SurveyScreen!
        var surveyDetailScreen: SurveyDetailScreen!

        describe("an Survey Detail screen") {

            beforeEach {
                loginScreen = LoginScreen(self)
                surveyScreen = SurveyScreen(self)
                surveyDetailScreen = SurveyDetailScreen(self)
            }

            describe("its open") {

                beforeEach {
                    loginScreen.loginIfNeeded()
                    surveyScreen.waitForAppearance()
                    surveyScreen.tapNextButton()
                }

                afterEach {
                    surveyDetailScreen.tapBackButton()
                    surveyScreen.waitForAppearance()
                }

                it("shows its ui components") {
                    surveyDetailScreen.waitForAppearance()

                    self.tester().waitForView(
                        withAccessibilityIdentifier: ViewId.surveyDetail(.titleText)()
                    )
                    self.tester().waitForView(
                        withAccessibilityIdentifier: ViewId.surveyDetail(.detailText)()
                    )
                    self.tester().waitForView(
                        withAccessibilityIdentifier: ViewId.surveyDetail(.mainImage)()
                    )
                    self.tester().waitForView(
                        withAccessibilityIdentifier: ViewId.surveyDetail(.startButton)()
                    )
                }
            }
        }
    }
}
