//
//  SurveyLoading.swift
//  Survey
//
//  Created by Bliss on 4/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

protocol SurveyLoadingCoordinator {

    func showHome()
}

struct SurveyLoading: View {

    let coordinator: SurveyLoadingCoordinator

    var body: some View {
        VStack {
            SurveyHeaderLoading()
            SurveyItemLoading()
        }
        .padding(.horizontal, .surveyPadding)
        .onAppear {
            // TODO: Use real loading
            DispatchQueue.main.asyncAfter(deadline: .now() + 1.0) {
                withAnimation {
                    coordinator.showHome()
                }
            }
        }
        .accessibility(.surveyLoading(.view))
    }
}
