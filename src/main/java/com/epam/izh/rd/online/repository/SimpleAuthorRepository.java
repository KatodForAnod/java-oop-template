package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            Author[] authorsCopy = new Author[authors.length + 1];
            System.arraycopy(authors, 0, authorsCopy, 0, authors.length);
            authorsCopy[authorsCopy.length - 1] = author;
            authors = authorsCopy;
            return true;
        }
        return false;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author : authors) {
            if (author.getName().equals(name) && author.getLastName().equals(lastname)) {
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        }
        int positionOfDeleteAuthor = -1;
        for (int i = 0; i < authors.length; i++) {
            if (author.getName().equals(authors[i].getName()) &&
                    author.getLastName().equals(authors[i].getLastName())) {
                positionOfDeleteAuthor = i;
            }
        }
        Author[] authorsCopy = new Author[authors.length - 1];

        for (int i = 0; i < authors.length; i++) {
            if (i < positionOfDeleteAuthor) {
                authorsCopy[i] = authors[i];
            } else if (i > positionOfDeleteAuthor) {
                authorsCopy[i - 1] = authors[i];
            }
        }
        authors = authorsCopy;
        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
