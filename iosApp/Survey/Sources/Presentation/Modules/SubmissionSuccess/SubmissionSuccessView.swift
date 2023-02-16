//
//  SubmissionSuccessView.swift
//  Survey
//
//  Created by Bliss on 15/2/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct SubmissionSuccessView: View {

    @Binding var isShowing: Bool

    private let successLottieName = "confetti"
    private let coordinator: SurveyDetailCoordinator

    var body: some View {
        ZStack {
            Rectangle()
                .foregroundColor(.moko.backgroundColor())
            VStack(alignment: .center) {
                LottieView(fileName: successLottieName) {
                    DispatchQueue.main.asyncAfter(deadline: .now() + .appearing) {
                        self.coordinator.closeSubmissionAndShowHome()
                    }
                }
                .frame(width: 200.0, height: 200.0)
                .accessibility(.submissionSuccess(.animation))
                Text(String.localizeId.submission_success_title())
                    .font(.boldTitle)
                    .foregroundColor(.moko.white())
                    .multilineTextAlignment(.center)
                    .padding()
            }
        }
        .transition(.opacity)
    }

    init(coordinator: SurveyDetailCoordinator, isShowing: Binding<Bool>) {
        self.coordinator = coordinator
        _isShowing = isShowing
    }
}
