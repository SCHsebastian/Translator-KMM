//
//  ProgressButton.swift
//  iosApp
//
//  Created by Sebastian Cardona Henao on 27/8/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ProgressButton: View {
    var text: String
    var isLoading: Bool
    var onClick: () -> Void
    
    var body: some View {
        Button(
            action: {
                if !isLoading {
                    onClick()
                }
            }
        ){
            if isLoading{
                ProgressView()
                    .animation(.easeInOut, value:  isLoading)
                    .background(Color.primaryColor)
                    .cornerRadius(100)
                    .progressViewStyle(CircularProgressViewStyle(tint: .white))
            } else {
                Text(text.uppercased())
                    .animation(.easeInOut, value:  isLoading)
                    .padding(.horizontal)
                    .padding(.vertical, 5)
                    .font(.body.weight(.bold))
                    .foregroundColor(Color.primaryColor)
                    .cornerRadius(100)
            }
        }
    }
}

#Preview {
    ProgressButton(
        text : "translate",
        isLoading: false,
        onClick: {}
    )
}

#Preview {
    ProgressButton(
        text : "translate",
        isLoading: true,
        onClick: {}
    )
}
