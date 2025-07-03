SET sql_mode = '' ;

set @maxCount=(select count(challenge_id) as cnt from challenges group by hacker_id order by  count(challenge_id) desc limit 1);

select h.hacker_id, h.name, count(c.challenge_id) as cnt from hackers as h left join challenges as c on h.hacker_id = c.hacker_id group by hacker_id having cnt =  @maxCount or cnt in (
    select  ta.cnt from 
        (select count(*) as cnt, hacker_id from challenges group by hacker_id) 
        as ta
        group by ta.cnt having count(ta.cnt) = 1
 ) order by cnt desc, h.hacker_id asc;
 