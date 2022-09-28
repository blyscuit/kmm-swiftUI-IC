//
//  FadePaginationView.swift
//  Survey
//
//  Created by Bliss on 28/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

struct FadePaginationView<T>: View {

    @Binding private var currentPage: Int
    @State private var currentVisible: Double = 1.0
    @State private var nextPage = 0
    private var turnLength: Double = 160.0
    private var turnSpeed: Double = 700.0
    @GestureVelocity private var velocity: CGVector

    var currentView: (T) -> AnyView
    var nextView: (T) -> AnyView
    var items: [T]

    var body: some View {
        ZStack {
            nextView(items[nextPage])
            currentView(items[currentPage])
                .opacity(currentVisible)
                .gesture(
                    DragGesture(minimumDistance: 6.0, coordinateSpace: .local)
                        .onChanged { value in
                            switch value.translation.width {
                            case ...0: nextPage = min(items.count - 1, currentPage + 1)
                            case 0...: nextPage = max(0, currentPage - 1)
                            default: return
                            }
                            let percentage = turnLength * 1.17 / abs(value.translation.width)
                            currentVisible = 1.0 * max(0.0, percentage)
                        }
                        .onEnded { value in
                            withAnimation(.easeInOut(duration: 0.3)) {
                                let velocity = self.velocity
                                if velocity.dx < -turnSpeed {
                                    previousPage()
                                } else if velocity.dx > turnSpeed {
                                    forwardPage()
                                } else {
                                    switch value.translation.width {
                                    case ...(-turnLength):
                                        previousPage()
                                    case turnLength...:
                                        forwardPage()
                                    default: break
                                    }
                                }
                                currentVisible = 1.0
                            }
                        }
                        .updatingVelocity($velocity)
                )
        }
    }

    init(
        currentPage: Binding<Int>,
        currentView: @escaping (T) -> AnyView,
        nextView: @escaping (T) -> AnyView,
        items: [T]
    ) {
        _currentPage = currentPage
        self.currentView = currentView
        self.nextView = nextView
        self.items = items
    }

    func forwardPage() {
        currentPage = max(0, currentPage - 1)
    }

    func previousPage() {
        currentPage = min(items.count - 1, currentPage + 1)
    }
}
