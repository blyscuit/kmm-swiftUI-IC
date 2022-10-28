//
//  PrimaryTextField.swift
//  Survey
//
//  Created by Bliss on 16/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

struct PrimaryTextField: ViewModifier {

    let error: Bool

    func body(content: Content) -> some View {
        ZStack {
            content
                .font(.regularBody)
                .accentColor(Color.white)
                .padding()
                .background(Color.white.opacity(0.18))
                .cornerRadius(10.0)
                .layoutPriority(2.0)
            if error {
                RoundedRectangle(cornerRadius: 10.0)
                    .stroke(Color.red, lineWidth: error ? 3.0 : 0.0)
            }
        }
    }
}

extension View {

    func primaryTextField(error: Bool = false) -> some View {
        modifier(PrimaryTextField(error: error))
    }
}
