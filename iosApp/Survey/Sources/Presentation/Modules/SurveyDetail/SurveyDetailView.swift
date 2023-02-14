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

    @StateObject var dataSource: DataSource

    @State var isAnimating = true
    @State var questionIndex = 0
    // TODO: Replace with real answer object
    @State var currentAnswers = [String]()

    var body: some View {
        ZStack {
            surveyView
        }
        .accessibilityElement(children: .contain)
        .accessibility(.surveyDetail(.view))
        .if(dataSource.isShowingTitleNavigationBar) { view in
            view.backButton {
                didPressBack()
            }
        }
        .if(!dataSource.isShowingTitleNavigationBar) { view in
            view.navigationBarItems(trailing: closeButton)
        }
        .onLoad {
            DispatchQueue.main.async {
                withAnimation(.easeInViewTransition) {
                    isAnimating = false
                }
            }
        }
        .loadingDialog(loading: $dataSource.isLoading)
        .alert(isPresented: $dataSource.isShowingErrorAlert, content: {
            Alert(title: Text(dataSource.viewState.error))
        })
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
            if dataSource.isShowingTitle {
                surveyTitleView
                    .transition(.move(edge: .leading).combined(with: .opacity))
            } else if let surveyDetail = dataSource.viewState.surveyDetail {
                animatedSurveyQuestionView(surveyDetail: surveyDetail)
                    .id(questionIndex)
            } else {
                Spacer()
            }
        }
    }

    var surveyTitleView: some View {
        VStack(alignment: .leading) {
            Text(survey.title)
                .lineLimit(3)
                .padding(.top, .mediumPadding)
                .foregroundColor(.white)
                .font(.boldLargeTitle)
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
            if dataSource.isShowingTitle {
                Button {
                    dataSource.didPressNext()
                } label: {
                    Text(String.localizeId.survey_detail_start_button())
                        .primaryButton()
                }
                .padding(.bottom, .mediumPadding)
                .accessibility(.surveyDetail(.startButton))
            } else {
                Button {
                    // TODO: Submit button logics
                    let totalItem = (dataSource.viewState.surveyDetail?.questions.count ?? 0) - 1
                    guard questionIndex < totalItem else { return }
                    currentAnswers = []
                    withAnimation(.easeIn(duration: .viewTransition)) {
                        questionIndex += 1
                    }
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

    init(survey: SurveyUiModel, coordinator: SurveyDetailCoordinator) {
        self.survey = survey
        self.coordinator = coordinator
        let dataSource = DataSource(id: survey.id)
        _dataSource = StateObject(wrappedValue: dataSource)
    }

    private func animatedSurveyQuestionView(surveyDetail: SurveyDetailUiModel) -> some View {
        return SurveyQuestionView(
            detail: surveyDetail,
            questionIndex: $questionIndex,
            answers: $currentAnswers
        )
        .transition(
            .asymmetric(
                insertion: .move(edge: .trailing).combined(with: .opacity),
                removal: .move(edge: .leading).combined(with: .opacity)
            )
        )
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
