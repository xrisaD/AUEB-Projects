getwd()
data<-read.table("stat/survey_data_2020/survey_data_2020.txt", header=TRUE, sep='\t')

plot(data$height, data$shoe, main="Scatterplot 3a")

# outlier deletion
ish = (data$shoe>400)
ih = (data$height>150)
data = data[!(ish|ih),]

plot(data$height, data$shoe, main="Scatterplot 3a")

cor(data$height, data$shoe, use="complete.obs")

dhs <- data[!is.na(data$shoe) & !is.na(data$height),]
plot(dhs$height~dhs$shoe, xlab="height", ylab="shoe", main="Linear least-squares regression")

model <-lm(height~shoe, data=dhs)
coefficients(model)
coef(model)
abline(model)




prob<-data$prob
math<-data$math
prob<-prob[!is.na(prob)]
math<-math[!is.na(math)]


women <- data$height[data$sex=='F']
men <- data$prob[data$sex=='M']
women<-women[!is.na(women)]
men<-men[!is.na(men)]

