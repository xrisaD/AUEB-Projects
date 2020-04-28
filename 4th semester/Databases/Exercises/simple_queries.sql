/*
	Find all houses in Austin city they are available between June and Augoust in 2017.
	Output: 6490 rows
	*/
	SELECT DISTINCT "Listings".id,"Listings".name, "Listings".listing_url FROM "Listings" JOIN "Calendar" ON "Listings".id="Calendar".listing_id WHERE "Calendar".date >= '2017-06-01' AND "Calendar".date < '2017-09-01' AND "Calendar".available='t' AND "Listings".city='Austin' ORDER BY "Listings".name;
/*
	Find all available days of Listing:1068507 in Febrouary 2017.
	Output: 31 rows
	*/
	SELECT "Calendar".date FROM "Listings", "Calendar" WHERE "Listings".id="Calendar".listing_id AND "Calendar".available = 't' AND "Calendar".date >= '2017-12-01' AND "Calendar".date < '2018-01-01' AND "Listings".id = 1068507 ;
/*
	Find all houses that Hannah has reviewed.
	Output:  295 rows
	*/
	SELECT "Listings".name, "Reviews".comments  FROM "Listings", "Reviews" WHERE "Listings".id = "Reviews".listing_id AND "Reviews".reviewer_name='Hannah' ORDER BY "Listings".name;
/*
	Find 100 most expensive houses that are available in the date='2017-06-01'. 
	Output: 100 rows
	*/
	SELECT "Listings".id,"Listings".name, "Listings".listing_url , "Listings".price FROM "Listings" JOIN "Calendar" ON "Listings".id="Calendar".listing_id WHERE "Calendar".date = '2017-06-01' AND "Calendar".available='t' ORDER BY CAST(SUBSTRING(REPLACE("Listings".price,',',''), 2, length("Listings".price)) AS DOUBLE PRECISION) DESC LIMIT 100;
/*
	Find all reviews for house with id=2583106
	Output: 31 rows
	*/
	SELECT "Reviews".reviewer_name , "Reviews".comments FROM "Listings", "Reviews" WHERE "Listings".id = "Reviews".listing_id AND "Reviews".listing_id=2583106;

/*
	Find all distinct available houses where price is lower than 100 between the months June and August.
	Output:  2099  rows
	*/
	SELECT DISTINCT "Listings".id,"Listings".name, "Listings".price FROM "Listings" JOIN "Calendar" ON "Listings".id="Calendar".listing_id WHERE "Calendar".date >= '2017-06-01' AND "Calendar".date < '2017-09-01' AND "Calendar".available='t' AND CAST(SUBSTRING(REPLACE("Listings".price,',',''), 2, length("Listings".price)) AS DOUBLE PRECISION) <100.00;
/*
	Find all available distinct listings with price between 200 and 400 order by price in London.		
	Output: 1681  rows
	*/
	SELECT DISTINCT "Listings".id, "Listings".price FROM "Listings", "Calendar" WHERE "Listings".id="Calendar".listing_id AND "Calendar".available='t' AND "Listings".city='Austin' AND CAST(SUBSTRING(REPLACE("Listings".price,',',''), 2, length("Listings".price)) AS DOUBLE PRECISION)>=200 AND CAST(SUBSTRING(REPLACE("Listings".price,',',''), 2, length("Listings".price)) AS DOUBLE PRECISION)<=400;

/*
	Find all reviews for each listing between the year 2014-15 and print even the listings that don't have a review .
	Output: 22039 rows
	*/
	SELECT "Listings".id,"Reviews".date,"Reviews".comments FROM "Listings" LEFT OUTER JOIN "Reviews" ON "Listings".id = "Reviews".listing_id AND "Reviews".date< '2015-01-01' AND  "Reviews".date> '2014-01-01' ;
/*
	Print all calendar writings and the listings that correspond to each calendar writing only if that listing's city is Austin.
	Output: 3526995 rows
	*/
	SELECT "Calendar".date, "Listings".id FROM "Calendar" LEFT OUTER JOIN "Listings" ON "Calendar".listing_id = "Listings".id AND "Listings".city = 'Austin';

/*
	Find all Listings with neighboorhood:78701 	
	Output: 399 rows
	*/
	SELECT "Listings".id,"Listings".neighbourhood_cleansed   FROM "Listings" JOIN "Neighbourhoods" ON  "Listings".neighbourhood_cleansed = "Neighbourhoods".neighbourhood WHERE "Neighbourhoods".neighbourhood =  78701;
	
/*
	Print all calendar writings of host_id 2690825 .		
	Output: 365 rows
	*/
	
	SELECT "Listings".host_id,"Listings".id, "Calendar".date FROM "Listings", "Calendar" WHERE  "Listings".id = "Calendar".listing_id AND "Listings".host_id='2690825' ;
/*
	Find the average price for every city and print 10 most expensive.
	Output: 10 rows
	*/
	SELECT "Listings".city, AVG(CAST(SUBSTRING(REPLACE("Listings".price,',',''), 2, length("Listings".price)) AS DOUBLE PRECISION)) AS AveragePrice FROM "Listings"GROUP BY "Listings".city ORDER BY AveragePrice DESC LIMIT 10 ;
