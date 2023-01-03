//
//  SurveySelectionView.swift
//  Survey
//
//  Created by Bliss on 28/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Shared
import SwiftUI

struct SurveySelectionView: View {

    @StateObject private var dataSource = DataSource()

    @Binding var isShowingAccountView: Bool

    @State private var currentPage = 0
    // TODO: Replace Example data
    @State private var surveys: [SurveyUiModel] = [
        SurveyUiModel(
            id: "1",
            imageUrl: "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_",
            title: "Scarlett Bangkok",
            description: "We'd love ot hear from you!"
        ),
        SurveyUiModel(
            id: "2",
            imageUrl: "https://dhdbhh0jsld0o.cloudfront.net/m/287db81c5e4242412cc0_",
            title: "ibis Bangkok Riverside",
            description: "We'd love ot hear from you!"
        ),
        SurveyUiModel(
            id: "3",
            imageUrl: "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_",
            title: "Scarlett Bangkok",
            description: "We'd love ot hear from you!"
        )
    ]

    var body: some View {
        ZStack {
            if dataSource.showingLoading {
                SurveyLoading()
            } else {
                survey
            }
        }
        .accessibilityElement(children: .contain)
        .accessibility(.surveySelection(.view))
        .onLoad {
            dataSource.fetch()
        }
    }

    var survey: some View {
        ZStack {
            FadePaginationView(
                currentPage: $currentPage,
                currentView: { item in
                    AnyView(SurveyItemView(survey: item))
                },
                nextView: { item in
                    AnyView(SurveyItemView(survey: item))
                },
                items: surveys
            )
            .onChange(of: currentPage) { newValue in
                // TODO: Update viewModel to new index
                print(newValue)
            }
            VStack(alignment: .leading) {
                Spacer()
                HStack(alignment: .bottom) {
                    PageControlView(currentPage: $currentPage, numberOfPages: surveys.count)
                        .frame(width: .zero, alignment: .leading)
                    Spacer()
                }
                Spacer()
                    .frame(height: 190.0)
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)

            VStack {
                if let surveyHeader = dataSource.viewState.surveyHeaderUiModel {
                    SurveyHeaderView(surveyHeader: surveyHeader) {
                        withAnimation {
                            isShowingAccountView.toggle()
                        }
                    }
                }
                Spacer()
                HStack {
                    Spacer()
                    Button {
                        // TODO: Add action when press next
                    } label: {
                        Assets.nextButton
                            .image
                            .resizable()
                            .frame(width: 56.0, height: 56.0)
                    }
                    .padding(.trailing, .smallPadding)
                    .padding(.bottom, .mediumPadding)
                    .accessibility(.surveySelection(.nextButton))
                }
            }
        }
    }

    init(_ isShowingAccountView: Binding<Bool>) {
        _isShowingAccountView = isShowingAccountView
    }
}
