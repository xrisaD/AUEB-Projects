
data<-read.table("stat/survey_data_2020/survey_data_2020.txt", header=TRUE, sep='\t')

women <- data$height[data$sex=='F']
men <- data$height[data$sex=='M']
women<-women[!is.na(women)]
men<-men[!is.na(men)]

hist(men)
ih = (men>150)
men = men[!ih]
hist(men)
hist(women)

nw<-length(women)
nm<-length(men)
df<-min(nm,nw)-1
t<-abs(qt(0.025,df=df))

(mean(men)-mean(women))+c(-1,1)*t*sqrt( (sd(women)^2/sqrt(nw)) + (sd(men)^2/sqrt(nm)))


women <- data$prob[data$sex=='F']
men <- data$prob[data$sex=='M']
women<-women[!is.na(women)]
men<-men[!is.na(men)]
nw<-length(women)
nm<-length(men)
df<-min(nw,nm)
t<-(mean(men)-mean(women))/sqrt((sd(men)^2/nm)+(sd(women)^2/nw))
t_star<-qt(0.1,df=df)
t
t_star

prob<-data$prob
math<-data$math
prob<-prob[!is.na(prob)]
math<-math[!is.na(math)]
np<-length(prob)
nm<-length(math)
np
nm
t<(mean(math)-mean(prob))/sqrt((sd(math)^2/nm)+(sd(prob)^2/np))
t
df<-min(np,nm)-1
2*pt(-abs(t),df=df)

