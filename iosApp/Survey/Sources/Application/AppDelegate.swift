//
//  AppDelegate.swift
//  Survey
//
//  Created by Bliss on 20/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import UIKit
import UserNotifications

class AppDelegate: NSObject, UIApplicationDelegate {

    func application(
        _ application: UIApplication,
        willFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        UNUserNotificationCenter.current().delegate = self
        UITestHelper.shared.speedUpUITestAnimation()
        return true
    }
}

extension AppDelegate: UNUserNotificationCenterDelegate {

    func userNotificationCenter(
        _ center: UNUserNotificationCenter,
        willPresent notification: UNNotification,
        withCompletionHandler completionHandler: @escaping (UNNotificationPresentationOptions) -> Void
    ) {
        completionHandler([.banner, .sound])
    }
}
