--Query the names of all the Japanese cities in the CITY table. The COUNTRYCODE for Japan is JPN.
--The CITY table is described as follows:
--
--FIELD  TYPE
--ID     NUMBER
--NAME   VARCHAR2(17)
--COUNTRYCODE VARCHAR2(3)
--DISTRICT VARCHAR2(20)
--POPULATION NUMBER

SELECT NAME FROM CITY WHERE COUNTRYCODE = 'JPN'


--Query a list of CITY and STATE from the STATION table.

SELECT DISTINCT CITY, STATE FROM STATION;

--Query a list of CITY names from STATION for cities that have an even ID number.
-- Print the results in any order, but exclude duplicates from the answer.
SELECT DISTINCT CITY FROM STATION WHERE MOD(ID,2)=0 ORDER BY CITY ASC;


--Find the difference between the total number of CITY entries in the table and the number
-- of distinct CITY entries in the table.
--For example, if there are three records in the table with CITY values 'New York', 'New York', 'Bengalaru',
-- there are 2 different city names: 'New York' and 'Bengalaru'. The query returns , because .

SELECT COUNT(CITY)-COUNT(DISTINCT CITY) FROM STATION;


--Query the two cities in STATION with the shortest and longest CITY names, as well as
--their respective lengths (i.e.: number of characters in the name). If there is more
--than one smallest or largest city, choose the one that comes first when ordered alphabetically.
--Sample Input
--
--For example, CITY has four entries: DEF, ABC, PQRS and WXY.
--
--Sample Output
--
--ABC 3
--PQRS 4
--Explanation
--
--When ordered alphabetically, the CITY names are listed as ABC, DEF, PQRS, and WXY, with lengths
-- and . The longest name is PQRS, but there are  options for shortest named city. Choose ABC,
-- because it comes first alphabetically.
--
--Note
--You can write two separate queries to get the desired output. It need not be a single query.

select city, length(city) from station order by length(city) DESC,city ASC fetch first row only;
select city, length(city) from station order by length(city) asc ,city asc fetch first row only;


--Query the list of CITY names starting with vowels (i.e., a, e, i, o, or u) from STATION.
--Your result cannot contain duplicates.

SELECT DISTINCT CITY FROM STATION WHERE SUBSTRING(CITY,1,1) in ('A', 'E', 'I', 'O', 'U') ORDER BY CITY ASC
SELECT DISTINCT CITY FROM STATION WHERE CITY LIKE 'A%' OR CITY LIKE 'E%' OR CITY LIKE 'I%' OR CITY LIKE 'O%' OR CITY LIKE 'U%';

-- Query the list of CITY names ending with vowels (a, e, i, o, u) from STATION. Your result cannot contain duplicates.
SELECT DISTINCT CITY FROM STATION WHERE CITY LIKE '%a' OR CITY LIKE '%e' OR CITY LIKE '%i' OR CITY LIKE '%o' OR CITY LIKE '%u';

-- Query the list of CITY names from STATION which have vowels (i.e., a, e, i, o, and u) as both their first and last characters. Your result cannot contain duplicates.

