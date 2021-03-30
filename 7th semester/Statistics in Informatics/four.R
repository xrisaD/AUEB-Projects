smarties <- rep(c('brown','red','yellow','blue','green'), c(22,19,16,15,8))
table(smarties)->t
chisq.test(t, p=(0.196, 0.198, 0.252, 0.178, 0.176))


mandms <- rep(c('brown','red','yellow','blue','green'), c(10,12,20,9,5))
table(mandms)->t2

rbind(t,t2)-> tfinal
t(tfinal)->tfinal
chisq.test(tfinal)