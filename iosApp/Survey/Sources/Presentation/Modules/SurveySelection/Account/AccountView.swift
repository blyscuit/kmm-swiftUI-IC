//
//  AccountView.swift
//  Survey
//
//  Created by Bliss on 26/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Kingfisher
import Shared
import SwiftUI

struct AccountView: View {

    let account: AccountUiModel

    var body: some View {
        ZStack {
            Rectangle()
                .foregroundColor(.black.opacity(0.9))
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .ignoresSafeArea()
            VStack(alignment: .leading) {
                HStack(alignment: .firstTextBaseline) {
                    Text(account.name)
                        .font(.boldLarge)
                        .lineLimit(1)
                        .accessibility(.account(.profileText))
                    Spacer()
                    KFImage(account.imageUrl.string.asURL)
                        .resizable()
                        .frame(width: 36.0, height: 36.0)
                        .cornerRadius(18.0)
                        .offset(y: 5.0)
                        .accessibility(.account(.profileImage))
                }
                .padding(.top, 8.0)
                Spacer()
                    .frame(height: 26.0)
                Rectangle()
                    .foregroundColor(.white)
                    .frame(height: 0.5)
                    .opacity(0.2)
                Spacer()
                    .frame(height: 35.0)
                Button {
                    // TODO: Add logout action
                } label: {
                    Text(String.localizeId.account_logout_button())
                        .font(.regularLarge)
                        .foregroundColor(.white)
                        .opacity(0.5)
                }
                .accessibility(.account(.logoutButton))
                Spacer()
                Text(account.appVersion)
                    .font(.regularTiny)
                    .foregroundColor(.white)
                    .opacity(0.5)
                    .accessibility(.account(.versionText))
            }
            .padding(20.0)
        }
        .frame(width: 200.0)
        .accessibilityElement(children: .contain)
        .accessibility(.account(.view))
    }
}
