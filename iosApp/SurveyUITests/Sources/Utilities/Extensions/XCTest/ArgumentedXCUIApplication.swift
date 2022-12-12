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
        add(launchArguments: .fastAnimation)
        add(launchArguments: .clearKeychain)
    }
}

extension ArgumentedXCUIApplication {

    func add(launchArguments argument: LaunchArgumentKey) {
        launchArguments.append(argument.rawValue)
    }

    func remove(launchArguments argument: LaunchArgumentKey) {
        launchArguments.removeAll(where: { $0 == argument.rawValue })
    }
}
