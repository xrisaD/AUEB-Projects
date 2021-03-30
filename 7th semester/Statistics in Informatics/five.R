s<-c(500, 1550, 1250, 1300, 750, 1000, 1250, 1300, 800, 2500)
e<-c(400, 1500, 1300, 1300, 800, 800, 1000, 1100, 650, 2200)

v<-c(80,81,75,83,71,73,65,67,54,77,55,83,91,6,92,86,73,82,69,73,70,59,68,72,72)
f<-c('a','a','a','a','g','a','g','g','g','a','g','g','a','g','a','a','g','g','g','a','g','g','a','a','a')
k<-c(TRUE,FALSE,FALSE,TRUE,TRUE,TRUE,TRUE,FALSE,FALSE,TRUE,FALSE,FALSE,FALSE,FALSE,TRUE,TRUE,FALSE,TRUE,FALSE,FALSE,FALSE,TRUE,FALSE,FALSE,FALSE)
data<-data.frame(gender=f,smoker=k,weight=v)

data[-14,]->data

x<-data$weight
n<-length(x)
df<-n-1
abs(qt(0.025, df=df))->t
mean(x)+c(-1,1)*t*sd(x)/sqrt(n)


men=data[data$gender=="a",]
women=data[data$gender=="g",]

nw<-nrow(women)
nm<-nrow(men)
df<-min(c(nw,nm))-1
abs(qt(0.1, df=df))->t

mean_x<-mean(men$weight)-mean(women$weight)

sd_n<-(sd(men$weight)^2/nm)+(sd(women$weight)^2/nw)

mean_x+c(-1,1)*t*sqrt(sd_n)



smokers=data[data$smoker==TRUE,]
nonsmokers=data[data$smoker==FALSE,]

ns<-nrow(smokers)
nn<-nrow(nonsmokers)
df<-min(c(nn,ns))-1
mean_x<-mean(smokers$weight)-mean(nonsmokers$weight)
sd_n<-(sd(smokers$weight)^2/ns)+(sd(women$weight)^2/nn)

t<-mean_x/sqrt(sd_n)

2*pt(-abs(t), df=df)
