//
//  SmallLanguageIcon.swift
//  iosApp
//
//  Created by Sebastian Cardona Henao on 23/8/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SmallLanguageIcon: View {
    var language: LanguageDecorator
    var body: some View {
        Image(uiImage: UIImage(named: language.imageName.lowercased())!)
            .resizable()
            .frame(width: 30, height: 30)
    }
}

#Preview {
    SmallLanguageIcon(language: LanguageDecorator.companion.byCode(langCode: "es"))
}
