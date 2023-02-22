//
//  ViewController.swift
//  table-demo
//
//  Created by Hans Dulimarta on 2/13/23.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var myTable: UITableView!
    
    var selectedRow = -1
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        self.myTable.dataSource = self
        self.myTable.delegate  = self
    }

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let dest = segue.destination as? CarDetailsViewController {
            dest.textToShow = "You selected car # \(self.selectedRow + 1)"
        }
    }
}

extension ViewController: UITableViewDataSource {
    func numberOfSections(in tableView: UITableView) -> Int {
        return 3
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if section == 0 {
            return 5
        } else if section == 1 {
            return 15
        } else {
            return 8
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if indexPath.section == 1 {
            // For custom cells we use the traditional technique of accessing
            // the custom cell data directly
            let cell = self.myTable.dequeueReusableCell(withIdentifier: CustomTableCell.identifier, for: indexPath) as! CustomTableCell
            cell.mainText.text = "Fly with bird \(indexPath.row + 1)"
            return cell
        } else {
            // For builtin cells we use the new iOS 14 configuration
            // for updating the cell
            let cell = self.myTable.dequeueReusableCell(withIdentifier: "HansCell", for: indexPath)
            var config = cell.defaultContentConfiguration()
            config.text = "Hello Car \(indexPath.row + 1)"
            config.secondaryText = "City traveller \((indexPath.row + 1) * 10)"
            config.image = UIImage(systemName: "car")
            cell.contentConfiguration = config
            return cell
        }
    }
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        if section == 0 {
            return "Cars For Rent"
        } else if section == 1 {
            return "Ride on Time"
        } else {
            return "Pedal for Medal"
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if indexPath.section == 1 {
            return 64
        } else {
            return 48
        }
    }
}

extension ViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        print("Row \(indexPath.row) is selected")
        if indexPath.section == 0 {
            self.selectedRow = indexPath.row
            performSegue(withIdentifier: "carDetails", sender: self)
        }
    }
}
