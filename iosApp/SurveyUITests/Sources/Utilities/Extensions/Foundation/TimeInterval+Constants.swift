//
//  TimeInterval+Constants.swift
//  Survey
//
//  Created by Bliss on 28/11/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Foundation

extension TimeInterval {

    /// 10.0
    static let `default` = 10.0
    /// 1.0
    static let instant = 1.0
    /// 20.0
    static let long = 20.0
}

extension DispatchTimeInterval {

    /// 10 seconds
    static let `default`: Self = .seconds(10)
    /// 15 seconds
    static let long: Self = .seconds(15)
}
