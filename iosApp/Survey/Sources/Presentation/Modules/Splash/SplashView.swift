//
//  SplashView.swift
//  Survey
//
//  Created by Bliss on 26/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import FlowStacks
import SwiftUI

protocol SplashCoordinator {

    func showLogin()
}

struct SplashView: View {

    let coordinator: SplashCoordinator

    var body: some View {
        ZStack {
            Assets.background
                .image
                .resizable()
                .aspectRatio(contentMode: .fill)
                .ignoresSafeArea()
                .frame(maxWidth: .infinity, maxHeight: .infinity)
        }
        .accessibility(.splash(.view))
        .onAppear {
            withAnimation(.linear(duration: .fast)) {
                coordinator.showLogin()
            }
        }
    }
}
