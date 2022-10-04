//
//  SurveyHeaderLoading.swift
//  Survey
//
//  Created by Bliss on 4/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SkeletonUI
import SwiftUI

struct SurveyHeaderLoading: View {

    var body: some View {
        VStack(alignment: .leading) {
            Spacer()
                .frame(height: 20.0)
            SkeletonTextView(width: 118.0)
            HStack {
                SkeletonTextView(width: 90.0)
                Spacer()
                Text(nil)
                    .skeleton(with: true, size: CGSize(width: 36.0, height: 36.0))
                    .shape(type: .circle)
            }
        }
    }
}
