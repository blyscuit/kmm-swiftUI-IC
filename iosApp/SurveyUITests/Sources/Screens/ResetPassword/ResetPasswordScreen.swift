//
//  ResetPasswordScreen.swift
//  Survey
//
//  Created by Bliss on 26/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import XCTest

final class ResetPasswordScreen: ScreenProtocol {

    typealias Identifier = ViewId.ResetPassword

    let application: XCUIApplication

    init(in application: XCUIApplication) {
        self.application = application
    }
}
