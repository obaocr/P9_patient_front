drop table p9ocr.rating;
drop table p9ocr.users;

CREATE TABLE Rating (
  Id INT NOT NULL AUTO_INCREMENT,
  moodysRating VARCHAR(125),
  sandPRating VARCHAR(125),
  fitchRating VARCHAR(125),
  orderNumber INT,
  PRIMARY KEY (Id)
)
;

CREATE TABLE Users (
  Id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(125),
  password VARCHAR(125),
  fullname VARCHAR(125),
  role VARCHAR(125),
  PRIMARY KEY (Id)
)
;

insert into p9ocr.Users(fullname, username, password, role) values("Administrator", "admin", "$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa", "ADMIN");
insert into p9ocr.Users(fullname, username, password, role) values("User", "user", "$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa", "USER");

