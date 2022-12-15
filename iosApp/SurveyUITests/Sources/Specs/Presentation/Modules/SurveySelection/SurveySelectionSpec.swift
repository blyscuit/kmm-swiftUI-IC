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
                    let mainImage = surveySelectionScreen.find(\.images, with: .mainImage)
                    expect(mainImage.exists) == true

                    let titleText = surveySelectionScreen.find(\.staticTexts, with: .titleText)
                    expect(titleText.exists) == true

                    let detailText = surveySelectionScreen.find(\.staticTexts, with: .detailText)
                    expect(detailText.exists) == true

                    let nextButton = surveySelectionScreen.find(\.buttons, with: .nextButton)
                    expect(nextButton.exists) == true

                    let headerView = surveySelectionScreen.find(\.staticTexts, with: .header)
                    expect(headerView.exists) == true
                }
            }
        }
    }
}
