//
//  PrimaryTextField.swift
//  Survey
//
//  Created by Bliss on 16/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

struct PrimaryTextField: ViewModifier {

    func body(content: Content) -> some View {
        content
            .font(.regularBody)
            .accentColor(Color.white)
            .padding()
            .background(Color.white.opacity(0.18))
            .cornerRadius(10.0)
    }
}

extension View {

    func primaryTextField() -> some View {
        modifier(PrimaryTextField())
    }
}
