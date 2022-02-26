import sqlite3

CREATE_GENRE_TABLE = "CREATE TABLE IF NOT EXISTS genre" \
                     "(id INTEGER NOT NULL, name VARCHAR(20), PRIMARY KEY (id));"

ADD_GENRE = "INSERT INTO genre (id, name) VALUES (?,?);"

GET_ALL_GENRES = "SELECT * FROM genre;"

DELETE_GENRE = "DELETE FROM genre WHERE id = ?;"


def connect():
    return sqlite3.connect("db.sqlite3")


def create_table(connection):
    with connection:
        connection.execute(CREATE_GENRE_TABLE)


def add_genre(connection, id, name):
    with connection:
        connection.execute(ADD_GENRE, (id, name))


def get_all_genres(connection):
    with connection:
        return connection.execute(GET_ALL_GENRES).fetchall()


def delete_genre_by_id(connection, id):
    with connection:
        connection.execute(DELETE_GENRE, (id,))
