select group_concat(num separator '&') from 
    (select @num:=@num+1 as num from 
        information_schema.tables as ta, 
        information_schema.tables as tb, 
        (select @num:=1) tmp
    ) tt
where num <=1000 and not exists (        
    select * from  (
        select @nb:=@nb+1 as nb from 
        information_schema.tables as tta,
        information_schema.tables as ttb, 
        (select @nb:=1) tmpa
    ) ttt
    where floor(num/nb) = (num/nb) and num > nb and nb > 1
);