//
//  AccountViewDataSourceSpec.swift
//  Survey
//
//  Created by Bliss on 22/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Nimble
import Quick
import Shared

@testable import Survey

final class AccountViewDataSourceSpec: QuickSpec {

    override func spec() {

        var logOutUseCase: LogOutUseCaseKMMMock!
        var coordinator: AccountCoordinatorMock!
        var accountViewModel: AccountViewModel!
        var dataSource: AccountView.DataSource!
        var accountUiModel: AccountUiModel!

        describe("a Account View Data Source") {

            beforeEach {
                accountUiModel = AccountUiModel(avatarUrl: "", name: "", appVersion: "")
                logOutUseCase = LogOutUseCaseKMMMock()
                coordinator = AccountCoordinatorMock()
                accountViewModel = AccountViewModel(
                    accountUiModel: accountUiModel,
                    logOutUseCase: logOutUseCase
                )
                dataSource = .init(
                    coordinator: coordinator,
                    viewModel: accountViewModel
                )
            }

            describe("its init") {

                it("loading state is false") {
                    let isLoading = try self.awaitPublisher(dataSource.$isShowingLoading.collectNext(1)).last
                    expect(isLoading) == false
                }

                it("account state has correct value") {
                    let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(1)).last
                    expect(viewState?.accountUiModel) == accountUiModel
                }
            }

            describe("its logout") {

                beforeEach {
                    logOutUseCase.invokeReturnValue = AnyFlow(result: KotlinUnit())
                    delayLogout()
                }

                it("sets loading state") {
                    let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(2)).last
                    expect(viewState?.isLoading) == true
                }

                it("sets logout to true") {
                    let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(3)).last
                    expect(viewState?.isLogout) == true
                }

                it("calls coordinator to show login") {
                    _ = try self.awaitPublisher(dataSource.$viewState.collectNext(3)).last
                    expect(coordinator.showLoginFromAccountCallsCount) == 1
                }
            }
        }

        func delayLogout() {
            DispatchQueue.main.asyncAfter(deadline: .now() + 0.01) {
                dataSource.logOut()
            }
        }
    }
}
