//
//  ContentView.swift
//  Survey
//
//  Created by Bliss on 14/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

struct LoginView: View {

    @StateObject private var dataSource: DataSource

    @State private var animating = false

    private let animationDuration: Double = 0.7

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
                    .accessibility(.login(.view))

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
        .hideBackButtonTitle()
        .onAppear {
            withAnimation(.easeIn(duration: animationDuration)) {
                animating = true
            }
        }
        .alert(isPresented: $dataSource.showingErrorAlert) {
            Alert(title: Text(dataSource.viewState.error.string))
        }
        .loadingDialog(loading: $dataSource.showingLoading)
    }

    var loginField: some View {
        TextField(
            String.localizeId.login_fields_email(),
            text: $dataSource.email
        )
        .autocapitalization(.none)
        .disableAutocorrection(true)
        .keyboardType(.emailAddress)
        .primaryTextField(error: $dataSource.showingEmailError)
        .accessibility(.login(.emailField))
    }

    var passwordField: some View {
        HStack {
            SecureField(
                String.localizeId.login_fields_password(),
                text: $dataSource.password
            )
            .accessibility(.login(.passwordField))
            if dataSource.password.isEmpty {
                Button(String.localizeId.login_button_forgot()) {
                    dataSource.showResetPassword()
                }
                .overlayButton()
                .accessibility(.login(.forgotButton))
            }
        }
        .primaryTextField(error: $dataSource.showingPasswordError)
        .frame(maxHeight: 56.0)
    }

    var loginButton: some View {
        Button {
            dataSource.login()
        } label: {
            Text(String.localizeId.login_button_login())
                .frame(maxWidth: .infinity)
                .primaryButton()
                .accessibility(.login(.loginButton))
        }
        .disabled(dataSource.showingLoading)
    }

    init(coordinator: LoginCoordinator) {
        _dataSource = StateObject(wrappedValue: DataSource(coordinator: coordinator))
    }

    func createPasswordField() -> AnyView {
        if #available(iOS 15.0, *) {
            return AnyView(
                passwordField
                    .onSubmit {
                        dataSource.login()
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
