#4.4 exercise
import re

with open('doc_with_acronyms.txt','r') as file:
    doc = file.read().replace('\n',' ')

#find acronyms
acronyms = re.findall("(([Α-Ω].)+[Α-Ω])",doc)
acronyms_finally = [tuple(i)[0] for i in acronyms]

print(acronyms_finally)
