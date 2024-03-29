//
//  AA_AppLoadSpec.swift
//  SurveyKIFUITests
//
//  Created by Bliss on 13/1/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Nimble
import Quick

// Named to be the first test
final class AA_AppLoadSpec: QuickSpec {

    override func spec() {

        var loginScreen: LoginScreen!
        var surveyScreen: SurveyScreen!

        describe("the app") {

            beforeEach {
                loginScreen = LoginScreen(self)
                surveyScreen = SurveyScreen(self)
            }

            describe("its launch") {

                context("when log in") {

                    beforeEach {
                        loginScreen.system().wait(forTimeInterval: .instant)
                        loginScreen.loginIfNeeded()
                    }

                    it("it loads") {
                        loginScreen.system().wait(forTimeInterval: .instant)
                        surveyScreen.waitForAppearance()
                    }
                }
            }
        }
    }
}
