//
//  StringResource+Localized.swift
//  Survey
//
//  Created by Bliss on 18/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Shared

extension String {

    static let localizeId = MR.strings()
}

extension StringResource {

    var localized: String {
        desc().localized()
    }
}
