//
//  SurveySelectionSpec.swift
//  Survey
//
//  Created by Bliss on 28/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Nimble
import Quick

final class SurveySelectionSpec: QuickSpec {

    override func spec() {

        var app: XCUIApplication!
        var surveySelectionScreen: SurveySelectionScreen!
        var surveyLoadingScreen: SurveyLoadingScreen!

        describe("a Survey Selection screen") {

            describe("its open") {

                beforeEach {
                    app = ArgumentedXCUIApplication()
                    app.launch()
                    let loginFlow = LoginFlow(in: app)
                    loginFlow.execute()
                    surveySelectionScreen = SurveySelectionScreen(in: app)
                    surveyLoadingScreen = SurveyLoadingScreen(in: app)
                    surveyLoadingScreen.waitForFinishLoading()
                }

                afterEach {
                    app.terminate()
                }

                it("it shows its ui components") {
                    surveySelectionScreen.waitForExistence(
                        timeout: .default,
                        \.images,
                        with: .mainImage
                    )
                    surveySelectionScreen.waitForExistence(
                        timeout: .instant,
                        \.staticTexts,
                        with: .titleText
                    )
                    surveySelectionScreen.waitForExistence(
                        timeout: .instant,
                        \.staticTexts,
                        with: .detailText
                    )
                    surveySelectionScreen.waitForExistence(
                        timeout: .instant,
                        \.buttons,
                        with: .nextButton
                    )
                    surveySelectionScreen.waitForExistence(
                        timeout: .instant,
                        \.staticTexts,
                        with: .headerTitleText
                    )
                    surveySelectionScreen.waitForExistence(
                        timeout: .instant,
                        \.staticTexts,
                        with: .headerDateText
                    )
                    surveySelectionScreen.waitForExistence(
                        timeout: .instant,
                        \.buttons,
                        with: .headerProfileImage
                    )
                }
            }
        }
    }
}
