//
//  SurveySelectionContainerView.swift
//  Survey
//
//  Created by Bliss on 26/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

struct SurveySelectionContainerView: View {

    @StateObject private var dataSource = SurveySelectionView.DataSource()

    @State var showingAccountView = false

    var body: some View {
        ZStack {
            SurveySelectionView(
                showingAccountView: $showingAccountView,
                dataSource: dataSource
            )

            if showingAccountView {
                Button {
                    withAnimation {
                        showingAccountView = false
                    }
                } label: {
                    ZStack {}
                        .frame(maxWidth: .infinity, maxHeight: .infinity)
                }
            }

            HStack {
                Spacer()
                if showingAccountView, let account = dataSource.viewState.accountUiModel {
                    AccountView(account: account)
                        .gesture(
                            DragGesture(minimumDistance: 50)
                                .onEnded { gesture in
                                    if gesture.predictedEndTranslation.width > 0 {
                                        withAnimation {
                                            showingAccountView = false
                                        }
                                    }
                                }
                        )
                        .transition(.offset(x: 200.0))
                }
            }
        }
    }
}
