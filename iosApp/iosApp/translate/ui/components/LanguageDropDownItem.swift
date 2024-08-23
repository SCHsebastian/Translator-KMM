//
//  LanguageDropDownItem.swift
//  iosApp
//
//  Created by Sebastian Cardona Henao on 23/8/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LanguageDropDownItem: View {
    var language: LanguageDecorator
    var onClick: () -> Void
    var body: some View {
        Button(action: onClick){
            HStack {
                if let image = UIImage(named: language.imageName.lowercased()){
                    Image(uiImage: image)
                        .resizable()
                        .frame(width: 40, height: 40)
                        .padding(.trailing, 5)
                    Text(language.language.langName).foregroundColor(.textBlack)
                }
            }
        }
    }
    
}

#Preview {
    LanguageDropDownItem(language: LanguageDecorator.companion.byCode(langCode: "es")) {}
}
