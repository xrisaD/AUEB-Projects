#tropopoihsh ths sthlhs zipcode wste na periexei to idio to zipcode kai oxi to onoma ths geitonias
#ta dedomena gia to zipcode prohlthan apo to pinaka neighborhood
import csv #no header edition
import collections

ZC={} #neighname,zipcode
def createTable(fnamer):
    with open(fnamer,encoding="utf8") as csv_file: #readfile
        csvreader = csv.reader(csv_file, delimiter=",")
        line_count = 0
        for row in csvreader:
            if(line_count==0):#header
                line_count += 1
            else:
                ZC[str(row[0])]=row[1]
                line_count += 1
def createCSV(fnamer,fnamew):
    with open(fnamer,encoding="utf8") as csv_file:
        with open(fnamew, mode='w', encoding="utf8",newline='') as z:
            z = csv.writer(z, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
            csvreader = csv.reader(csv_file, delimiter=",")
            line_count = 0
            for row in csvreader:
                if(line_count==0):#header
                    line_count += 1
                else:
                    line_count += 1
                    if row[4] in ZC:
                        row[4]=ZC[str(row[4])]
                        z.writerow(row)
                    else:
                        row[4]=''
                        z.writerow(row)
                        
#createTable('portland_normalizedCSVs\\neighborhood.csv')
#createCSV('portland_normalizedCSVs\\summary_listing.csv','portland_normalizedCSVs\\summary_listingNEWP.csv');

#createTable('denver_normalizedCSVs\\neighborhood.csv')
#createCSV('denver_normalizedCSVs\\summary_listing.csv','denver_normalizedCSVs\\summary_listingNEWP.csv');

#createTable('boston_normalizedCSVs\\neighborhood.csv')
#createCSV('boston_normalizedCSVs\\summary_listing.csv','boston_normalizedCSVs\\summary_listingNEWP.csv');

