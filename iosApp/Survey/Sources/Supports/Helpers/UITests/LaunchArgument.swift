//
//  LaunchArgument.swift
//  Survey
//
//  Created by Bliss on 7/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Foundation

enum LaunchArgumentKey: String {

    case fastAnimation
    case clearKeychain
}

enum LaunchEnvironment {

    static func stringFor(_ key: LaunchArgumentKey) -> String? {
        return ProcessInfo.processInfo.environment[key.rawValue]
    }
}

enum LaunchArgument {

    static func contains(_ key: LaunchArgumentKey) -> Bool {
        return ProcessInfo.processInfo.arguments.contains(key.rawValue)
    }
}
