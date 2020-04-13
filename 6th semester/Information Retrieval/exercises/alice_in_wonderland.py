#9.4 i)
#!pip install -U nltk

#download book: Alice in Wonderland
import nltk
nltk.download('gutenberg')
from nltk.corpus import gutenberg
from nltk.text import Text
from pprint import pprint
alice = Text(nltk.corpus.gutenberg.words('carroll-alice.txt'))

#find more freq words
count = nltk.FreqDist(alice)
#create rank
sorted_words = sorted([(word,count[word]) for word in count],key=lambda x: x[::-1], reverse=True)
ranks = {}
for w,r in zip(sorted_words,range(1,len(count))):
  ranks[w[0]] = r
#25 most frequence words
most_freq = count.most_common(26)
#pprint(most_freq)

#25 most frequence words which start with 'c'
most_freq_c = sorted([(word,count[word]) for word in count if word.startswith('c') or word.startswith('C')],key=lambda x: x[::-1], reverse=True)[0:24]
#pprint(most_freq_c)

#print
allWords = most_freq + most_freq_c
k = count.most_common(1)[0][1]
thres = 100
sum = 0
for w in allWords:
    print(" Word: " + w[0] + " Frequency: " + str(w[1]) + " Rank: " + str(ranks[w[0]]) + " Probability: "+ str(w[1]/len(alice)) + " Rank*Prob = "+ str(rank*(w[1]/len(alice))))
    #check Zip's Law
    print(str(k/ranks[w[0]]))
    if(k/ranks[w[0]] > w[1] - thres and  k/ranks[w[0]]**m < w[1] + thres):
      sum+=1
print("Sum of  words: "+ str(len(alice)))
print("Unique words: "+ str(len(count)))
if(sum==50):
  print("Yes")
else:
  print("No")
