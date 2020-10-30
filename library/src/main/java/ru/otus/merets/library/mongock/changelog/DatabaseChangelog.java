package ru.otus.merets.library.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.merets.library.domain.Author;
import ru.otus.merets.library.domain.Book;
import ru.otus.merets.library.domain.Comment;
import ru.otus.merets.library.domain.Genre;
import ru.otus.merets.library.repository.BookRepository;
import ru.otus.merets.library.repository.CommentRepository;

import java.util.Set;

@ChangeLog
public class DatabaseChangelog {
    @ChangeSet(order = "001", id="dropDb", author = "ihavedone", runAlways = true)
    public void dropDb(MongoDatabase db){
        db.drop();
    }

    @ChangeSet(order = "002", id="insertBooks", author = "ihavedone", runAlways = true)
    public void insertBooks(BookRepository bookRepository, CommentRepository commentRepository){
        Genre comedy = new Genre("1", "Comedy");
        Genre historical = new Genre("2", "Historical");
        Genre drama = new Genre("3", "Drama");
        Genre fantasy = new Genre("4", "Fantasy");
        Genre it = new Genre("5", "IT");
        Genre philosophy = new Genre("6", "Philosofy");
        Genre novel = new Genre("7", "Novel");

        Author leoTolstoy = new Author("1", "Leo Tolstoy");
        Author bjarneStroustrup = new Author("2", "Bjarne Stroustrup");
        Author julianRowling = new Author("3", "Julian Rowling");
        Author friedrichNietzsche = new Author("4", "Friedrich Nietzsche");
        Author fyodorDostoevsky = new Author("5", "Fyodor Dostoevsky");
        Author michaelBeck = new Author("6", "Michael Beck");
        Author haraldBohme = new Author("7", "Harald Bohme");
        Author mirkoDziadzka = new Author("8", "Mirko Dziadzka");
        Author ulrichKunitz = new Author("9", "Ulrich Kunitz");
        Author robertMagnus = new Author("10", "Robert Magnus");
        Author dirkVerworner = new Author("11", "Dirk Verworner");

        Book warAndPeace = new Book(
                "1",
                "War and Peace",
                Set.of(leoTolstoy),
                Set.of(historical,drama,novel)
        );
        Book cpp = new Book(
                "2",
                "Programming: Principles and Practice Using C++ (2nd Edition)",
                Set.of(bjarneStroustrup),
                Set.of(it)
        );
        Book idiot = new Book(
                "3",
                "The Idiot",
                Set.of(fyodorDostoevsky),
                Set.of(historical,novel)
        );
        Book harryPotter = new Book(
                "4",
                "Harry Potter",
                Set.of(julianRowling),
                Set.of(fantasy)
        );
        Book goodAndEvil = new Book(
                "5",
                "Beyond Good and Evil: The Philosophy Classic",
                Set.of(friedrichNietzsche),
                Set.of(philosophy)
        );
        Book linuxKernel = new Book(
                "6",
                "Linux Kernel Internals (2nd Edition)",
                Set.of(michaelBeck,haraldBohme,mirkoDziadzka,ulrichKunitz,robertMagnus,dirkVerworner),
                Set.of(it)
        );

        bookRepository.save( warAndPeace );
        bookRepository.save( cpp );
        bookRepository.save( idiot );
        bookRepository.save( harryPotter );
        bookRepository.save( goodAndEvil );
        bookRepository.save( linuxKernel );

        Comment comment1 = new Comment("1", "Wow!", warAndPeace);
        Comment comment2 = new Comment("2", "Amazing!", warAndPeace);
        Comment comment3 = new Comment("3", "Thank you", warAndPeace);

        commentRepository.save(comment1);
        commentRepository.save(comment2);
        commentRepository.save(comment3);
    }

}
