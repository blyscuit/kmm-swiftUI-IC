//
//  ContentView.swift
//  Survey
//
//  Created by Bliss on 14/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Shared
import SwiftUI
import KMPNativeCoroutinesCombine
import Combine

struct ContentView: View {

    class DataSource: ObservableObject {

        @Published var viewState = LoginViewState()

        @LazyKoin var viewModel: LoginViewModel

        init() {
            createPublisher(for: viewModel.viewStateNative)
                .catch { error -> Just<LoginViewState> in
                    let loginViewState = LoginViewState(error: error.localizedDescription)
                    return Just(loginViewState)
                }
                .receive(on: DispatchQueue.main)
                .assign(to: &$viewState)

            viewModel.login(email: "dev@nimblehq.co", password: "112345678")
        }
    }

    @ObservedObject var data = DataSource()

    let greet = Greeting().greeting()

    var body: some View {
        VStack {
        Text("\(greet) \(data.viewState.isSuccess.description)")
        Text("\(data.viewState.error ?? "What")")
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
