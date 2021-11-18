select city, CHAR_LENGTH(city) from station order by CHAR_LENGTH(city) desc limit 1;
select city, CHAR_LENGTH(city) from station order by CHAR_LENGTH(city), city asc limit 1;
