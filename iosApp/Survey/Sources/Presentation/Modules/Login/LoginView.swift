//
//  ContentView.swift
//  Survey
//
//  Created by Bliss on 14/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

struct LoginView: View {

    @State private var email: String = ""
    @State private var password: String = ""

    var body: some View {
        ZStack {
            ZStack {}
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .background(Assets.backgroundBlur.image.resizable())
                .ignoresSafeArea()
            VStack(
                alignment: .center,
                spacing: 20.0
            ) {
                Assets.logoWhite.image

                Spacer().frame(maxHeight: 70.0)

                loginField
                createPasswordField()
                loginButton
            }
            .padding(.horizontal, 24.0)
        }
        .onTapGesture {
            hideKeyboard()
        }
        .accessibilityElement(children: .contain)
        .accessibility(.login(.view))
    }

    var loginField: some View {
        TextField(Localize.loginFieldsEmail(), text: $email)
            .keyboardType(.emailAddress)
            .primaryTextField()
            .accessibility(.login(.emailField))
    }

    var passwordField: some View {
        HStack {
            SecureField(Localize.loginFieldsPassword(), text: $password)
                .accessibility(.login(.passwordField))
            if password.isEmpty {
                Button(Localize.loginButtonForgot()) {}
                    .overlayButton()
                    .accessibility(.login(.forgotButton))
            }
        }
        .primaryTextField()
        .frame(maxHeight: 56.0)
    }

    var loginButton: some View {
        Button {} label: {
            Text(Localize.loginButtonLogin())
                .frame(maxWidth: .infinity)
                .primaryButton()
                .accessibility(.login(.loginButton))
        }
    }

    func createPasswordField() -> AnyView {
        if #available(iOS 15.0, *) {
            return AnyView(
                passwordField
                    .onSubmit {}
            )
        } else {
            return AnyView(
                passwordField
                    .primaryTextField()
            )
        }
    }
}

struct LoginView_Previews: PreviewProvider {

    static var previews: some View {
        LoginView()
    }
}
