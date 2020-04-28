/* 
Find all listings that if they are rented the available days, have profit > 1000 between June and Augoust in 2017  ordered by profit (sum(price))
Output: 5484 rows
*/
SELECT listing_id, SUM(price) AS Total
FROM "Calendar"
WHERE "Calendar".date >= '2017-06-01' AND "Calendar".date < '2017-09-01' AND "Calendar".available='t' 
GROUP BY listing_id
HAVING SUM(price)>1000 
ORDER BY SUM(price) DESC;

/* Find cities that their beds count is more than 100
Output: 3 rows
*/
SELECT city, SUM(beds) AS Beds
FROM "Listing"
GROUP BY city
HAVING SUM(beds)>100;


/*Print listings that have more than 30 amenities ordered by amenities count.
Output:23 rows
*/

SELECT listing_id, COUNT(amenity_id)
FROM "ListingAmenity"
GROUP BY listing_id
HAVING COUNT(amenity_id)>30
ORDER BY COUNT(amenity_id) DESC;

/*Find how many times each amenity is in listings.
Output:52 rows
*/
SELECT am.amenity_id,am.amenity_name,COUNT(la.listing_id) AS CountAmenity
FROM "Amenity" am,"ListingAmenity" la
WHERE am.amenity_id=la.amenity_id
GROUP BY am.amenity_id;

/*Find how many listings each host possess. 
Output:7492 rows
*/
SELECT h.id,h.name,COUNT(l.id) AS SumListing
FROM "Listing" l,"Host" h
WHERE h.id=l.host_id
GROUP BY h.id
ORDER BY h.name;

/*Find all listings in city Lakeway and their average price.
Output:38 rows rows
*/
SELECT l.id,AVG(c.price) AS AveragePrice
FROM "Listing" l,"Calendar" c
WHERE l.id=c.listing_id AND l.city='Lakeway'
GROUP BY l.id;