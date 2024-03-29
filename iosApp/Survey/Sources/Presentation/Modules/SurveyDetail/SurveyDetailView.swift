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
    func closeSubmissionAndShowHome()
}

struct SurveyDetailView: View {

    let survey: SurveyUiModel
    let coordinator: SurveyDetailCoordinator

    @StateObject var dataSource: DataSource

    @State var isAnimating = true
    @State var isShowingQuitPrompt = false
    @State var currentAnswers = [SurveyAnswer]()

    var body: some View {
        ZStack {
            surveyView
                .if(!dataSource.isShowingTitleNavigationBar) { view in
                    view.navigationBarItems(trailing: closeButton)
                }
            if dataSource.isShowingSuccessConfirmation {
                SubmissionSuccessView(
                    coordinator: coordinator,
                    isShowing: $dataSource.isShowingSuccessConfirmation
                )
                .ignoresSafeArea()
            }
        }
        .accessibilityElement(children: .contain)
        .accessibility(.surveyDetail(.view))
        .if(dataSource.isShowingTitleNavigationBar) { view in
            view.backButton {
                didPressBack()
            }
        }
        .onLoad {
            DispatchQueue.main.async {
                withAnimation(.easeInViewTransition) {
                    isAnimating = false
                }
            }
        }
        .loadingDialog(loading: $dataSource.isLoading)
        .alert(isPresented: $dataSource.isShowingErrorAlert) {
            Alert(title: Text(dataSource.viewState.error))
        }
        .alert(isPresented: $isShowingQuitPrompt) {
            quitAlert
        }
    }

    var surveyView: some View {
        ZStack {
            SurveyDetailImage(isAnimating: $isAnimating, survey: survey)

            VStack {
                surveyQuestionView
                Spacer()
                nextButtonArea
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
                    .id(dataSource.questionIndex)
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

    var nextButtonArea: some View {
        HStack {
            Spacer()
            if dataSource.isShowingTitle {
                startButton
            } else if dataSource.isShowingSubmit {
                submitButton
            } else {
                nextButton
            }
        }
    }

    var startButton: some View {
        Button {
            dataSource.didPressStart()
        } label: {
            Text(String.localizeId.survey_detail_start_button())
                .primaryButton()
        }
        .padding(.bottom, .mediumPadding)
        .accessibility(.surveyDetail(.startButton))
    }

    var nextButton: some View {
        Button {
            dataSource.didPressNext(answers: currentAnswers)
            currentAnswers = []
        } label: {
            Assets.nextButton
                .image
                .resizable()
                .frame(width: 56.0, height: 56.0)
        }
        .padding(.bottom, .mediumPadding)
        .accessibility(.surveyQuestion(.nextButton))
    }

    var submitButton: some View {
        Button {
            dataSource.didPressSubmit()
        } label: {
            Text(String.localizeId.survey_submit_button())
                .primaryButton()
        }
        .padding(.bottom, .mediumPadding)
        .accessibility(.surveyDetail(.submitButton))
    }

    var closeButton: some View {
        Button {
            isShowingQuitPrompt = true
        } label: {
            Assets.closeButton
                .image
                .resizable()
                .frame(width: 28.0, height: 28.0)
                .accessibility(.surveyQuestion(.closeButton))
                .opacity(dataSource.isShowingSuccessConfirmation ? 0.0 : 1.0)
        }
        .disabled(isAnimating)
    }

    var quitAlert: Alert {
        Alert(
            title: Text(String.localizeId.survey_quit_title()),
            message: Text(String.localizeId.survey_quit_message()),
            primaryButton: .default(
                Text(String.localizeId.survey_quit_confirm())
            ) {
                didPressBack()
            },
            secondaryButton: .cancel(
                Text(String.localizeId.survey_quit_cancel())
                    .bold()
            ) {
                isShowingQuitPrompt = false
            }
        )
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
            questionIndex: $dataSource.questionIndex,
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
