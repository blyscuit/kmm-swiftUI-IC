//
//  ScreenProtocol+Permission.swift
//  Survey
//
//  Created by Bliss on 28/11/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import XCTest

extension ScreenProtocol {

    func grantNotificationPermissionIfNeeded() {
        let permissionMessage = "Send You Notifications"
        wait(assertOnFailure: false) {
            self.springboard.labelContains(permissionMessage) || self.notification.exists
        }
        if springboard.buttons["Allow"].exists {
            springboard.buttons["Allow"].tap()
        }
    }
}
