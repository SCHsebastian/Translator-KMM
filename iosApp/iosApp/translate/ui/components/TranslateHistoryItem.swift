//
//  TranslateHistoryItem.swift
//  iosApp
//
//  Created by Sebastian Cardona Henao on 28/8/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct TranslateHistoryItem: View {
    
    let item: HistoryItemDecorator
    let onClick: () -> Void
    
    var body: some View {
        Button(action: onClick) {
            VStack(alignment: .leading) {
                HStack {
                    SmallLanguageIcon(language: item.fromLang)
                        .padding(.trailing)
                    Text(item.fromText)
                        .foregroundColor(.lightBlue)
                        .font(.body)
                }
                .padding()
                .frame(maxWidth: .infinity, alignment: .leading)
                Divider()
                    .padding()
                HStack {
                    SmallLanguageIcon(language: item.toLang)
                        .padding(.trailing)
                    Text(item.toText)
                        .foregroundColor(.onSurface)
                        .font(.body)
                }
                .padding()
                .frame(maxWidth: .infinity, alignment: .leading)
                
            }
            .frame(maxWidth: .infinity)
            .padding()
            .gradientSurface()
            .cornerRadius(15)
            .shadow(radius: 4)
        }
    }
}

#Preview {
    TranslateHistoryItem(
        item: HistoryItemDecorator(
            id: 0,
            fromText: "Hola",
            toText: "Hi",
            fromLang: LanguageDecorator.companion.byCode(langCode: "es"),
            toLang: LanguageDecorator.companion.byCode(langCode: "en")
        ),
        onClick: {}
    )
}
