//
//  SurveySelectionViewDataSourceSpec.swift
//  Survey
//
//  Created by Bliss on 15/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Nimble
import Quick
import Shared

@testable import Survey

final class SurveySelectionViewDataSourceSpec: QuickSpec {

    override func spec() {

        var getCurrentDateUseCase: GetCurrentDateUseCaseKMMMock!
        var getProfileUseCase: GetProfileUseCaseKMMMock!
        var surveySelectionViewModel: SurveySelectionViewModel!
        var dataSource: SurveySelectionView.DataSource!

        describe("a Survey Selection View Data Source") {

            beforeEach {
                getCurrentDateUseCase = GetCurrentDateUseCaseKMMMock()
                getProfileUseCase = GetProfileUseCaseKMMMock()
                surveySelectionViewModel = SurveySelectionViewModel(
                    getCurrentDateUseCase: getCurrentDateUseCase,
                    getProfileUseCase: getProfileUseCase,
                    dateTimeFormatter: DateTimeFormatterImpl()
                )
                dataSource = .init(
                    viewModel: surveySelectionViewModel
                )
            }

            describe("its init") {

                it("loading state is true") {
                    let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(1)).last
                    expect(viewState?.isLoading) == true
                }

                it("surveyHeader state is null") {
                    let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(1)).last
                    expect(viewState?.surveyHeaderUiModel) == nil
                }
            }

            describe("its fetch") {

                let user = User(name: "name", avatarUrl: "avatarUrl")

                beforeEach {
                    getCurrentDateUseCase.invokeReturnValue = AnyFlow(result: KotlinLong(1))
                    getProfileUseCase.invokeReturnValue = AnyFlow(result: user)
                    delayFetch()
                }

                it("sets loading state") {
                    let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(1)).last
                    expect(viewState?.isLoading) == true
                }

                it("sets surveyHeader with correct date") {
                    let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(2)).last
                    expect(viewState?.surveyHeaderUiModel?.dateText) == "Thursday, January 1"
                }
            }
        }

        func delayFetch() {
            DispatchQueue.main.asyncAfter(deadline: .now() + 0.1) {
                dataSource.fetch()
            }
        }
    }
}
