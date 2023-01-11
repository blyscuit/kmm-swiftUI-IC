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

            describe("its open") {

                beforeEach {
                    loginScreen.loginIfNeeded()
                    surveyScreen.navigateToAccount()
                }

                afterEach {
                    accountScreen.navigateToHome()
                }
                
                it("it loads") { expect(true) }

                it("it shows its ui components") {
                    accountScreen.tester().waitForView(
                        withAccessibilityIdentifier: ViewId.account(.profileText)()
                    )
                    accountScreen.tester().waitForView(
                        withAccessibilityIdentifier: ViewId.account(.profileImage)()
                    )
                    accountScreen.tester().waitForView(
                        withAccessibilityIdentifier: ViewId.account(.logoutButton)()
                    )
                    accountScreen.tester().waitForView(
                        withAccessibilityIdentifier: ViewId.account(.versionText)()
                    )
                }

                describe("its dismiss area") {

                    context("when tapped") {

                        beforeEach {
                            accountScreen.tester().tapScreen(at: .init(x: 10.0, y: 100.0))
                        }

                        it("dismisses account screen") {
                            accountScreen.tester().waitForAbsenceOfView(
                                withAccessibilityIdentifier: ViewId.account(.profileImage)()
                            )
                        }
                    }
                }

                describe("its logout button") {

                    context("when tapped") {

                        beforeEach {
                            accountScreen.logOut()
                        }

                        it("shows login screen") {
                            accountScreen.tester().waitForView(
                                withAccessibilityIdentifier: ViewId.login(.loginButton)()
                            )
                        }
                    }
                }
            }
        }
    }
}
