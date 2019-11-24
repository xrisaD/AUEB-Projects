#sumptiksi grammwn me skopo thn dhmiourgia twn katalllwn eggrafwn
import csv #no header edition
import collections
def edit(fnamer,fnamew):
    with open(fnamew, mode='w', encoding="utf8") as z:
        with open(fnamer,encoding="utf8") as csv_file: #readfile
            z = csv.writer(z, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
            csvreader = csv.reader(csv_file, delimiter=",")
            line_count = 0
            D=[]
            befrow=[]
            for row in csvreader:
                i=0
                while(i<len(row)-1):
                    D.append(row[i].rstrip())
                    i=i+1
                D.append(str(row[len(row)-1]).replace('\n',''))
                z.writerow(D);
                D=[]
                line_count += 1
#edit('portland_normalizedCSVs\\review.csv','portland_normalizedCSVs\\reviewNEWP.csv')
#edit('denver_normalizedCSVs\\review.csv','denver_normalizedCSVs\\reviewNEWP.csv')
#edit('boston_normalizedCSVs\\review.csv','boston_normalizedCSVs\\reviewNEWP.csv')
