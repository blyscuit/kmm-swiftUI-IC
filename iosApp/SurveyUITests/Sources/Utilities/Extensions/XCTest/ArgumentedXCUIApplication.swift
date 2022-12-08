//
//  FastXCUIApplication.swift
//  Survey
//
//  Created by Bliss on 7/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import XCTest

final class ArgumentedXCUIApplication: XCUIApplication {

    override init() {
        super.init()
        isFastAnimation = true
    }
}

extension ArgumentedXCUIApplication {

    var isFastAnimation: Bool {
        get {
            LaunchArgument.contains(.fastAnimation)
        }

        set {
            if newValue {
                add(launchArguments: .fastAnimation)
            } else {
                remove(launchArguments: .fastAnimation)
            }
        }
    }

    func add(launchArguments argument: LaunchArgumentKey) {
        launchArguments.append(argument.rawValue)
    }

    func remove(launchArguments argument: LaunchArgumentKey) {
        launchArguments.removeAll(where: { $0 == argument.rawValue })
    }
}
