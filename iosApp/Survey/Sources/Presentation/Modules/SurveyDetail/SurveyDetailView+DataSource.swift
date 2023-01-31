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

        private var cancellables = Set<AnyCancellable>()

        init(
            viewModel: SurveyDetailViewModel = KoinApplication.shared.inject(),
            id: String
        ) {
            self.viewModel = viewModel
            viewModel.setSurveyId(id: id)
            viewModel.getDetail()

            createPublisher(for: viewModel.viewStateNative)
                .catch { error -> Just<SurveyDetailViewState> in
                    let surveyDetailViewState = SurveyDetailViewState(
                        error: error.localizedDescription
                    )
                    return Just(surveyDetailViewState)
                }
                .receive(on: DispatchQueue.main)
                .sink { [weak self] value in
                    guard let self else { return }
                    self.updateStates(value)
                }
                .store(in: &cancellables)
        }

        func didPressNext() {
            viewModel.showQuestion()
        }

        private func updateStates(_ state: SurveyDetailViewState) {
            viewState = state
            isShowingErrorAlert = !state.error.string.isEmpty
            isLoading = state.isLoading && state.isShowingQuestion
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
