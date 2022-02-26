delta(A,B,C, Wynik) :-
    Wynik is B*B - 4*A*C.

kwadrat(A,B,C,Wynik) :-
    WD<0,
    delta(A,B,C,WD),
    Wynik is 0.

kwadrat(A,B,C,Wynik) :-
    WD is 0,
    delta(A,B,C,WD),
    Wynik is -B/(2*A).

kwadrat(A,B,C,Wynik) :-
    WD>0,
    delta(A,B,C,WD),
    Wynik is ((-1*B-sqrt(Wynik))/2*A).

kwadrat(A,B,C,Wynik) :-
    WD>0,
    delta(A,B,C,WD),
    Wynik is ((-1*B+sqrt(Wynik))/2*A).