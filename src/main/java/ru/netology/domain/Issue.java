package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor

public class Issue {
    private int id;
    private String name;
    private Set<String> assignees;
    private List<String> labels;
    private String author;
    private Calendar dateOfCreation;
    private Set<Integer> tag;
    private boolean statusOpened = true;
    private Calendar dateOfUpdate;
    private int quantityOfComments;
}
