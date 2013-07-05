USE mailer;

CREATE TABLE `EMAIL` (
  `MailID` int(11) NOT NULL AUTO_INCREMENT,
  `FromAddress` varchar(200) DEFAULT NULL,
  `ToAddress` varchar(200) DEFAULT NULL,
  `Subject` varchar(200) DEFAULT NULL,
  `Body` varchar(2000) DEFAULT NULL,
  `RequestTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Send` char(1) DEFAULT 'N',
  `SendTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`MailID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


INSERT
INTO `EMAIL`
	(`FromAddress`,`ToAddress`,`Subject`,`Body`,`RequestTime`,`Send`,`SendTime`)
VALUES
	('sender@exmaple.com','receiver@example.com','TEST','이것 또한 다 지나가리라...', NOW(),'N','0000-00-00 00:00:00');


INSERT INTO EMAIL
(FromAddress, ToAddress, Subject, Body, Send)
SELECT FromAddress, ToAddress, Subject, Body, Send FROM EMAIL

UPDATE EMAIL
SET
Send = 'N',
RequestTime = NOW()

SELECT count(*) FROM EMAIL

DELETE FROM EMAIL ORDER BY MailID DESC LIMIT 6000