//
//  XCUIElement+Label.swift
//  Survey
//
//  Created by Bliss on 28/11/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import XCTest

extension XCUIElement {

    func labelContains(_ text: String) -> Bool {
        let predicate = NSPredicate(format: "label CONTAINS %@", text)
        return staticTexts.matching(predicate).firstMatch.exists
    }
}
