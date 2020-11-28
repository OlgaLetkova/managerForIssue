package ru.netology.domain;

import java.util.function.Predicate;

public class FilterByAuthor implements Predicate<Issue> {
    private String author;

    public FilterByAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean test(Issue issue) {
        return author.contentEquals(issue.getAuthor());
    }
}