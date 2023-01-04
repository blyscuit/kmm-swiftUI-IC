//
//  AccountScreen.swift
//  Survey
//
//  Created by Bliss on 21/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Foundation

final class AccountScreen: GenericScreen {

    func navigateToHome() {
        tester.tapScreen(at: .init(x: 10.0, y: 100.0))
    }
}
