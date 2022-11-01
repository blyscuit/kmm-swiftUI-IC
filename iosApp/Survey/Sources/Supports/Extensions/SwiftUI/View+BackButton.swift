//
//  View+BackButton.swift
//  Survey
//
//  Created by Bliss on 26/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

extension View {

    func defaultBackButton(_ presentationMode: Binding<PresentationMode>) -> some View {
        var backButton: some View {
            Button {
                presentationMode.wrappedValue.dismiss()
            } label: {
                HStack(spacing: 0) {
                    Image(systemName: .backArrow)
                        .resizable()
                        .font(.body.weight(.semibold))
                        .foregroundColor(.white)
                        .offset(x: -8.0)
                }
            }
        }

        return navigationBarBackButtonHidden(true)
            .navigationBarItems(leading: backButton)
    }
}
