:- dynamic
	xpozytywne/2,
	xnegatywne/2.

wybrany_film(kobieta_na_skraju_dojrzalosci) :- 
	kategoria(dramat),
	towarzystwo(dziewczyna),
	pora(wieczor).

wybrany_film(wielki_mike) :- 
	kategoria(dramat),
	towarzystwo(chlopak_lub_rodzina),
	pora(obiad).

wybrany_film(slodki_listopad) :- 
	kategoria(komedia_romantyczna),
	towarzystwo(dziewczyna_lub_dzieci),
	pora(deszczowy_dzien_lub_walentynki).

wybrany_film(idealny_facet_dla_mojej_dziewczyny) :- 
	kategoria(komedia_romantyczna),
	towarzystwo(dziewczyna),
	pora(wieczor).

wybrany_film(matrix) :- 
	kategoria(akcja),
	towarzystwo(sam_lub_znajomi),
	pora(deszczowy_dzien).

wybrany_film(transporter) :- 
	kategoria(akcja),
	towarzystwo(sam_lub_znajomi),
	pora(obiad_lub_wieczor).

wybrany_film(amityville) :- 
	kategoria(horror),
	towarzystwo(rodzina),
	pora(wieczor).

wybrany_film(smiertelna_wyliczanka) :- 
	kategoria(horror),
	towarzystwo(chlopak_lub_dziewczyna_lub_znajomi),
	pora(wieczor).

wybrany_film(wieczny_student) :- 
	kategoria(komedia),
	towarzystwo(znajomi),
	pora(wieczor).

wybrany_film(vinci) :- 
	kategoria(komedia),
	towarzystwo(chlopak_lub_sam_lub_znajomi),
	pora(wieczor).

wybrany_film(tytus_romek_i_atomek_wsrod_zlodziei_marzen) :- 
	kategoria(animowany),
	towarzystwo(syn),
	pora(wieczor_lub_urodziny).

wybrany_film(barbie_roszpunka) :- 
	kategoria(animowany),
	towarzystwo(corka),
	pora(wieczor_lub_urodziny).

kategoria(dramat) :- pozytywne(czy_jest, o_problemach); pozytywne(czy_jest, biograficzny).
kategoria(komedia) :- pozytywne(czy_jest, zabawny).
kategoria(komedia_romantyczna) :- kategoria(komedia), pozytywne(czy_jest, romantyczny).
kategoria(akcja) :- pozytywne(czy_jest, strzelanina); pozytywne(czy_jest, przemoc).
kategoria(horror) :- pozytywne(czy_jest, przemoc); pozytywne(czy_jest, straszny).
kategoria(animowany) :- pozytywne(czy_jest, bajka).

towarzystwo(chlopak) :- pozytywne(chcesz_obejrzec, z_chlopakiem).
towarzystwo(dziewczyna) :- pozytywne(chcesz_obejrzec, z_dziewczyna).
towarzystwo(rodzina) :- pozytywne(chcesz_obejrzec, z_rodzina).
towarzystwo(znajomi) :- pozytywne(chcesz_obejrzec, ze_znajomymi).
towarzystwo(syn) :- pozytywne(chcesz_obejrzec, z_synem).
towarzystwo(corka) :- pozytywne(chcesz_obejrzec, z_corka).
towarzystwo(sam) :- pozytywne(chcesz_obejrzec, sam).
towarzystwo(chlopak_lub_rodzina) :- towarzystwo(chlopak); towarzystwo(rodzina).
towarzystwo(dziewczyna_lub_dzieci) :- towarzystwo(dziewczyna); towarzystwo(syn); towarzystwo(corka).
towarzystwo(sam_lub_znajomi) :- towarzystwo(sam); towarzystwo(znajomi).
towarzystwo(chlopak_lub_dziewczyna_lub_znajomi) :- towarzystwo(chlopak); towarzystwo(dziewczyna); towarzystwo(znajomi).
towarzystwo(chlopak_lub_sam_lub_znajomi) :- towarzystwo(chlopak); towarzystwo(sam); towarzystwo(znajomi).

pora(wieczor) :- pozytywne(czy_bedziesz_ogladac, wieczorem).
pora(obiad) :- pozytywne(czy_bedziesz_ogladac, przy_obiedzie).
pora(deszczowy_dzien) :- pozytywne(czy_bedziesz_ogladac, w_deszczowy_dzien).
pora(urodziny) :- pozytywne(czy_bedziesz_ogladac, w_urodziny).
pora(walentynki) :- pozytywne(czy_bedziesz_ogladac, w_walentynki).
pora(deszczowy_dzien_lub_walentynki) :- pora(deszczowy_dzien); pora(walentynki).
pora(obiad_lub_wieczor) :- pora(obiad); pora(wieczor).
pora(wieczor_lub_urodziny) :- pora(wieczor); pora(urodziny).

pozytywne(X,Y) :- xpozytywne(X,Y), !.

pozytywne(X,Y) :- \+xnegatywne(X,Y), pytaj(X,Y,tak).

negatywne(X,Y) :- xnegatywne(X,Y), !.

negatywne(X,Y) :- \+xpozytywne(X,Y), pytaj(X,Y,nie).

pytaj(X,Y,tak) :- !, format('~w ten film ~w ? (t/n)~n',[X,Y]),
                    read(Reply),
                    (Reply = 't'),
                    pamietaj(X,Y,tak).
                    
pytaj(X,Y,nie) :- !, format('~w ten film ~w ? (t/n)~n',[X,Y]),
                    read(Reply),
                    (Reply = 'n'),
                    pamietaj(X,Y,nie).
                    
pamietaj(X,Y,tak) :- assertz(xpozytywne(X,Y)).

pamietaj(X,Y,nie) :- assertz(xnegatywne(X,Y)).

wyczysc_fakty :- write('Przycisnij cos aby wyjsc'), nl,
                    retractall(xpozytywne(_,_)),
                    retractall(xnegatywne(_,_)),
                    get_char(_).
                    
wykonaj :- wybrany_film(X), !,
            format('~nPowinienes obejrzec film ~w', X),
            nl, wyczysc_fakty.
            
wykonaj :- write('Nie jestem w stanie Ci doradzic jaki film masz obejrzec.'), nl,
            wyczysc_fakty.