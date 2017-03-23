CREATE DATABASE  IF NOT EXISTS `movie_rating` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `movie_rating`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: movie_rating
-- ------------------------------------------------------
-- Server version	5.7.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id_actor` smallint(5) NOT NULL AUTO_INCREMENT,
  `first_name_ru` varchar(45) NOT NULL,
  `last_name_ru` varchar(45) NOT NULL,
  `first_name_en` varchar(45) DEFAULT NULL,
  `last_name_en` varchar(45) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_actor`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
INSERT INTO `actor` VALUES (1,'Райан','Гослинг','Ryan','Gosling',0),(2,'Эмма','Стоун','Emma','Stone',0),(3,'Тим','Роббинс','Tim','Robins',0),(4,'Морган','Фриман','Morgan','Friman',0),(5,'Боб','Гантон','Bob','Ganton',0),(6,'Том','Хэнкс','Tom','Hanks',0),(7,'Лиам','Нисон','Liam','Nilson',0);
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `code` varchar(2) NOT NULL,
  `name_en` varchar(100) NOT NULL,
  `name_ru` varchar(100) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES ('AD','Andorra','Андорра'),('AE','United Arab Emirates','Объединённые Арабские Эмираты'),('AF','Afghanistan','Афганистан'),('AG','Antigua and Barbuda','Антигуа и Барбуда'),('AI','Anguilla','Ангилья'),('AL','Albania','Албания'),('AM','Armenia','Армения'),('AN','Netherlands Antilles','Нидерландские Антильские острова'),('AO','Angola','Ангола'),('AQ','Antarctica','Антарктика'),('AR','Argentina','Аргентина'),('AT','Austria','Австрия'),('AU','Australia','Австралия'),('AW','Aruba','Аруба'),('AZ','Azerbaijan','Азейбарджан'),('BA','Bosnia and Herzegovina','Босния и Герцеговина'),('BB','Barbados','Барбадос'),('BD','Bangladesh','Бангладеш'),('BE','Belgium','Бельгия'),('BF','Burkina Faso','Буркина-Фасо'),('BG','Bulgaria','Болгария'),('BH','Bahrain','Бахрейн'),('BI','Burundi','Бурунди'),('BJ','Benin','Бенин'),('BM','Bermuda','Бермудские Отсрова'),('BN','Brunei Darussalam','Бруней'),('BO','Bolivia','Боливия'),('BR','Brazil','Бразилия'),('BS','Bahamas','Багамские острава'),('BT','Bhutan','Бутан'),('BV','Bouvet Island','Остров Буве'),('BW','Botswana','Ботсвана'),('BY','Belarus','Беларусь'),('BZ','Belize','Белиз'),('CA','Canada','Канада'),('CC','Cocos (Keeling) Islands','Кокосовые Острова'),('CF','Central African Republic','Центральная Африканская Республика'),('CG','Congo','Конго'),('CH','Switzerland','Швейцария'),('CI','Ivory Coast','Кот-д’Ивуар'),('CK','Cook Islands','Острова Кука'),('CL','Chile','Чили'),('CM','Cameroon','Камерун'),('CN','China','Китай'),('CO','Colombia','Колумбия'),('CR','Costa Rica','Коста-Рика'),('CU','Cuba','Куба'),('CV','Cape Verde','Кабо-Верде'),('CX','Christmas Island','Остров Рождества'),('CY','Cyprus','Кипр'),('CZ','Czech Republic','Чехия'),('DE','Germany','Германия'),('DJ','Djibouti','Джибути'),('DK','Denmark','Дания'),('DM','Dominica','Доминика'),('DO','Dominican Republic','Доминиканская Республика'),('DS','American Samoa','Американское Самоа'),('DZ','Algeria','Алжир'),('EC','Ecuador','Эквадор'),('EE','Estonia','Эстония'),('EG','Egypt','Египет'),('EH','Western Sahara','Западная Сахара'),('ER','Eritrea','Эритрея'),('ES','Spain','Испания'),('ET','Ethiopia','Эфиопия'),('FI','Finland','Финляндия'),('FJ','Fiji','Фиджи'),('FK','Falkland Islands (Malvinas)','Фолклендские острова'),('FM','Micronesia, Federated States of','Федеративные Штаты Микронезии'),('FO','Faroe Islands','Фарерские острова'),('FR','France','Франция'),('FX','France, Metropolitan','Метрополия Франции'),('GA','Gabon','Габон'),('GB','United Kingdom','Великобритания'),('GD','Grenada','Гренада'),('GE','Georgia','Грузия'),('GF','French Guiana','Гвиана'),('GH','Ghana','Гана'),('GI','Gibraltar','Гибралтар'),('GK','Guernsey','Гернси'),('GL','Greenland','Гренландия'),('GM','Gambia','Гамбия'),('GN','Guinea','Гвинея'),('GP','Guadeloupe','Гваделупа'),('GQ','Equatorial Guinea','Экваториальная Гвинея'),('GR','Greece','Греция'),('GS','South Georgia South Sandwich Islands','Южная Георгия и Южные Сандвичевы Острова'),('GT','Guatemala','Гватемала'),('GU','Guam','Гуам'),('GW','Guinea-Bissau','Гвинея-Бисау'),('GY','Guyana','Гайана'),('HK','Hong Kong','Гонконг'),('HM','Heard and Mc Donald Islands','Остров Херд и острова Макдональд'),('HN','Honduras','Гондурас'),('HR','Croatia (Hrvatska)','Хорватия'),('HT','Haiti','Гаити'),('HU','Hungary','Венгрия'),('ID','Indonesia','Индонезия'),('IE','Ireland','Ирландия'),('IL','Israel','Израиль'),('IM','Isle of Man','Остров Мэн'),('IN','India','Индия'),('IO','British Indian Ocean Territory','Заморская территория Великобритании'),('IQ','Iraq','Ирак'),('IR','Iran (Islamic Republic of)','Иран'),('IS','Iceland','Исландия'),('IT','Italy','Италия'),('JE','Jersey','Джерси'),('JM','Jamaica','Ямайка'),('JO','Jordan','Джордан'),('JP','Japan','Япония'),('KE','Kenya','Кения'),('KG','Kyrgyzstan','Киргизия'),('KH','Cambodia','Камбоджа'),('KI','Kiribati','Кирибати'),('KM','Comoros','Коморы'),('KN','Saint Kitts and Nevis','Сент-Китс и Невис'),('KP','Korea, Democratic People\'s Republic of','Корейская Народно-Демократическая Республика'),('KR','Korea, Republic of','Корея'),('KW','Kuwait','Кувейт'),('KY','Cayman Islands','Каймановые Острова'),('KZ','Kazakhstan','Казахстан'),('LA','Lao People\'s Democratic Republic','Лаос'),('LB','Lebanon','Ливан'),('LC','Saint Lucia','Сент-Люсия'),('LI','Liechtenstein','Лихтенштейн'),('LK','Sri Lanka','Шри-Ланка'),('LR','Liberia','Либерия'),('LS','Lesotho','Лесото'),('LT','Lithuania','Литва'),('LU','Luxembourg','Люксемберг'),('LV','Latvia','Латвия'),('LY','Libyan Arab Jamahiriya','Ливия'),('MA','Morocco','Марокко'),('MC','Monaco','Монако'),('MD','Moldova, Republic of','Молдова'),('ME','Montenegro','Черногория'),('MG','Madagascar','Мадагаскар'),('MH','Marshall Islands','Маршалловы Острова'),('MK','Macedonia','Македония'),('ML','Mali','Мали'),('MM','Myanmar','Мьянма'),('MN','Mongolia','Монголия'),('MO','Macau','Макао'),('MP','Northern Mariana Islands','Северные Марианские Острова'),('MQ','Martinique','Мартиника'),('MR','Mauritania','Мавритания'),('MS','Montserrat','Монтсеррат '),('MT','Malta','Мальта'),('MU','Mauritius','Маврикий'),('MV','Maldives','Мальдивы'),('MW','Malawi','Малави'),('MX','Mexico','Мексика'),('MY','Malaysia','Малайзия'),('MZ','Mozambique','Мозамбик'),('NA','Namibia','Намибия'),('NC','New Caledonia','Новая Каледония'),('NE','Niger','Нигер'),('NF','Norfolk Island','Остров Норфолк'),('NG','Nigeria','Нигерия'),('NI','Nicaragua','Никарагуа'),('NL','Netherlands','Нидерланды'),('NO','Norway','Норвегия'),('NP','Nepal','Непал'),('NR','Nauru','Науру'),('NU','Niue','Ниуэ'),('NZ','New Zealand','Новая Зеландия'),('OM','Oman','Оман'),('PA','Panama','Панама'),('PE','Peru','Перу'),('PF','French Polynesia','Французская Полинезия'),('PG','Papua New Guinea','Папуа Новая Гвинея'),('PH','Philippines','Филиппины'),('PK','Pakistan','Пакистан'),('PL','Poland','Польша'),('PM','St. Pierre and Miquelon','Сен-Пьер и Микелон'),('PN','Pitcairn','Острова Питкэрн'),('PR','Puerto Rico','Пуэрто-Рико'),('PS','Palestine','Палестина'),('PT','Portugal','Португалия'),('PW','Palau','Палау'),('PY','Paraguay','Парагвай'),('QA','Qatar','Катар'),('RE','Reunion','Реюньон'),('RO','Romania','Румыния'),('RS','Serbia','Сербия'),('RU','Russian Federation','Российская Федерация'),('RW','Rwanda','Руанда'),('SA','Saudi Arabia','Саудовская Аравия'),('SB','Solomon Islands','Соломоновы Острова'),('SC','Seychelles','Сейшеллы'),('SD','Sudan','Судан'),('SE','Sweden','Швеция'),('SG','Singapore','Сингапур'),('SH','St. Helena','Остров Святой Елены'),('SI','Slovenia','Словения'),('SJ','Svalbard and Jan Mayen Islands','Шпицберген и Ян-Майен'),('SK','Slovakia','Словакия'),('SL','Sierra Leone','Сьерра-Леоне'),('SM','San Marino','Сан-Марино'),('SN','Senegal','Сенегал'),('SO','Somalia','Сомалия'),('SR','Suriname','Суринам'),('ST','Sao Tome and Principe','Сан-Томе и Принсипи'),('SV','El Salvador','Сальвадор'),('SY','Syrian Arab Republic','Сирия'),('SZ','Swaziland','Свазиленд'),('TC','Turks and Caicos Islands','Теркс и Кайкос'),('TD','Chad','Чад'),('TF','French Southern Territories','Французские Южные и Антарктические территории'),('TG','Togo','Того'),('TH','Thailand','Тайланд'),('TJ','Tajikistan','Таджикистан'),('TK','Tokelau','Токелау'),('TM','Turkmenistan','Туркменистан'),('TN','Tunisia','Тунис'),('TO','Tonga','Тонго'),('TP','East Timor','Восточный Тимор'),('TR','Turkey','Турция'),('TT','Trinidad and Tobago','Тринидад и Тобаго'),('TV','Tuvalu','Тувалу'),('TW','Taiwan','Тайвань'),('TY','Mayotte','Майотта'),('TZ','Tanzania, United Republic of','Танзания'),('UA','Ukraine','Украина'),('UG','Uganda','Уганда'),('UM','United States minor outlying islands','Внешние малые острова США'),('US','United States','США'),('UY','Uruguay','Уругвай'),('UZ','Uzbekistan','Узбекистан'),('VA','Vatican City State','Ватикан'),('VC','Saint Vincent and the Grenadines','Сент-Винсент и Гренадины'),('VE','Venezuela','Венесуэла'),('VG','Virgin Islands (British)','Виргинские Острова'),('VI','Virgin Islands (U.S.)','Виргинские острова'),('VN','Vietnam','Вьетнам'),('VU','Vanuatu','Вануату'),('WF','Wallis and Futuna Islands','Уоллис и Футуна'),('WS','Samoa','Самоа'),('XK','Kosovo','Косово'),('YE','Yemen','Йемен'),('ZA','South Africa','Южно-Африканская Республика'),('ZM','Zambia','Замбия'),('ZR','Zaire','Демократическая Республика Конго'),('ZW','Zimbabwe','Зимбабве');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genre` (
  `id_genre` tinyint(3) NOT NULL AUTO_INCREMENT,
  `name_ru` varchar(45) NOT NULL,
  `name_en` varchar(45) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_genre`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'аниме','anime',0),(2,'биография','biography',0),(3,'боевик','action',0),(4,'вестерн','vestern',0),(5,'военный','military',0),(6,'детектив','detective',0),(7,'детский','child',0),(8,'документальный','documental',0),(9,'драма','drama',0),(10,'игра','game',0),(11,'комедия','comedy',0),(12,'криминал','criminal',0),(13,'мелодрама','melodrama',0),(14,'мультфильм','cartoon',0),(15,'семейный','family',0),(16,'спорт','sport',0),(17,'триллер','thriller',0),(18,'ужасы','horror',0),(19,'фантастика','fantastic',0),(20,'приключения','adventure',0),(21,'фэнтези','fantasy',0),(22,'история','history',0);
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie` (
  `id_movie` smallint(5) NOT NULL AUTO_INCREMENT,
  `title_ru` varchar(255) NOT NULL,
  `title_en` varchar(255) NOT NULL,
  `release_year` int(11) NOT NULL,
  `description_ru` text NOT NULL,
  `description_en` text NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `poster_path` varchar(100) NOT NULL,
  PRIMARY KEY (`id_movie`),
  KEY `idx_title` (`title_ru`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'Побег из шоушенка','The Shawshank Redemption',1994,'Успешный банкир Энди Дюфрейн обвинен в убийстве собственной жены и ее любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решетки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, вооруженный живым умом и доброй душой, отказывается мириться с приговором судьбы и начинает разрабатывать невероятно дерзкий план своего освобождения.','Chronicles the experiences of a formerly successful banker as a prisoner in the gloomy jailhouse of Shawshank after being found guilty of a crime he did not commit. The film portrays the man\'s unique way of dealing with his new, torturous life; along the way he befriends a number of fellow prisoners, most notably a wise long-term inmate named Red.',0,'images/poster/shawshank.jpg'),(2,'Зеленая миля','The Green Mile',1999,'Обвиненный в страшном преступлении, Джон Коффи оказывается в блоке смертников тюрьмы «Холодная гора». Вновь прибывший обладал поразительным ростом и был пугающе спокоен, что, впрочем, никак не влияло на отношение к нему начальника блока Пола Эджкомба, привыкшего исполнять приговор.','Death Row guards at a penitentiary, in the 1930\'s, have a moral dilemma with their job when they discover one of their prisoners, a convicted murderer, has a special gift.',0,'images/poster/green_mile.jpg'),(3,'Форест Гамп',' 	Форрест Гамп',1994,'От лица главного героя Форреста Гампа, слабоумного безобидного человека с благородным и открытым сердцем, рассказывается история его необыкновенной жизни.','Forrest Gump is a simple man with a low I.Q. but good intentions. He is running through childhood with his best and only friend Jenny. His \'mama\' teaches him the ways of life and leaves him to choose his destiny. Forrest joins the army for service in Vietnam, finding new friends called Dan and Bubba, he wins medals, creates a famous shrimp fishing fleet, inspires people to jog, starts a ping-pong craze, creates the smiley, writes bumper stickers and songs, donates to people and meets the president several times. However, this is all irrelevant to Forrest who can only think of his childhood sweetheart Jenny Curran, who has messed up her life. Although in the end all he wants to prove is that anyone can love anyone.',0,'images/poster/forest_gump.jpg'),(4,'Список Шиндлера','Schindler\'s List',1993,'Фильм рассказывает реальную историю загадочного Оскара Шиндлера, члена нацистской партии, преуспевающего фабриканта, спасшего во время Второй мировой войны почти 1200 евреев.','Oskar Schindler is a vainglorious and greedy German businessman who becomes an unlikely humanitarian amid the barbaric German Nazi reign when he feels compelled to turn his factory into a refuge for Jews. Based on the true story of Oskar Schindler who managed to save about 1100 Jews from being gassed at the Auschwitz concentration camp, it is a testament to the good in all of us.',0,'images/poster/schindler_list.jpg'),(5,'1+1','Intouchables',2011,'Пострадав в результате несчастного случая, богатый аристократ Филипп нанимает в помощники человека, который менее всего подходит для этой работы, — молодого жителя предместья Дрисса, только что освободившегося из тюрьмы. Несмотря на то, что Филипп прикован к инвалидному креслу, Дриссу удается привнести в размеренную жизнь аристократа дух приключений.','In Paris, the aristocratic and intellectual Philippe is a quadriplegic millionaire who is interviewing candidates for the position of his carer, with his red-haired secretary Magalie. Out of the blue, the rude African Driss cuts the line of candidates and brings a document from the Social Security and asks Phillipe to sign it to prove that he is seeking a job position so he can receive his unemployment benefit. Philippe challenges Driss, offering him a trial period of one month to gain experience helping him. Then Driss can decide whether he would like to stay with him or not. Driss accepts the challenge and moves to the mansion, changing the boring life of Phillipe and his employees',0,'images/poster/1+1.jpg'),(6,'Начало','Inception',2011,'Кобб — талантливый вор, лучший из лучших в опасном искусстве извлечения: он крадет ценные секреты из глубин подсознания во время сна, когда человеческий разум наиболее уязвим. Редкие способности Кобба сделали его ценным игроком в привычном к предательству мире промышленного шпионажа, но они же превратили его в извечного беглеца и лишили всего, что он когда-либо любил.',' Dom Cobb is a skilled thief, the absolute best in the dangerous art of extraction, stealing valuable secrets from deep within the subconscious during the dream state, when the mind is at its most vulnerable. Cobb\'s rare ability has made him a coveted player in this treacherous new world of corporate espionage, but it has also made him an international fugitive and cost him everything he has ever loved. Now Cobb is being offered a chance at redemption. One last job could give him his life back but only if he can accomplish the impossible - inception. Instead of the perfect heist, Cobb and his team of specialists have to pull off the reverse: their task is not to steal an idea but to plant one. If they succeed, it could be the perfect crime. But no amount of careful planning or expertise can prepare the team for the dangerous enemy that seems to predict their every move. An enemy that only Cobb could have seen coming.',0,'images/poster/inception.jpg'),(7,'Король Лев','The Lion King',1994,'У величественного Короля-Льва Муфасы рождается наследник по имени Симба. Уже в детстве любознательный малыш становится жертвой интриг своего завистливого дяди Шрама, мечтающего о власти.','A young lion Prince is cast out of his pride by his cruel uncle, who claims he killed his father. While the uncle rules with an iron paw, the prince grows up beyond the Savannah, living by a philosophy: No worries for the rest of your days. But when his past comes to haunt him, the young Prince must decide his fate: will he remain an outcast, or face his demons and become what he needs to be?',0,'images/poster/lion_king.jpg'),(8,'Леон','Léon',1994,'Профессиональный убийца Леон, не знающий пощады и жалости, знакомится со своей очаровательной соседкой Матильдой, семью которой расстреливают полицейские, замешанные в торговле наркотиками. Благодаря этому знакомству он впервые испытывает чувство любви, но…','After her father, mother, older sister and little brother are killed by her father\'s employers, the 12-year-old daughter of an abject drug dealer is forced to take refuge in the apartment of a professional hitman who at her request teaches her the methods of his job so she can take her revenge on the corrupt DEA agent who ruined her life by killing her beloved brother. ',0,'images/poster/leon.jpg');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_actor`
