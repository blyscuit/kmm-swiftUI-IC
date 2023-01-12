//
//  KIF+Swift.swift
//  Survey
//
//  Created by Bliss on 20/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import KIF

extension XCTestCase {

    func tester(file: String = #file, _ line: Int = #line) -> KIFUITestActor {
        return KIFUITestActor(inFile: file, atLine: line, delegate: self)
    }

    func system(file: String = #file, _ line: Int = #line) -> KIFSystemTestActor {
        return KIFSystemTestActor(inFile: file, atLine: line, delegate: self)
    }
}
