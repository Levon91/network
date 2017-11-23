
USE `network`;

ALTER TABLE `user`
  ADD COLUMN `mobile_number` VARCHAR (30) AFTER `last_name`;