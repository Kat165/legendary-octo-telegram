import genre


def genre_manager():
    connection = genre.connect()
    genre.create_table(connection)
    genre.add_genre(connection, 1, "fantastyka")
    genre.add_genre(connection, 2, "przygodowa")
    genre.add_genre(connection, 3, "romans")
    genre.add_genre(connection, 4, "dramat")
    genre.add_genre(connection, 5, "sci-fi")
    genre.add_genre(connection, 6, "horror")
    genre.add_genre(connection, 7, "kryminał")
    genre.add_genre(connection, 8, "poezja")
    genre.add_genre(connection, 9, "reportaż")
    genre.add_genre(connection, 10, "literatura piękna")
    gen = genre.get_all_genres(connection)
    for g in gen:
        print(g)
