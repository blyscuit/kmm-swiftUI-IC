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

    @ObservedObject private var dataSource: DataSource

    @Binding var isShowingAccountView: Bool

    @State private var currentPage = 0

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
                items: dataSource.surveys
            )
            .onChange(of: currentPage) { index in
                dataSource.checkFetchMore(index: index)
            }
            VStack(alignment: .leading) {
                Spacer()
                HStack(alignment: .bottom) {
                    PageControlView(
                        currentPage: $currentPage,
                        numberOfPages: dataSource.surveys.count
                    )
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
                        guard dataSource.viewState.accountUiModel != nil else { return }
                        withAnimation {
                            isShowingAccountView.toggle()
                        }
                    }
                }
                Spacer()
                HStack {
                    Spacer()
                    Button {
                        dataSource.showSurveyDetail()
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

    init(isShowingAccountView: Binding<Bool>, dataSource: DataSource) {
        _isShowingAccountView = isShowingAccountView
        self.dataSource = dataSource
    }
}
