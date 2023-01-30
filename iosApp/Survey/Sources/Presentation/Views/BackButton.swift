//
//  BackButton.swift
//  Survey
//
//  Created by Bliss on 30/1/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct BackButton: View {

    @State private var isActionable = true

    private var action: () -> Void

    var body: some View {
        Button {
            guard isActionable else { return }
            isActionable = false
            action()
        } label: {
            HStack(spacing: 0) {
                Image(systemName: .backArrow)
                    .resizable()
                    .font(.body.weight(.semibold))
                    .foregroundColor(.white)
                    .offset(x: -8.0)
            }
        }
    }

    init(action: @escaping () -> Void) {
        self.action = action
    }
}
