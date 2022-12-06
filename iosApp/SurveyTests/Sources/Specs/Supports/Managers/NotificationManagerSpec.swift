//
//  NotificationManagerSpec.swift
//  Survey
//
//  Created by Bliss on 20/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Nimble
import Quick

@testable import Survey

final class NotificationManagerSpec: QuickSpec {

    // swiftlint:disable function_body_length
    override func spec() {

        describe("a NotificationManager") {

            var notificationManager: NotificationManager!
            var notificationCenter: NotificationCenterCallableMock!

            beforeEach {
                notificationCenter = NotificationCenterCallableMock()
                notificationManager = NotificationManager(notificationCenter: notificationCenter)
            }

            describe("its requestPermission") {

                let title = "title"
                let message = "message"
                let time = 0.1

                context("notificationCenter is allowed") {
                    beforeEach {
                        notificationCenter.requestAuthorizationOptionsCompletionHandlerClosure = { _, completion in
                            completion(true, nil)
                        }
                        notificationManager.schedule(
                            title: title,
                            message: message,
                            time: time
                        )
                    }

                    it("calls notificationCenter's add with correct contents") {
                        expect(
                            notificationCenter
                                .addWithCompletionHandlerReceivedArguments?
                                .request
                                .content
                                .title
                        ) == title
                        expect(
                            notificationCenter
                                .addWithCompletionHandlerReceivedArguments?
                                .request
                                .content
                                .subtitle
                        ) == message
                    }
                }

                context("notificationCenter is not allowed") {
                    beforeEach {
                        notificationCenter.requestAuthorizationOptionsCompletionHandlerClosure = { _, completion in
                            completion(false, nil)
                        }
                        notificationManager.schedule(
                            title: title,
                            message: message,
                            time: time
                        )
                    }

                    it("does not call notificationCenter's add") {
                        expect(notificationCenter.addWithCompletionHandlerCalled) == false
                    }
                }
            }
        }
    }
}
