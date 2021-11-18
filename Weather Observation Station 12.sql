select distinct(city) from station where city not regexp '^[aeiouAEIOU].*' and city not regexp '.*[aeiouAEIOU]$';
