//
//  Image+Constants.swift
//  Survey
//
//  Created by Bliss on 1/11/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

extension Image {

    init(systemName: Constants.SystemImage) {
        self.init(systemName: systemName.rawValue)
    }
}
