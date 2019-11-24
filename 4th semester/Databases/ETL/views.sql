-- Austin_v_Revenue_Crossover
-- records: 530


CREATE VIEW Austin_v_Revenue_Crossover AS
    SELECT z.date,z.zipcode,l.bedrooms,percentile_cont(0.5) within group (order by CASE WHEN c.price IS NULL THEN l.price
																						ELSE c.price 
																					END) 
	airbnb_price_day,z.price zillow_price_month,ceiling(z.price/percentile_cont(0.5) within group (order by CASE WHEN c.price IS NULL THEN l.price
																						ELSE c.price 
																					END) ) crossover_pt
    FROM "ZilP" z,"Calendar" c,"Listing" l
    WHERE  (z.bedroom IS NOT NULL ) AND (l.bedrooms IS NOT NULL ) AND (z.zipcode IS NOT NULL ) 
	AND (l.neighbourhood_cleansed IS NOT NULL ) AND (z.date IS NOT NULL ) AND (c.date IS NOT NULL ) 
	AND date_trunc('day',c.date) = z.date AND z.city = 'Austin'  AND l.room_type='Entire home/apt' 
	AND l.id=c.listing_id AND l.bedrooms>0 AND l.neighbourhood_cleansed=z.zipcode 
	AND (l.bedrooms=z.bedroom OR (l.bedrooms>5 AND z.bedroom=5))
		GROUP BY z.date,z.zipcode,l.bedrooms,z.price;

		
-- Boston_v_Revenue_Crossover
-- records: 828	
		
CREATE VIEW Boston_v_Revenue_Crossover AS
    SELECT z.date,z.zipcode,l.bedrooms,percentile_cont(0.5) within group (order by CASE WHEN c.price IS NULL THEN l.price
																						ELSE c.price 
																					END) 
	airbnb_price_day,z.price zillow_price_month,ceiling(z.price/percentile_cont(0.5) within group (order by CASE WHEN c.price IS NULL THEN l.price
																						ELSE c.price 
																					END) ) crossover_pt
    FROM "ZilP" z,"Calendar" c,"Listing" l
    WHERE  (z.bedroom IS NOT NULL ) AND (l.bedrooms IS NOT NULL ) AND (z.zipcode IS NOT NULL ) 
	AND (l.neighbourhood_cleansed IS NOT NULL ) AND (z.date IS NOT NULL ) AND (c.date IS NOT NULL ) 
	AND date_trunc('day',c.date) = z.date AND z.city = 'Boston'  AND l.room_type='Entire home/apt' 
	AND l.id=c.listing_id AND l.bedrooms>0 AND l.neighbourhood_cleansed=z.zipcode 
	AND (l.bedrooms=z.bedroom OR (l.bedrooms>5 AND z.bedroom=5))
		GROUP BY z.date,z.zipcode,l.bedrooms,z.price;
		
		
-- Denver_v_Revenue_Crossover
-- records: 360
		
CREATE VIEW Denver_v_Revenue_Crossover AS
    SELECT z.date,z.zipcode,l.bedrooms,percentile_cont(0.5) within group (order by CASE WHEN c.price IS NULL THEN l.price
																						ELSE c.price 
																					END) 
	airbnb_price_day,z.price zillow_price_month,ceiling(z.price/percentile_cont(0.5) within group (order by CASE WHEN c.price IS NULL THEN l.price
																						ELSE c.price 
																					END) ) crossover_pt
    FROM "ZilP" z,"Calendar" c,"Listing" l
    WHERE  (z.bedroom IS NOT NULL ) AND (l.bedrooms IS NOT NULL ) AND (z.zipcode IS NOT NULL ) 
	AND (l.neighbourhood_cleansed IS NOT NULL ) AND (z.date IS NOT NULL ) AND (c.date IS NOT NULL ) 
	AND date_trunc('day',c.date) = z.date AND z.city = 'Denver'  AND l.room_type='Entire home/apt' 
	AND l.id=c.listing_id AND l.bedrooms>0 AND l.neighbourhood_cleansed=z.zipcode 
	AND (l.bedrooms=z.bedroom OR (l.bedrooms>5 AND z.bedroom=5))
		GROUP BY z.date,z.zipcode,l.bedrooms,z.price;

-- Portland_v_Revenue_Crossover
-- records: 252	
		
CREATE VIEW Portland_v_Revenue_Crossover AS
    SELECT z.date,z.zipcode,l.bedrooms,percentile_cont(0.5) within group (order by CASE WHEN c.price IS NULL THEN l.price
																						ELSE c.price 
																					END) 
	airbnb_price_day,z.price zillow_price_month,ceiling(z.price/percentile_cont(0.5) within group (order by CASE WHEN c.price IS NULL THEN l.price
																						ELSE c.price 
																					END) ) crossover_pt
    FROM "ZilP" z,"Calendar" c,"Listing" l
    WHERE  (z.bedroom IS NOT NULL ) AND (l.bedrooms IS NOT NULL ) AND (z.zipcode IS NOT NULL ) 
	AND (l.neighbourhood_cleansed IS NOT NULL ) AND (z.date IS NOT NULL ) AND (c.date IS NOT NULL ) 
	AND date_trunc('day',c.date) = z.date AND z.city = 'Portland'  AND l.room_type='Entire home/apt' 
	AND l.id=c.listing_id AND l.bedrooms>0 AND l.neighbourhood_cleansed=z.zipcode 
	AND (l.bedrooms=z.bedroom OR (l.bedrooms>5 AND z.bedroom=5))
		GROUP BY z.date,z.zipcode,l.bedrooms,z.price;	