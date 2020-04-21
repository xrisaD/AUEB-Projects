#9.4 Zip's Law Exercise
#!pip install -U nltk

import nltk
nltk.download('gutenberg')
from nltk.corpus import gutenberg
from nltk.text import Text
from nltk.tokenize import WhitespaceTokenizer
from pprint import pprint
import matplotlib.pyplot as plt

#Tokenization
whitespace_wt = WhitespaceTokenizer()

#download book: Alice in Wonderland
alice = whitespace_wt.tokenize(nltk.corpus.gutenberg.raw('carroll-alice.txt'))

#find freq words
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
    print(" Word: " + w[0] + " Frequency: " + str(w[1]) + " Rank: " + str(ranks[w[0]]) + " Probability: "+ str(w[1]/len(alice)) + " Rank*Prob = "+ str(ranks[w[0]]*(w[1]/len(alice))))
len_alice = len(alice)
print("Sum of  words: "+ str(len_alice))
print("Unique words: "+ str(len(count)))

#compute f = k*r^m
freqs = []
for w in count.most_common(50):
  freqs.append(w[1])
plt.plot(range(1,51), freqs)
plt.show()

#athroistiki pithanothta emfanisis
x = [] 
y = []
x.append(freqs[0])
y.append((x[0]/len_alice)*100)
for i in range(1,50):
  x.append(x[i-1]+freqs[i])
  y.append((x[i]/len_alice)*100)

plt.plot(x, y,'--')
plt.show()

#Compute k
y2 = []
for w,r in zip(sorted_words,range(1,len(count))):
  y2.append(int(w[1])*r)
plt.plot(range(1,len(count)), y2)
plt.show()
