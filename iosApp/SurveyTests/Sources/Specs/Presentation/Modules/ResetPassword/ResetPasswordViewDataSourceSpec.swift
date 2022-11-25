//
//  ResetPasswordViewDataSourceSpec.swift
//  Survey
//
//  Created by Bliss on 25/11/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Nimble
import Quick
import Shared

@testable import Survey

final class ResetPasswordViewDataSourceSpec: QuickSpec {

    // swiftlint:disable function_body_length
    override func spec() {

        var resetPasswordUseCase: ResetPasswordUseCaseKMMMock!
        var resetPasswordViewModel: ResetPasswordViewModel!
        var dataSource: ResetPasswordView.DataSource!
        var notificationManager: NotificationManageableMock!

        describe("a Reset Password View Data Source") {

            beforeEach {
                resetPasswordUseCase = ResetPasswordUseCaseKMMMock()
                resetPasswordViewModel = ResetPasswordViewModel(
                    resetPasswordUseCase: resetPasswordUseCase
                )
                notificationManager = NotificationManageableMock()
                dataSource = .init(
                    viewModel: resetPasswordViewModel,
                    notificationManager: notificationManager
                )
            }

            describe("its init") {

                it("has no loading state") {
                    let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(1)).last
                    expect(viewState?.isLoading) == false
                }

                it("has no successNotification state") {
                    let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(1)).last
                    expect(viewState?.successNotification) == nil
                }

                it("has no error state") {
                    let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(1)).last
                    expect(viewState?.error) == nil
                }
            }

            describe("its reset") {

                beforeEach {
                    _ = try? self.awaitPublisher(dataSource.$viewState.collectNext(1))
                }

                context("when it return notification") {

                    beforeEach {
                        let notification = NotificationUiModel(
                            title: .init(resourceId: "", bundle: .main),
                            message: .init(resourceId: "", bundle: .main)
                        )
                        resetPasswordUseCase.invokeEmailReturnValue = AnyFlow(result: notification)
                        dataSource.reset()
                    }

                    it("sets loading state") {
                        let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(1)).last
                        expect(viewState?.isLoading) == true
                    }

                    it("sets successNotification state") {
                        let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(2))
                        print("=====")
                        print(viewState)
                        expect(viewState.last?.successNotification) != nil
                    }

                    it("calls notification manager to schedule notification") {
                        _ = try self.awaitPublisher(dataSource.$viewState.collectNext(2))
                        expect(notificationManager.scheduleTitleMessageTimeCallsCount) == 1
                    }
                }

                context("when it return failure") {

                    let error = "iOS_Error"
                    beforeEach {
                        resetPasswordUseCase.invokeEmailReturnValue = AnyFlow<NotificationUiModel>(
                            errorMessage: error
                        )
                        dataSource.reset()
                    }

                    it("sets showingErrorAlert to true") {
                        let showingError = try self.awaitPublisher(dataSource.$showingErrorAlert.collectNext(2)).last
                        expect(showingError) == true
                    }

                    it("sets correct error state") {
                        let viewState = try self.awaitPublisher(dataSource.$viewState.collectNext(2)).last
                        expect(viewState?.error) == error
                    }
                }
            }
        }
    }
}
