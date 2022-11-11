//
//  Font+Extensions.swift
//  Survey
//
//  Created by Bliss on 16/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

extension Font {

    private enum Neuzei {

        case regular
        case bold

        var name: String {
            switch self {
            case .regular:
                return Fonts.neuzeitSLTStdBook.fontName
            case .bold: return Fonts.neuzeitSLTStdBookHeavy.fontName
            }
        }
    }

    /// Weight: 800, Size: 15.0
    static let regularSmall: Font = .neuzei(size: 13.0)

    /// Weight: 400, Size: 17.0
    static let regularBody: Font = .neuzei()

    /// Weight: 800, Size: 17.0
    static let boldBody: Font = .neuzei(.bold)

    private static func neuzei(_ weigth: Neuzei = .regular, size: CGFloat = 17.0) -> Font {
        return Font.custom(weigth.name, size: size)
    }
}
