//
//  AccountSpec.swift
//  Survey
//
//  Created by Bliss on 20/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Nimble
import Quick

final class AccountSpec: QuickSpec {

    override func spec() {

        typealias ViewIdentifier = ViewId.Account

        var loginScreen: LoginScreen!
        var surveyScreen: SurveyScreen!
        var accountScreen: AccountScreen!

        describe("an Account screen") {

            beforeEach {
                loginScreen = LoginScreen(self)
                surveyScreen = SurveyScreen(self)
                accountScreen = AccountScreen(self)
            }

            it("loads") {
                loginScreen.loginIfNeeded()
                self.system().wait(forTimeInterval: .instant)
            }

            describe("its open") {

                beforeEach {
                    loginScreen.loginIfNeeded()
                    surveyScreen.navigateToAccount()
                }

                afterEach {
                    accountScreen.navigateToHome()
                }

                describe("its dismiss area") {

                    context("when tapped") {

                        beforeEach {
                            self.tester().tapScreen(at: .init(x: 10.0, y: 100.0))
                        }

                        it("dismisses account screen") {
                            self.tester().waitForAbsenceOfView(
                                withAccessibilityIdentifier: ViewId.account(.profileImage)()
                            )
                        }
                    }
                }

                describe("its logout button") {

                    context("when tapped") {

                        beforeEach {
                            surveyScreen.navigateToAccount()
                            accountScreen.logOut()
                        }

                        it("shows login screen") {
                            self.tester().waitForView(withAccessibilityIdentifier: ViewId.login(.loginButton)())
                        }
                    }
                }
            }
        }
    }
}
