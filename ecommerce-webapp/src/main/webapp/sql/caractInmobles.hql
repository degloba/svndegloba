select count(*) from Inmobles as inmobles  
where inmobles.id IN (select  DISTINCT caractinmobles.id.idinmoble 
from  Caracteristiques as caracteristiques , Caractinmobles as caractinmobles,  
ValuesCaracteristiques as valuescaracteristiques,  Inmobles as inmobles 
where  caracteristiques.id = caractinmobles.id.idcaract 
and caractinmobles.id.idcaract = valuescaracteristiques.id.idcaracteristica and inmobles.id = caractinmobles.id.idinmoble 
and caracteristiques.id = 4 and valuescaracteristiques.value = '33')