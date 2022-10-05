//
//  SurveyHeaderView.swift
//  Survey
//
//  Created by Bliss on 5/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

struct SurveyHeaderView: View {
    var body: some View {
        VStack(alignment: .leading, spacing: 4.0) {
            // TODO: Use real data from ViewModel
            Text("MONDAY, JUNE 15")
                .font(.boldSmall)
            HStack {
                Text("Today")
                    .font(.boldLarge)
                Spacer()
                Assets.background.image
                    .resizable()
                    .frame(width: 36.0, height: 36.0)
                    .cornerRadius(18.0)
            }
        }
        .padding([.top, .horizontal], 20.0)
    }
}
