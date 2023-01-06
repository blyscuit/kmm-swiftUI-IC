//
//  SurveyDetailImage.swift
//  Survey
//
//  Created by Bliss on 6/1/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Shared
import SwiftUI

struct SurveyDetailImage: View {

    @Binding var isAnimating: Bool

    let survey: SurveyUiModel
    let scaleEffect = 1.42

    var body: some View {
        ZStack {

            GeometryReader { geometry in
                Image.url(survey.largeImageUrl)
                    .resizable()
                    .aspectRatio(contentMode: .fill)
                    .frame(width: geometry.size.width, height: geometry.size.height)
                    .scaleEffect(isAnimating ? 1.0 : scaleEffect, anchor: .topTrailing)
                    .accessibility(.surveyDetail(.mainImage))
            }
            .ignoresSafeArea()

            SurveyItemOverlayView()
                .ignoresSafeArea()
        }
        .accessibilityElement(children: .contain)
    }
}
