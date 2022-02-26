import book_genre_ex
import books_ex
import genre_ex
import users_ex
import opinion_ex


def database():
    print("Książki\n")
    books_ex.books_manager()
    print("Gatunki\n")
    genre_ex.genre_manager()
    print("Użytkownicy\n")
    users_ex.user_manager()
    print("opinie\n")
    opinion_ex.opinion_manager()
    print("gatunek - książka\n")
    book_genre_ex.book_genre_manager()


database()
