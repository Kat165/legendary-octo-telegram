import sqlite3

CREATE_TABLE_BG = "CREATE TABLE IF NOT EXISTS book_genres" \
                  "(id INTEGER, book_id INTEGER, genre_id INTEGER, PRIMARY KEY (id), " \
                  "FOREIGN KEY (book_id) REFERENCES bookapp_book (id), " \
                  "FOREIGN KEY (genre_id) REFERENCES genre (id));"

ADD_BG = "INSERT INTO book_genres (id, book_id, genre_id) VALUES (?,?,?);"

GET_ALL_BG = "SELECT * FROM book_genres;"


def connect():
    return sqlite3.connect("db.sqlite3")


def create_table(connection):
    with connection:
        connection.execute(CREATE_TABLE_BG)


def add_bg(connection, id, id_b, id_g):
    with connection:
        connection.execute(ADD_BG, (id, id_b, id_g))


def get_all_bg(connection):
    with connection:
        return connection.execute(GET_ALL_BG).fetchall()
