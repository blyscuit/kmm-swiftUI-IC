//
//  AppCoordinator.swift
//  Survey
//
//  Created by Bliss on 26/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import FlowStacks
import SwiftUI

struct AppCoordinator: View {

    @ObservedObject var coordinator = RouteCoordinator()

    var body: some View {
        Router($coordinator.routes) { screen, _ in
            switch screen {
            case .login:
                LoginView(coordinator: coordinator)
            case .resetPassword:
                ResetPasswordView()
            case .splash:
                SplashView(coordinator: coordinator)
            case .surveySelection:
                SurveySelectionView()
            }
        }
    }

    private func showLogin() {
        routes = [.root(.login)]
    }

    private func showSurvey() {
        routes = [.root(.surveySelection)]
    }

    private func goBack() {
        routes.goBack()
    }

    private func goBackToRoot() {
        routes.goBackToRoot()
    }
}
