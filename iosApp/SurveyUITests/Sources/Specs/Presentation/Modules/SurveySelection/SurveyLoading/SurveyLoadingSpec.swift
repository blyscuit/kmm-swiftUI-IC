//
//  SurveyLoadingSpec.swift
//  Survey
//
//  Created by Bliss on 4/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Nimble
import Quick

final class SurveyLoadingSpec: QuickSpec {

    override func spec() {

        var app: XCUIApplication!
        var surveyLoadingScreen: SurveyLoadingScreen!

        describe("a Survey Loading screen") {

            describe("its open") {

                beforeEach {
                    app = ArgumentedXCUIApplication()
                    app.launch()
                    let loginFlow = LoginFlow(in: app)
                    loginFlow.execute()
                    surveyLoadingScreen = SurveyLoadingScreen(in: app)
                }

                afterEach {
                    app.terminate()
                }

                it("it shows its ui components") {
                    let view = surveyLoadingScreen.find(\.otherElements, with: .view)
                    expect(view.exists) == true
                }
            }
        }
    }
}
