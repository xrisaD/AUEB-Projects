getwd()
data<-read.table("stat//grades_2014_data.txt", sep="\t", col.names=c("INITIALS", "SEX","CLASS","MIDTERM","FINAL","SCORE","GRADE"), fill=FALSE, strip.white=TRUE,header=TRUE)
attach(data)
plot(MIDTERM,GRADE,main="Scatterplot")
model<-lm(GRADE~MIDTERM)
abline(model, col="red")
plot(residuals(model))
abline(0,0)
qqnorm(residuals(model))

model
confint(model)
summary(model)

predict(model, newdata=data.frame(MIDTERM=7), interval="confidence")
predict(model, newdata=data.frame(MIDTERM=7), interval="prediction")
