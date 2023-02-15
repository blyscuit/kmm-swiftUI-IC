//
//  SubmissionSuccessView.swift
//  Survey
//
//  Created by Bliss on 15/2/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

protocol SubmissionSuccessCoordinator {

    func closeAndShowHome()
}

struct SubmissionSuccessView: View {

    private let successLottieName = "confetti"
    private let coordinator: SubmissionSuccessCoordinator

    var body: some View {
        ZStack {
            Rectangle()
                .foregroundColor(.moko.backgroundColor())
            VStack(alignment: .center) {
                LottieView(fileName: successLottieName) {
                    self.coordinator.closeAndShowHome()
                }
                .frame(width: 200.0, height: 200.0)
                Text(String.localizeId.submission_success_title())
                    .font(.boldTitle)
                    .foregroundColor(.moko.white())
                    .multilineTextAlignment(.center)
                    .padding()
            }
        }
        .navigationBarBackButtonHidden(true)
    }

    init(coordinator: SubmissionSuccessCoordinator) {
        self.coordinator = coordinator
    }
}
