//
//  View+ConditionalModifier.swift
//  Survey
//
//  Created by Bliss on 6/1/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

extension View {

    @ViewBuilder func `if`<Content: View>(
        _ condition: Bool,
        transform: (Self) -> Content
    ) -> some View {
        if condition {
            transform(self)
        } else {
            self
        }
    }
}
