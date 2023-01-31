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
            BackButton {
                action()
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
