//
//  ResetPasswordView.swift
//  Survey
//
//  Created by Bliss on 26/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

struct ResetPasswordView: View {

    @State private var email: String = ""
    @State private var loading = false

    var body: some View {
        EmbedLoadingView(loading: loading) {
            ZStack {
                GeometryReader { geometry in
                    Assets.backgroundBlur
                        .image
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                        .ignoresSafeArea()
                        .frame(maxWidth: .infinity, maxHeight: .infinity)

                    VStack(
                        alignment: .center,
                        spacing: .itemSpacing
                    ) {
                        Spacer().frame(maxHeight: geometry.size.height / 10.0)

                        Assets.logoWhite.image

                        Text(Localize.resetPasswordTextInstruction())
                            .multilineTextAlignment(.center)

                        Spacer().frame(maxHeight: 70.0)

                        emailField
                        resetButton
                    }
                    .padding(.horizontal, .defaultPadding)
                }
            }
            .onTapGesture {
                hideKeyboard()
            }
            .accessibilityElement(children: .contain)
            .accessibility(.resetPassword(.view))
        }
    }

    var emailField: some View {
        TextField(Localize.resetPasswordFieldEmail(), text: $email)
            .keyboardType(.emailAddress)
            .primaryTextField()
            .accessibility(.resetPassword(.emailField))
    }

    var resetButton: some View {
        Button {} label: {
            Text(Localize.resetPasswordButtonReset())
                .frame(maxWidth: .infinity)
                .primaryButton()
                .accessibility(.resetPassword(.resetButton))
        }
    }
}
