CREATE INDEX rev_index ON "Review" (listing_id);

CREATE INDEX l1 ON "Listing" (guests_included, review_scores_rating, price);

DROP INDEX l1;

CREATE INDEX l2 ON "Listing" (guests_included);

DROP INDEX l2;

CREATE INDEX l3 ON "Listing" (review_scores_rating);

DROP INDEX l3;

CREATE INDEX l4 ON "Listing" (price);

DROP INDEX l4;

CREATE INDEX l5 ON "Listing" (guests_included,price);

DROP INDEX l5;

CREATE INDEX l6 ON "Listing" (review_scores_rating,price);

DROP INDEX l6;

CREATE INDEX l7 ON "Listing" (guests_included, review_scores_rating);

DROP INDEX l7;

CREATE INDEX cal_index ON "Calendar" (listing_id,date,available);

CREATE INDEX listing_index ON "Listing" (city);

CREATE INDEX listingamenity_index ON "ListingAmenity" (listing_id);

CREATE INDEX amenity_index ON "Amenity" (amenity_id);

CREATE INDEX host_index ON "Listing" (host_id);

CREATE INDEX listingid_index ON "Calendar" (listing_id);
