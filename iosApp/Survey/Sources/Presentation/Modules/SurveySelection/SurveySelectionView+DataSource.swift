//
//  SurveySelectionView+DataSource.swift
//  Survey
//
//  Created by Bliss on 15/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Combine
import KMPNativeCoroutinesCombine
import Shared

// sourcery: AutoMockable
protocol SurveySelectionCoordinator {

    func showSurveyDetail(_ parameters: ScreenParameters.SurveyDetail)
}

extension SurveySelectionView {

    final class DataSource: ObservableObject {

        let viewModel: SurveySelectionViewModel
        let coordinator: SurveySelectionCoordinator

        @Published private(set) var viewState = SurveySelectionViewState()
        @Published var showingLoading = false
        @Published private(set) var surveys = [SurveyUiModel]()

        private var cancellables = Set<AnyCancellable>()

        init(
            viewModel: SurveySelectionViewModel = KoinApplication.shared.inject(),
            coordinator: SurveySelectionCoordinator
        ) {
            self.viewModel = viewModel
            self.coordinator = coordinator
            createPublisher(for: viewModel.viewStateNative)
                .catch { _ -> Just<SurveySelectionViewState> in
                    let surveySelectionViewState = SurveySelectionViewState()
                    return Just(surveySelectionViewState)
                }
                .receive(on: DispatchQueue.main)
                .sink { [weak self] value in
                    guard let self else { return }
                    self.updateStates(value)
                }
                .store(in: &cancellables)
        }

        func fetch() {
            viewModel.fetch()
        }

        func checkFetchMore(index: Int) {
            viewModel.checkFetchMore(itemIndex: Int32(index))
        }

        func showSurveyDetail() {
            guard let survey = viewModel.currentSurvey else { return }
            coordinator.showSurveyDetail(.init(survey: survey))
        }

        private func updateStates(_ state: SurveySelectionViewState) {
            viewState = state
            showingLoading = state.isLoading
            surveys = state.surveys
        }
    }
}
