#ETL function
#rename regionname to zipcode
#add '-01'
#delete before 2016
import csv
import collections
def edit(fnamer,fnamew):
    D=[]
    END=[]
    with open(fnamew, mode='w') as z:
        with open(fnamer) as csv_file:
            z = csv.writer(z, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
            csv_reader = csv.reader(csv_file, delimiter=',')
            si=0
            j=0
            line_count = 0
            for row in csv_reader:
                if line_count == 0:#edit header
                    line_count += 1
                    first="zipcode"
                    i=6
                    firstIN=True
                    while(i<len(row)):
                        if(int(row[i].rstrip()[:4])>=2016):
                            D.append(row[i]+"-01")
                            j=j+1
                            if(firstIN):
                                firstIN=False
                                si=i
                        i=i+1
                    END.append(first);
                    ii=1
                    while(ii<6):#other expect dates
                        END.append(row[ii]);
                        ii = ii + 1
                    END.extend(D)
                    z.writerow(END);
                else:
                    line_count += 1
                    i=0
                    newRow=[]
                    while(i<len(row)):
                        if(i<6 or i>=si):
                            newRow.append(row[i])
                        i= i + 1
                    z.writerow(newRow);
#edit('Zip_MedianRentalPrice_1Bedroom.csv','Zip_MedianRentalPrice_1BedroomNEW.csv')
#edit('Zip_MedianRentalPrice_2Bedroom.csv','Zip_MedianRentalPrice_3BedroomNEW.csv')
#edit('Zip_MedianRentalPrice_3Bedroom.csv','Zip_MedianRentalPrice_3BedroomNEW.csv')
#edit('Zip_MedianRentalPrice_4Bedroom.csv','Zip_MedianRentalPrice_4BedroomNEW.csv')
#edit('Zip_MedianRentalPrice_5BedroomOrMore.csv','Zip_MedianRentalPrice_5BedroomOrmoreNEW.csv')
