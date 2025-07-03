SET sql_mode = '' ;

select ta.n,
 case when ta.p is null then 'Root' 
      when count(tb.n) = 2 then 'Inner'
      when count(tb.n) = 0 then 'Leaf'
 end as type
from bst as ta left join bst as tb on tb.p = ta.n group by ta.n order by ta.n;
