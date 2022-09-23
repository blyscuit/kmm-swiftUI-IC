//
//  ContentView.swift
//  Survey
//
//  Created by Bliss on 14/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Shared
import SwiftUI
import Combine
import KMPNativeCoroutinesCombine

struct ContentView: View {

    class DataSource: ObservableObject {
        @Published var list = BreedViewState(breeds: nil, error: nil, isLoading: true, isEmpty: true)
        private var cancellables = [AnyCancellable]()

        let viewModel = LoginViewModel(networkClient: NetworkClient())

        init() {
            createPublisher(for: viewModel.breedStateNative)
                .replaceError(
                    with: BreedViewState(
                        breeds: nil,
                        error: "Somethign went wrong.",
                        isLoading: false,
                        isEmpty: true)
                )
                .assign(to: &$list)
        }
    }

    let greet = Greeting().greeting()

    @ObservedObject var data = DataSource()

    var body: some View {
        Text("Start")
        if let arr = data.list.breeds {
            List(arr, id: \.self) {
                Text($0)
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
