//
//  ViewId.swift
//  Survey
//
//  Created by Bliss on 19/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

protocol Viewable {}

enum ViewId {

    case login(Login)
    case splash(Splash)
    case resetPassword(ResetPassword)
    case surveySelection(SurveySelection)
    case surveyLoading(SurveyLoading)
    case account(Account)
    case general(General)

    func callAsFunction() -> String {
        switch self {
        case let .login(login): return login.rawValue
        case let .splash(splash): return splash.rawValue
        case let .resetPassword(resetPassword): return resetPassword.rawValue
        case let .surveySelection(surveySelection): return surveySelection.rawValue
        case let .surveyLoading(surveyLoading): return surveyLoading.rawValue
        case let .account(account): return account.rawValue
        case let .general(general): return general.rawValue
        }
    }
}

extension View {

    func accessibility(_ viewId: ViewId) -> some View {
        accessibilityIdentifier(viewId())
    }
}
