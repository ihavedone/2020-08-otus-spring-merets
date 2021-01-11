package ru.otus.merets.library.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.merets.library.domain.*;
import ru.otus.merets.library.repository.BookRepository;
import ru.otus.merets.library.repository.UserRepository;

import java.util.Set;

@ChangeLog
public class DatabaseChangelog {
    @ChangeSet(order = "001", id="dropDb", author = "ihavedone", runAlways = true)
    public void dropDb(MongoDatabase db){
        db.drop();
    }

    @ChangeSet(order = "002", id="insertBooks", author = "ihavedone", runAlways = true)
    public void insertBooks(BookRepository bookRepository){
        Genre comedy = new Genre("Comedy");
        Genre historical = new Genre("Historical");
        Genre drama = new Genre( "Drama");
        Genre fantasy = new Genre( "Fantasy");
        Genre it = new Genre( "IT");
        Genre philosophy = new Genre( "Philosofy");
        Genre novel = new Genre( "Novel");

        Author leoTolstoy = new Author( "Leo Tolstoy");
        Author bjarneStroustrup = new Author( "Bjarne Stroustrup");
        Author julianRowling = new Author( "Julian Rowling");
        Author friedrichNietzsche = new Author( "Friedrich Nietzsche");
        Author fyodorDostoevsky = new Author( "Fyodor Dostoevsky");
        Author michaelBeck = new Author( "Michael Beck");
        Author haraldBohme = new Author( "Harald Bohme");
        Author mirkoDziadzka = new Author( "Mirko Dziadzka");
        Author ulrichKunitz = new Author( "Ulrich Kunitz");
        Author robertMagnus = new Author( "Robert Magnus");
        Author dirkVerworner = new Author( "Dirk Verworner");

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
    }

    @ChangeSet(order = "003", id="insertUsers", author = "ihavedone", runAlways = true)
    public void insertUsers(UserRepository userRepository){
        User user = new User(null,
                "admin",
                "$argon2id$v=19$m=65536,t=3,p=1$bmdrNWhBanRsOHJMdnNOaA$5FkuOWVnnYSamOBIE4zHfX8zfi0v/XtvFb/WkIwilJc",
                Set.of("ADMIN"));

        userRepository.save( user );
    }

}
