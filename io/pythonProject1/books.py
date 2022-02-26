import sqlite3

CREATE_BOOKS_TABLE = "CREATE TABLE IF NOT EXISTS bookapp_book " \
                     "(id INTEGER, title VARCHAR(50),author VARCHAR(50), isbn VARCHAR(20), description VARCHAR(2000), " \
                     "PRIMARY KEY (id));"
# CREATE_AUTH_PERMISSIONS = "CREATE TABLE auth_permission " \
#                          "(id INTEGER PRIMARY KEY, content_type_id INTEGER, codename VARCHAR(100), name VARCHAR(225));"

INSERT_INTO_BOOKS_TABLE = "INSERT INTO bookapp_book" \
                          "(id,title,author,isbn,description) VALUES (?,?,?,?,?);"

DELETE_BOOK_BY_TITLE = "DELETE FROM bookapp_book WHERE title = ?;"

GET_ALL_BOOKS = "SELECT title,author,isbn FROM bookapp_book;"


def connect():
    return sqlite3.connect("db.sqlite3")


def create_table(connection):
    with connection:
        connection.execute(CREATE_BOOKS_TABLE)


def get_all_books(connection):
    with connection:
        return connection.execute(GET_ALL_BOOKS).fetchall()


def add_book(connection, id, title, author, isbn, description):
    with connection:
        connection.execute(INSERT_INTO_BOOKS_TABLE, (id, title, author, isbn, description))


def delete_book(connection, title):
    with connection:
        connection.execute(DELETE_BOOK_BY_TITLE, (title,))
