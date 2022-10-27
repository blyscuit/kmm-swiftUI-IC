//
//  AccountFlow.swift
//  SurveyUITests
//
//  Created by Bliss on 27/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import XCTest

final class AccountFlow {

    let app: XCUIApplication

    init(in application: XCUIApplication) {
        app = application
    }

    func execute() {
        let loginFlow = LoginFlow(in: app)
        loginFlow.execute()
        let surveySelectionScreen = SurveySelectionScreen(in: app)
        surveySelectionScreen.tapButton(.headerProfileImage)
    }
}
