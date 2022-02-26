import sqlite3
from datetime import date

CREATE_OPINION_TABLE = "CREATE TABLE IF NOT EXISTS opinion" \
                       "(id INTEGER NOT NULL, contents VARCHAR(500), book_id INTEGER NOT NULL, user_id INTEGER NOT NULL, rating NUMERIC(7,2), date DATE, " \
                       "PRIMARY KEY (id), " \
                       "FOREIGN KEY (book_id) REFERENCES bookapp_book (id), " \
                       "FOREIGN KEY (user_id) REFERENCES users (id));"

ADD_OPINION = "INSERT INTO opinion (id, contents, book_id,user_id, rating, date) VALUES (?,?,?,?,?,?)"

GET_ALL_OPINIONS = "SELECT * FROM opinion"

DELETE_OPINION_BY_ID = "DELETE FROM opinion WHERE id_opinion = ?;"


def connect():
    return sqlite3.connect("db.sqlite3")


def create_table(connection):
    with connection:
        connection.execute(CREATE_OPINION_TABLE)


#def add_opinion(connection, contents, rating):
#    d = date.today().strftime("%Y-%m-%d")
#    with connection:
#        connection.execute(ADD_OPINION, (d, contents, rating))


def add_opinion(connection, id, id_b, id_u,  contents, rating):
    d = date.today().strftime("%Y-%m-%d")
    with connection:
        connection.execute(ADD_OPINION, (id, contents, id_b, id_u, rating, d))


def get_all_opinions(connection):
    with connection:
        return connection.execute(GET_ALL_OPINIONS).fetchall()


def delete_opinion_by_id(connection, id):
    with connection:
        connection.execute(DELETE_OPINION_BY_ID, (id,))
