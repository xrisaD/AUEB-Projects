
bikesInfo <- read.csv(file = 'stat/train.csv',header=TRUE)
bikesInfo

attach(bikesInfo)

#a)
# link + periptoseis:12165
nrow(bikesInfo)

#b) 
# Perigrafi twn metavlitwn pou tha xrisimopoihsoume:

# season | season (1:winter, 2:spring, 3:summer, 4:fall) KATHGORIKI
# temp | Normalized feeling temperature in Celsius. POSOTIKI
# registered | count of registered users POSOTIKI
# cnt | count of total rental bikes including both casual and registered POSOTIKI

#c)
#season
# Count number of occurences for each unique value
x = table(season)

labels = c("winter","spring","summer","fall")
pie(x, labels = labels , main="Pie Chart of Seasons")

# temp

hist(temp, main="Histogram of Temperature")

#registered

hist(registered,main="Histogram of registered users")

# cnt
hist(cnt,main="Histogram of total rental bikes")

#d
# temp
mean(temp)
sd(temp)
var(temp)
fivenum(temp)
summary(temp)
#mean,sd epeidh sxedon kanoniki katanomi, poy einai omoiomorfi

#registered

mean(registered)
sd(registered)
var(registered)
fivenum(registered)
summary(registered)
#fivenum, epeidh oxi summetriki katanomh, xreiazomaste perisoteri pliroforia
#apo afti pou mas parexei to mean,sd

# cnt
mean(cnt)
sd(cnt)
var(cnt)
fivenum(cnt)
summary(cnt)

#fivenum, epeidh oxi summetriki katanomh, xreiazomaste perisoteri pliroforia
#apo afti pou mas parexei to mean,sd


# epilegw season - cnt 
# h season epireazei to posa podilata enoikiazonta?


boxplot(cnt~season,names=labels)

#paratirisi, twn xeimwna ligoteres enoikiaseis podilatwn apoles tis epoxes
#einai aitiati to 
