//
//  EmbedLoadingView.swift
//  Survey
//
//  Created by Bliss on 27/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

struct EmbedLoadingView<Content: View>: View {

    @State var loading: Bool

    var content: () -> Content

    @Environment(\.presentationMode) var presentationMode

    var body: some View {
        if loading {
            ZStack {
                content()

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
            }
            .defaultBackButton(presentationMode)
            .disabled(loading)
        } else {
            content()
        }
    }
}
