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

    @State var isAnimating = true
    @State var isShowingTitle = true
    @State var isShowingTitleNavigationBar = true

    var body: some View {
        ZStack {
            surveyView
        }
        .accessibilityElement(children: .contain)
        .accessibility(.surveyDetail(.view))
        .if(isShowingTitleNavigationBar) { view in
            view.backButton {
                didPressBack()
            }
        }
        .if(!isShowingTitleNavigationBar) { view in
            view.navigationBarItems(trailing: closeButton)
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

            VStack {
                surveyQuestionView
                Spacer()
                nextButton
            }
            .padding(.horizontal, .smallPadding)
            .opacity(isAnimating ? 0.0 : 1.0)
        }
    }

    var surveyQuestionView: some View {
        VStack {
            if isShowingTitle {
                surveyTitleView
                    .transition(.move(edge: .leading).combined(with: .opacity))
            } else {
                SurveyQuestionView()
                    .transition(.move(edge: .trailing).combined(with: .opacity))
            }
        }
    }

    var surveyTitleView: some View {
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
        }
        .frame(maxWidth: .infinity, alignment: .leading)
    }

    var nextButton: some View {
        HStack {
            Spacer()
            if isShowingTitle {
                Button {
                    // TODO: Add action when press next. Move following line to real logic.
                    isShowingTitleNavigationBar = false
                    DispatchQueue.main.asyncAfter(deadline: .now() + .instant) {
                        withAnimation(.easeIn(duration: .viewTransition)) {
                            self.isShowingTitle = false
                        }
                    }
                } label: {
                    Text(String.localizeId.survey_detail_start_button())
                        .primaryButton()
                }
                .padding(.bottom, .mediumPadding)
                .accessibility(.surveyDetail(.startButton))
            } else {
                Button {
                    // TODO: Add action when press next
                } label: {
                    Assets.nextButton
                        .image
                        .resizable()
                        .frame(width: 56.0, height: 56.0)
                }
                .padding(.bottom, .mediumPadding)
                .accessibility(.surveyQuestion(.nextButton))
            }
        }
    }

    var closeButton: some View {
        Button {
            // TODO: Implement close button
            didPressBack()
        } label: {
            Assets.closeButton
                .image
                .resizable()
                .frame(width: 28.0, height: 28.0)
                .accessibility(.surveyQuestion(.closeButton))
        }
    }

    func didPressBack() {
        withAnimation(.easeInViewTransition) {
            isAnimating = true
        }
        DispatchQueue.main.asyncAfter(deadline: .now() + .viewTransition) {
            self.coordinator.backToHome()
        }
    }
}
