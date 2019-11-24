#edit amenity and listing2amenity
#krata ola t amenities ths polhs Austin 
#perasma twn amenities twn upoloipwn polewn wste na krathsoume ena teliko arxeio me ola ta amenities
#epeksergasia olwn twn listing2amenity me vasi afthn thn antistixisi id-name twn amenities

import csv
import collections
D = {} #name,id
DB={} #old,new
DD={}
DP={}
with open('amenity.csv') as csv_file:
     csv_reader = csv.reader(csv_file, delimiter=',')
     line_count = 0
     for row in csv_reader:
        if line_count == 0:
            line_count += 1
        else:
            line_count += 1
            D[row[1].lower().replace('"',"").replace("'","").rstrip()]=row[0]
            max= int(row[0])
with open('boston_normalizedCSVs\\amenity.csv') as csv_file:
     csv_reader = csv.reader(csv_file, delimiter=',')
     line_count = 0
     for row in csv_reader:
        if line_count == 0:
            line_count += 1
        else:
            line_count += 1
            if row[1].lower().replace('"',"").replace("'","").rstrip() not in D:
                max = max + 1
                D[row[1].lower().replace('"',"").replace("'","").rstrip()]=max
                DB[row[0]]=max
            else:
                DB[row[0]]=D[row[1].lower().replace('"',"").replace("'","").rstrip()]
with open('denver_normalizedCSVs\\amenity.csv') as csv_file:
     csv_reader = csv.reader(csv_file, delimiter=',')
     line_count = 0
     for row in csv_reader:
        if line_count == 0:
            line_count += 1
        else:
            line_count += 1
            if row[1].lower().replace('"',"").replace("'","").rstrip()not  in D:
                max = max + 1
                D[row[1].lower().replace('"',"").rstrip()]=max
                DD[row[0]]=max
            else:
                DD[row[0]]=D[row[1].lower().replace('"',"").replace("'","").rstrip()]
with open('portland_normalizedCSVs\\amenity.csv') as csv_file:
     csv_reader = csv.reader(csv_file, delimiter=',')
     line_count = 0
     for row in csv_reader:
        if line_count == 0:
            line_count += 1
        else:
            line_count += 1
            if row[1].lower().replace('"',"").replace("'","").rstrip() not in D:
                
                max = max + 1
                D[row[1].lower().replace('"',"").replace("'","").rstrip()]=max
                DP[row[0]]=max
            else:
                DP[row[0]]=D[row[1].lower().replace('"',"").replace("'","").rstrip()]
with open('endamenity.csv', mode='w') as endamenity:
    endamenity = csv.writer(endamenity, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
    for key in D.keys():
        endamenity.writerow([D[key],key.rstrip()])

with open('endamenity.csv', mode='w') as endamenity:
    endamenity = csv.writer(endamenity, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
    for key in D.keys():
        endamenity.writerow([D[key],key.rstrip()])

with open('C:\\Users\\Xrisa\\Desktop\\databasesProject\\boston_normalizedCSVs\\listing2amenityNew.csv', mode='w') as lamenity:
    with open('C:\\Users\\Xrisa\\Desktop\\databasesProject\\boston_normalizedCSVs\\listing2amenity.csv') as csv_file:
        lamenity = csv.writer(lamenity, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
        csv_reader = csv.reader(csv_file, delimiter=',')
        for row in csv_reader:
            if line_count == 0:
                line_count += 1
                lamenity.writerow(row)
            else:
                line_count += 1
                if row[1]  in DB:
                    #print(row[1])
                    new=DB[row[1]]
                    lamenity.writerow([row[0],new])
with open('denver_normalizedCSVs\\listing2amenityNew.csv', mode='w') as lamenity:
    with open('denver_normalizedCSVs\\listing2amenity.csv') as csv_file:
        lamenity = csv.writer(lamenity, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
        csv_reader = csv.reader(csv_file, delimiter=',')
        for row in csv_reader:
            if line_count == 0:
                line_count += 1
                lamenity.writerow(row)
            else:
                line_count += 1
                if row[1]  in DD:
                    new=DD[row[1]]
                    lamenity.writerow([row[0],new])
with open('portland_normalizedCSVs\\listing2amenityNew.csv', mode='w') as lamenity:
    with open('portland_normalizedCSVs\\listing2amenity.csv') as csv_file:
        lamenity = csv.writer(lamenity, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
        csv_reader = csv.reader(csv_file, delimiter=',')
        for row in csv_reader:
            if line_count == 0:
                line_count += 1
                lamenity.writerow(row)
            else:
                line_count += 1
                if row[1]  in DP:
                    new=DP[row[1]]
                    lamenity.writerow([row[0],new])
