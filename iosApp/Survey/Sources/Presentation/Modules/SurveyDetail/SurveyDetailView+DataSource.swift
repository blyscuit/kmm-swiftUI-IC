//
//  SurveyDetailView+DataSource.swift
//  Survey
//
//  Created by Bliss on 31/1/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Combine
import KMPNativeCoroutinesCombine
import Shared
import SwiftUI

extension SurveyDetailView {

    final class DataSource: ObservableObject {

        let viewModel: SurveyDetailViewModel

        @Published private(set) var viewState = SurveyDetailViewState()
        @Published var isShowingErrorAlert = false
        @Published var isLoading = false
        @Published var isShowingTitle = true
        @Published var isShowingTitleNavigationBar = true
        @Published var questionIndex = 0
        @Published var isShowingSuccessConfirmation = false
        @Published var isShowingSubmit = false

        private var cancellables = Set<AnyCancellable>()

        init(
            viewModel: SurveyDetailViewModel = KoinApplication.shared.inject(),
            id: String
        ) {
            self.viewModel = viewModel
            viewModel.setSurveyId(id: id)
            viewModel.getDetail()

            let viewState = createGuaranteedPublisher(
                for: viewModel.viewStateNative,
                fallback: SurveyDetailViewState()
            )
            let questionViewState = createGuaranteedPublisher(
                for: viewModel.questionViewStateNative,
                fallback: SurveyQuestionViewState()
            )
            viewState
                .combineLatest(questionViewState)
                .receive(on: DispatchQueue.main)
                .sink { [weak self] viewState, questionViewState in
                    guard let self else { return }
                    self.updateStates(viewState, questionViewState)
                }
                .store(in: &cancellables)
        }

        func didPressStart() {
            viewModel.showQuestion()
        }

        func didPressNext(answers: [SurveyAnswer]) {
            viewModel.addAnswer(values: answers)
        }

        func didPressSubmit() {
            viewModel.submitAnswer()
        }

        private func updateStates(
            _ state: SurveyDetailViewState,
            _ questionState: SurveyQuestionViewState
        ) {
            viewState = state
            isShowingErrorAlert = !state.error.string.isEmpty
            isLoading = (state.isLoading || questionState.isLoading) && (state.isShowingQuestion)
            isShowingSuccessConfirmation = questionState.isShowingSuccess
            withAnimation(.easeIn(duration: .viewTransition)) {
                isShowingSubmit = questionState.isShowingSubmit
                questionIndex = Int(questionState.currentQuestionIndex)
            }
            if state.surveyDetail != nil, state.isShowingQuestion {
                isShowingTitleNavigationBar = false
                DispatchQueue.main.asyncAfter(deadline: .now() + .instant) {
                    withAnimation(.easeIn(duration: .viewTransition)) {
                        self.isShowingTitle = false
                    }
                }
            }
        }
    }
}
