//
//  AccountSpec.swift
//  SurveyUITests
//
//  Created by Bliss on 27/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Nimble
import Quick

final class AccountSpec: QuickSpec {

    override func spec() {

        var app: XCUIApplication!
        var accountScreen: AccountScreen!
        var surveySelectionScreen: SurveySelectionScreen!

        describe("an Account screen") {

            describe("its open") {

                beforeEach {
                    app = XCUIApplication()
                    app.launch()
                    let accountFlow = AccountFlow(in: app)
                    accountFlow.execute()
                    accountScreen = AccountScreen(in: app)
                    surveySelectionScreen = SurveySelectionScreen(in: app)
                }

                afterEach {
                    app.terminate()
                }

                it("it shows its ui components") {
                    let profileText = accountScreen.find(\.staticTexts, with: .profileText)
                    expect(profileText.exists) == true

                    let profileImage = accountScreen.find(\.images, with: .profileImage)
                    expect(profileImage.exists) == true

                    let logoutButton = accountScreen.find(\.buttons, with: .logoutButton)
                    expect(logoutButton.exists) == true

                    let versionText = accountScreen.find(\.staticTexts, with: .versionText)
                    expect(versionText.exists) == true

                    let surveyImage = surveySelectionScreen.find(\.images, with: .mainImage)
                    expect(surveyImage.exists) == true
                }

                describe("its dismiss area") {

                    context("when tapped") {

                        beforeEach {
                            let point = CGPoint(x: 10.0, y: 80.0)
                            app.tapCoordinate(at: point)
                        }

                        it("dismisses account screen") {
                            let profileText = accountScreen.find(\.staticTexts, with: .profileText)
                            expect(profileText.exists)
                                .toEventually(beFalse(), timeout: .seconds(1))

                            let surveyImage = surveySelectionScreen.find(\.images, with: .mainImage)
                            expect(surveyImage.exists) == true
                        }
                    }
                }
            }
        }
    }
}
