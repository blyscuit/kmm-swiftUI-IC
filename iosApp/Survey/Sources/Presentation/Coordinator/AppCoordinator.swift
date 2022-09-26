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

    @State var routes: Routes<Screen> = [.root(.splash)]

    var body: some View {
        Router($routes) { screen, _ in
            switch screen {
            case .splash:
                SplashView {
                    showLogin()
                }
            case .login:
                LoginView()
            }
        }
    }

    private func showLogin() {
        routes = [.root(.login)]
    }

    private func goBack() {
        routes.goBack()
    }

    private func goBackToRoot() {
        routes.goBackToRoot()
    }
}
