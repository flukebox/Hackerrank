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

