//
//  SplashView.swift
//  Survey
//
//  Created by Bliss on 26/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import FlowStacks
import SwiftUI

struct SplashView: View {

    @State var animating = false

    let showLogin: () -> Void

    private let animationDuration: Double = 0.7

    var body: some View {
        ZStack {
            ZStack {}
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .background(Assets.background.image.resizable().aspectRatio(contentMode: .fill))
                .ignoresSafeArea()

            if animating {
                ZStack {}
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
                    .background(Assets.backgroundBlur.image.resizable().aspectRatio(contentMode: .fill))
                    .ignoresSafeArea()
            }

            VStack(
                alignment: .center
            ) {
                Assets.logoWhite.image

                if animating {
                    Spacer().frame(maxHeight: 300.0)
                }
            }
            .padding(.horizontal, 24.0)
        }
        .accessibility(.splash(.view))
        .onAppear {
            withAnimation(.linear(duration: animationDuration)) {
                animating = true
            }
            DispatchQueue.main.asyncAfter(deadline: .now() + animationDuration - 0.1) {
                withAnimation(.linear(duration: 0.2)) {
                    showLogin()
                }
            }
        }
    }
}
