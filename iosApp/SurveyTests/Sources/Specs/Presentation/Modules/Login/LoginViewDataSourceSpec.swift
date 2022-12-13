//  swiftlint:disable function_body_length
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

                it("has no loading state") {
                    let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(1)).last
                    expect(viewState?.isLoading) == false
                }

                it("has no error state") {
                    let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(1)).last
                    expect(viewState?.error) == nil
                }

                it("has no email error state") {
                    let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(1)).last
                    expect(viewState?.isEmailError) == false
                }

                it("has no password error state") {
                    let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(1)).last
                    expect(viewState?.isPasswordError) == false
                }
            }

            describe("its login") {

                beforeEach {
                    loginUseCase.invokeEmailPasswordReturnValue = AnyFlow<Token>(errorMessage: "")
                    dataSource.login()
                }

                it("sets loading state") {
                    let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(2)).last
                    expect(viewState?.isLoading) == true
                }

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
                        dataSource.login()
                    }

                    it("sets success state") {
                        let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(3)).last
                        expect(viewState?.isSuccess) == true
                    }

                    it("coordinator shows home") {
                        try self.awaitPublisher(dataSource.$viewState.collectNext(3))
                        expect(loginCoordinator.showHomeLoadingCallsCount) == 1
                    }
                }

                context("when it return failure") {

                    let error = "iOS_Error"
                    beforeEach {
                        loginUseCase.invokeEmailPasswordReturnValue = AnyFlow<Token>(errorMessage: error)
                        dataSource.login()
                    }

                    it("sets showingErrorAlert to true") {
                        let showingError = try self.awaitPublisher(dataSource.$showingErrorAlert.collectNext(3)).last
                        expect(showingError) == true
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
    }
}
