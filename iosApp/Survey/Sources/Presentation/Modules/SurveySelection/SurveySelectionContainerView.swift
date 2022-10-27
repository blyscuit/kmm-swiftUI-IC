//
//  SurveySelectionContainerView.swift
//  Survey
//
//  Created by Bliss on 26/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

struct SurveySelectionContainerView: View {

    @State var showingAccountView = false

    var body: some View {
        ZStack {
            SurveySelectionView($showingAccountView)

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
                AccountView()
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
            }
            .offset(x: showingAccountView ? 0.0 : 200.0)
        }
    }
}
