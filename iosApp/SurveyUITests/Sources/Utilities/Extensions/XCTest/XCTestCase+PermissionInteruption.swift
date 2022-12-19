//
//  XCTestCase+PermissionInteruption.swift
//  SurveyUITests
//
//  Created by Bliss on 16/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import XCTest

extension XCTestCase {

    func addPermissionInterruptionMonitor() -> NSObjectProtocol {
        return addUIInterruptionMonitor(withDescription: "Alert handler") { (alert: XCUIElement) -> Bool in
            let confirmLabels = ["Allow", "OK"]
            for label in confirmLabels {
                let allow = alert.buttons[label]
                if allow.exists {
                    allow.tap()
                    break
                }
            }
            return true
        }
    }
}
