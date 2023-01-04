//
//  XCUIApplication+.swift
//  SurveyUITests
//
//  Created by Bliss on 27/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import XCTest

extension XCUIApplication {

    func tapCoordinate(at point: CGPoint) {
        let normalized = coordinate(withNormalizedOffset: .zero)
        let offset = CGVector(dx: point.x, dy: point.y)
        let coordinate = normalized.withOffset(offset)
        coordinate.tap()
    }
}
