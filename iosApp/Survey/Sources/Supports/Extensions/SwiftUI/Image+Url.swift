//
//  Image+Url.swift
//  Survey
//
//  Created by Bliss on 3/1/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Kingfisher
import SwiftUI

extension Image {

    static func url(_ imagePath: String) -> KFImage {
        return KFImage(imagePath.asURL)
    }
}
