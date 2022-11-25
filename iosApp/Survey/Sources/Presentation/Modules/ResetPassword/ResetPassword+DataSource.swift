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

    class DataSource: ObservableObject {

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
                    self?.viewState = value
                    self?.showingErrorAlert = !value.error.string.isEmpty
                    self?.showingLoading = value.isLoading
                    if let notification = value.successNotification {
                        self?.notificationManager.schedule(
                            title: notification.title(),
                            message: notification.message(),
                            time: 0.1
                        )
                    }
                }
                .store(in: &cancellables)
        }

        func reset() {
            viewModel.reset(email: email)
        }
    }
}
