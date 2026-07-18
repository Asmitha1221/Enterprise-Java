CREATE DATABASE qadb;
USE qadb;
CREATE TABLE quiz
(
qno varchar(5) PRIMARY KEY,
question varchar(200),
op1 varchar(100),
op2 varchar(100),
op3 varchar(100),
op4 varchar(100),
ans varchar(100)
);
INSERT INTO quiz VALUES
('001','Which keyword is used to inherit a class in
Java?','implements','extends','inherit','using','extends');

INSERT INTO quiz VALUES
('002','Which method is the entry point of a Java
program?','start()','main()','run()','init()','main()');

INSERT INTO quiz VALUES
('003','Which package contains Scanner
class?','java.io','java.util','java.lang','java.sql','java.util');

INSERT INTO quiz VALUES
('004','Which is not a primitive data type?','int','float','String','char','String');

INSERT INTO quiz VALUES

('005','Which exception occurs when a number is divided by
zero?','IOException','NullPointerException','ArithmeticException','ClassNotFoundException','
ArithmeticException');