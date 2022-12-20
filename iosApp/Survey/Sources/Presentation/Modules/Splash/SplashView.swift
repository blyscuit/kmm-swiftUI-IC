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

    @StateObject private var dataSource: DataSource

    var body: some View {
        GeometryReader { geometry in
            Assets.background
                .image
                .resizable()
                .aspectRatio(contentMode: .fill)
                .ignoresSafeArea()
                .frame(width: geometry.size.width, height: geometry.size.height)
        }
        .accessibility(.splash(.view))
        .onAppear {
            dataSource.checkLogin()
        }
    }

    init(coordinator: SplashCoordinator) {
        _dataSource = StateObject(wrappedValue: DataSource(coordinator: coordinator))
    }
}
