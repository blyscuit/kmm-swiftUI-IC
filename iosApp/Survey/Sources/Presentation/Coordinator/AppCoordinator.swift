//
//  AppCoordinator.swift
//  Survey
//
//  Created by Bliss on 26/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Combine
import FlowStacks
import KMPNativeCoroutinesCombine
import Shared
import SwiftUI

struct AppCoordinator: View {
    class DataSource: ObservableObject {
        @LazyKoin var viewModel: ResetPasswordViewModel
        var cancels = Set<AnyCancellable>()

        init() {
            createPublisher(for: viewModel.viewStateNative)
                .assertNoFailure()
                .sink { output in
                    print(output)
                    guard let successNotification = output.successNotification else { return }
                    NotificationManager.current.schedule(
                        title: successNotification.title,
                        message: successNotification.message,
                        time: 0.2
                    )
                }
                .store(in: &cancels)
            viewModel.reset(email: "pisit@nimblehq.co")
        }
    }

    @ObservedObject var coordinator = RouteCoordinator()
    @ObservedObject var dataSource = DataSource()

    var body: some View {
        Router($coordinator.routes) { screen, _ in
            switch screen {
            case .login:
                LoginView()
            case .splash:
                SplashView(coordinator: coordinator)
            }
        }
    }
}
