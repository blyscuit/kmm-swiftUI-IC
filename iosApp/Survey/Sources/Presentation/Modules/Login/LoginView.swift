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
    @State private var animating = false

    private let animationDuration: Double = 0.8

    var body: some View {
        ZStack {
            Assets.background
                .image
                .resizable()
                .aspectRatio(contentMode: .fill)
                .ignoresSafeArea()
                .frame(maxWidth: .infinity, maxHeight: .infinity)

            if animating {
                Assets.backgroundBlur
                    .image
                    .resizable()
                    .aspectRatio(contentMode: .fill)
                    .ignoresSafeArea()
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
            }

            VStack(
                alignment: .center,
                spacing: 20.0
            ) {
                Assets.logoWhite.image

                Spacer().frame(maxHeight: 70.0)

                if animating {
                    loginField
                    createPasswordField()
                    loginButton
                }
            }
            .padding(.horizontal, 24.0)
        }
        .onTapGesture {
            hideKeyboard()
        }
        .accessibilityElement(children: .contain)
        .accessibility(.login(.view))
        .onAppear {
            withAnimation(.easeIn(duration: animationDuration)) {
                animating = true
            }
        }
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
                Button(Localize.loginButtonForgot()) {
                    // TODO: Add action then press `forgot`
                }
                .overlayButton()
                .accessibility(.login(.forgotButton))
            }
        }
        .primaryTextField()
        .frame(maxHeight: 56.0)
    }

    var loginButton: some View {
        Button {
            // TODO: Add action when press `login`
        } label: {
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
                    .onSubmit {
                        // TODO: Add action when press `return`
                    }
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
