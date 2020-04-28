UPDATE "Host"
SET response_rate = REPLACE(response_rate,'%','');

UPDATE "Host"
SET response_rate = NULLIF(response_rate,'N/A');
	
ALTER TABLE "Host"
ALTER COLUMN response_rate TYPE numeric USING response_rate::numeric;

ALTER TABLE "Host"
ADD city VARCHAR(260);

ALTER TABLE "Host"
ADD state VARCHAR(260);

ALTER TABLE "Host"
ADD country VARCHAR(260);

UPDATE "Host"
SET city=SPLIT_PART("Host".location,',',1);

UPDATE "Host"
SET state=SPLIT_PART("Host".location,',',2);

UPDATE "Host"
SET country=SPLIT_PART("Host".location,',',3);

ALTER TABLE "Host"
DROP COLUMN location;

UPDATE "Listing"
SET  amenities = REPLACE(amenities,'{','');

UPDATE "Listing"
SET  amenities = REPLACE(amenities,'}','');

CREATE TABLE "Amenity" (
	amenity_id SERIAL NOT NULL ,
	amenity_name VARCHAR(550)
);

INSERT INTO "Amenity" (amenity_name)
SELECT DISTINCT regexp_split_to_table("Listing".amenities, ',') 
FROM "Listing";

ALTER TABLE "Amenity"
ADD PRIMARY KEY(amenity_id);

CREATE TABLE "ListingAmenity" (
	listing_id INT,
	amenity_id INT
);

ALTER TABLE "ListingAmenity"
ADD amenity_name VARCHAR(550);

INSERT INTO "ListingAmenity"(listing_id,amenity_name)
SELECT "Listing".id,regexp_split_to_table("Listing".amenities, ',')
FROM "Listing";


INSERT INTO "ListingAmenity" (listing_id,amenity_id)
SELECT  "Listing".id, "Amenity".amenity_id
FROM "Listing","Amenity"
WHERE "Amenity".amenity_name =regexp_split_to_table("Listing".amenities, ',');
		
UPDATE "ListingAmenity"
SET  amenity_id = "Amenity".amenity_id
FROM "Amenity"
WHERE "ListingAmenity".amenity_name = "Amenity".amenity_name;		

ALTER TABLE "ListingAmenity" DROP COLUMN amenity_name;

ALTER TABLE "Listing" DROP COLUMN amenities;

ALTER TABLE "ListingAmenity" ADD PRIMARY KEY(listing_id,amenity_id);

ALTER TABLE "ListingAmenity"
ADD CONSTRAINT listing_fk FOREIGN KEY (listing_id) REFERENCES "Listing" (id);

ALTER TABLE "ListingAmenity"
ADD CONSTRAINT amenity_fk FOREIGN KEY (amenity_id) REFERENCES "Amenity" (amenity_id);






