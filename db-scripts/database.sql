
CREATE TABLE users  ( 
	ID      	int(11) AUTO_INCREMENT COMMENT 'Уникальный идентификатор'  NOT NULL,
	LOGIN   	varchar(25) COMMENT 'Логин пользователя'  NOT NULL,
	PASSWORD	varchar(50) COMMENT 'Пароль пользователя'  NOT NULL,
	SALT    	varchar(50) COMMENT 'Соль'  NOT NULL,
	PRIMARY KEY(ID)
)
COMMENT = 'Таблица пользователей' 
/
ALTER TABLE users
	ADD CONSTRAINT UNQ_IDX_USERS
	UNIQUE (LOGIN)
/

CREATE TABLE credit_periods  ( 
	ID    	int(11) AUTO_INCREMENT COMMENT 'Уникальный идентификатор'  NOT NULL,
	PERIOD	int(11) COMMENT 'Срок'  NOT NULL,
	PRIMARY KEY(ID)
)
COMMENT = 'Сроки кредитования' 
/
ALTER TABLE credit_periods
	ADD CONSTRAINT UNQ_IDX_PERIODS
	UNIQUE (PERIOD)
/

CREATE TABLE clients  ( 
	ID                    	int(11) AUTO_INCREMENT COMMENT 'Уникальный идентификатор'  NOT NULL,
	IIN                   	varchar(12) COMMENT 'ИИН'  NOT NULL,
	LAST_NAME             	varchar(50) COMMENT 'Фамилия'  NOT NULL,
	FIRST_NAME            	varchar(50) COMMENT 'Имя'  NOT NULL,
	MIDDLE_NAME           	varchar(50) COMMENT 'Отчество'  NOT NULL,
	PHONE_NUMBER          	varchar(14) COMMENT 'Мобильный телефон'  NOT NULL,
	BIRTH_DATE            	date COMMENT 'Дата рождения'  NOT NULL,
	GENDER                	varchar(6) COMMENT 'Пол' NOT NULL,
	DOCUMENT_NUMBER       	varchar(25) COMMENT 'Номер документа'  NOT NULL,
	DOCUMENT_ISSUED_BY    	varchar(50) COMMENT 'Кем выдан'  NOT NULL,
	DOCUMENT_DATE_OF_ISSUE	date COMMENT 'Дата выдачи'  NOT NULL,
	DOCUMENT_VALID_TO     	date COMMENT 'Действителен до'  NOT NULL,
	SALARY                	decimal(10,2) COMMENT 'Заработная плата'  NOT NULL,
	PAYMENTS              	decimal(10,2) COMMENT 'Коммунальные платежи, аренда'  NOT NULL,
	PRIMARY KEY(ID)
)
COMMENT = 'Данные о клиенте' 
/
ALTER TABLE clients
	ADD CONSTRAINT UNQ_IDX_CLIENTS
	UNIQUE (IIN)
/

CREATE TABLE credit_requests  ( 
	ID            	int(11) AUTO_INCREMENT COMMENT 'Уникальный идентификатор'  NOT NULL,
	CLIENT_ID     	int(11) COMMENT 'Уникальный идентификатор клиента'  NOT NULL,
	REQUEST_DATE  	timestamp DEFAULT CURRENT_TIMESTAMP COMMENT 'Дата заявки'  NOT NULL,
	REQUEST_NUMBER	varchar(50) COMMENT 'Номер заявки'  NULL,
	AMOUNT        	decimal(10,2) COMMENT 'Сумма'  NOT NULL,
	PERIOD_ID       int(11) COMMENT 'Уникальный идентификатор срок'  NOT NULL,
	RATE          	varchar(10) COMMENT 'Ставка'  NOT NULL,
	MONTHLY_PAYMENT	decimal(10,2) COMMENT 'Еж. платеж' NOT NULL,
	AMOUNT_IN_USD  	decimal(10,2) COMMENT 'Сумма в $' NOT NULL,
	TOTAL_PAYOUT   	decimal(10,2) COMMENT 'Общая сумма выплат' NOT NULL,
	OVERPAYMENT    	decimal(10,2) COMMENT 'Переплата' NOT NULL,
	STATUS        	varchar(25) COMMENT 'Статус'  NOT NULL,
	PRIMARY KEY(ID)
)
COMMENT = 'Данные о кредите' 
/
ALTER TABLE credit_requests
	ADD CONSTRAINT UNQ_IDX_REQUESTS
	UNIQUE (REQUEST_NUMBER)
/
ALTER TABLE credit_requests
	ADD CONSTRAINT FK_CLNT_RQST
FOREIGN KEY (CLIENT_ID) REFERENCES clients(ID);
/
ALTER TABLE credit_requests
	ADD CONSTRAINT FK_PRD_RQST
FOREIGN KEY (PERIOD_ID) REFERENCES credit_periods(ID);
/


CREATE TABLE doc_num_seq  ( 
	idx	int(11) NOT NULL 
	)
/

CREATE FUNCTION DOC_NUM_GEN() RETURNS VARCHAR(50)
    DETERMINISTIC
BEGIN
    DECLARE num int;
    DECLARE doc_num varchar(50);
 
    SELECT idx INTO num FROM doc_num_seq;

    SET doc_num = CONCAT("REQ-", DATE_FORMAT(SYSDATE(), '%d%m%y'), "-", num);
    
    UPDATE doc_num_seq SET idx = idx + 1;
 
    RETURN (doc_num);
END
/

CREATE TRIGGER REQ_DOC_NUM BEFORE INSERT ON credit_requests
FOR EACH ROW BEGIN
    SET NEW.REQUEST_NUMBER = DOC_NUM_GEN();
END;
/