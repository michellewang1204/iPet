-- phpMyAdmin SQL Dump
-- version 5.0.4deb2+deb11u1
-- https://www.phpmyadmin.net/
--
-- 主機： localhost:3306
-- 產生時間： 2022 年 12 月 26 日 15:03
-- 伺服器版本： 10.5.15-MariaDB-0+deb11u1
-- PHP 版本： 7.4.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `project`
--

-- --------------------------------------------------------

--
-- 資料表結構 `dewtime_remind`
--

CREATE TABLE `dewtime_remind` (
  `pid` int(11) NOT NULL,
  `category` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `dtime` date NOT NULL,
  `tocheck` varchar(5) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 傾印資料表的資料 `dewtime_remind`
--

INSERT INTO `dewtime_remind` (`pid`, `category`, `dtime`, `tocheck`) VALUES
(1, '寵愛食剋', '2018-12-01', 't'),
(3, '蚤不到', '2019-12-24', 't'),
(4, '寵愛食剋', '2022-12-19', 't'),
(4, '蚤不到', '2022-11-16', 't'),
(4, '蚤不到', '2022-11-25', 't'),
(4, '蚤不到', '2022-12-19', 't'),
(6, '全能狗', '2016-05-30', 't'),
(7, '必剋蝨', '2018-07-25', 'f'),
(10, '益百分', '2019-03-31', 'f'),
(11, '犬新寶', '2020-01-21', 'f'),
(21, '全能狗', '2022-12-11', 't'),
(21, '寵愛食剋', '2022-12-11', 't'),
(27, '寵愛食剋', '2022-12-12', 't'),
(27, '蚤不到', '2022-12-12', 't'),
(27, '蚤不到', '2022-12-16', 't');

-- --------------------------------------------------------

--
-- 資料表結構 `exctime_remind`
--

CREATE TABLE `exctime_remind` (
  `pid` int(11) NOT NULL,
  `etime` time NOT NULL,
  `tocheck` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `hour` int(11) NOT NULL,
  `minute` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 傾印資料表的資料 `exctime_remind`
--

INSERT INTO `exctime_remind` (`pid`, `etime`, `tocheck`, `hour`, `minute`) VALUES
(1, '13:00:00', '0', 13, 0),
(2, '12:00:00', '0', 12, 0),
(3, '13:00:00', '0', 13, 0),
(3, '15:42:00', '0', 15, 42),
(4, '08:13:00', '1', 8, 13),
(4, '08:14:00', '0', 8, 14),
(4, '12:40:00', '0', 12, 40),
(4, '17:53:00', '1', 17, 53),
(4, '20:06:00', '0', 20, 6),
(5, '15:00:00', '0', 15, 0),
(6, '16:00:00', '1', 16, 0),
(7, '15:00:00', '0', 15, 0),
(8, '15:00:00', '1', 15, 0),
(9, '14:00:00', '0', 14, 0),
(10, '18:00:00', '1', 18, 0),
(11, '16:00:00', '1', 16, 0),
(15, '08:00:00', '0', 8, 0),
(15, '13:30:00', '0', 13, 30),
(21, '13:56:00', '0', 13, 56),
(21, '16:40:00', '0', 16, 40),
(21, '16:42:00', '0', 16, 42),
(21, '20:28:00', '0', 20, 28),
(27, '02:59:00', '1', 2, 59),
(27, '05:40:00', '0', 5, 40),
(27, '10:51:00', '0', 10, 51);

-- --------------------------------------------------------

--
-- 資料表結構 `feedtime_remind`
--

CREATE TABLE `feedtime_remind` (
  `pid` int(11) NOT NULL,
  `ftime` time NOT NULL,
  `tocheck` int(5) NOT NULL,
  `hour` int(10) NOT NULL,
  `minute` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 傾印資料表的資料 `feedtime_remind`
--

INSERT INTO `feedtime_remind` (`pid`, `ftime`, `tocheck`, `hour`, `minute`) VALUES
(1, '12:00:00', 0, 12, 0),
(2, '09:00:00', 1, 9, 0),
(3, '13:21:00', 1, 13, 21),
(3, '15:08:00', 0, 15, 8),
(3, '18:33:00', 1, 18, 33),
(3, '19:21:00', 1, 19, 21),
(4, '01:56:00', 0, 1, 56),
(4, '20:06:00', 0, 20, 6),
(5, '14:00:00', 1, 14, 0),
(6, '14:00:00', 0, 14, 0),
(7, '12:00:00', 1, 12, 0),
(7, '18:03:00', 0, 18, 3),
(8, '11:00:00', 1, 11, 0),
(9, '12:00:00', 0, 12, 0),
(10, '16:00:00', 1, 16, 0),
(11, '15:00:00', 1, 15, 0),
(15, '18:20:00', 0, 18, 20),
(21, '13:54:00', 0, 13, 54),
(21, '17:04:00', 0, 17, 4),
(21, '20:28:00', 0, 20, 28),
(23, '12:37:00', 0, 12, 37),
(23, '15:21:00', 1, 15, 21),
(23, '15:30:00', 0, 15, 30),
(23, '16:37:00', 0, 16, 37),
(27, '02:55:00', 0, 2, 55),
(27, '10:17:00', 0, 10, 17),
(27, '10:51:00', 0, 10, 51);

-- --------------------------------------------------------

--
-- 資料表結構 `MBTI_analysis`
--

CREATE TABLE `MBTI_analysis` (
  `MBTI` varchar(5) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Category` varchar(5) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Personality` varchar(250) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `No1` varchar(5) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `No2` varchar(5) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `No3` varchar(5) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 傾印資料表的資料 `MBTI_analysis`
--

INSERT INTO `MBTI_analysis` (`MBTI`, `Category`, `Personality`, `No1`, `No2`, `No3`) VALUES
('ENFJ', '主人公', '主人公人格類型的人是天生的領導者，充滿激情，魅力四射。 這類型人格的人約占人口的 2%，他們常常是我們的政客，教練和老師，幫助、啟發他人取得成就並造福整個世界。 他們渾身散發著天然的自信，潛移默化地影響著周圍的人，也能夠指導他人團結協作，幫助他們提升自己並改進社區，而他們自己也可從中獲得自豪感與快樂。', '柯基', '吉娃娃', '拉布拉多犬'),
('ENFP', '競選者', '競選者人格類型的人是真正富有自由精神的人。 他們常常是聚會上的焦點，但是與眼前的興奮和快樂相比，他們更享受與人們建立的社會和情感聯繫。 富有魅力，獨立，精力充沛且有同情心，占人口 7% 的他們在人群中隨處可見。', '柯基', '吉娃娃', '拉布拉多犬'),
('ENTJ', '指揮官', '指揮官人格類型的人是天生的領導者。 這種人格類型的人天生具有魅力和信心，他們所散發的權威性能召集大家為著一個共同目標努力。 但與領導者人格類型有所不同的是，他們的性格中有著近乎殘酷的理性，用強大的動力、堅定的決心和鋒芒畢露的思想實現為自己制定的一切目標。 好在只有 3% 的人口具有這種人格類型，否則就會無情碾壓那些剩下的大多數膽小又敏感的人格類型 — 但是對於許多我們習以為常的公司和機構，我們都要感謝他們的貢獻。', '博美', '法鬥', '瑪爾濟斯'),
('ENTP', '辯論家', '辯論家人格類型的人是故意持相反意見的人，善於把觀點和信條剪得支離破碎並撒在空中給所有人看。 與更有決心的人格類型相比，“辯論家”這樣做並非想要取得更深層的含義或戰略性的目標，而僅僅因為有趣。 沒人比“辯論家”們更喜歡頭腦的交鋒，因為這可以給他們一個運用聰明才智，連結不同想法來證明自己觀點的機會。', '博美', '瑪爾濟斯', '法鬥'),
('ESFJ', '執政官', '最適合形容“執政官”們的詞就是“受歡迎”了 — 這也符合常理，因為他們大約占人口的 12%，是非常常見的類型。 在中學裡，他們常常是啦啦隊員或四分衛，在聚光燈下帶領著隊伍走向勝利和榮譽。 在以後的人生裡，“執政官”同樣享受去支援他們的朋友和愛著的人，組織聚會，盡一切可能讓每個人開心。', '柯基', '拉布拉多犬', '吉娃娃'),
('ESFP', '表演者', '如果有人總是不由自主地開始又唱又跳，可以將其劃分為表演者人格類型。 表演者人格類型的人會沉醉於當前的興奮狀態，而且希望人人如此。 說起激勵他人，給他人打氣助威，表演者人格類型的人會毫不吝惜自己的時間和精力，令人難以招架，任何其他人格類型在這方面都不能與之相提並論。', '吉娃娃', '柯基', '哈士奇'),
('ESTJ', '執行官', '執行官人格類型的人是傳統和秩序的代表，利用他們對正確，錯誤，和社會標準的理解來團結家庭和社區。 他們誠實，愛奉獻，有尊嚴，他們的明確建議和指導被人看重，也願意披荊斬棘，帶領大家努力前行。 他們會因為團結大家而驕傲，常常承擔起社區組織者的角色，努力組織大家一起慶祝當地重要的節日，或守護著那些使家庭和社區緊密相連的傳統價值觀。', '米克斯', '博美', '瑪爾濟斯'),
('ESTP', '企業家', '企業家人格類型的人對周圍的環境頗有影響 — 在聚會上發現他們的最好方式就是去找那些在人群中穿梭自如的人。 他們帶著直接而樸實的幽默談笑風生，喜歡成為人群中的焦點。 如果觀眾被邀請上臺，他們會自薦，或推薦一個害羞的朋友。理論，抽象概念和單調乏味的關於全球問題及其影響的討論很難令他們長時間保持興趣。 他們的對話充滿活力，也不乏智慧，他們喜歡討論此時此刻的事，或者乾脆動身去做。 企業家人格類型的人不會瞻前顧後，他們會在前進的過程中改正錯誤，而不是閑坐著思考備用計畫和撤退方案。', '拉布拉多犬', '吉娃娃', '柯基'),
('INFJ', '提倡者', '提倡者人格類型的人非常稀少，只有不到 1% 的人口屬於這種類型，但他們對世界的貢獻不容忽視。 他們具有與生俱來的理想主義和道德感，但真正令他們與其他理想主義人格類型區分開來的是，他們果斷決絕。他們不是懶散的空想家，而是能腳踏實地完成目標，留下深遠的積極影響的人。', '拉布拉多犬', '柴犬', '柯基'),
('INFP', '調停者', '調停者人格類型的人是真正的理想主義者，他們總是從最壞的人和事中尋找最好的一面，想方設法讓情況變得更好。 雖然可能看起來冷靜，內向甚至害羞，但他們內心的火焰和熱情可以光芒四射。 他們僅占人口的 4%，常常被人誤解，但當他們找到志同道合的人時，他們之間的和諧就像是快樂和靈感的源泉。', '吉娃娃', '柯基', '哈士奇'),
('INTJ', '建築師', '人們常說，高處不勝寒。作為人數最少且戰略能力最強的人格類型之一，“建築師”們對此深有體會。 他們僅占人口的 2%，女性則更為稀少，只有 0.8%。這讓他們很難找到志同道合能夠與其過人的智慧和審慎的思考方式比肩的同類。 建築師人格類型的人想像力豐富卻很果斷，雄心壯志但注重隱私，充滿好奇心但從不浪費精力。', '吉娃娃', '拉布拉多犬', '哈士奇'),
('INTP', '邏輯學家', '只有 3% 的人口為邏輯學家型人格，極為罕見，儘管如此，他們也並不以為意，因為他們根本不屑與“平庸”為伍。 邏輯學家們展現出積極主動的創造性，異于常人的視角以及永不枯竭的智慧，這都令他們深感自豪。 人們常常將邏輯學家稱為哲學家、思考者，或是愛空想的教授，在歷史的長河中，許多科學發現就是他們的智慧之花結出的豐碩果實。', '柴犬', '哈士奇', '拉布拉多犬'),
('ISFJ', '守衛者', '守衛者人格類型是一個很獨特的類型，他們的許多品質都與他們自身的特質不相符。 雖然非常照顧他人的感受，一旦到了需要保護其家人或朋友的時候，會變得非常強悍；雖然安靜內向，卻有很好的社交技巧和強大的社會關係；雖然追求安全和穩定，但只要他們得到了理解和尊重，就願意接受改變。 和很多事物一樣，具有守衛者人格類型的人作為一個整體不可小覷，他們的身份由他們如何使用這些強項而定義。', '吉娃娃', '哈士奇', '柯基'),
('ISFP', '探險家', '探險家人格類型的人是真正的藝術家，這並不是說他們是通常意義上的興高采烈到郊外畫幾棵小樹的畫家。 但他們通常都精於此道。 他們會運用審美，設計，甚至選擇和行動來打破社會常規。 探險家人格類型的人喜歡用美感和行為方面的實驗來顛覆傳統的期望。很有可能他們已經說過不止一次“不要控制我！”', '博美', '柯基', '米克斯'),
('ISTJ', '軍需官', '軍需官人格類型的人被認為是數量最多的，大約占人口總數的 13%。 他們有很多明顯的特徵，例如正直，務實，恪盡職守，使他們深受家庭以及擁護傳統，規則，標準的組織的青睞，比如律所，監管部門和軍隊。 這種人格類型的人願意為自己的行為負責，為努力完成目標所做的一切感到驕傲。他們會毫不吝嗇時間和精力，耐心準確地完成每個任務。', '柯基', '吉娃娃', '拉布拉多犬'),
('ISTP', '鑑賞家', '鑑賞家人格類型的人喜歡用雙手和眼睛去探索事物，他們通過冷靜的理性主義和精神飽滿的好奇心來感知和體驗這個世界。 擁有這種人格的人是天生的製造者，他們在不同的項目中穿梭，從創造有用、充足的產物中獲得樂趣，並在創造的過程中從外界學習。 藝術能手人格類型的人通常是機械師和工程師，他們親手拆卸東西，並把它們安裝還原到比之前更好一點的狀態，從中獲得極大的樂趣。', '柯基', '吉娃娃', '哈士奇');

-- --------------------------------------------------------

--
-- 資料表結構 `medtime_remind`
--

CREATE TABLE `medtime_remind` (
  `pid` int(11) NOT NULL,
  `mtime` time NOT NULL,
  `tocheck` int(5) NOT NULL,
  `hour` int(11) NOT NULL,
  `minute` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 傾印資料表的資料 `medtime_remind`
--

INSERT INTO `medtime_remind` (`pid`, `mtime`, `tocheck`, `hour`, `minute`) VALUES
(3, '15:51:00', 0, 15, 51),
(4, '05:02:00', 1, 5, 2),
(4, '11:14:00', 1, 11, 14),
(4, '12:27:00', 0, 12, 27),
(4, '14:30:00', 0, 14, 30),
(4, '20:06:00', 0, 20, 6),
(5, '13:00:00', 0, 13, 0),
(8, '12:00:00', 0, 12, 0),
(21, '20:28:00', 0, 20, 28),
(23, '21:38:00', 0, 21, 38),
(27, '03:51:00', 0, 3, 51),
(27, '06:40:00', 0, 6, 40),
(27, '08:09:00', 0, 8, 9),
(27, '09:32:00', 0, 9, 32);

-- --------------------------------------------------------

--
-- 資料表結構 `person`
--

CREATE TABLE `person` (
  `email` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(15) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 傾印資料表的資料 `person`
--

INSERT INTO `person` (`email`, `password`) VALUES
('a1073321', '5555'),
('a1083301', 'eee'),
('a1083302', 'kkk'),
('a1083303', 'fff'),
('a1083304', 'ggg'),
('a1083305', 'hhh'),
('a1083306', 'iii'),
('a1083307', 'jjj'),
('a1083312', '666666'),
('a1083320', 'ccc'),
('a1083332', 'ddd'),
('angela', '123456'),
('billy', 'billy17511'),
('ipet', 'ipetwpww'),
('rome', 'andy1234'),
('test', 'ttt123'),
('test1', '2222'),
('test2', '666666');

-- --------------------------------------------------------

--
-- 資料表結構 `pet`
--

CREATE TABLE `pet` (
  `email` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `petname` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `birthday` date NOT NULL,
  `variety` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `pid` int(11) NOT NULL,
  `sex` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `uri` varchar(500) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 傾印資料表的資料 `pet`
--

INSERT INTO `pet` (`email`, `petname`, `birthday`, `variety`, `pid`, `sex`, `uri`) VALUES
('a1073321', '橘子', '2018-09-08', '柴犬', 1, 'girl', ''),
('a1083302', '橘子', '2020-05-05', '米克斯', 2, 'girl', ''),
('a1073321', '柚子\n', '2019-09-08', '哈士奇', 3, 'boy', ''),
('a1083312', 'Justin', '2019-05-25', '吉娃娃', 4, 'boy', 'content://com.google.android.apps.docs.storage/document/acc%3D1%3Bdoc%3Dencoded%3DDCeRs3qsk%2F1J8UK7GznxihS26wtm6u3tWTykNGNH6a%2FKPdxzBtXz'),
('a1083320', 'Peter', '2019-09-25', '瑪爾濟斯', 5, 'boy', ''),
('a1083332', '衰臉', '2016-02-25', '米克斯', 6, 'girl', ''),
('a1083301', 'Lucky', '2018-05-25', '博美', 7, 'girl', ''),
('a1083302', 'Mark', '2017-07-08', '哈士奇', 8, 'boy', ''),
('a1083303', 'Lucas', '2016-08-08', '柯基', 9, 'boy', ''),
('a1083304', '哈魯', '2019-02-20', '拉布拉多犬', 10, 'boy', ''),
('a1083305', '嚕嚕', '2020-06-04', '法鬥', 11, 'girl', ''),
('ipet', 'djel2', '2018-01-01', '哈士奇', 13, 'boy', ''),
('angel', 'angel', '2006-12-25', '柴犬', 15, 'girl', ''),
('billy', '貢丸', '2018-03-03', '哈士奇', 17, 'boy', 'content://media/external/images/media/314'),
('test', 'hello', '2018-01-01', '哈士奇', 19, 'boy', 'content://media/external/images/media/310'),
('angela', 'Angel', '2015-12-06', '柯基', 21, 'boy', ''),
('test2', 'Hank', '2015-08-26', '米克斯', 23, 'girl', 'content://media/external/images/media/313'),
('a', 'bb', '2000-12-04', '哈士奇', 25, 'boy', 'content://com.android.providers.media.documents/document/image%3A342110'),
('rome', 'yui', '2018-06-22', '柴犬', 27, 'boy', 'content://com.android.providers.downloads.documents/document/3016');

-- --------------------------------------------------------

--
-- 資料表結構 `vactime_remind`
--

CREATE TABLE `vactime_remind` (
  `pid` int(11) NOT NULL,
  `category` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `mtime` date NOT NULL,
  `tocheck` varchar(5) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 傾印資料表的資料 `vactime_remind`
--

INSERT INTO `vactime_remind` (`pid`, `category`, `mtime`, `tocheck`) VALUES
(1, '狂犬病', '2018-12-01', 't'),
(3, '五合一', '2019-12-25', 't'),
(4, '五合一', '2022-12-10', 't'),
(4, '八合一', '2022-12-17', 't'),
(4, '八合一', '2022-12-24', 't'),
(4, '十合一', '2022-12-10', 't'),
(4, '十合一', '2022-12-17', 't'),
(5, '八合一', '2022-12-17', 't'),
(5, '十合一', '2022-12-17', 't'),
(6, '八合一', '2016-05-30', 't'),
(7, '十合一', '2018-06-25', 't'),
(11, '萊姆病', '2020-07-20', 'f'),
(21, '五合一', '2022-12-11', 't'),
(21, '五合一', '2022-12-12', 't'),
(21, '五合一', '2022-12-13', 't'),
(21, '八合一', '2022-12-12', 't'),
(27, '五合一', '2022-12-12', 't'),
(27, '五合一', '2022-12-23', 't'),
(27, '萊姆病', '2022-12-12', 't'),
(27, '萊姆病', '2022-12-23', 't');

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `dewtime_remind`
--
ALTER TABLE `dewtime_remind`
  ADD PRIMARY KEY (`pid`,`category`,`dtime`);

--
-- 資料表索引 `exctime_remind`
--
ALTER TABLE `exctime_remind`
  ADD PRIMARY KEY (`pid`,`etime`);

--
-- 資料表索引 `feedtime_remind`
--
ALTER TABLE `feedtime_remind`
  ADD PRIMARY KEY (`pid`,`ftime`);

--
-- 資料表索引 `MBTI_analysis`
--
ALTER TABLE `MBTI_analysis`
  ADD PRIMARY KEY (`MBTI`);

--
-- 資料表索引 `medtime_remind`
--
ALTER TABLE `medtime_remind`
  ADD PRIMARY KEY (`pid`,`mtime`) USING BTREE;

--
-- 資料表索引 `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`email`);

--
-- 資料表索引 `pet`
--
ALTER TABLE `pet`
  ADD PRIMARY KEY (`pid`);

--
-- 資料表索引 `vactime_remind`
--
ALTER TABLE `vactime_remind`
  ADD PRIMARY KEY (`pid`,`category`,`mtime`);

--
-- 已傾印資料表的限制式
--

--
-- 資料表的限制式 `exctime_remind`
--
ALTER TABLE `exctime_remind`
  ADD CONSTRAINT `exctime_remind_name` FOREIGN KEY (`pid`) REFERENCES `pet` (`pid`);

--
-- 資料表的限制式 `feedtime_remind`
--
ALTER TABLE `feedtime_remind`
  ADD CONSTRAINT `feedremind_name` FOREIGN KEY (`pid`) REFERENCES `pet` (`pid`);

--
-- 資料表的限制式 `medtime_remind`
--
ALTER TABLE `medtime_remind`
  ADD CONSTRAINT `medicine_remind_name` FOREIGN KEY (`pid`) REFERENCES `pet` (`pid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
