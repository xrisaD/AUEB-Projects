# 9.7
# Least Squares method
# Zip's Law
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import math

#data 
with open('songs_frequency.txt', 'r') as file:
    data = file.read().replace('\n', '')

# general: Y = m*X + c
# Zip's Law: Y=logr, X=logf c=logk

X = [math.log(int(x),10) for x in data.split()]
Y = [math.log(i,10) for i in  range(1,len(X)+1)]
#plt.scatter(X, Y)
#plt.show()

# Compute m,c
# m = (Σ_(i=0->n) (x_i-x_mean)*(y_i-y_mean))/ (Σ_(i=0->n) (x_i-x_mean)^2)
# c = y_mean - m*x_mean

X_mean = np.mean(X)
Y_mean = np.mean(Y)
numerator = 0
denominator = 0
for i in range(len(X)):
    numerator += (X[i] - X_mean)*(Y[i] - Y_mean)
    denominator += (X[i] - X_mean)**2
m = num / den
logk = Y_mean - m*X_mean

print("m = ",m)
print("logk = ", logk)

# line
Y_new = []
for x in X:
  Y_new.append(m*x+logk)

plt.scatter(X, Y)
plt.scatter(X, Y_new, color='red')
plt.plot(X, Y_new,'r')
plt.show()
