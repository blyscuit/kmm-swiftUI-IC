//
//  Screen.swift
//  Survey
//
//  Created by Bliss on 26/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

enum Screen {

    case login
    case splash
    case resetPassword
    case surveySelection
    case surveyDetail(_ parameters: ScreenParameters.SurveyDetail)
}
