CREATE TABLE user (
	id VARCHAR(36) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);
    
CREATE TABLE course (
    id VARCHAR(36) NOT NULL,
    course_name VARCHAR(255) NOT NULL,
    path_to_file VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);