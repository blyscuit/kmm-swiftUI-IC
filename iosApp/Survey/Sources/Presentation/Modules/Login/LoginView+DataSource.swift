//
//  LoginView+DataSource.swift
//  Survey
//
//  Created by Bliss on 21/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Combine
import KMPNativeCoroutinesCombine
import Shared

// sourcery: AutoMockable
protocol LoginCoordinator {

    func showResetPassword()
    func showHome()
}

extension LoginView {

    class DataSource: ObservableObject {

        let coordinator: LoginCoordinator
        let viewModel: LoginViewModel

        #if DEBUG
            @Published var email: String = SharedBuildConfig.UITestConfig().email()
            @Published var password: String = SharedBuildConfig.UITestConfig().password()
        #else
            @Published var email: String = ""
            @Published var password: String = ""
        #endif

        @Published private(set) var viewState = LoginViewState()
        @Published var showingErrorAlert = false
        @Published var showingLoading = false
        @Published var showingEmailError = false
        @Published var showingPasswordError = false

        private var cancellables = Set<AnyCancellable>()

        init(
            coordinator: LoginCoordinator,
            viewModel: LoginViewModel = KoinApplication.shared.inject()
        ) {
            self.coordinator = coordinator
            self.viewModel = viewModel
            createPublisher(for: viewModel.viewStateNative)
                .catch { error -> Just<LoginViewState> in
                    let loginViewState = LoginViewState(error: error.localizedDescription)
                    return Just(loginViewState)
                }
                .receive(on: DispatchQueue.main)
                .sink { [weak self] value in
                    self?.updateStates(value)
                }
                .store(in: &cancellables)
        }

        func login() {
            viewModel.login(email: email, password: password)
        }

        func showResetPassword() {
            coordinator.showResetPassword()
        }

        private func updateStates(_ state: LoginViewState) {
            viewState = state
            showingEmailError = state.isEmailError
            showingPasswordError = state.isPasswordError
            showingLoading = state.isLoading
            showingErrorAlert = !state.error.string.isEmpty
            if state.isSuccess {
                coordinator.showHome()
            }
        }
    }
}
