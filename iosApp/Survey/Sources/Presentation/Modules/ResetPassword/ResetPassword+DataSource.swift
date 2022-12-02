//  swiftlint:disable:this file_name
//
//  ResetPassword+DataSource.swift
//  Survey
//
//  Created by Bliss on 24/11/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Combine
import KMPNativeCoroutinesCombine
import Shared

extension ResetPasswordView {

    final class DataSource: ObservableObject {

        let viewModel: ResetPasswordViewModel
        let notificationManager: NotificationManageable

        @Published private(set) var viewState = ResetPasswordViewState()
        @Published var showingErrorAlert = false
        @Published var showingLoading = false
        @Published var email: String = ""

        private var cancellables = Set<AnyCancellable>()

        init(
            viewModel: ResetPasswordViewModel = KoinApplication.shared.inject(),
            notificationManager: NotificationManageable = NotificationManager.current
        ) {
            self.viewModel = viewModel
            self.notificationManager = notificationManager
            createPublisher(for: viewModel.viewStateNative)
                .catch { error -> Just<ResetPasswordViewState> in
                    let resetPasswordViewState = ResetPasswordViewState(
                        error: error.localizedDescription
                    )
                    return Just(resetPasswordViewState)
                }
                .receive(on: DispatchQueue.main)
                .sink { [weak self] value in
                    guard let self = self else { return }
                    self.updateStates(value)
                    guard let notification = value.successNotification else { return }
                    self.scheduleNotification(notification)
                }
                .store(in: &cancellables)
        }

        func reset() {
            viewModel.reset(email: email)
        }

        private func updateStates(_ state: ResetPasswordViewState) {
            viewState = state
            showingErrorAlert = !state.error.string.isEmpty
            showingLoading = state.isLoading
        }

        private func scheduleNotification(_ notification: NotificationUiModel) {
            notificationManager.schedule(
                title: notification.title(),
                message: notification.message(),
                time: 0.1
            )
        }
    }
}
