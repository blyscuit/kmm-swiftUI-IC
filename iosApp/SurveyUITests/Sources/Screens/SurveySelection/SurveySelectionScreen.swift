//
//  SurveySelectionScreen.swift
//  Survey
//
//  Created by Bliss on 28/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import XCTest

final class SurveySelectionScreen: ScreenProtocol {

    typealias Identifier = ViewId.SurveySelection

    let application: XCUIApplication

    init(in application: XCUIApplication) {
        self.application = application
    }
}