--

DROP TABLE IF EXISTS `movie_actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_actor` (
  `id_movie` smallint(5) NOT NULL,
  `id_actor` smallint(5) NOT NULL,
  PRIMARY KEY (`id_movie`,`id_actor`),
  KEY `fk_movie_has_actor_actor1_idx` (`id_actor`),
  KEY `fk_movie_has_actor_movie1_idx` (`id_movie`),
  CONSTRAINT `fk_movie_has_actor_actor1` FOREIGN KEY (`id_actor`) REFERENCES `actor` (`id_actor`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_movie_has_actor_movie1` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id_movie`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_actor`
--

LOCK TABLES `movie_actor` WRITE;
/*!40000 ALTER TABLE `movie_actor` DISABLE KEYS */;
INSERT INTO `movie_actor` VALUES (1,3),(1,4),(1,5),(2,6),(3,6),(4,7);
/*!40000 ALTER TABLE `movie_actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_country`
--

DROP TABLE IF EXISTS `movie_country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_country` (
  `id_movie` smallint(5) NOT NULL,
  `country_code` varchar(2) NOT NULL,
  PRIMARY KEY (`id_movie`,`country_code`),
  KEY `fk_movie_has_country_country1_idx` (`country_code`),
  KEY `fk_movie_has_country_movie_idx` (`id_movie`),
  CONSTRAINT `fk_movie_has_country_country1` FOREIGN KEY (`country_code`) REFERENCES `country` (`code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_movie_has_country_movie` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id_movie`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_country`
--

LOCK TABLES `movie_country` WRITE;
/*!40000 ALTER TABLE `movie_country` DISABLE KEYS */;
INSERT INTO `movie_country` VALUES (1,'US'),(2,'US'),(3,'US'),(4,'US'),(5,'FR'),(6,'GB'),(6,'US'),(7,'US');
/*!40000 ALTER TABLE `movie_country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_genre`
--

DROP TABLE IF EXISTS `movie_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_genre` (
  `id_movie` smallint(5) NOT NULL,
  `id_genre` tinyint(3) NOT NULL,
  PRIMARY KEY (`id_movie`,`id_genre`),
  KEY `fk_movie_has_genre_genre1_idx` (`id_genre`),
  KEY `fk_movie_has_genre_movie1_idx` (`id_movie`),
  CONSTRAINT `fk_movie_has_genre_genre1` FOREIGN KEY (`id_genre`) REFERENCES `genre` (`id_genre`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_movie_has_genre_movie1` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id_movie`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_genre`
--

LOCK TABLES `movie_genre` WRITE;
/*!40000 ALTER TABLE `movie_genre` DISABLE KEYS */;
INSERT INTO `movie_genre` VALUES (4,2),(5,2),(6,3),(2,6),(1,9),(2,9),(3,9),(4,9),(5,9),(6,9),(7,9),(3,11),(5,11),(1,12),(2,12),(7,14),(7,15),(6,17),(6,19),(7,20),(2,21),(4,22);
/*!40000 ALTER TABLE `movie_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rating` (
  `id_movie` smallint(5) NOT NULL,
  `id_user` int(11) NOT NULL,
  `mark` int(2) NOT NULL,
  PRIMARY KEY (`id_user`,`id_movie`),
  KEY `fk_rating_movie1_idx` (`id_movie`),
  KEY `fk_rating_user1_idx` (`id_user`),
  CONSTRAINT `fk_rating_movie1` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id_movie`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_rating_user1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
INSERT INTO `rating` VALUES (1,2,9),(2,2,7),(3,2,8),(4,2,5),(5,2,9),(7,2,10),(1,3,8),(2,3,6),(3,3,8),(4,3,9),(6,3,9),(7,3,9),(1,4,9),(2,4,9),(3,4,9),(4,4,9),(5,4,10),(7,4,9),(1,5,10),(2,5,10),(3,5,8),(4,5,9),(6,5,8);
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `id_movie` smallint(5) NOT NULL,
  `id_user` int(11) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `review` text NOT NULL,
  `publish_date` datetime NOT NULL,
  PRIMARY KEY (`id_movie`,`id_user`),
  KEY `fk_user_has_movie_movie1_idx` (`id_movie`),
  KEY `fk_user_has_movie_user1_idx` (`id_user`),
  CONSTRAINT `fk_user_has_movie_movie1` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id_movie`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_movie_user1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,2,'Понравился','Фильм без преувеличения шедевр, очередной плод успешного взаимодействия Фрэнка Дарабонта и Стивена Кинга.','2016-12-29 17:00:00'),(1,4,NULL,'Хороший фильм.','2017-01-20 17:00:00'),(2,3,'Бич человечества','Уважаемые дамы и господа! Перед тем, как вы начнете читать мою рецензию, назовите мне пожалуйста болезнь, которая уничтожила больше всего людей во все времена и может по праву считаться «Бичом человечества»? «Конечно же, чума!» — ответите вы. Нет, друзья мои, это не чума, не СПИД, не рак. Эта эпидемия пошла с Древнейших времен и до сих пор пожинает богатые «плоды» своей деятельности. Имя ей — человеческое Равнодушие. Мало кто осознает, сколько бед принесла в прошлом и еще принесет в будущем эта чудовищная болезнь. Против нее не существует панацеи, единственный способ с ней бороться — начать с самого себя.','2016-12-20 16:00:00'),(2,4,'Настоящая драма','Кинолента Фрэнка Дарабонта заставляет поверить, что наше нахождение на земле лишь временное пребывание. На примере Пола можно понять, что находится на этой планете до бесконечности вовсе не здорово, даже при том условии, что ты не имеешь особых заболеваний. Люди бывают слишком жестоки и бессердечны, и далеко не каждый может за этим спокойно наблюдать, особенно если ты человек с таким добрым сердцем, как Джон, настолько добрым, что каждый прожитый день становится настоящим испытанием.','2017-01-21 17:00:00'),(2,5,'«Свет»','Я не хочу и не буду рассуждать о фильме с завышенной точки зрения. Одним словом замечательный фильм, ярчайший шедевр, оставивший после себя невероятное количество впечатлений. Волшебный фильм, неповторимый. Я не видел ничего подобного после «Шоушенка». Фильм тронул мое сердце и заставил чуть ли не зарыдать. До того он прекрасный, друзья.','2017-01-19 17:00:00'),(6,2,'Отлично','Что имеем в итоге? Качественное, эффектное и красивое кино с достаточно глубокой сюжетной линией и четко прорисованными персонажами. Фильм, который можно и нужно смотреть каждому, вне зависимости от того, поклонник он фантастики или нет. Каждый найдет в «Начале» что-то привлекательное для себя. Но… господа, ну нет здесь того нерва, который должен присутствовать в кино, которое претендует на звание шедевра. Может это, конечно, избытки «фантастикизации» подающих надежды проектов. Ведь согласитесь, практически все последние громкие, яркие и подающие надежды проекты относятся к жанру фантастики. Спасибо хоть за то, что в этот раз обошлось без 3D. На самом деле, меня отчасти огорчает тот факт, что в последнее время режиссеры делают ставки на спецэффекты и это самое пресловутое 3D. Все меньше становится картин, которые берут своей сюжетной историей. Очень хотелось бы, чтобы в гонке за современными технологиями современные кинопроизводители не потеряли человечность, которую мы наблюдаем в старых полюбившихся фильмах, например, в том же «Побеге из Шоушенка» или «Зеленой миле», уже названных мною выше. Ведь технологии-то идут вперед, не сегодня завтра уже придумают что-то новенькое и 3D станет вчерашним днем. А картины, в которых мы наблюдаем человеческие эмоции, чувства не устареют никогда. Их будут смотреть и сегодня, и завтра, и 20 лет спустя. Так что верните-ка вы «Побег» с «Милей» на место — они этого заслужили. А «Начало»… просто хорошее кино. ','2017-01-20 17:00:00');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(65) NOT NULL,
  `login` varchar(25) NOT NULL,
  `password` varchar(100) NOT NULL,
  `date_register` date NOT NULL,
  `email` varchar(50) NOT NULL,
  `status` varchar(60) DEFAULT 'новичок',
  `is_admin` tinyint(1) NOT NULL DEFAULT '0',
  `is_banned` tinyint(1) NOT NULL DEFAULT '0',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Дарья','Сухоребрая','admin','21232f297a57a5a743894a0e4a801fc3','2012-12-20','admin@mail.ru','admin',1,0,0),(2,'Дарья','Сергеева','darya','ee11cbb19052e40b07aac0ca060c23ee','2001-01-20','darya@mail.ru','новичок',0,1,0),(3,'Сергей','Подгайский','sergey','sss','2012-01-20','sergey@gmail.com','новичок',0,0,0),(4,'Евгений','Смердов','evgen','eee','2003-03-20','eugene@gmail.com','новичок',0,1,1),(5,'Глухов','Роман','roman','rrr','2007-09-20','roman@gmail.com','новичок',0,1,0),(8,'М','М','meme','46756b989b1050a317258e6d5e8e9891','2017-02-27','darya.suxorebraya.00@mail.com','новичок',0,1,0),(9,'МИ','Ми','memememe','46756b989b1050a317258e6d5e8e9891','2017-02-27','darya.suxorebraya.00@mail.com','новичок',0,0,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `id_user` int(11) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `status` varchar(60) NOT NULL DEFAULT 'новичок',
  PRIMARY KEY (`id_user`),
  CONSTRAINT `user_user_info` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (2,'Дарья','Сухоребрая','новичок'),(3,'Сергей','Подгайский','профессионал'),(4,'Евгений','Иванов','мастер'),(5,'Роман','Сельский','мастер'),(8,'mememe','mememe','новичок'),(9,'mememe','mememe','новичок');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-23 21:04:31
