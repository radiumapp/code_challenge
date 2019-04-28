//
//  DocumentMessageCell.swift
//  Agus Cahyono
//
//  Created by Agus Cahyono on 27/04/19.
//  Copyright © 2019 Agus Cahyono. All rights reserved.
//

import UIKit

class DocumentMessageCell: UITableViewCell {

    let dateFormatter: DateFormatter = {
        let formatter = DateFormatter()
        formatter.dateFormat = "dd/MM/yyyy"
        return formatter
    }()
    
    
    @IBOutlet weak var profileImageView: UIImageView!
    @IBOutlet weak var incomingMessageLabel: UILabel!
    @IBOutlet weak var incomingCreatedAtLabel: UILabel!
    @IBOutlet weak var containerIncoming: UIView!
    
    
    @IBOutlet weak var containerOutgoing: UIView!
    @IBOutlet weak var outgoingMessageLabel: UILabel!
    @IBOutlet weak var outgoingCreatedAtLabel: UILabel!
    
    class var reusableIndentifer: String { return String(describing: self) }
    
    static func reUse() -> UINib {
        return UINib(nibName: self.reusableIndentifer, bundle: Bundle.main)
    }
    
    var isIncoming: Bool = false {
        didSet {
            if isIncoming {
                containerIncoming.isHidden = false
                containerOutgoing.isHidden = true
                profileImageView.isHidden = false
                incomingMessageLabel.isHidden = false
                incomingCreatedAtLabel.isHidden = false
                outgoingMessageLabel.isHidden = true
                outgoingCreatedAtLabel.isHidden = true
            } else {
                containerIncoming.isHidden = true
                containerOutgoing.isHidden = false
                profileImageView.isHidden = true
                incomingMessageLabel.isHidden = true
                incomingCreatedAtLabel.isHidden = true
                outgoingMessageLabel.isHidden = false
                outgoingCreatedAtLabel.isHidden = false
            }
        }
    }
    
    var message: String = "" {
        didSet {
            if isIncoming {
                incomingMessageLabel.text = message
            } else {
                outgoingMessageLabel.text = message
            }
        }
    }
    
    var messageTime: String = "" {
        didSet {
            if isIncoming {
                incomingCreatedAtLabel.text = messageTime
            } else {
                outgoingCreatedAtLabel.text = messageTime
            }
        }
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    func setup(_ message: Chat) {
        
        if message.from == "B" {
            self.isIncoming = true
        } else {
            self.isIncoming = false
        }
        
//        self.message = message.body ?? ""
        if let firstMessage = message.timestamp {
            self.messageTime = dateFormatter.string(from: Date().backToDateFromString(string: firstMessage))
        }
    }
    
}
