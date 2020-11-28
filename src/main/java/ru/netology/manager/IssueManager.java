package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.*;
import java.util.function.Predicate;

public class IssueManager {
    private IssueRepository repository;

    public IssueManager() {
        this.repository = new IssueRepository();
    }

    public boolean add(Issue item) {return this.repository.add(item);}

    public List<Issue> getAll() {return this.repository.getAll();}

    public List<Issue> getOpenedIssues() {return this.repository.getOpenedIssues();}

    public List<Issue> getClosedIssues() {return this.repository.getClosedIssues();}


    public List<Issue> searchBy(Predicate<Issue> filter) {
        List<Issue> allIssues = repository.getAll();
        List<Issue> result = new ArrayList<>();
        Issue tmp;
        for (int i = 0; i < allIssues.size(); i++) {
            tmp = allIssues.get(i);
            if (filter.test(tmp)) {
                result.add(tmp);
            }
        }
        return result;
    }


    public List<Issue> directOrderSortBy(Comparator<Issue> comparator) {
        List<Issue> result = repository.getAll();
        result.sort(comparator);
        return result;
    }


    public List<Issue> reverseOrderSortBy(Comparator<Issue> comparator) {
        List<Issue> result = repository.getAll();
        result.sort(comparator.reversed());
        return result;
    }


    public void openById(int id) {
        List<Issue> allIssues = repository.getAll();
        Issue tmp;
        for (int i = 0; i < allIssues.size(); i++) {
            tmp = allIssues.get(i);
            if (tmp.getId() == id) {
                repository.getAll().get(i).setStatusOpened(true);
                return;
            }
        }
    }

    public void closeById(int id) {
        List<Issue> allIssues = repository.getAll();
        Issue tmp;
        for (int i = 0; i < allIssues.size(); i++) {
            tmp = allIssues.get(i);
            if (tmp.getId() == id) {
                repository.getAll().get(i).setStatusOpened(false);
                return;
            }
        }
    }
}
