//
//  LoadingDialog.swift
//  Survey
//
//  Created by Bliss on 27/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

struct LoadingDialog: ViewModifier {

    @Binding var loading: Bool

    @Environment(\.presentationMode) var presentationMode

    init(loading: Binding<Bool>) {
        _loading = loading
    }

    func body(content: Content) -> some View {
        ZStack {
            content
            if loading {
                ZStack {
                    RoundedRectangle(cornerRadius: 14.0)
                        .foregroundColor(.black)
                        .opacity(0.7)
                        .frame(width: 100.0, height: 100.0, alignment: .center)
                    ProgressView()
                        .progressViewStyle(CircularProgressViewStyle(tint: .white))
                }
                .frame(alignment: .center)
                .offset(y: -70.0)
                .defaultBackButton(presentationMode)
            }
        }
        .disabled(loading)
    }
}

extension View {

    func loadingDialog(loading: Binding<Bool>) -> some View {
        modifier(LoadingDialog(loading: loading))
    }
}
