-- Drop user first if they exist
DROP USER if exists 'quarxAdmin'@'%' ;

-- Now create user with prop privileges
CREATE USER 'quarxAdmin'@'%' IDENTIFIED BY 'mypassword';

GRANT ALL PRIVILEGES ON * . * TO 'quarxAdmin'@'%';