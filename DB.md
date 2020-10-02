## Mariadb Installation

```
sudo apt update
sudo apt install mariadb-server
sudo mysql_secure_installation
```
## Create amara user

* login as root in to MariaDB console
	```
	sudo mysql
	```

* Create a database
	```
	CREATE USER amara_user@localhost IDENTIFIED BY 'amara';
	```

* Create amara DB user

	```
	CREATE DATABASE amara;
	GRANT ALL PRIVILEGES ON amara.* TO amara_user@localhost;
	FLUSH PRIVILEGES;
	SHOW GRANTS FOR amara_user@localhost;
	```

## Create tables with script
```
mysql -u amara_user -p -D amara < create_tables.sql
```