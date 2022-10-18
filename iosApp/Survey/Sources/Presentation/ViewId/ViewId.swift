//
//  ViewId.swift
//  Survey
//
//  Created by Bliss on 19/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

protocol Viewable {}

enum ViewId {

    case login(Login)
    case general(General)

    func callAsFunction() -> String {
        switch self {
        case let .login(login): return login.rawValue
        case let .general(general): return general.rawValue
        }
    }
}

extension View {

    func accessibility(_ viewId: ViewId) -> some View {
        accessibilityIdentifier(viewId())
    }
}
