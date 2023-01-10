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

    @StateObject var coordinator = RouteCoordinator()

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
                SurveySelectionContainerView(coordinator: coordinator)
            }
        }
    }
}
