#exercise 4.6
import re
import calendar

#read file and create a string
with open('document.txt', 'r') as file:
    doc = file.read().replace('\n', ' ')

#create regex with months
months = ""
for i in range(1,13):
    month = calendar.month_name[i]
    month_short_form = month[:3]
    
    if(i==12):
        months += month + "|" + month_short_form
        break
    months += month + "|" + month_short_form + "|"

#find dates 
dates_type_1 = re.findall("(([0-9]|[0-2][0-9]|3[0-1]) ("+months+") \d+)", doc)
dates_type_2 = re.findall("(([0-9]|[0-2][0-9]|3[0-1])(-|/)([0-9]|0[0-9]|1[0-2])(-|/)\d+)"
               , doc)
dates_1 = [tuple(i)[0] for i in dates_type_1]
dates_2 = [tuple(i)[0] for i in dates_type_2]

#print dates
print(dates_1)
print(dates_2)

