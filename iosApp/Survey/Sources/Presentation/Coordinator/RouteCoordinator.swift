//
//  RouteCoordinator.swift
//  Survey
//
//  Created by Bliss on 14/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import FlowStacks

protocol BaseCoordinator {

    func goBack()
    func goBackToRoot()
}

class RouteCoordinator: ObservableObject {

    @Published var routes: Routes<Screen> = [.root(.splash, embedInNavigationView: true)]

    func showLogin() {
        routes = [.root(.login, embedInNavigationView: true)]
    }
}

extension RouteCoordinator: BaseCoordinator {

    func goBack() {
        routes.goBack()
    }

    func goBackToRoot() {
        routes.goBackToRoot()
    }
}

extension RouteCoordinator: SplashCoordinator {

    func showLoginFromSplash() {
        showLogin()
    }
}

extension RouteCoordinator: LoginCoordinator {

    func showResetPassword() {
        routes.push(.resetPassword)
    }

    func showHome() {
        routes = [.root(.surveySelection)]
    }
}

extension RouteCoordinator: AccountCoordinator {

    func showLoginFromAccount() {
        showLogin()
    }
}

extension RouteCoordinator: SurveySelectionCoordinator {

    func showSurveyDetail(_ parameters: ScreenParameters.SurveyDetail) {
        withoutAnimation {
            self.routes.presentCover(.surveyDetail(parameters), embedInNavigationView: true)
        }
    }
}

extension RouteCoordinator: SurveyDetailCoordinator {

    func backToHome() {
        withoutAnimation {
            self.routes.dismiss()
        }
    }
}
