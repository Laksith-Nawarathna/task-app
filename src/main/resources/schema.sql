CREATE TABLE IF NOT EXISTS `User`(
                                     username VARCHAR(50) PRIMARY KEY ,
                                     full_name VARCHAR(255) NOT NULL ,
                                     password VARCHAR(300) NOT NULL
);

CREATE TABLE IF NOT EXISTS `Project`(
                                        id INT AUTO_INCREMENT PRIMARY KEY ,
                                        name VARCHAR(100) NOT NULL ,
                                        username VARCHAR(50) NOT NULL ,
                                        CONSTRAINT fk_user FOREIGN KEY (username) REFERENCES `User`(username)
);

CREATE TABLE IF NOT EXISTS `Task`(
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     content VARCHAR(500) NOT NULL ,
                                     status ENUM('COMPLETED','NOT_COMPLETED') NOT NULL DEFAULT 'NOT_COMPLETED',
                                     project_id INT NOT NULL ,
                                     CONSTRAINT fk_project FOREIGN KEY (project_id) REFERENCES `Project`(id)
);