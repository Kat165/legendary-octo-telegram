import sqlite3

CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS users" \
                     "(id INTEGER, password VARCHAR(128), last_login DATETIME, is_superuser BOOL, username VARCHAR(20), " \
                     "last_name VARCHAR(30), email VARCHAR(254), is_staff BOOL, is_active BOOL, date_joined DATETIME, " \
                     "PRIMARY KEY (id));"

ADD_USER = "INSERT INTO users (id, nick, password, email) VALUES (?,?,?,?);"

GET_ALL_USERS = "SELECT * FROM users"

DELETE_US_BY_EMAIL = "DELETE FROM users WHERE email = ?;"


def connect():
    return sqlite3.connect("db.sqlite3")


def create_table(connection):
    with connection:
        connection.execute(CREATE_USERS_TABLE)


def add_user(connection, id, nick, password, email):
    with connection:
        connection.execute(ADD_USER, (id, nick, password, email))


def get_all_users(connection):
    with connection:
        return connection.execute(GET_ALL_USERS).fetchall()


def delete_user_by_email(connection, email):
    with connection:
        connection.execute(DELETE_US_BY_EMAIL, (email,))
