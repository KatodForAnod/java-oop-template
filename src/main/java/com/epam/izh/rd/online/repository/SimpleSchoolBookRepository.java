package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] schoolBooksCopy = new SchoolBook[schoolBooks.length + 1];
        System.arraycopy(schoolBooks, 0, schoolBooksCopy, 0, schoolBooks.length);
        schoolBooksCopy[schoolBooksCopy.length - 1] = book;
        schoolBooks = schoolBooksCopy;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] schoolBooksCopy = new SchoolBook[schoolBooks.length];
        int countFoundBooks = 0;

        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) {
                schoolBooksCopy[countFoundBooks] = schoolBook;
                countFoundBooks++;
            }
        }

        SchoolBook[] foundSchoolBooks = new SchoolBook[countFoundBooks];
        System.arraycopy(schoolBooksCopy, 0, foundSchoolBooks, 0, countFoundBooks);

        return foundSchoolBooks;
    }

    @Override
    public boolean removeByName(String name) {
        if (findByName(name).length <= 0) {
            return false;
        }
        int countFoundBooks = 0;
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                countFoundBooks++;
            } else {
                schoolBooks[i - countFoundBooks] = schoolBooks[i];
            }
        }
        SchoolBook[] schoolBooksCopy = new SchoolBook[schoolBooks.length - countFoundBooks];
        System.arraycopy(schoolBooks, 0, schoolBooksCopy, 0, schoolBooks.length - countFoundBooks);
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
