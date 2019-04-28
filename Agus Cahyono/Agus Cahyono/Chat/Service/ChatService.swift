//  
//  ChatService.swift
//  Agus Cahyono
//
//  Created by Agus Cahyono on 27/04/19.
//  Copyright © 2019 Agus Cahyono. All rights reserved.
//

import Foundation

class ChatService: ChatServiceProtocol {
    
    
    /// FETCH LIST OF CHAT
    ///
    /// - Parameters:
    ///   - success: success description
    ///   - failure: failure description
    func didFetchChat(success: @escaping (Chats) -> Void, failure: @escaping (String) -> Void) {
        
        do {
            
            let url = Bundle.main.url(forResource: "message_dataset", withExtension: "json")!
            let data = try Data(contentsOf: url)
            let res = try JSONDecoder().decode(Chats.self, from: data)
            success(res)
            
        }
        catch {
            failure(error.localizedDescription)
        }
        
    }
    
}
