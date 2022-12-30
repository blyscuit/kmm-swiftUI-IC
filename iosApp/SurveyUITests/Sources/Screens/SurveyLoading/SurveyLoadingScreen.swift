//
//  SurveyLoadingScreen.swift
//  Survey
//
//  Created by Bliss on 4/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import XCTest

final class SurveyLoadingScreen: ScreenProtocol {

    typealias Identifier = ViewId.SurveyLoading

    let application: XCUIApplication

    init(in application: XCUIApplication) {
        self.application = application
    }

    func waitForFinishLoading(assertOnFailure: Bool = true) {
        wait(timeout: .default, assertOnFailure: assertOnFailure) {
            !self.application.otherElements[Identifier.view.rawValue].exists
        }
    }
}
