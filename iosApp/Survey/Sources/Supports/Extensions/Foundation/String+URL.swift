//
//  String+URL.swift
//  Survey
//
//  Created by Bliss on 1/11/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Foundation

extension String {

    var asURL: URL {
        URL(string: self) ?? URL(fileURLWithPath: "")
    }
}
