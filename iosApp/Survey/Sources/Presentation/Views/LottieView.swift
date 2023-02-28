//
//  LottieView.swift
//  Survey
//
//  Created by Bliss on 15/2/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Lottie
import SwiftUI

struct LottieView: UIViewRepresentable {

    let fileName: String
    let completion: () -> Void

    private let animationView = LottieAnimationView()

    func makeUIView(context _: Context) -> some UIView {
        let view = UIView()

        animationView.animation = LottieAnimation.named(fileName)
        animationView.contentMode = .scaleAspectFit
        animationView.play { if $0 { completion() } }

        view.addSubview(animationView)

        animationView.translatesAutoresizingMaskIntoConstraints = false
        animationView.heightAnchor.constraint(equalTo: view.heightAnchor).isActive = true
        animationView.widthAnchor.constraint(equalTo: view.widthAnchor).isActive = true

        return view
    }

    func updateUIView(_: UIViewType, context _: Context) {}
}
