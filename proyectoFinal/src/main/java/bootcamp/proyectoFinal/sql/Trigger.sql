
DELIMITER $
drop trigger if exists restarCantidad $
CREATE TRIGGER restarCantidad BEFORE INSERT ON ticket_details
FOR EACH ROW BEGIN
    IF (SELECT stock FROM product WHERE product.code = NEW.product_code) < NEW.ammount_products THEN
        SIGNAL SQLSTATE '42927'
        SET MESSAGE_TEXT = 'Error.';
    END IF;
    UPDATE product
        SET product.stock=(product.stock - NEW.ammount_products)
        WHERE product.code= NEW.product_code;
END $
DELIMITER ;





