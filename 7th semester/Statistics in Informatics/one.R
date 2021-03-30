# data I
d1 = c(30.3, 31.0, 31.1, 32.1, 32.6, 32.7, 33.4, 33.6, 34.2, 34.5)

hist(d1, prob=TRUE)
pdf<- density(d1)
lines(pdf)

m = mean(d1)
s = sd(d1)
x <- seq(from=29, to=35, by=0.1)
y<- dnorm(x, mean=m, sd=s)
lines(x,y)

qqnorm(d1)
qqline(d1)

quantile(d1,0.9)
qnorm(0.9,m,s)

quantile(d1,0.1)
qnorm(0.1,m,s)

quantile(d1,0.6)
qnorm(0.6,m,s)


# data II 
d2 = c(0.0, 0.0, 0.2, 0.8, 1.2, 1.4, 3.2, 4.2, 6.4, 9.0)

hist(d2, prob=TRUE)
pdf<- density(d2)
lines(pdf)

m = mean(d2)
s = sd(d2)
x <- seq(from=0, to=10, by=0.1)
y<- dnorm(x, mean=m, sd=s)
lines(x,y)

qqnorm(d2)
qqline(d2)

quantile(d2,0.7)
qnorm(0.7,m,s)

quantile(d2,0.1)
qnorm(0.1,m,s)

quantile(d2,0.6)
qnorm(0.6,m,s)

quantile(d2,0.4)
qnorm(0.4,m,s)


# data III 
d3 = c(0, 1, 6, 8, 10, 13, 15, 16, 17, 17, 18, 18, 20, 20, 21, 25, 26, 30, 35, 39,40, 41, 43, 44, 46, 48, 52, 54, 58, 59, 59, 60, 66, 81, 86, 87, 88, 89, 94, 96)

hist(d3, prob=TRUE)
pdf<- density(d3)
lines(pdf)

m = mean(d3)
s = sd(d3)
x <- seq(from=0, to=97, by=0.1)
y<- dnorm(x, mean=m, sd=s)
lines(x,y)

qqnorm(d3)
qqline(d3)



qqnorm(d3)
qqline(d3)

quantile(d3,0.8)
qnorm(0.8,m,s)

quantile(d3,0.1)
qnorm(0.1,m,s)

quantile(d3,0.6)
qnorm(0.6,m,s)

quantile(d3,0.4)
qnorm(0.4,m,s)
