//
//  AccountScreen.swift
//  SurveyUITests
//
//  Created by Bliss on 27/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import XCTest

final class AccountScreen: ScreenProtocol {

    typealias Identifier = ViewId.Account

    let application: XCUIApplication

    init(in application: XCUIApplication) {
        self.application = application
    }
}
