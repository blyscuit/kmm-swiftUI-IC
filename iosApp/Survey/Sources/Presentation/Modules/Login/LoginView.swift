//
//  ContentView.swift
//  Survey
//
//  Created by Bliss on 14/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

protocol LoginCoordinator {

    func showResetPassword()
}

struct LoginView: View {

    @State private var email: String = ""
    @State private var password: String = ""
    @State private var animating = false

    private let animationDuration: Double = 0.7

    var coordinator: LoginCoordinator

    var body: some View {
        ZStack {
            GeometryReader { geometry in
                Assets.background
                    .image
                    .resizable()
                    .aspectRatio(contentMode: .fill)
                    .ignoresSafeArea()
                    .frame(maxWidth: .infinity, maxHeight: .infinity)

                Assets.backgroundBlur
                    .image
                    .resizable()
                    .aspectRatio(contentMode: .fill)
                    .ignoresSafeArea()
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
                    .opacity(animating ? 1.0 : 0.001)

                VStack(
                    alignment: .center,
                    spacing: 20.0
                ) {
                    Spacer().frame(maxHeight: geometry.size.height / 10.0)

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
        TextField(Localize.loginFieldEmail(), text: $email)
            .keyboardType(.emailAddress)
            .primaryTextField()
            .accessibility(.login(.emailField))
    }

    var passwordField: some View {
        HStack {
            SecureField(Localize.loginFieldPassword(), text: $password)
                .accessibility(.login(.passwordField))
            if password.isEmpty {
                Button(Localize.loginButtonForgot()) {
                    coordinator.showResetPassword()
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
