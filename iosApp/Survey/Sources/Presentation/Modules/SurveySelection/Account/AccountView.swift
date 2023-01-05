//
//  AccountView.swift
//  Survey
//
//  Created by Bliss on 26/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Shared
import SwiftUI

// sourcery: AutoMockable
protocol AccountCoordinator {

    func showLogin()
}

struct AccountView: View {

    @StateObject var dataSource: DataSource

    var body: some View {
        ZStack {
            Rectangle()
                .foregroundColor(.black.opacity(0.9))
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .ignoresSafeArea()
            VStack(alignment: .leading) {
                profileSection
                Spacer()
                    .frame(height: 26.0)
                Rectangle()
                    .foregroundColor(.white)
                    .frame(height: 0.5)
                    .opacity(0.2)
                Spacer()
                    .frame(height: 35.0)
                logoutSection
                Spacer()
                versionSection
            }
            .padding(.smallPadding)
        }
        .frame(width: 200.0)
        .accessibilityElement(children: .contain)
        .accessibility(.account(.view))
        .loadingDialog(loading: $dataSource.showingLoading)
    }

    var account: AccountUiModel? { dataSource.viewState.accountUiModel }

    var profileSection: some View {
        HStack(alignment: .firstTextBaseline) {
            Text((account?.name).string)
                .font(.boldLarge)
                .lineLimit(1)
                .accessibility(.account(.profileText))
            Spacer()
            Image.url((account?.avatarUrl).string)
                .resizable()
                .frame(width: 36.0, height: 36.0)
                .cornerRadius(18.0)
                .offset(y: 5.0)
                .accessibility(.account(.profileImage))
        }
        .padding(.top, 8.0)
    }

    var logoutSection: some View {
        Button {
            dataSource.logOut()
        } label: {
            Text(String.localizeId.account_logout_button())
                .font(.regularLarge)
                .foregroundColor(.white)
                .opacity(0.5)
        }
        .accessibility(.account(.logoutButton))
    }

    var versionSection: some View {
        Text((account?.appVersion).string)
            .font(.regularTiny)
            .foregroundColor(.white)
            .opacity(0.5)
            .accessibility(.account(.versionText))
    }

    init(account: AccountUiModel, coordinator: AccountCoordinator) {
        _dataSource = StateObject(
            wrappedValue: DataSource(
                coordinator: coordinator,
                viewModel: AccountViewModel(accountUiModel: account)
            )
        )
    }
}
