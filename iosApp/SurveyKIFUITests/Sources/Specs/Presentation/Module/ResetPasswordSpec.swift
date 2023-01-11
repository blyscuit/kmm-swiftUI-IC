////
////  ResetPasswordSpec.swift
////  SurveyKIFUITests
////
////  Created by Bliss on 11/1/23.
////  Copyright © 2023 Nimble. All rights reserved.
////
//
//import Nimble
//import Quick
//
//final class ResetPasswordSpec: QuickSpec {
//
//    override func spec() {
//
//        var loginScreen: LoginScreen!
//        var surveyScreen: SurveyScreen!
//
//        describe("a Reset Password screen") {
//
//            beforeEach {
//                loginScreen = LoginScreen(self)
//                surveyScreen = SurveyScreen(self)
//            }
//
//            describe("its open") {
//
//                beforeEach {
//                    surveyScreen.logoutIfNeeded()
//                    loginScreen.navigateToResetPassword()
//                }
//
//                afterEach {
//                    loginScreen.pressBack()
//                    loginScreen.waitForAppearance()
//                }
//
//                describe("its reset button") {
//
//                    context("when email is not valid") {
//
//                        beforeEach {
//                            self.tester().clearText(
//                                fromAndThenEnterText: "notemail",
//                                intoViewWithAccessibilityIdentifier: ViewId.resetPassword(.emailField)()
//                            )
//                            self.tester().tapView(
//                                withAccessibilityIdentifier: ViewId.resetPassword(.resetButton)()
//                            )
//                        }
//
//                        it("shows error") {
//                            self.tester().waitForView(withAccessibilityLabel: "Email is invalid")
//                            self.tester().tapView(withAccessibilityLabel: "OK")
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
