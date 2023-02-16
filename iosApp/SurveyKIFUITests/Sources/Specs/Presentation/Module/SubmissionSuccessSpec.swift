//
//  SubmissionSuccessSpec.swift
//  SurveyKIFUITests
//
//  Created by Bliss on 16/2/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Nimble
import Quick

final class SubmissionSuccessSpec: QuickSpec {

    override func spec() {

        var loginScreen: LoginScreen!
        var surveyScreen: SurveyScreen!
        var surveyDetailScreen: SurveyDetailScreen!
        var surveyQuestionScreen: SurveyQuestionScreen!
        var submissionSuccessScreen: SubmissionSuccessScreen!

        describe("a Submission Success screen") {

            beforeEach {
                loginScreen = LoginScreen(self)
                surveyScreen = SurveyScreen(self)
                surveyDetailScreen = SurveyDetailScreen(self)
                surveyQuestionScreen = SurveyQuestionScreen(self)
                submissionSuccessScreen = SubmissionSuccessScreen(self)
            }

            describe("its open") {

                beforeEach {
                    loginScreen.loginIfNeeded()
                    surveyScreen.waitForAppearance()
                    surveyScreen.tapNextButton()
                    surveyDetailScreen.waitForAppearance()
                    surveyDetailScreen.tapNextButton()
                    surveyQuestionScreen.waitForAppearance()
                    surveyQuestionScreen.tapNextAndSubmit()
                }

                afterEach {
                    surveyScreen.waitForAppearance()
                }

                it("shows then hides its ui components") {
                    submissionSuccessScreen.waitForAppearance()
                    submissionSuccessScreen.waitForDisappearance()
                }
            }
        }
    }
}
