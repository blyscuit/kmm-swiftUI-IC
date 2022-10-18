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

    @Published var routes: Routes<Screen> = [.root(.login)]
}

extension RouteCoordinator: BaseCoordinator {

    func goBack() {
        routes.goBack()
    }

    func goBackToRoot() {
        routes.goBackToRoot()
    }
}
