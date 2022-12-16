//
//  LoginViewDataSourceSpec.swift
//  Survey
//
//  Created by Bliss on 28/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Nimble
import Quick
import Shared

@testable import Survey

final class LoginViewDataSourceSpec: QuickSpec {

    override func spec() {

        var loginCoordinator: LoginCoordinatorMock!
        var loginUseCase: LogInUseCaseKMMMock!
        var loginViewModel: LoginViewModel!
        var dataSource: LoginView.DataSource!

        describe("a Login View Data Source") {

            beforeEach {
                loginCoordinator = LoginCoordinatorMock()
                loginUseCase = LogInUseCaseKMMMock()
                loginViewModel = LoginViewModel(logInUseCase: loginUseCase)
                dataSource = .init(
                    coordinator: loginCoordinator,
                    viewModel: loginViewModel
                )
            }

            describe("its init") {

                it("has no succes state") {
                    let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(1)).last
                    expect(viewState?.isSuccess) == false
                }

                it("showingLoading is false") {
                    let showingLoading = try self.awaitPublisher(dataSource.$showingLoading.collectNext(1)).last
                    expect(showingLoading) == false
                }

                it("showingErrorAlert is false") {
                    let showingErrorAlert = try self.awaitPublisher(dataSource.$showingErrorAlert.collectNext(1)).last
                    expect(showingErrorAlert) == false
                }

                it("showingEmailError is false") {
                    let showingEmailError = try self.awaitPublisher(dataSource.$showingEmailError.collectNext(1)).last
                    expect(showingEmailError) == false
                }

                it("showingPasswordError is false") {
                    let showingPasswordError = try self.awaitPublisher(
                        dataSource.$showingPasswordError.collectNext(1)
                    ).last
                    expect(showingPasswordError) == false
                }
            }

            describe("its login") {

                context("when it return token") {

                    beforeEach {
                        let token = Token(
                            accessToken: "",
                            tokenType: "",
                            expiresIn: 0,
                            refreshToken: "",
                            createdAt: 0
                        )
                        loginUseCase.invokeEmailPasswordReturnValue = AnyFlow(result: token)
                        delayLogin()
                    }

                    it("showingPasswordError is true") {
                        let showingPasswordError = try self.awaitPublisher(
                            dataSource.$showingLoading.collectNext(2)
                        ).last
                        expect(showingPasswordError) == true
                    }

                    it("sets success state") {
                        let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(3)).last
                        expect(viewState?.isSuccess) == true
                    }

                    it("coordinator shows home") {
                        try self.awaitPublisher(dataSource.$viewState.collectNext(3))
                        expect(loginCoordinator.showHomeCallsCount) == 1
                    }
                }

                context("when it return failure") {

                    let error = "iOS_Error"
                    beforeEach {
                        loginUseCase.invokeEmailPasswordReturnValue = AnyFlow<Token>(errorMessage: error)
                        delayLogin()
                    }

                    it("showingErrorAlert is true") {
                        let showingErrorAlert = try self.awaitPublisher(
                            dataSource.$showingErrorAlert.collectNext(3)
                        ).last
                        expect(showingErrorAlert) == true
                    }

                    it("sets correct error state") {
                        let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(3)).last
                        expect(viewState?.error) == error
                    }

                    it("sets no success state") {
                        let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(3)).last
                        expect(viewState?.isSuccess) == false
                    }
                }
            }

            describe("its showResetPassword") {

                beforeEach {
                    dataSource.showResetPassword()
                }

                it("coordinator shows reset password") {
                    expect(loginCoordinator.showResetPasswordCallsCount) == 1
                }
            }
        }

        func delayLogin() {
            DispatchQueue.main.asyncAfter(deadline: .now() + 0.01) {
                dataSource.login()
            }
        }
    }
}
