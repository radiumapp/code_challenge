//
//  ChatView+UITableView.swift
//  Agus Cahyono
//
//  Created by Agus Cahyono on 27/04/19.
//  Copyright © 2019 Agus Cahyono. All rights reserved.
//

import Foundation
import UIKit

extension ChatView: UITableViewDelegate, UITableViewDataSource, GroupImageDelegate {
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 2
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if section == 0 {
             return self.count
        } else {
            return self.count
        }
    }
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return "SECTION \(section)"
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 48
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let message = self.viewModel.chat(indexPath.row)
        
        if indexPath.section == 0 {
            if message.attachment == AttachmentType.image.rawValue {
                let imagecell = tableView.dequeueReusableCell(withIdentifier: ImageMessageCell.reusableIndentifer, for: indexPath) as! ImageMessageCell
                imagecell.setup(message)
                return imagecell
            } else if message.attachment == AttachmentType.document.rawValue {
                let documentCell = tableView.dequeueReusableCell(withIdentifier: DocumentMessageCell.reusableIndentifer, for: indexPath) as! DocumentMessageCell
                documentCell.setup(message)
                return documentCell
            } else if message.attachment == AttachmentType.contact.rawValue {

                if message.from == "A" {
                    let contactCell = tableView.dequeueReusableCell(withIdentifier: ContactGroupMessageCell.reusableIndentifer, for: indexPath) as! ContactGroupMessageCell
                    contactCell.countData = 1
                    return contactCell
                } else {
                    let contactCell = tableView.dequeueReusableCell(withIdentifier: ContactGroupMessageOtherCellTableViewCell.reusableIndentifer, for: indexPath) as! ContactGroupMessageOtherCellTableViewCell
                    contactCell.countData = 4
                    return contactCell
                }

            } else {
                let cell = tableView.dequeueReusableCell(withIdentifier: MessageCell.reusableIndentifer, for: indexPath) as! MessageCell
                cell.setup(message)
                return cell
            }
        } else {
            if message.attachment == AttachmentType.image.rawValue {
                if message.from == "A" {
                    let imagecell = tableView.dequeueReusableCell(withIdentifier: GroupImageTableViewCell.reusableIndentifer, for: indexPath) as! GroupImageTableViewCell
                    imagecell.countData = 4
                    return imagecell
                } else {
                    let imagecell = tableView.dequeueReusableCell(withIdentifier: GroupImageMessageOtherCell.reusableIndentifer, for: indexPath) as! GroupImageMessageOtherCell
                    imagecell.countData = 4
                    return imagecell
                }

            } else if message.attachment == AttachmentType.document.rawValue {
                let documentCell = tableView.dequeueReusableCell(withIdentifier: DocumentMessageCell.reusableIndentifer, for: indexPath) as! DocumentMessageCell
                documentCell.setup(message)
                return documentCell
            } else if message.attachment == AttachmentType.contact.rawValue {
                if message.from == "A" {
                    let contactCell = tableView.dequeueReusableCell(withIdentifier: ContactGroupMessageCell.reusableIndentifer, for: indexPath) as! ContactGroupMessageCell
                    contactCell.countData = 4
                    return contactCell
                } else {
                    let contactCell = tableView.dequeueReusableCell(withIdentifier: ContactGroupMessageOtherCellTableViewCell.reusableIndentifer, for: indexPath) as! ContactGroupMessageOtherCellTableViewCell
                    contactCell.countData = 4
                    return contactCell
                }

            } else {
                let cell = tableView.dequeueReusableCell(withIdentifier: MessageCell.reusableIndentifer, for: indexPath) as! MessageCell
                cell.setup(message)
                return cell
            }
        }
        
        
        
    }
    
    func didSelectedOn(_ index: Int, image: UIImage?) {
        print("index selected: \(index)")
    }
    
    
}
