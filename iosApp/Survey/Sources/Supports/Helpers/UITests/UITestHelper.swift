//
//  UITestHelper.swift
//  Survey
//
//  Created by Bliss on 7/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Shared
import UIKit

final class UITestHelper {

    static let shared = UITestHelper()

    var speed: Float = 1.0

    var isFastAnimationUITests: Bool {
        LaunchArgument.contains(.fastAnimation)
    }

    var clearingKeychainUITests: Bool {
        LaunchArgument.contains(.clearKeychain)
    }

    func speedUpUITestAnimation() {
        #if DEBUG
            if isFastAnimationUITests {
                // swiftformat:disable:next numberFormatting
                speed = 1_000.0
                UIApplication
                    .shared
                    .connectedScenes
                    .compactMap { $0 as? UIWindowScene }
                    .flatMap { $0.windows }
                    .first { $0.isKeyWindow }?
                    .layer
                    .speed = speed
            }
        #endif
    }

    func clearKeychainUITest() {
        #if DEBUG
            if clearingKeychainUITests {
                DispatchQueue.main.async {
                    UITestLogout.shared.logOut()
                }
            }
        #endif
    }
}

extension Double {

    var speedModified: Double {
        #if DEBUG
            self / Double(UITestHelper.shared.speed)
        #else
            self
        #endif
    }
}
