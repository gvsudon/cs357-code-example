//
//  CustomTableCellTableViewCell.swift
//  table-demo
//
//  Created by Hans Dulimarta on 2/16/23.
//

import UIKit

class CustomTableCell: UITableViewCell {

    static let identifier = "MyCustomCell"
    @IBOutlet weak var mainText: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        print(#file)
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

    }

}
