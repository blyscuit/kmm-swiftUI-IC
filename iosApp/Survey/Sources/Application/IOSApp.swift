//
//  IOSApp.swift
//  Survey
//
//  Created by Bliss on 14/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Shared
import SwiftUI

@main
struct IOSApp: App {

    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate

    var body: some Scene {
        WindowGroup {
            AppCoordinator()
                .preferredColorScheme(.dark)
        }
    }
}
