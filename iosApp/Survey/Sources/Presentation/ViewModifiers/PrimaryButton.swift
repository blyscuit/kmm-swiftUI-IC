//
//  PrimaryButton.swift
//  Survey
//
//  Created by Bliss on 16/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

struct PrimaryButton: ViewModifier {

    func body(content: Content) -> some View {
        content
            .font(.boldBody)
            .padding()
            .background(Color.white)
            .foregroundColor(Color.black)
            .cornerRadius(10.0)
    }
}

extension View {

    func primaryButton() -> some View {
        modifier(PrimaryButton())
    }
}
