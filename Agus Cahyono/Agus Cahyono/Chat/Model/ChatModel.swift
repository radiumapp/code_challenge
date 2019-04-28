//  
//  ChatModel.swift
//  Agus Cahyono
//
//  Created by Agus Cahyono on 27/04/19.
//  Copyright © 2019 Agus Cahyono. All rights reserved.
//

import UIKit

enum AttachmentType: String {
    case image = "image"
    case document = "document"
    case contact = "contact"
    case text = ""
}

struct MockChat: Equatable {
    
    let id: Int?
    var timestamp: String?
    let body, attachment, from, to: String?
    
    static func == (lhs: MockChat, rhs: MockChat) -> Bool {
        return lhs.attachment == rhs.attachment && lhs.from != rhs.from && lhs.to != rhs.to
    }
    
}

struct Chats: Codable {
    
    let data: [Chat]?
    
}

struct Chat: Codable {
    
    let id: Int?
    var timestamp: String?
    let body, attachment, from, to: String?
}

