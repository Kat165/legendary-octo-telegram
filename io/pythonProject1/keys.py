import sqlite3

PK_bookapp_book = "ALTER TABLE ONLY bookapp_book " \
                  "ADD CONSTRAINT book_pk PRIMARY KEY (id);"

PK_genre = "ALTER TABLE ONLY genre " \
           "ADD CONSTRAINT genre_pk PRIMARY KEY (id);"

PK_opinion = "ALTER TABLE ONLY opinion " \
             "ADD CONSTRAINT opinion_pk PRIMARY KEY (id);"

PK_user = "ALTER TABLE ONLY users " \
          "ADD CONSTRAINT users_pk PRIMARY KEY (id);"

PK_b_g = "ALTER TABLE ONLY book_genres " \
         "ADD CONSTRAINT bg_pk PRIMARY KEY (id);"


def connect():
    return sqlite3.connect("db.sqlite3")


def addPkeys(connection):
    with connection:
        connection.execute(PK_bookapp_book)
        connection.execute(PK_genre)
        connection.execute(PK_opinion)
        connection.execute(PK_user)
        connection.execute(PK_b_g)


def connectPK():
    connection = connect()
    addPkeys(connection)


connectPK()
