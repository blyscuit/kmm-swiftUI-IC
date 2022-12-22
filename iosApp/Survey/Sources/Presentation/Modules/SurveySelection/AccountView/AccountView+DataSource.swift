//
//  AccountView+DataSource.swift
//  Survey
//
//  Created by Bliss on 22/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Combine
import KMPNativeCoroutinesCombine
import Shared

extension AccountView {

    final class DataSource: ObservableObject {

        private let viewModel: AccountViewModel
        private let coordinator: AccountCoordinator

        @Published private(set) var viewState = AccountViewState()
        @Published var showingLoading = false

        private var cancellables = Set<AnyCancellable>()

        init(
            account: AccountUiModel,
            coordinator: AccountCoordinator
        ) {
            viewModel = AccountViewModel(accountUiModel: account)
            self.coordinator = coordinator
            createPublisher(for: viewModel.viewStateNative)
                .catch { _ -> Just<AccountViewState> in
                    Just(AccountViewState())
                }
                .receive(on: DispatchQueue.main)
                .sink { [weak self] value in
                    guard let self = self else { return }
                    self.updateStates(value)
                }
                .store(in: &cancellables)
        }

        func logOut() {
            viewModel.logOut()
        }

        private func updateStates(_ state: AccountViewState) {
            viewState = state
            showingLoading = state.isLoading
            if state.isLogout {
                coordinator.showLogin()
            }
        }
    }
}
