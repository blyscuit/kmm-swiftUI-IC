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

        describe("an Account screen") {

            describe("its open") {

                beforeEach {
                    self.tester().loginIfNeeded()
                    self.tester().navigateToHome()
                }

                afterEach {
                    self.tester().navigateToHome()
                }

                it("it shows its ui components") {
                    self.tester().waitForView(
                        withAccessibilityIdentifier: ViewId.account(.profileText)()
                    )
                    self.tester().waitForView(
                        withAccessibilityIdentifier: ViewId.account(.profileImage)()
                    )
                    self.tester().waitForView(
                        withAccessibilityIdentifier: ViewId.account(.logoutButton)()
                    )
                    self.tester().waitForView(
                        withAccessibilityIdentifier: ViewId.account(.versionText)()
                    )
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
            }
        }
    }
}
