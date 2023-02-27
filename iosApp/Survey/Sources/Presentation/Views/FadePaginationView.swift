//
//  FadePaginationView.swift
//  Survey
//
//  Created by Bliss on 28/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI
import SwiftUI_Pull_To_Refresh

struct FadePaginationView<T>: View {

    @Binding private var currentPage: Int

    @State private var currentVisibility: Double = 1.0
    @State private var nextPage = 0

    @GestureVelocity private var velocity: CGVector

    private var turningLength: Double = 160.0
    private var turningSpeed: Double = 700.0
    private var minimumTurnDistance: Double = 6.0
    private var turningVisibilityMultiplier: Double = 1.17

    var currentView: (T) -> AnyView
    var nextView: (T) -> AnyView
    var items: [T]
    var didPullToRefresh: OnRefresh

    var body: some View {
        ZStack {
            if items.count > nextPage {
                nextView(items[nextPage])
            }
            if items.count > currentPage {
                ZStack {
                    currentView(items[currentPage])
                        .opacity(currentVisibility)
                    RefreshableScrollView(
                        loadingViewBackgroundColor: .clear,
                        onRefresh: didPullToRefresh
                    ) {
                        ZStack {}
                    }.gesture(
                        DragGesture(minimumDistance: minimumTurnDistance, coordinateSpace: .local)
                            .onChanged { value in
                                switch value.translation.width {
                                case ...0: nextPage = min(items.count - 1, currentPage + 1)
                                case 0...: nextPage = max(0, currentPage - 1)
                                default: return
                                }
                                let turningVisibilityPercentage =
                                    turningLength * turningVisibilityMultiplier / abs(value.translation.width)
                                currentVisibility = 1.0 * max(0.0, turningVisibilityPercentage)
                            }
                            .onEnded { value in
                                withAnimation(.easeInOut(duration: .default)) {
                                    let velocity = self.velocity
                                    if velocity.dx < -turningSpeed {
                                        // Swipe back fast
                                        previousPage()
                                    } else if velocity.dx > turningSpeed {
                                        // Swipe forward fast
                                        forwardPage()
                                    } else {
                                        // Slow swipe
                                        switch value.translation.width {
                                        case ...(-turningLength):
                                            // Swipe back reaching threshold
                                            previousPage()
                                        case turningLength...:
                                            // Swipe forward reaching threshold
                                            forwardPage()
                                        default: break
                                        }
                                    }
                                    currentVisibility = 1.0
                                }
                            }
                            .updatingVelocity($velocity)
                    )
                }
            } else { VStack {} }
        }
    }

    init(
        currentPage: Binding<Int>,
        currentView: @escaping (T) -> AnyView,
        nextView: @escaping (T) -> AnyView,
        items: [T],
        didPullToRefresh: OnRefresh?
    ) {
        _currentPage = currentPage
        self.currentView = currentView
        self.nextView = nextView
        self.items = items
        self.didPullToRefresh = didPullToRefresh ?? { $0() }
    }

    private func forwardPage() {
        currentPage = max(0, currentPage - 1)
    }

    private func previousPage() {
        currentPage = min(items.count - 1, currentPage + 1)
    }
}
