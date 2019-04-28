//
//  GridImageCollectionViewCell.swift
//  Agus Cahyono
//
//  Created by Agus Cahyono on 27/04/19.
//  Copyright © 2019 Agus Cahyono. All rights reserved.
//

import UIKit

class GridImageCollectionViewCell: UICollectionViewCell {
    
    @IBOutlet weak var image: UIImageView!
    
    
    class var reusableIndentifer: String { return String(describing: self) }
    
    static func reUse() -> UINib {
        return UINib(nibName: self.reusableIndentifer, bundle: Bundle.main)
    }

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

}
