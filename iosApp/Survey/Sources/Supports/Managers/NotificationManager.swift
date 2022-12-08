//
//  NotificationManager.swift
//  Survey
//
//  Created by Bliss on 20/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import UserNotifications

// sourcery: AutoMockable
protocol NotificationCenterCallable {

    func requestAuthorization(
        options: UNAuthorizationOptions,
        completionHandler: @escaping (Bool, Error?) -> Void
    )
    func add(
        _ request: UNNotificationRequest,
        withCompletionHandler completionHandler: ((Error?) -> Void)?
    )
}

// sourcery: AutoMockable
protocol NotificationManageable {

    func requestPermission(completion: @escaping () -> Void)
    func schedule(title: String, message: String, time: TimeInterval)
}

struct NotificationManager: NotificationManageable {

    static var current: NotificationManageable {
        NotificationManager(notificationCenter: UNUserNotificationCenter.current())
    }

    let notificationCenter: NotificationCenterCallable

    func requestPermission(completion: @escaping () -> Void) {
        notificationCenter.requestAuthorization(options: [.alert, .badge, .sound]) { success, error in
            if success {
                completion()
            } else if let error = error {
                print(error.localizedDescription)
            }
        }
    }

    func schedule(title: String, message: String, time: TimeInterval) {
        requestPermission {
            let content = UNMutableNotificationContent()
            content.title = title
            content.subtitle = message
            content.sound = UNNotificationSound.default

            let trigger = UNTimeIntervalNotificationTrigger(timeInterval: time, repeats: false)

            let request = UNNotificationRequest(
                identifier: UUID().uuidString,
                content: content,
                trigger: trigger
            )

            self.notificationCenter.add(request, withCompletionHandler: nil)
        }
    }
}

extension UNUserNotificationCenter: NotificationCenterCallable {}
