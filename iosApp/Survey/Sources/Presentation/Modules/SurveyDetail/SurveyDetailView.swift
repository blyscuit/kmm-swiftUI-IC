//
//  SurveyDetailView.swift
//  Survey
//
//  Created by Bliss on 6/1/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Shared
import SwiftUI

protocol SurveyDetailCoordinator {

    func backToHome()
}

struct SurveyDetailView: View {

    let survey: SurveyUiModel
    let coordinator: SurveyDetailCoordinator

    @State var isAnimatingBack = false
    @State var isAnimating = true

    var body: some View {
        ZStack {
            surveyView
        }
        .accessibilityElement(children: .contain)
        .accessibility(.surveyDetail(.view))
        .backButton {
            didPressBack()
        }
        .onLoad {
            DispatchQueue.main.async {
                withAnimation(.easeInViewTransition) {
                    isAnimating = false
                }
            }
        }
    }

    var surveyView: some View {
        ZStack {
            SurveyDetailImage(isAnimating: $isAnimating, survey: survey)

            VStack(alignment: .leading) {
                Text(survey.title)
                    .lineLimit(3)
                    .padding(.top, .mediumPadding)
                    .foregroundColor(.white)
                    .font(.boldLarge)
                    .accessibility(.surveyDetail(.titleText))
                Text(survey.description_)
                    .lineLimit(.max)
                    .padding(.top, .lineSpacing)
                    .foregroundColor(.white)
                    .font(.regularBody)
                    .accessibility(.surveyDetail(.detailText))
                Spacer()
                HStack {
                    Spacer()
                    Button {
                        // TODO: Add action when press next
                    } label: {
                        Text(String.localizeId.survey_detail_start_button())
                            .primaryButton()
                    }
                    .padding(.bottom, .mediumPadding)
                    .accessibility(.surveyDetail(.startButton))
                }
            }
            .frame(maxWidth: .infinity)
            .padding(.horizontal, .smallPadding)
            .opacity(isAnimating ? 0.0 : 1.0)
        }
    }

    func didPressBack() {
        guard !isAnimatingBack else { return }
        isAnimatingBack = true
        withAnimation(.easeInViewTransition) {
            isAnimating = true
        }
        DispatchQueue.main.asyncAfter(deadline: .now() + .viewTransition) {
            self.coordinator.backToHome()
        }
    }
}
