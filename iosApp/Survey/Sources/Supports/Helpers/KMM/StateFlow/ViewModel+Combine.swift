//
//  ViewModel+Combine.swift
//  Survey
//
//  Created by Bliss on 22/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Combine
import Shared

extension ViewModel {

    func adaptor<T>(flow: AnyFlow<T>) -> FlowAdapter<AnyObject> {
        return asCallbacks(flow.source)
    }

    func asPublisher<T>(flow: AnyFlow<T>) -> AnyPublisher<T, Never> {
        createPublisher(adaptor(flow: flow), type: flow)
            .assertNoFailure()
            .eraseToAnyPublisher()
    }
}
