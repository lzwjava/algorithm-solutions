set @a  = (select LAT_N from station order by LAT_N limit 1);

set @b  = (select LAT_N from station order by LAT_N desc limit 1);

set @c  = (select LONG_W from station order by LONG_W limit 1);

set @d  = (select LONG_W from station order by LONG_W desc limit 1);

select TRUNCATE(SQRT(pow(@a-@b,2)+ pow(@c-@d,2)),4);