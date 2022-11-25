//
//  ResetPasswordView.swift
//  Survey
//
//  Created by Bliss on 26/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

struct ResetPasswordView: View {

    @StateObject var dataSource = DataSource()

    var body: some View {
        ZStack {
            GeometryReader { geometry in
                Assets.backgroundBlur
                    .image
                    .resizable()
                    .aspectRatio(contentMode: .fill)
                    .ignoresSafeArea()
                    .frame(width: geometry.size.width, height: geometry.size.height)
                    .accessibility(.resetPassword(.view))

                VStack(
                    alignment: .center,
                    spacing: .itemSpacing
                ) {
                    Spacer().frame(maxHeight: geometry.size.height / 10.0)

                    Assets.logoWhite.image

                    Text(String.localizeId.reset_password_text_instruction())
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
        .loadingDialog(loading: $dataSource.showingLoading)
        .accessibilityElement(children: .contain)
        .alert(isPresented: $dataSource.showingErrorAlert, content: {
            Alert(title: Text(dataSource.viewState.error))
        })
    }

    var emailField: some View {
        TextField(String.localizeId.reset_password_field_email(), text: $dataSource.email)
            .disableAutocorrection(true)
            .autocapitalization(.none)
            .keyboardType(.emailAddress)
            .primaryTextField()
            .accessibility(.resetPassword(.emailField))
    }

    var resetButton: some View {
        Button {
            dataSource.reset()
        } label: {
            Text(String.localizeId.reset_password_button_reset())
                .frame(maxWidth: .infinity)
                .primaryButton()
                .accessibility(.resetPassword(.resetButton))
        }
    }
}
