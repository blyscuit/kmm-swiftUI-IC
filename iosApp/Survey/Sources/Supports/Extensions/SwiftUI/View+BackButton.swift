//
//  View+BackButton.swift
//  Survey
//
//  Created by Bliss on 26/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

extension View {

    func backButton(action: @escaping () -> Void) -> some View {
        var backButton: some View {
            Button {
                action()
            } label: {
                HStack(spacing: 0) {
                    Image(systemName: .backArrow)
                        .resizable()
                        .font(.body.weight(.semibold))
                        .foregroundColor(.white)
                        .offset(x: -8.0)
                }
            }
            .accessibility(ViewId.general(.backButton))
        }

        return navigationBarBackButtonHidden(true)
            .navigationBarItems(leading: backButton)
    }

    func defaultBackButton(_ presentationMode: Binding<PresentationMode>) -> some View {
        return backButton {
            presentationMode.wrappedValue.dismiss()
        }
    }
}
