//
//  SplashViewDataSourceSpec.swift
//  Survey
//
//  Created by Bliss on 12/12/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Nimble
import Quick
import Shared

@testable import Survey

final class SplashViewDataSourceSpec: QuickSpec {

    override func spec() {

        var checkLoginUseCase: CheckLoginUseCaseKMMMock!
        var splashViewModel: SplashViewModel!
        var dataSource: SplashView.DataSource!
        var coordinator: SplashCoordinatorMock!

        describe("a Splash View Data Source") {

            beforeEach {
                checkLoginUseCase = CheckLoginUseCaseKMMMock()
                splashViewModel = SplashViewModel(
                    checkLoginUseCase: checkLoginUseCase
                )
                coordinator = SplashCoordinatorMock()
                dataSource = .init(
                    viewModel: splashViewModel,
                    coordinator: coordinator
                )
            }

            describe("its init") {

                it("loading state is false") {
                    let viewState = try self.awaitPublisher(dataSource.$viewState.coldCollectNext(1)).last
                    expect(viewState?.isLoading) == false
                }

                it("login state is false") {
                    let viewState = try self.awaitPublisher(dataSource.$viewState.coldCollectNext(1)).last
                    expect(viewState?.isLogin) == false
                }
            }

            describe("its checkLogin") {

                context("when checkLoginUseCase returns true") {

                    beforeEach {
                        checkLoginUseCase.invokeReturnValue = WrappedFlow.shared.bool(bool: true)
                        delayCheckLogin()
                    }

                    it("sets loading state") {
                        let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(1)).last
                        expect(viewState?.isLoading) == true
                    }

                    it("sets loading false") {
                        let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(2)).last
                        expect(viewState?.isLoading) == false
                    }

                    it("sets login true") {
                        let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(2)).last
                        expect(viewState?.isLogin) == true
                    }
                }

                context("when checkLoginUseCase returns false") {

                    beforeEach {
                        checkLoginUseCase.invokeReturnValue = WrappedFlow.shared.bool(bool: false)
                        delayCheckLogin()
                    }

                    it("sets loading false") {
                        let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(2)).last
                        expect(viewState?.isLoading) == false
                    }

                    it("sets login false") {
                        let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(2)).last
                        expect(viewState?.isLogin) == false
                    }
                }

                context("when it return failure") {

                    beforeEach {
                        checkLoginUseCase.invokeReturnValue = WrappedFlow.shared.bool(
                            errorMessage: "error"
                        )
                        dataSource.checkLogin()
                    }

                    it("sets loading false") {
                        let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(2)).last
                        expect(viewState?.isLoading) == false
                    }

                    it("sets login false") {
                        let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(2)).last
                        expect(viewState?.isLogin) == false
                    }
                }
            }
        }

        func delayCheckLogin() {
            DispatchQueue.main.asyncAfter(deadline: .now() + 0.01) {
                dataSource.checkLogin()
            }
        }
    }
}
