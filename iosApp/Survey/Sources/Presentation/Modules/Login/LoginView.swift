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
    func showHomeLoading()
}

struct LoginView: View {

    @State private var email: String = ""
    @State private var password: String = ""
    @State private var animating = false

    private let animationDuration: Double = 0.7

    let coordinator: LoginCoordinator

    var body: some View {
        ZStack {
            GeometryReader { geometry in
                Assets.background
                    .image
                    .resizable()
                    .aspectRatio(contentMode: .fill)
                    .ignoresSafeArea()
                    .frame(width: geometry.size.width, height: geometry.size.height)

                Assets.backgroundBlur
                    .image
                    .resizable()
                    .aspectRatio(contentMode: .fill)
                    .ignoresSafeArea()
                    .frame(width: geometry.size.width, height: geometry.size.height)
                    .opacity(animating ? 1.0 : 0.0)

                VStack(
                    alignment: .center,
                    spacing: .itemSpacing
                ) {
                    Spacer().frame(maxHeight: geometry.size.height / 10.0)

                    Assets.logoWhite.image
                        .frame(maxWidth: .infinity)
                        .offset(y: animating ? 0.0 : 200.0)

                    Spacer().frame(maxHeight: 70.0)

                    if animating {
                        loginField
                        createPasswordField()
                        loginButton
                    }
                }
                .padding(.horizontal, .defaultPadding)
            }
        }
        .onTapGesture {
            hideKeyboard()
        }
        .accessibilityElement(children: .contain)
        .accessibility(.login(.view))
        .hideBackButtonTitle()
        .onAppear {
            withAnimation(.easeIn(duration: animationDuration)) {
                animating = true
            }
        }
    }

    var loginField: some View {
        TextField(String.localizeId.login_fields_email.localized, text: $email)
            .keyboardType(.emailAddress)
            .primaryTextField()
            .accessibility(.login(.emailField))
    }

    var passwordField: some View {
        HStack {
            SecureField(String.localizeId.login_fields_password.localized, text: $password)
                .accessibility(.login(.passwordField))
            if password.isEmpty {
                Button(String.localizeId.login_button_forgot.localized) {
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
            withAnimation {
                coordinator.showHomeLoading()
            }
        } label: {
            Text(String.localizeId.login_button_login.localized)
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
