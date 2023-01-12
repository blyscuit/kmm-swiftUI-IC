//
//  SurveySelectionSpec.swift
//  SurveyKIFUITests
//
//  Created by Bliss on 28/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Nimble
import Quick

final class SurveySelectionSpec: QuickSpec {

    override func spec() {

        var loginScreen: LoginScreen!
        var surveyScreen: SurveyScreen!

        describe("an Account screen") {

            beforeEach {
                loginScreen = LoginScreen(self)
                surveyScreen = SurveyScreen(self)
            }

            describe("its open") {

                beforeEach {
                    loginScreen.loginIfNeeded()
                    surveyScreen.waitForAppearance()
                }

                describe("its image area") {

                    context("when swipe left multitple times") {

                        beforeEach {
                            for _ in 0 ..< 5 {
                                surveyScreen.swipeRight()
                            }
                        }

                        it("show new surveys") {
                            self.tester().waitForView(
                                withAccessibilityIdentifier: ViewId.surveySelection(.mainImage)()
                            )
                        }
                    }
                }
            }
        }
    }
}
