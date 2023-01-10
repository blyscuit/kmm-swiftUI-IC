//
//  SplashView+DataSource.swift
//  Survey
//
//  Created by Bliss on 12/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Combine
import KMPNativeCoroutinesCombine
import Shared
import SwiftUI

// sourcery: AutoMockable
protocol SplashCoordinator {

    func showLoginFromSplash()
    func showHome()
}

extension SplashView {

    final class DataSource: ObservableObject {

        let viewModel: SplashViewModel
        let coordinator: SplashCoordinator

        @Published private(set) var viewState = SplashViewState()

        private var cancellables = Set<AnyCancellable>()

        init(
            viewModel: SplashViewModel = KoinApplication.shared.inject(),
            coordinator: SplashCoordinator
        ) {
            self.viewModel = viewModel
            self.coordinator = coordinator
            createPublisher(for: viewModel.viewStateNative)
                .dropFirst()
                .catch { _ -> Just<SplashViewState> in
                    let splashViewState = SplashViewState()
                    return Just(splashViewState)
                }
                .receive(on: DispatchQueue.main)
                .sink { [weak self] value in
                    guard let self else { return }
                    self.updateStates(value)
                }
                .store(in: &cancellables)
        }

        func checkLogin() {
            viewModel.checkLogin()
        }

        private func updateStates(_ state: SplashViewState) {
            viewState = state
            guard !viewState.isLoading else { return }
            withAnimation(.linear(duration: .fast)) {
                if viewState.isLogin {
                    coordinator.showHome()
                } else {
                    coordinator.showLoginFromSplash()
                }
            }
        }
    }
}
