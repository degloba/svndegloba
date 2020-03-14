select * from inmobles e where
	e.ID IN 
		(
			select  DISTINCT v.IDINMOBLE 
			from  CARACTERISTIQUES g , CARACTINMOBLES v,  VALUES_CARACTERISTIQUES vv,  INMOBLES e 
			where  g.ID = v.IDCARACT and v.IDCARACT = vv.IDCARACTERISTICA and e.ID = v.IDINMOBLE 
			and g.ID = 8 -- habitacions   
			and vv.value = 33) -- filtervalue
			and e.ID in 
			(
			select DISTINCT v.IDINMOBLE 
			from  CARACTERISTIQUES g , CARACTINMOBLES v,  VALUES_CARACTERISTIQUES vv,  INMOBLES e 
			where  g.ID = v.IDCARACT and v.IDCARACT = vv.IDCARACTERISTICA and e.ID = v.IDINMOBLE 
			and g.ID = 6 -- banys
			and vv.value = 33) -- filtervalue
			and e.ID in  
			(
			select DISTINCT v.IDINMOBLE 
			from  CARACTERISTIQUES g , CARACTINMOBLES v,  VALUES_CARACTERISTIQUES vv, INMOBLES e 
			where  g.ID = v.IDCARACT and v.IDCARACT = vv.IDCARACTERISTICA and e.ID = v.IDINMOBLE 
			and g.ID = 73 --nom
			and vv.value like '%caden%'    -- filtervalue
			) 
			and e.ID in  
			(
			select DISTINCT v.IDINMOBLE 
			from  CARACTERISTIQUES g , CARACTINMOBLES v,  VALUES_CARACTERISTIQUES vv, INMOBLES e 
			where  g.ID = v.IDCARACT and v.IDCARACT = vv.IDCARACTERISTICA and e.ID = v.IDINMOBLE 
			and g.ID = 26 --nom
			and vv.value = 'true'    -- filtervalue
			) 
			and e.ID in  
			(
			select DISTINCT v.IDINMOBLE 
			from  CARACTERISTIQUES g , CARACTINMOBLES v,  VALUES_CARACTERISTIQUES vv, INMOBLES e 
			where  g.ID = v.IDCARACT and v.IDCARACT = vv.IDCARACTERISTICA and e.ID = v.IDINMOBLE 
			and g.ID = 74 --CIUTAT
			and vv.value = 967 --manresa   -- filtervalue
			) 
			and e.ID in  
			(
			select DISTINCT v.IDINMOBLE 
			from  CARACTERISTIQUES g , CARACTINMOBLES v,  VALUES_CARACTERISTIQUES vv, INMOBLES e 
			where  g.ID = v.IDCARACT and v.IDCARACT = vv.IDCARACTERISTICA and e.ID = v.IDINMOBLE 
			and g.ID = 55 --preu
			and vv.value = 135000   -- filtervalue
			) 
			and e.ID in  
			(
			select DISTINCT v.IDINMOBLE 
			from  CARACTERISTIQUES g , CARACTINMOBLES v,  VALUES_CARACTERISTIQUES vv, INMOBLES e 
			where  g.ID = v.IDCARACT and v.IDCARACT = vv.IDCARACTERISTICA and e.ID = v.IDINMOBLE 
			and g.ID = 33 --traster
			and vv.value = 'true'    -- filtervalue
			) 
			and e.ID in  
			(
			select DISTINCT v.IDINMOBLE 
			from  CARACTERISTIQUES g , CARACTINMOBLES v,  VALUES_CARACTERISTIQUES vv, INMOBLES e 
			where  g.ID = v.IDCARACT and v.IDCARACT = vv.IDCARACTERISTICA and e.ID = v.IDINMOBLE 
			and g.ID = 10 --amoblat
			and vv.value = 'true'    
			) 
			and e.ID in  
			(
			select DISTINCT v.IDINMOBLE 
			from  CARACTERISTIQUES g , CARACTINMOBLES v,  VALUES_CARACTERISTIQUES vv, INMOBLES e 
			where  g.ID = v.IDCARACT and v.IDCARACT = vv.IDCARACTERISTICA and e.ID = v.IDINMOBLE 
			and g.ID = 13 --obranova
			and vv.value = 'true'    
			) 
	





UPDATE [INSACO].[dbo].[VALUES_CARACTERISTIQUES]
   SET [VALUE] = '8'
 WHERE idcaracteristica=75  --provincia

UPDATE [INSACO].[dbo].[VALUES_CARACTERISTIQUES]
   SET [VALUE] = '967'
 WHERE idcaracteristica=74  --ciutat

UPDATE [INSACO].[dbo].[VALUES_CARACTERISTIQUES]
   SET [VALUE] = 135000
 WHERE idcaracteristica=55  --preu
and idinmoble>36900 and idinmoble<37000


-- COMPTADORS TAULES INMOBLES-CARACTERISTIQUES
select count(*) INMOBLES from inmobles
select count(*) CARACTS from CARACTERISTIQUES
select count(*) CARACT_INMO from caractinmobles
select count(*) VALUES_CARACT from VALUES_CARACTERISTIQUES

			