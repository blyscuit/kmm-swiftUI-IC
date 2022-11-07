//
//  SurveyItemView.swift
//  Survey
//
//  Created by Bliss on 28/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Kingfisher
import Shared
import SwiftUI

struct SurveyItemView: View {

    let survey: SurveyUiModel

    var body: some View {
        ZStack {
            GeometryReader { geo in
                KFImage(survey.largeImageUrl.asURL)
                    .resizable()
                    .scaledToFill()
                    .frame(maxWidth: geo.size.width, maxHeight: geo.size.height)
                    .clipped()
                    .accessibility(.surveySelection(.mainImage))
            }
            .ignoresSafeArea()

            Rectangle()
                .foregroundColor(.clear)
                .background(
                    LinearGradient(colors: [.clear, .black], startPoint: .top, endPoint: .bottom)
                )
                .opacity(0.6)
                .ignoresSafeArea()

            GeometryReader { geo in
                let titleOffset = geo.size.height - 160.0
                Text(survey.title)
                    .font(.boldTitle)
                    .lineLimit(2)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .offset(y: titleOffset)
                    .accessibility(.surveySelection(.titleText))

                let descriptionOffset = geo.size.height - 76.0
                Text(survey.description_)
                    .font(.regularBody)
                    .lineLimit(2)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .offset(y: descriptionOffset)
                    .accessibility(.surveySelection(.detailText))
            }
            .id(survey.id)
            .padding(.leading, 20.0)
            .padding(.trailing, 76.0)
        }
        .accessibilityElement(children: .contain)
    }
}
