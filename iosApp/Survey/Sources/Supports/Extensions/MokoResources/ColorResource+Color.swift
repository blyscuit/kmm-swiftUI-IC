//
//  ColorResource+Color.swift
//  Survey
//
//  Created by Bliss on 15/2/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Shared
import SwiftUI

extension SwiftUI.Color {

    static let moko = MR.colors()
}

extension ColorResource.Single {

    func callAsFunction() -> SwiftUI.Color {
        SwiftUI.Color(color.toUIColor())
    }
}
