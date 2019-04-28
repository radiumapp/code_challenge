//
//  ContactGroupMessageOtherCellTableViewCell.swift
//  Agus Cahyono
//
//  Created by Agus Cahyono on 27/04/19.
//  Copyright © 2019 Agus Cahyono. All rights reserved.
//

import UIKit

class ContactGroupMessageOtherCellTableViewCell: UITableViewCell {
    
    @IBOutlet weak var gridContacts: DynamicCollectionView!
    
    class var reusableIndentifer: String { return String(describing: self) }
    
    static func reUse() -> UINib {
        return UINib(nibName: self.reusableIndentifer, bundle: Bundle.main)
    }
    
    var countData = 0

    override func awakeFromNib() {
        super.awakeFromNib()
        
        gridContacts.dataSource = self
        gridContacts.delegate = self
        
        gridContacts.register(ContactMessageCollectionViewCell.reUse(), forCellWithReuseIdentifier: ContactMessageCollectionViewCell.reusableIndentifer)
        
        gridContacts.backgroundColor = .clear
        gridContacts.isUserInteractionEnabled = true
        gridContacts.showsHorizontalScrollIndicator = false
        gridContacts.showsVerticalScrollIndicator =  false
        gridContacts.isScrollEnabled = false
        gridContacts.clipsToBounds = true
        gridContacts.contentInset = UIEdgeInsets.init(top: 0, left: 8, bottom: 8, right: 8)
        
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}


extension ContactGroupMessageOtherCellTableViewCell: UICollectionViewDelegateFlowLayout {
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        
        let padding: CGFloat =  18
        let collectionViewSize = collectionView.frame.size.width - padding
        
        return CGSize(width: collectionViewSize / 2, height: collectionViewSize/2)
        
    }
    
}

extension ContactGroupMessageOtherCellTableViewCell: UICollectionViewDelegate, UICollectionViewDataSource {
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return countData
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        let colls = collectionView.dequeueReusableCell(withReuseIdentifier: ContactMessageCollectionViewCell.reusableIndentifer, for: indexPath) as! ContactMessageCollectionViewCell
        
        return colls
        
    }
    
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        print("KEPILIH")
    }
    
}
