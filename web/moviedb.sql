-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 10, 2023 at 11:52 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `moviedb`
--

-- --------------------------------------------------------

--
-- Table structure for table `genres`
--

CREATE TABLE `genres` (
  `genreID` int(11) NOT NULL,
  `genreName` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `genres`
--

INSERT INTO `genres` (`genreID`, `genreName`) VALUES
(1, 'Action'),
(2, 'Comedy'),
(3, 'Romance'),
(4, 'Western'),
(5, 'Drama'),
(6, 'Fantasy'),
(7, 'Sci-fi'),
(8, 'Horror'),
(9, 'Crime');

-- --------------------------------------------------------

--
-- Table structure for table `movies`
--

CREATE TABLE `movies` (
  `movieID` int(11) NOT NULL,
  `title` text NOT NULL,
  `summary` text NOT NULL,
  `releaseDate` date NOT NULL,
  `genreID` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `movies`
--

INSERT INTO `movies` (`movieID`, `title`, `summary`, `releaseDate`, `genreID`) VALUES
(13, 'Die Hard', 'NYPD officer John McClane battles a group of terrorists led by Hans Gruber, who have taken hostages in a Los Angeles skyscraper, in this classic action film.', '1988-07-15', 1),
(14, 'Mad Max: Fury Road', 'In a post-apocalyptic wasteland, Max teams up with Imperator Furiosa to escape a tyrannical warlord and his army in an epic, high-octane chase.', '2015-05-15', 1),
(15, 'John Wick', 'When a retired hitman\'s beloved dog is killed, he seeks vengeance against those who wronged him, unleashing a relentless and stylishly choreographed rampage.', '2014-10-24', 1),
(16, 'The Dark Knight', 'Batman faces the Joker, a criminal mastermind who seeks to create chaos in Gotham City, pushing the caped crusader to his limits in this intense superhero action thriller.', '2008-07-18', 1),
(17, 'The Matrix', 'Neo, a computer hacker, discovers the truth about his reality and joins a group of rebels in a battle against intelligent machines that have enslaved humanity.', '1999-03-31', 1),
(18, 'Superbad', 'Two high school friends, Seth and Evan, embark on a wild and hilarious adventure to buy alcohol for a party, leading to a series of misadventures that test their friendship.', '2007-08-17', 2),
(19, 'Bridesmaids', 'Annie, a down-on-her-luck single woman, competes with her best friend\'s other bridesmaids for the coveted role of Maid of Honor, leading to a series of outrageous and comedic events.', '2007-05-13', 2),
(20, 'The Grand Budapest Hotel', 'This Wes Anderson film follows the adventures of a hotel concierge and his protÃ©gÃ© as they become embroiled in a murder mystery, blending quirky humor with a visually stunning narrative.', '2014-03-06', 2),
(21, 'Anchorman: The Legend of Ron Burgundy', 'Ron Burgundy, a 1970s news anchor with an inflated ego, faces comedic challenges when a talented and ambitious female journalist joins the news team, disrupting the status quo.', '2004-07-09', 2),
(22, 'The Hangover', 'A bachelor party in Las Vegas goes hilariously wrong when the groomsmen wake up with no memory of the previous night and must retrace their steps to find the missing groom.', '2009-06-05', 2),
(23, 'The Notebook', 'In this adaptation of Nicholas Sparks\' novel, Noah and Allie, two young lovers from different social backgrounds, embark on a passionate and tumultuous journey that spans decades.', '2004-06-25', 3),
(24, 'Before Sunrise', 'Jesse and CÃ©line, two strangers, meet on a train and decide to spend a night exploring Vienna together, forging a deep connection as they discuss life, love, and the passage of time.', '1995-01-27', 3),
(25, 'Pride and Prejudice', 'Based on Jane Austen\'s classic novel, this film follows the complex relationship between Elizabeth Bennet and Mr. Darcy as they navigate social expectations, misunderstandings, and, ultimately, love.', '2005-11-23', 3),
(26, 'Eternal Sunshine of the Spotless Mind', 'Joel and Clementine undergo a procedure to erase memories of each other after a painful breakup, but as the process unfolds, they rediscover the depth of their connection and the value of shared experiences.', '2004-03-13', 3),
(27, 'La La Land', 'In this modern musical, aspiring actress Mia and jazz musician Sebastian cross paths in Los Angeles. As they pursue their dreams, their love story unfolds against the backdrop of the city\'s vibrant and challenging entertainment industry.', '2016-12-09', 3),
(28, 'The Good, the Bad and the Ugly', 'Directed by Sergio Leone, this iconic Spaghetti Western follows three gunslingersâ Blondie (the Good), Angel Eyes (the Bad), and Tuco (the Ugly)âas they search for a buried treasure during the American Civil War.', '1966-12-23', 4),
(29, 'Unforgiven', 'Directed by Clint Eastwood, \"Unforgiven\" tells the story of a retired gunslinger who takes on one last job, confronting his violent past in a town where justice is meted out with a brutal hand.', '1992-08-07', 4),
(30, 'Django Unchained', 'Directed by Quentin Tarantino, \"Django Unchained\" follows Django, a freed slave turned bounty hunter, as he partners with a German bounty hunter to rescue his wife from a brutal plantation owner in the pre-Civil War South.', '2012-12-25', 4),
(31, 'The Magnificent Seven', 'In this classic Western directed by John Sturges, a group of seven gunmen is hired to protect a Mexican village from a group of bandits led by the ruthless Calvera.', '1960-10-23', 4),
(32, 'True Grit', 'Directed by the Coen Brothers, \"True Grit\" is a remake of the 1969 film and follows a young girl seeking revenge for her father\'s murder. She enlists the help of a U.S. Marshal, Rooster Cogburn, to track down the killer in Indian territory.', '2010-12-22', 4),
(33, 'The Shawshank Redemption', 'Adapted from Stephen King\'s novella, this film tells the story of Andy Dufresne, a banker sentenced to life in Shawshank State Penitentiary. The movie explores his friendship with fellow inmate Red and his quest for redemption.', '1994-09-23', 5),
(34, 'Forrest Gump', '\"Forrest Gump\" follows the life of a man with a low IQ, played by Tom Hanks, as he unintentionally influences several defining moments in American history. The film explores themes of love, destiny, and the human spirit.', '1994-07-06', 5),
(35, 'The Godfather', 'Directed by Francis Ford Coppola, \"The Godfather\" is a crime drama that chronicles the Corleone family\'s patriarch, Vito Corleone, as he transfers control of his empire to his reluctant son Michael.', '1972-03-24', 5),
(36, 'Schindler\'s List', 'Directed by Steven Spielberg, \"Schindler\'s List\" tells the true story of Oskar Schindler, a German businessman who saved the lives of more than a thousand Jewish refugees during the Holocaust.', '1993-12-15', 5),
(37, 'The Social Network', 'Directed by David Fincher, \"The Social Network\" explores the founding and tumultuous rise of Facebook. The film delves into themes of friendship, betrayal, and the impact of social media on relationships.', '2010-10-01', 5),
(38, 'The Lord of the Rings: The Fellowship of the Ring', 'Directed by Peter Jackson, this epic fantasy film is the first installment in \"The Lord of the Rings\" trilogy. It follows Frodo Baggins and his companions as they set out on a perilous journey to destroy the One Ring and prevent the dark lord Sauron from conquering Middle-earth.', '2001-12-19', 6),
(39, 'Harry Potter and the Sorcerer\'s Stone', 'Based on J.K. Rowling\'s novel, this film introduces the world to the young wizard Harry Potter as he begins his magical education at Hogwarts School of Witchcraft and Wizardry, uncovering secrets about his past along the way.', '2001-11-16', 6),
(40, 'Pan\'s Labyrinth', 'Directed by Guillermo del Toro, \"Pan\'s Labyrinth\" is a dark fantasy set in post-Civil War Spain. It follows the journey of a young girl named Ofelia, who discovers a mythical and dangerous world while facing the harsh realities of her own.', '2006-10-11', 6),
(41, 'The Chronicles of Narnia: The Lion, the Witch and the Wardrobe', 'Adapted from C.S. Lewis\'s novel, this film tells the tale of the Pevensie siblings, who enter the magical land of Narnia. They join forces with the lion Aslan to defeat the White Witch and restore peace to the realm.', '2005-12-09', 6),
(42, 'Avatar', 'Directed by James Cameron, \"Avatar\" is a visually stunning science fiction and fantasy film set on the alien moon Pandora. It follows Jake Sully, a paraplegic former Marine, as he becomes involved in the conflict between human interests and the indigenous Na\'vi people.', '2009-12-18', 6),
(43, 'Blade Runner', 'Directed by Ridley Scott, \"Blade Runner\" is a neo-noir science fiction film set in a dystopian future Los Angeles. It follows Rick Deckard, a \"blade runner\" tasked with hunting down bioengineered beings known as replicants.', '1982-06-25', 7),
(44, 'Inception', 'Directed by Christopher Nolan, \"Inception\" explores the world of shared dreaming. Dom Cobb, a skilled thief, is tasked with planting an idea into someone\'s mind by infiltrating their dreams.', '2010-07-08', 7),
(45, 'Star Wars: Episode IV - A New Hope', 'Created by George Lucas, the original \"Star Wars\" film follows Luke Skywalker as he joins the Rebel Alliance to fight against the evil Galactic Empire. The movie is a space opera with iconic characters and a classic battle between good and evil.', '1977-05-25', 7),
(46, 'Ex Machina', 'In \"Ex Machina,\" a young programmer is invited to administer the Turing test to an intelligent humanoid robot. As the test progresses, he becomes entangled in a complex web of ethical dilemmas and deception.', '2014-01-21', 7),
(47, 'The Exorcist', 'Directed by William Friedkin, \"The Exorcist\" is a classic horror film about a young girl possessed by a malevolent demon. A Catholic priest is brought in to perform an exorcism to save her soul.', '1973-12-26', 8),
(48, 'Halloween', 'Directed by John Carpenter, \"Halloween\" follows the masked killer Michael Myers as he escapes from a mental institution and returns to his hometown to terrorize a babysitter, Laurie Strode.', '1978-10-25', 8),
(49, 'he Shining', 'Directed by Stanley Kubrick and based on Stephen King\'s novel, \"The Shining\" tells the story of the Torrance family as they stay in an isolated and haunted hotel. Jack Torrance, played by Jack Nicholson, slowly descends into madness.', '1980-05-23', 8),
(50, 'The Ring', 'Directed by Gore Verbinski, \"The Ring\" is a psychological horror film about a cursed videotape that causes the viewer to die seven days after watching it. A journalist investigates the mysterious tape to break the curse.', '2002-10-18', 8),
(51, 'Get Out', 'Directed by Jordan Peele, \"Get Out\" is a horror-thriller that explores racial tensions. A young African American man visits his white girlfriend\'s family estate and uncovers disturbing secrets about the community.', '2017-02-24', 8),
(52, 'Pulp Fiction', 'Directed by Quentin Tarantino, \"Pulp Fiction\" weaves interconnected stories of crime, redemption, and the underworld in a non-linear narrative. The film features hitmen, a boxer, and other characters whose lives intersect in unexpected ways.', '1994-10-14', 9),
(53, 'Heat', 'Directed by Michael Mann, \"Heat\" is a crime thriller that follows the intense cat-and-mouse game between a group of professional bank robbers led by Neil McCauley (Robert De Niro) and the dedicated LAPD detective Vincent Hanna (Al Pacino).', '1995-12-15', 9),
(54, 'The Departed', 'Directed by Martin Scorsese, \"The Departed\" is a crime thriller set in Boston, where an undercover cop and a mole in the police force try to identify each other while infiltrating an Irish gang and the Massachusetts State Police, respectively.', '2006-10-06', 9),
(55, 'No Country for Old Men', 'Directed by Joel and Ethan Coen, this crime thriller is based on Cormac McCarthy\'s novel. The film follows a hunter who stumbles upon a drug deal gone wrong, leading to a violent pursuit involving a relentless hitman with a unique weapon.', '2007-11-09', 9),
(56, 'Wish', 'In âWish,â Asha, a sharp-witted idealist, makes a wish so powerful that it is answered by a cosmic forceâa little ball of boundless energy called Star. Together, Asha and Star confront a most formidable foeâthe ruler of Rosas, King Magnificoâto save her community and prove that when the will of one courageous human connects with the magic of the stars, wondrous things can happen.', '2023-11-22', 1),
(57, 'Silent Night', 'From legendary director John Woo and the producer of John Wick comes this gritty revenge tale of a tormented father (Joel Kinnaman) who witnesses his young son die when caught in a gangâs crossfire on Christmas Eve.', '2023-12-01', 1),
(58, 'Animal', 'This is the story of a son whose love for his father knows no bounds. As their bond begins to fracture, a chain of extraordinary events unfolds causing the son to undergo a remarkable transformation consumed by a thirst for vengeance.', '2023-12-01', 5),
(59, 'Five Nights at Freddy\'s', 'A troubled security guard begins working at Freddy Fazbear\'s Pizzeria. While spending his first night on the job, he realizes the late shift at Freddy\'s won\'t be so easy to make it through. Also streaming on Peacock.', '2023-10-27', 8);

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `reviewID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `movieID` int(11) NOT NULL,
  `rating` int(10) NOT NULL,
  `comment` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`reviewID`, `userID`, `movieID`, `rating`, `comment`) VALUES
(26, 11, 13, 10, 'Great movie, I really enjoyed the action here. One man on a mission to save the day. I would recommend this movie to anyone interested in a guns blazing fast paced action movie.'),
(27, 11, 59, 7, 'I went to go see this movie because I played the games. It was good I was a little disappointed in a few places but for the most part I enjoyed the movie.'),
(28, 11, 58, 8, 'A few friends made me go see this movie and I wasn\'t disappointed. It definitely lived up to the hype that my friends made it out to be.'),
(29, 11, 57, 9, 'Action packed and a great watch. Went to see it because it was directed by the same guy that directed John Wick and he didn\'t disappoint.'),
(30, 11, 56, 7, 'Feel good movie that had some sad moments. Great movie for a date night.'),
(31, 11, 14, 8, 'Really good movie that was full of action! Mad Max has always been a favorite of mine and this movie didn\'t leave me wanting.'),
(32, 11, 15, 10, 'Full of action and cool weapons. A must watch for all action movie fans.'),
(33, 12, 13, 10, 'A must watch for action movie fans. I love this series of movies and go back and watch them regularly.'),
(34, 12, 19, 7, 'This kind of movie usually isn\'t something that I enjoy but this one was pretty good. '),
(35, 12, 57, 9, 'Action packed and had a good story. Really enjoyed this movie, recommended watch!'),
(36, 12, 59, 9, 'This movie had me skeptical from the get go, but I ended up really liking it. If you enjoyed the video games you will enjoy this movie.'),
(37, 12, 40, 9, 'This is one of my favorite movies. Definitely something that everyone needs to watch.'),
(38, 12, 42, 10, 'I heard so many negative things about this movie that I ended up not watching it till recently. I don\'t know what everyone else was on about this movie was great! Definitely a longer watch than most but really enjoyed this. ');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userID` int(11) NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL,
  `userType` text NOT NULL,
  `email` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `username`, `password`, `userType`, `email`) VALUES
(3, 'admin', '7899f31d2a50c68e502e69ecbaed75ff$4096$65335cc7a159fc55a357bc57bcba8f4bd8a5beaa5a78c3038bab65fc49ae55f2', 'admin', 'admin@gmail.com'),
(11, 'DimaDog', '0a2f6bdca8c03f63efd405484f26c3a8$4096$a3164259b28ca08fcd8ce427c16db31688f79e4aeffb7142f071e26c46e677e4', 'user', 'Dima@goodboy.com'),
(12, 'ConnorPup', '6e1bd5b36b6a1e29b7ac9905b4f54e67$4096$6098ba21e98eea6b59c517f19fa3fabc3eaf4823acab702082a70e5d62c77e2e', 'user', 'connor@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `genres`
--
ALTER TABLE `genres`
  ADD PRIMARY KEY (`genreID`);

--
-- Indexes for table `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`movieID`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`reviewID`),
  ADD KEY `fk_movie` (`movieID`),
  ADD KEY `fk_user` (`userID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `genres`
--
ALTER TABLE `genres`
  MODIFY `genreID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `movies`
--
ALTER TABLE `movies`
  MODIFY `movieID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `reviewID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `fk_movie` FOREIGN KEY (`movieID`) REFERENCES `movies` (`movieID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
