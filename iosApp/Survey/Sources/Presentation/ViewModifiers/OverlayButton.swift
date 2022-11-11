//
//  OverlayButton.swift
//  Survey
//
//  Created by Bliss on 16/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

struct OverlayButton: ViewModifier {

    func body(content: Content) -> some View {
        content
            .font(.regularSmall)
            .foregroundColor(Color.white)
            .opacity(0.5)
    }
}

extension View {

    func overlayButton() -> some View {
        modifier(OverlayButton())
    }
}
