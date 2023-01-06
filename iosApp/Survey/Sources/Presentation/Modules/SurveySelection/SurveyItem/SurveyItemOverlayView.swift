//
//  SurveyItemOverlayView.swift
//  Survey
//
//  Created by Bliss on 6/1/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Shared
import SwiftUI

struct SurveyItemOverlayView: View {

    var body: some View {
        Rectangle()
            .foregroundColor(.clear)
            .background(
                LinearGradient(colors: [.clear, .black], startPoint: .top, endPoint: .bottom)
            )
            .opacity(0.6)
    }
}
