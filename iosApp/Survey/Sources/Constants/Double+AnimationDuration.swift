//
//  Double+AnimationDuration.swift
//  Survey
//
//  Created by Bliss on 7/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Foundation

extension Double {

    /// 0.7
    static var appearing: Self { 0.7.speedModified }
    /// 0.01
    static var instant: Self { 0.01.speedModified }
    /// 0.2
    static var fast: Self { 0.2.speedModified }
    /// 0.3
    static var `default`: Self { 0.3.speedModified }
    /// 0.5
    static var viewTransition: Self { 0.5.speedModified }
}
