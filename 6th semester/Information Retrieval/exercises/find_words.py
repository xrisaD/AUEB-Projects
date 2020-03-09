#exersice 4.9
import sys
import copy

#split sentence
sentence = "Hello world".lower()
worlds = sentence.split()

#read file and create a string
with open('document.html', 'r') as file:
    doc = file.read()

#find HTML body
start_index = doc.find("<body>")
end_index = doc.find("</body>")
if(start_index==-1 or end_index==-1):
    sys.exit("Wrong HTML file!")

#HTML tags
first = '<font style="background-color:yellow;">'
sec = '</font>'

#separate HTML parts
new_doc = doc[start_index+7:end_index]
result_html = doc[:start_index+7]

#find words and change their background-color
for w in worlds:
    s = 0
    l_doc = new_doc.lower()
    index = l_doc.find(w,s)
    while(index>=0):
        new_doc = new_doc[:index] + first + new_doc[index:index+len(w)] + sec + new_doc[index+len(w):]
        l_doc = l_doc[:index] + first + l_doc[index:index+len(w)] + sec + l_doc[index+len(w):]
        s = index + len(w) + len(first) + len(sec)
        index = l_doc.find(w,s)
        
#combine HTML parts
new_HTML = result_html + new_doc + doc[end_index:]

#write HTML file
f = open("result_HTML.html", "w")
f.write(new_HTML)
f.close()
