//
//  SurveyQuestionView.swift
//  Survey
//
//  Created by Bliss on 11/1/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct SurveyQuestionView: View {

    var body: some View {
        VStack {
            Text("1/5")
                .font(.boldMedium)
                .foregroundColor(.white)
                .opacity(0.5)
            Text("")
                .padding(.top, .tinyPadding)
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
                .padding(.bottom, .mediumPadding)
                .accessibility(.surveyDetail(.startButton))
            }
        }
    }
}
