//
//  TextToSpeech.swift
//  iosApp
//
//  Created by Sebastian Cardona Henao on 28/8/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import AVFoundation

struct TextToSpeech {
    
    private let sythesizer = AVSpeechSynthesizer()
    
    func speak(text: String, language: String){
        let utterance = AVSpeechUtterance(string: text)
        utterance.voice = AVSpeechSynthesisVoice(language: language)
        utterance.volume = 1
        sythesizer.speak(utterance)
    }
    
}
