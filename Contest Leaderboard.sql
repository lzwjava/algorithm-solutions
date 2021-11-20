SET sql_mode = '' ;

select h.hacker_id,h.name, sum(cs) as ss from hackers as h 
    left join 
        (select *, max(score) as cs from submissions as s group by s.hacker_id, s.challenge_id) as s 
         on s.hacker_id = h.hacker_id
         group by h.hacker_id 
         having ss != 0
         order by ss desc, h.hacker_id asc;