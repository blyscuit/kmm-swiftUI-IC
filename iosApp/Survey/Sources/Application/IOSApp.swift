//
//  IOSApp.swift
//  Survey
//
//  Created by Bliss on 14/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Combine
import Shared
import SwiftUI
import KMPNativeCoroutinesCombine

@main
struct IOSApp: App {

    var networkClient = NetworkClient()
    var cancellables = Set<AnyCancellable>()

    init() {
        // Create an AnyPublisher for your flow
        let publisher = createPublisher(for: networkClient.getAllLaunchesNative())

        // Now use this publisher as you would any other
        publisher.sink { completion in
            print("Received completion: \(completion)")
        } receiveValue: { value in
            print("Received value: \(value)")
        }.store(in: &cancellables)
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
