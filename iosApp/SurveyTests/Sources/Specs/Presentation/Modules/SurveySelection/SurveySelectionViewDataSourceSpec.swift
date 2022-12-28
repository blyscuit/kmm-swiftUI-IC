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
        var getAppVersionUseCase: GetAppVersionUseCaseKMMMock!
        var surveyListUseCase: SurveyListUseCaseKMMMock!
        var surveySelectionViewModel: SurveySelectionViewModel!
        var dataSource: SurveySelectionView.DataSource!

        describe("a Survey Selection View Data Source") {

            beforeEach {
                getCurrentDateUseCase = GetCurrentDateUseCaseKMMMock()
                getProfileUseCase = GetProfileUseCaseKMMMock()
                getAppVersionUseCase = GetAppVersionUseCaseKMMMock()
                surveyListUseCase = SurveyListUseCaseKMMMock()
                surveySelectionViewModel = SurveySelectionViewModel(
                    getCurrentDateUseCase: getCurrentDateUseCase,
                    getProfileUseCase: getProfileUseCase,
                    getAppVersionUseCase: getAppVersionUseCase,
                    surveyListUseCase: surveyListUseCase,
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
                let appVersion = AppVersion(appVersion: "", buildNumber: "")
                let survey = Survey(id: "", imageUrl: "", title: "", description: "")
                let surveys = Array(repeating: survey, count: 3)

                beforeEach {
                    getCurrentDateUseCase.invokeReturnValue = AnyFlow(result: KotlinLong(1))
                    getProfileUseCase.invokeReturnValue = AnyFlow(result: user)
                    getAppVersionUseCase.invokeReturnValue = AnyFlow(result: appVersion)
                    surveyListUseCase.invokePageReturnValue = AnyFlow(result: NSArray(array: surveys))
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

                it("sets survey with correct item") {
                    let surveys = try self.awaitPublisher(dataSource.$surveys.collectNext(2)).last
                    expect(surveys?.count) == 3
                }

                describe("its checkFetchMore") {

                    beforeEach {
                        try? self.awaitPublisher(dataSource.$surveys.collectNext(2))
                        delayCheckFetchMore()
                    }

                    it("sets survey with correct item") {
                        let surveys = try self.awaitPublisher(dataSource.$surveys.collectNext(1)).last
                        expect(surveys?.count) == 6
                    }
                }
            }
        }

        func delayFetch() {
            DispatchQueue.main.asyncAfter(deadline: .now() + 0.01) {
                dataSource.fetch()
            }
        }

        func delayCheckFetchMore() {
            DispatchQueue.main.asyncAfter(deadline: .now() + 0.01) {
                dataSource.checkFetchMore(index: 3)
            }
        }
    }
}
