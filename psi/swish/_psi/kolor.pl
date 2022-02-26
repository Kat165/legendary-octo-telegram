kolor(czerwony).
kolor(zielony).
kolor(niebieski).

koloruj(A,B,C,D,E):-
    pokoloruj(A,B),
    pokoloruj(A,C),
    pokoloruj(A,D),
    pokoloruj(A,E),
    pokoloruj(B,C),
    pokoloruj(C,D),
    pokoloruj(D,E).

pokoloruj(czerwony,zielony).
pokoloruj(czerwony,niebieski).
pokoloruj(niebieski,zielony).
pokoloruj(niebieski,czerwony).
pokoloruj(zielony,niebieski).
pokoloruj(zielony,czerwony).