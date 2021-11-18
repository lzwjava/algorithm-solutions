set @rowindex=-1;

select round(avg(lat_n), 4)
 from
(select @rowindex:=@rowindex+1 as rowindex, lat_n 
from station
order by lat_n) as l where l.rowindex in (floor(@rowindex/2), ceil(@rowindex/2));
