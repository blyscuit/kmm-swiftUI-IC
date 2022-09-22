//
//  CombineAdapters.swift
//  Survey
//
//  Created by Bliss on 14/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Combine
import Shared

func createPublisher<T>(_ flowAdapter: FlowAdapter<AnyObject>, type: AnyFlow<T>) -> AnyPublisher<T, KotlinError> {
    return Deferred<Publishers.HandleEvents<PassthroughSubject<T, KotlinError>>> {
        let subject = PassthroughSubject<T, KotlinError>()
        let canceller = flowAdapter.subscribe(
            onEach: { item in
                guard let item = item as? T else { return }
                subject.send(item)
            },
            onComplete: { subject.send(completion: .finished) },
            onThrow: { error in subject.send(completion: .failure(KotlinError(error))) }
        )
        return subject.handleEvents(receiveCancel: { canceller.cancel() })
    }
    .eraseToAnyPublisher()
}