SELECT DISTINCT CITY FROM STATION WHERE (CITY LIKE 'A%' OR CITY LIKE 'E%' OR CITY LIKE 'I%' OR CITY LIKE 'O%' OR CITY LIKE 'U%') AND  (CITY LIKE '%a' OR CITY LIKE '%e' OR CITY LIKE '%i' OR CITY LIKE '%o' OR CITY LIKE '%u)';


-- Query the list of CITY names from STATION that do not start with vowels. Your result cannot contain duplicates.
SELECT DISTINCT CITY FROM STATION WHERE SUBSTRING(CITY,1,1) not in ('A', 'E', 'I', 'O', 'U') ORDER BY CITY ASC

--Query the list of CITY names from STATION that do not end with vowels. Your result cannot contain duplicates.
SELECT DISTINCT CITY FROM STATION WHERE RIGHT(CITY,1) not in ('a', 'e', 'i', 'o', 'u') ORDER BY CITY ASC

--Query the list of CITY names from STATION that either do not start with vowels or do not end with vowels. Your result cannot contain duplicates.
SELECT DISTINCT CITY FROM STATION WHERE SUBSTRING(CITY,1,1) not in ('A', 'E', 'I', 'O', 'U') OR RIGHT(CITY,1) not in ('a', 'e', 'i', 'o', 'u')  ORDER BY CITY ASC

-- Query the list of CITY names from STATION that do not start with vowels and do not end with vowels. Your result cannot contain duplicates.
SELECT DISTINCT CITY FROM STATION WHERE SUBSTRING(CITY,1,1) not in ('A', 'E', 'I', 'O', 'U') AND RIGHT(CITY,1) not in ('a', 'e', 'i', 'o', 'u')  ORDER BY CITY ASC


--Write a query identifying the type of each record in the TRIANGLES table using its three side lengths.
--Output one of the following statements for each record in the table:
--
--Equilateral: It's a triangle with 3 sides of equal length.
--Isosceles: It's a triangle with 2 sides of equal length.
--Scalene: It's a triangle with 3 sides of differing lengths.
--Not A Triangle: The given values of A, B, and C don't form a triangle.

SELECT
CASE
WHEN (A+B <= C OR B+C <= A OR A+C <= B) THEN 'Not A Triangle'
WHEN (A=B and B=C) THEN 'Equilateral'
WHEN (A=B OR B=C OR C=A) THEN 'Isosceles'
ELSE 'Scalene'
END
FROM TRIANGLES

--Generate the following two result sets:
--Query an alphabetically ordered list of all names in OCCUPATIONS, immediately followed by the first letter of each
--profession as a parenthetical (i.e.: enclosed in parentheses). For example: AnActorName(A), ADoctorName(D),
--AProfessorName(P), and ASingerName(S).
--Query the number of ocurrences of each occupation in OCCUPATIONS. Sort the occurrences in ascending order, and output
--them in the following format:
--
--There are a total of [occupation_count] [occupation]s.
--where [occupation_count] is the number of occurrences of an occupation in OCCUPATIONS and [occupation] is
--the lowercase occupation name. If more than one Occupation has the same [occupation_count], they should be
--ordered alphabetically.
--
--Note: There will be at least two entries in the table for each type of occupation.
--
--Input Format
--
--The OCCUPATIONS table is described as follows:
--FIELD  TYPE
--NAME   VARCHAR2
--OCCUPATION   VARCHAR2

--Jane(A)
--Jenny(D)
--Julia(A)
--Ketty(P)
--Maria(A)
--Meera(S)
--Priya(S)
--Samantha(D)
--There are a total of 2 doctors.
--There are a total of 2 singers.
--There are a total of 3 actors.
--There are a total of 3 professors.
--

SELECT CONCAT(NAME, "(", LEFT(OCCUPATION, 1), ")") FROM OCCUPATIONS ORDER BY NAME ASC;
SELECT CONCAT("There are a total of ", COUNT(OCCUPATION)," ", Lower(OCCUPATION),"s.") FROM OCCUPATIONS GROUP by OCCUPATION ORDER BY COUNT(OCCUPATION) asc, LEFT(OCCUPATION, 1) asc;

--Pivot the Occupation column in OCCUPATIONS so that each Name is sorted alphabetically and
--displayed underneath its corresponding Occupation. The output column headers should be Doctor, Professor, Singer,
--and Actor, respectively.
--
--Note: Print NULL when there are no more names corresponding to an occupation.

select
    -- Get Names for Occupations for each RowOrder
    D, P, S, A
from (
    -- Row of RN, Occupation Group by RN for each Name
    select
        RN,
        max(case Occupation when 'Doctor' then Name end) as D,
        max(case Occupation when 'Professor' then Name end) as P,
        max(case Occupation when 'Singer' then Name end) as S,
        max(case Occupation when 'Actor' then Name end) as A
    from (
    -- Order Occupation, Name, RowNumber (of Name within List of same Occupation ord by Name asc)
            select
                Occupation, Name, row_number() over(partition by Occupation order by Name ASC) as RN
            from Occupations
         ) as NameWithRN
    group by RN
    ) as JustNames

--
--You are given a table, BST, containing two columns: N and P, where N represents the value of a node in Binary Tree, and P is the parent of N.
--
--
--
--Write a query to find the node type of Binary Tree ordered by the value of the node. Output one of the following for each node:
--
--Root: If node is root node.
--Leaf: If node is leaf node.
--Inner: If node is neither root nor leaf node.
--Sample Input
--
--
--
--Sample Output
--
--1 Leaf
--2 Inner
--3 Leaf
--5 Root
--6 Leaf
--8 Inner
--9 Leaf
--
--Explanation
--
--The Binary Tree below illustrates the sample:
--
--
--
SELECT BST.N, CASE
WHEN (BST.P is not  NULL and  MAX(BST1.N) is not NULL) then  "Inner"
WHEN (BST.P is NULL) then  "Root"
WHEN (MAX(BST1.N)  is NULL) then  "Leaf"
END
from BST left join (SELECT N, P from BST) as BST1 on BST1.P = BST.N
GROUP BY BST.N, BST.P
ORDER BY BST.N;


--Amber's conglomerate corporation just acquired some new companies. Each of the companies follows this hierarchy:
--Given the table schemas below, write a query to print the company_code, founder name, total number of lead managers,
--total number of senior managers, total number of managers, and total number of employees. Order your output by
--ascending company_code.

--select company_code, founder from Company;
--
--
--select lead_manager_code, company_code from Lead_Manager;
--select senior_manager_code, lead_manager_code, company_code from Senior_Manager;
--select manager_code, senior_manager_code, lead_manager_code, company_code from Manager;
--select employee_code, manager_code, senior_manager_code, lead_manager_code, company_code from Employee;
--

select company_code, founder from Company;
select lead_manager_code, company_code from Lead_Manager;
select senior_manager_code, lead_manager_code, company_code from Senior_Manager;
select manager_code, senior_manager_code, lead_manager_code, company_code from Manager;

select  J.company_code,  J.founder,
count(distinct(J.lead_manager_code)), count(distinct(J.senior_manager_code)),
count(distinct(J.manager_code)), count(distinct(J.employee_code))
from (
select C.company_code,  C.founder, employee_code, manager_code, senior_manager_code, lead_manager_code
from Employee as E join Company as C on C.company_code = E.company_code) As J
group by J.company_code, J.founder;




--A median is defined as a number separating the higher half of a data set from the lower half.
--Query the median of the Northern Latitudes (LAT_N) from STATION and round your answer to  decimal places.
with ranked as (
  select LAT_N,
    row_number() over (order by LAT_N) as r,
    count(LAT_N) over () as c
  from STATION
),
median as (
  select LAT_N
  from ranked
  where r in (floor((c+1)/2), ceil((c+1)/2))
)
select Round(avg(LAT_N), 4) from median

--Query the average population of all cities in CITY where District is California.
--
--Input Format
--
--The CITY table is described as follows:
--
--FIELD  TYPE
--ID     NUMBER
--NAME   VARCHAR2(17)
--COUNTRYCODE VARCHAR2(3)
--DISTRICT VARCHAR2(20)
--POPULATION NUMBER
select avg(POPULATION) from CITY where DISTRICT = 'California'


--Query the average population for all cities in CITY, rounded down to the nearest integer.
select Round(avg(POPULATION), 0) from CITY;


--Samantha was tasked with calculating the average monthly salaries for all employees in the EMPLOYEES table,
--but did not realize her keyboard's  key was broken until after completing the calculation. She wants your
--help finding the difference between her miscalculation (using salaries with any zeros removed), and the actual
--average salary.
--
--Write a query calculating the amount of error (i.e.:  average monthly salaries), and round it up to the next integer.
--
--Input Format
--
--The EMPLOYEES table is described as follows:
--
--FIELD  TYPE
--ID     NUMBER
--NAME   VARCHAR2(17)
--SALARY  NUMBER

SELECT CEIL(AVG(SALARY)-AVG(CAST(REPLACE(CONCAT(SALARY), "0", "") as UNSIGNED))) from EMPLOYEES;


--We define an employee's total earnings to be their monthly  worked, and the maximum total earnings to
--be the maximum total earnings for any employee in the Employee table. Write a query to find the maximum total
--earnings for all employees as well as the total number of employees who have maximum total earnings.
--Then print these values as  space-separated integers.
-- The Employee table containing employee data for a company is described as follows:

--Input Format
--
--The EMPLOYEE table is described as follows:
--
--EMPLOYEE_ID     NUMBER
--NAME   VARCHAR2(17)
--SALARY  NUMBER
--MONTHS  NUMBER

SELECT SALARY*MONTHS as EARNING,COUNT(*)
FROM EMPLOYEE
GROUP BY EARNING
ORDER BY EARNING DESC LIMIT 1


-- Weather Observation Station 2
--Query the following two values from the STATION table:
--
--The sum of all values in LAT_N rounded to a scale of  decimal places.
--The sum of all values in LONG_W rounded to a scale of  decimal places.
--Input Format
--
--The STATION table is described as follows:

SELECT ROUND(SUM(LAT_N), 2), ROUND(SUM(LONG_W), 2)  FROM STATION;


--Weather Observation Station 13
--
--Query the sum of Northern Latitudes (LAT_N) from STATION having values greater than 38.7880  and less 137.2345 than .
--Truncate your answer to 4 decimal places.


SELECT ROUND(SUM(LAT_N), 4) FROM STATION
WHERE LAT_N > 38.7880 AND LAT_N <  137.2345;


--Weather Observation Station 14
--
--Query the greatest value of the Northern Latitudes (LAT_N) from STATION that is less than 137.2345.
-- Truncate your answer to 4  decimal places.

SELECT ROUND(MAX(LAT_N), 4) FROM STATION
WHERE LAT_N <  137.2345;

--Weather Observation Station 15
--
--Query the Western Longitude (LONG_W) for the largest Northern Latitude (LAT_N) in STATION that is less than 137.2345.
-- Truncate your answer to 4  decimal places.

SELECT ROUND(LONG_W, 4) FROM STATION AS S1 JOIN
(SELECT MAX(LAT_N) AS LAT_N FROM STATION WHERE LAT_N <  137.2345) AS S2 ON S1.LAT_N = S2.LAT_N
LIMIT 1;



--Weather Observation Station 16
--
-- Query the smallest Northern Latitude (LAT_N) from STATION that is greater than 38.7780.
-- Truncate your answer to 4  decimal places.
SELECT ROUND(MIN(LAT_N), 4) FROM STATION WHERE LAT_N >  38.7780
LIMIT 1;

--Weather Observation Station 17
--Query the Western Longitude (LONG_W)where the smallest Northern Latitude (LAT_N) in STATION is greater than 38.7780.
-- Truncate your answer to 4  decimal places.
SELECT ROUND(LONG_W, 4) FROM STATION AS S1 JOIN
(SELECT MIN(LAT_N) AS LAT_N FROM STATION WHERE LAT_N >  38.7780) AS S2 ON S1.LAT_N = S2.LAT_N
LIMIT 1;


--Weather Observation Station 18
--Consider P1(a,b) and P2(c, d) to be two points on a 2D plane.
--
--a happens to equal the minimum value in Northern Latitude (LAT_N in STATION).
--b happens to equal the minimum value in Western Longitude (LONG_W in STATION).
--c happens to equal the maximum value in Northern Latitude (LAT_N in STATION).
--d happens to equal the maximum value in Western Longitude (LONG_W in STATION).
--Query the Manhattan Distance between points  P1 and P2 and round it to a scale of 4 decimal places.

--Definition: The distance between two points measured along axes at right angles.
--In a plane with p1 at (x1, y1) and p2 at (x2, y2), it is |x1 - x2| + |y1 - y2|.
SELECT ROUND((MAX(LAT_N) - MIN(LAT_N)) + (MAX(LONG_W) - MIN(LONG_W)), 4) FROM STATION;

--Weather Observation Station 19
--Consider P1(a,b) and P2(c, d) to be two points on a 2D plane.
--
--a happens to equal the minimum value in Northern Latitude (LAT_N in STATION).
--b happens to equal the minimum value in Western Longitude (LONG_W in STATION).
--c happens to equal the maximum value in Northern Latitude (LAT_N in STATION).
--d happens to equal the maximum value in Western Longitude (LONG_W in STATION).
--Query the Euclidean Distance between points  P1 and P2 and round it to a scale of 4 decimal places.
SELECT ROUND(SQRT((X*X+Y*Y)), 4) FROM(
SELECT (MAX(LAT_N)-MIN(LAT_N)) as X, (MAX(LONG_W)-MIN(LONG_W)) as Y FROM STATION) AS XY;


--African Cities
--Given the CITY and COUNTRY tables, query the names of all cities where the CONTINENT is 'Africa'.
--
--Note: CITY.CountryCode and COUNTRY.Code are matching key columns.
--
SELECT CITY.NAME FROM CITY
JOIN (SELECT * FROM COUNTRY WHERE CONTINENT = 'AFRICA') AS COUNTRY
ON CITY.CountryCode = COUNTRY.Code;

--Average Population of Each Continent
--Given the CITY and COUNTRY tables, query the names of all the continents (COUNTRY.Continent)
--and their respective average city populations (CITY.Population) rounded down to the nearest integer.
--
--Note: CITY.CountryCode and COUNTRY.Code are matching key columns.
SELECT COUNTRY.CONTINENT, FLOOR(AVG(CITY.POPULATION)) FROM CITY
JOIN COUNTRY
ON CITY.CountryCode = COUNTRY.Code
GROUP BY COUNTRY.CONTINENT;


--Revising Aggregations - The Count Function
--Query a count of the number of cities in CITY having a Population larger than .
--
SELECT COUNT(*) FROM CITY WHERE POPULATION > 100000;


--Revising Aggregations - The Sum Function
--Query the total population of all cities in CITY where District is California.
SELECT SUM(POPULATION) FROM CITY WHERE DISTRICT = 'California';


--JoinPlacements
--You are given three tables: Students, Friends and Packages.
--Students contains two columns: ID and Name.
--Friends contains two columns: ID and Friend_ID (ID of the ONLY best friend).
--Packages contains two columns: ID and Salary (offered salary in $ thousands per month).
--
--Write a query to output the names of those students whose best friends got offered a higher salary than them.
--Names must be ordered by the salary amount offered to the best friends. It is guaranteed that no two students
--got same salary offer.
SELECT STUDENTS.NAME
FROM STUDENTS
JOIN FRIENDS ON STUDENTS.ID = FRIENDS.ID
JOIN PACKAGES AS P1 ON STUDENTS.ID = P1.ID
JOIN PACKAGES AS P2 ON FRIENDS.FRIEND_ID = P2.ID
WHERE P1.SALARY < P2.SALARY
ORDER BY P2.SALARY


--Symmetric Pairs
--You are given a table, Functions, containing two columns: X and Y.
--Two pairs (X1, Y1) and (X2, Y2) are said to be symmetric pairs if X1 = Y2 and X2 = Y1.
--Write a query to output all such symmetric pairs in ascending order by the value of X.
--List the rows such that X1 ≤ Y1.

SET @rn1 = 0;
SET @rn2 = 0;
SELECT DISTINCT IF(F1.X<F1.Y, F1.X, F1.Y) as X , IF(F1.X<F1.Y, F1.Y, F1.X) as Y
FROM (SELECT *,  (@rn1:=@rn1 + 1) AS RN FROM Functions) as F1
JOIN (SELECT *,  (@rn2:=@rn2 + 1) AS RN FROM Functions) as F2
ON F1.X = F2.Y AND F1.Y = F2.X AND F1.RN != F2.RN
ORDER BY X ASC, Y ASC


--Population Census
--Given the CITY and COUNTRY tables, query the sum of the populations of all cities where the CONTINENT is 'Asia'.
--Note: CITY.CountryCode and COUNTRY.Code are matching key columns.
--
--
SELECT SUM(CITY.POPULATION) FROM CITY
JOIN (SELECT * FROM COUNTRY WHERE CONTINENT = 'Asia') AS COUNTRY
ON CITY.CountryCode = COUNTRY.Code;

--The Report
--You are given two tables: Students and Grades. Students contains three columns ID, Name and Marks.
--
--Ketty gives Eve a task to generate a report containing three columns: Name, Grade and Mark.
--Ketty doesn't want the NAMES of those students who received a grade lower than 8.
--The report must be in descending order by grade
--i.e. higher grades are entered first. If there is more than one student with the same grade (8-10) assigned to them,
--order those particular students by their name alphabetically. Finally, if the grade is lower than 8,
--use "NULL" as their name and list them by their grades in descending order.
--If there is more than one student with the same grade (1-7) assigned to them,
--order those particular students by their marks in ascending order.

SELECT IF(GRADE>=8, NAME, "NULL"),
GRADE,
MARKS FROM STUDENTS
JOIN GRADES ON MARKS >= MIN_MARK AND MARKS <= MAX_MARK
ORDER BY GRADE DESC, NAME ASC


--Top Competitors
--Julia just finished conducting a coding contest, and she needs your help assembling the leaderboard!
--Write a query to print the respective hacker_id and name of hackers who achieved full scores
--for more than one challenge. Order your output in descending order by the total number of challenges
--in which the hacker earned a full score. If more than one hacker received full scores in same number of challenges,
--then sort them by ascending hacker_id.

SELECT P.HACKER_ID, P.NAME FROM (
    SELECT S.HACKER_ID, H.NAME, COUNT(*) AS C
    FROM SUBMISSIONS AS S
    JOIN CHALLENGES AS C ON S.CHALLENGE_ID = C.CHALLENGE_ID
    JOIN DIFFICULTY AS D ON D.DIFFICULTY_LEVEL = C.DIFFICULTY_LEVEL
    JOIN HACKERS AS H ON H.HACKER_ID = S.HACKER_ID
    WHERE S.SCORE = D.SCORE
    GROUP BY S.HACKER_ID, H.NAME
) AS P WHERE P.C > 1
ORDER BY P.C DESC,  P.HACKER_ID ASC

--Ollivander's Inventory
--Harry Potter and his friends are at Ollivander's with Ron, finally replacing Charlie's old broken wand.
--Hermione decides the best way to choose is by determining the minimum number of gold galleons needed
--to buy each non-evil wand of high power and age. Write a query to print the id, age, coins_needed,
--and power of the wands that Ron's interested in, sorted in order of descending power. If more than one wand
--has same power, sort the result in order of descending age.

--Input Format
--
--The following tables contain data on the wands in Ollivander's inventory:
--
--Wands: The id is the id of the wand, code is the code of the wand, coins_needed is the total number of gold galleons
--needed to buy the wand, and power denotes the quality of the wand (the higher the power, the better the wand is).
--
--Wands_Property: The code is the code of the wand, age is the age of the wand, and is_evil denotes whether the wand
--is good for the dark arts. If the value of is_evil is 0, it means that the wand is not evil. The mapping between code
--and age is one-one, meaning that if there are two pairs,  and
--meaning that if there are two pairs,  and , then  and .


SELECT W.ID, WP.AGE, W.COINS_NEEDED, W.POWER
FROM WANDS AS W
JOIN WANDS_PROPERTY AS WP
ON W.CODE = WP.CODE AND WP.IS_EVIL = 0
WHERE (WP.AGE, W.POWER, W.COINS_NEEDED) IN (
SELECT WANDS_PROPERTY.AGE, WANDS.POWER, MIN(WANDS.COINS_NEEDED)
    FROM WANDS JOIN WANDS_PROPERTY
    ON WANDS.CODE = WANDS_PROPERTY.CODE AND WANDS_PROPERTY.IS_EVIL = 0
    GROUP BY WANDS_PROPERTY.AGE, WANDS.POWER
)
ORDER BY W.POWER DESC, WP.AGE DESC

--Challenges
--Julia asked her students to create some coding challenges. Write a query to print the hacker_id, name,
--and the total number of challenges created by each student. Sort your results by the total number of challenges
--in descending order. If more than one student created the same number of challenges, then sort the result by
--hacker_id. If more than one student created the same number of challenges and the count is less than the maximum
--number of challenges created, then exclude those students from the result.

SELECT HACKER_ID, NAME, CNT FROM (
SELECT H.HACKER_ID, H.NAME, COUNT(*) AS CNT
FROM HACKERS AS H JOIN CHALLENGES AS C ON H.HACKER_ID = C.HACKER_ID
GROUP BY H.HACKER_ID, H.NAME
) XYZ
WHERE  XYZ.CNT  IN (
    SELECT CNT FROM (
    SELECT CNT, COUNT(*) AS CC, DENSE_RANK() OVER(ORDER BY CNT DESC) AS RN
    FROM (SELECT COUNT(*) AS CNT  FROM CHALLENGES GROUP BY HACKER_ID) AS HC
    GROUP BY CNT
    ) FHC
    WHERE (RN !=1 AND CC = 1) OR (RN = 1)
)
ORDER BY CNT DESC, HACKER_ID ASC


--Contest Leaderboard
--You did such a great job helping Julia with her last coding contest challenge that she wants you to work on
--this one, too!
--
--The total score of a hacker is the sum of their maximum scores for all of the challenges.
--Write a query to print the hacker_id, name, and total score of the hackers ordered by the descending score.
--If more than one hacker achieved the same total score, then sort the result by ascending hacker_id.
--Exclude all hackers with a total score of 0  from your result.

SELECT HACKER_ID, NAME, SUM(SCORE) AS SSCORE
FROM (
    SELECT S.HACKER_ID, H.NAME, MAX(S.SCORE) AS SCORE
    FROM HACKERS AS H
    JOIN SUBMISSIONS AS S
    ON S.HACKER_ID = H.HACKER_ID
    GROUP BY H.HACKER_ID, H.NAME, S.CHALLENGE_ID
) HS WHERE SCORE > 0
GROUP BY HACKER_ID, NAME
ORDER BY SSCORE DESC, HACKER_ID ASC



--15 Days of Learning SQL
--Julia conducted a  days of learning SQL contest. The start date of the contest was March 01, 2016
--and the end date was March 15, 2016.
--Write a query to print total number of unique hackers who made at least  submission each day
--(starting on the first day of the contest), and find the hacker_id and name of the hacker who made maximum
--number of submissions each day. If more than one such hacker has a maximum number of submissions, print the
--lowest hacker_id. The query should print this information for each day of the contest, sorted by the date.

;
WITH  S1(SUBMISSION_DATE, HACKER_ID) AS (
--GET UNIQUE HACKER_ID WHO HAVE SUBMISSIONS EACH DAY STARTING FROM FIRST DAY TILL THIS DAY
    SELECT SUBMISSION_DATE, HACKER_ID FROM (
        SELECT HACKER_ID, SUBMISSION_DATE,
        DENSE_RANK() OVER(PARTITION BY HACKER_ID ORDER BY SUBMISSION_DATE ASC) RN
        FROM SUBMISSIONS
    )
    WHERE RN = (SUBMISSION_DATE-TO_DATE('2016-03-01', 'YYYY-MM-DD')) + 1
    GROUP BY SUBMISSION_DATE, HACKER_ID
),
S2(SUBMISSION_DATE, UNQ_CNT) AS (
--GET UNIQUE HACKER_ID COUNT WHO HAVE SUBMISSIONS EACH DAY STARTING FROM FIRST DAY TILL THIS DAY
    SELECT SUBMISSION_DATE, COUNT(HACKER_ID) AS UNQ_CNT FROM S1
    GROUP BY SUBMISSION_DATE
),
S3(SUBMISSION_DATE, HACKER_ID, CNT) AS (
--FOR EACH SUBMISSION_DATE,  MAX SUBMISSIONS BY UNIQUE HACKER_ID's
    SELECT SUBMISSION_DATE, HACKER_ID, CNT
    FROM (
        SELECT SUBMISSION_DATE, HACKER_ID, CNT,
        DENSE_RANK() OVER(PARTITION BY SUBMISSION_DATE ORDER BY CNT DESC) CNT_RANK
        FROM (
            SELECT SUBMISSION_DATE, HACKER_ID, COUNT(*) AS CNT
            FROM SUBMISSIONS
            GROUP BY SUBMISSION_DATE, HACKER_ID
        )
    ) WHERE CNT_RANK = 1
)

-- Just Join S2 and Modified S3 to get the Result
SELECT S2.SUBMISSION_DATE, S2.UNQ_CNT, S4.HACKER_ID, NAME
FROM S2
JOIN (
    SELECT SUBMISSION_DATE, MIN(HACKER_ID) AS HACKER_ID
    FROM  S3
    GROUP BY SUBMISSION_DATE) S4
ON S4.SUBMISSION_DATE = S2.SUBMISSION_DATE
JOIN HACKERS ON HACKERS.HACKER_ID = S4.HACKER_ID
ORDER BY S2.SUBMISSION_DATE;


--Draw The Triangle 2
--P(R) represents a pattern drawn by Julia in R rows. The following pattern represents P(5):
--
--*
--* *
--* * *
--* * * *
--* * * * *
--Write a query to print the pattern P(20).
DELIMITER $$

CREATE PROCEDURE PrintTriangle(len INT)
BEGIN
    DECLARE counter INT DEFAULT 1;
    DECLARE result VARCHAR(255) DEFAULT '';

    WHILE counter <= len DO
        SET result = REPEAT('* ', counter);
        SELECT result;
        SET counter = counter + 1;
    END WHILE;
END$$

DELIMITER ;

CALL PrintTriangle(20);

--Draw The Triangle 1
--P(R) represents a pattern drawn by Julia in R rows. The following pattern represents P(5):
--
--* * * * *
--* * * *
--* * *
--* *
--*
--Write a query to print the pattern P(20).
DELIMITER $$

CREATE PROCEDURE PrintTriangle(len INT)
BEGIN
    DECLARE counter INT DEFAULT len;
    DECLARE result VARCHAR(255) DEFAULT '';

    WHILE counter > 0 DO
        SET result = REPEAT('* ', counter);
        SELECT result;
        SET counter = counter - 1;
    END WHILE;
END$$

DELIMITER ;

CALL PrintTriangle(20);



--Print Prime Numbers
--Write a query to print all prime numbers less than or equal to . Print your result on a single line,
-- and use the ampersand (&) character as your separator (instead of a space).
--
--For example, the output for all prime numbers  would be:
-- 2&3&5&7
DELIMITER $$

CREATE PROCEDURE isPrime(IN n INT, OUT res INT)
BEGIN
    DECLARE counter INT DEFAULT 2;
    WHILE (counter < n AND n%counter != 0 )  DO
        SET counter = counter + 1;
    END WHILE;
    SELECT IF( counter < n, 0, 1) into res;
END$$

CREATE PROCEDURE PrintPrime(IN n INT)
BEGIN
    DECLARE counter INT DEFAULT 3;
    DECLARE result VARCHAR(1025) DEFAULT '2';
    WHILE n > 2 AND counter <= n DO
        SET @res = 0;
        CALL isPrime(counter, @res);
        IF( @res = 1) THEN
            SET result = CONCAT(result, "&", counter);
        END IF;
        SET counter = counter + 1;
    END WHILE;
    SELECT result;
END$$

DELIMITER ;

CALL PrintPrime(1000);



--SQL Project Planning
--You are given a table, Projects, containing three columns: Task_ID, Start_Date and End_Date.
--It is guaranteed that the difference between the End_Date and the Start_Date is equal to 1 day for each row
--in the table.
--
--If the End_Date of the tasks are consecutive, then they are part of the same project. Samantha is interested
--in finding the total number of different projects completed.
--
--Write a query to output the start and end dates of projects listed by the number of days it took to complete
--the project in ascending order. If there is more than one project that have the same number of completion days,
--then order by the start date of the project.

WITH SD_RANK(START_DATE, S_RANK) AS (
SELECT START_DATE, RANK() OVER( ORDER BY START_DATE) S_RANK
FROM PROJECTS WHERE START_DATE  NOT IN (SELECT END_DATE FROM PROJECTS)
),
ED_RANK(END_DATE, E_RANK) AS (
  SELECT END_DATE, RANK() OVER( ORDER BY END_DATE) E_RANK
  FROM PROJECTS WHERE END_DATE  NOT IN (SELECT START_DATE FROM PROJECTS)
)
SELECT START_DATE, END_DATE FROM SD_RANK JOIN ED_RANK ON SD_RANK.S_RANK = ED_RANK.E_RANK
ORDER BY (END_DATE - START_DATE) ASC, START_DATE;


--Interviews
--Samantha interviews many candidates from different colleges using coding challenges and contests.
--Write a query to print the contest_id, hacker_id, name, and the sums of total_submissions,
--total_accepted_submissions, total_views, and total_unique_views for each contest sorted by contest_id.
--Exclude the contest from the result if all four sums are .
--
--Note: A specific contest can be used to screen candidates at more than one college, but each college only holds
--screening contest.
WITH VIEW_STATS_AGG(CHALLENGE_ID, TOTAL_VIEWS, TOTAL_UNIQUE_VIEWS) AS (
    SELECT CHALLENGE_ID, SUM(TOTAL_VIEWS), SUM(TOTAL_UNIQUE_VIEWS)
    FROM VIEW_STATS
    GROUP BY CHALLENGE_ID
),
SUBMISSION_STATS_AGG(CHALLENGE_ID, TOTAL_SUBMISSIONS, TOTAL_ACCEPTED_SUBMISSIONS) AS(
    SELECT CHALLENGE_ID, SUM(TOTAL_SUBMISSIONS), SUM(TOTAL_ACCEPTED_SUBMISSIONS)
    FROM SUBMISSION_STATS
    GROUP BY CHALLENGE_ID
),
Q(CONTEST_ID, HACKER_ID, NAME, S1, S2, S3, S4) AS (
    SELECT C.CONTEST_ID, C.HACKER_ID, C.NAME, SUM(SU.TOTAL_SUBMISSIONS), SUM(SU.TOTAL_ACCEPTED_SUBMISSIONS), SUM(S.TOTAL_VIEWS), SUM(S.TOTAL_UNIQUE_VIEWS)
    FROM CONTESTS C
    JOIN COLLEGES CC ON C.CONTEST_ID = CC.CONTEST_ID
    JOIN CHALLENGES CL ON CL.COLLEGE_ID = CC.COLLEGE_ID
    LEFT JOIN VIEW_STATS_AGG S ON S.CHALLENGE_ID = CL.CHALLENGE_ID
    LEFT JOIN SUBMISSION_STATS_AGG SU ON SU.CHALLENGE_ID = CL.CHALLENGE_ID
    GROUP BY C.CONTEST_ID, C.HACKER_ID, C.NAME
    ORDER BY C.CONTEST_ID ASC
)
SELECT * FROM Q  WHERE S1+S2+S3+S4 > 0
ORDER BY Q.CONTEST_ID ASC;
