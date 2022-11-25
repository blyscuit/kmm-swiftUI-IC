//
//  TimeInterval+Constants.swift
//  Survey
//
//  Created by Bliss on 28/11/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Foundation

extension TimeInterval {

    static let `default` = 10.0
}

extension DispatchTimeInterval {

    static let `default`: Self = .seconds(10)
    static let long: Self = .seconds(20)
}
