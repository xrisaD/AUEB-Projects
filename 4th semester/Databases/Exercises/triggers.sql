CREATE OR REPLACE FUNCTION NumOfReviews_funct()
  RETURNS trigger AS
  $$
	BEGIN
	 IF TG_OP = 'INSERT' THEN
		UPDATE "Listing"
		SET number_of_reviews = number_of_reviews + 1
		WHERE NEW.listing_id = "Listing".id;
		RETURN NEW;
	 ELSIF TG_OP = 'DELETE' THEN
	 	UPDATE "Listing"
		SET number_of_reviews = number_of_reviews - 1
		WHERE OLD.listing_id = "Listing".id;
		RETURN NULL;
	 END IF;
 END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER IncreaseOfReviews
BEFORE INSERT ON "Review"
FOR EACH ROW 
EXECUTE PROCEDURE NumOfReviews_funct();

CREATE TRIGGER DecreaseOfReviews
AFTER DELETE ON "Review"
FOR EACH ROW 
EXECUTE PROCEDURE NumOfReviews_funct();


 
 
/*���� ���� Listing ������ ������� �� price ��� ��� �������, ���� ������� �������� ��� ���� ���������� ������� ��� ������ Summary_Listing ��� �������� �� ����� ���� �����.*/ 
CREATE OR REPLACE FUNCTION Price_update()
  RETURNS trigger AS
  $$
	BEGIN
	 IF TG_OP = 'UPDATE' THEN
		UPDATE "Summary_Listing"
		SET price = NEW.price
		WHERE NEW.id = id;
		RETURN NEW;
	 END IF;
 END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER PriceChange
AFTER UPDATE ON "Listing"
FOR EACH ROW 
EXECUTE PROCEDURE Price_update();
