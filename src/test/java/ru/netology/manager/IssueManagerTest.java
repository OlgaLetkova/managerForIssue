package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {
    private IssueManager manager = new IssueManager();
    private Issue issue1 = new Issue(1, "Create DisplayName MethodOrderer to order tests by their display name", Set.of("Britter"), List.of("new feature"), "dylan-asos", new GregorianCalendar(2020, Calendar.SEPTEMBER, 18), Set.of(2212), true, new GregorianCalendar(2020, Calendar.NOVEMBER, 20), 3);
    private Issue issue2 = new Issue(2, "Passing TestExecutionListener to engine launched by EngineTestKit", Set.of("Marc Phillip"), List.of("question"), "nipafx", new GregorianCalendar(2020, Calendar.NOVEMBER, 15), Set.of(2480), false, new GregorianCalendar(2020, Calendar.NOVEMBER, 26), 1);
    private Issue issue3 = new Issue(3, "PreDestroy callback is not invoked for enclosing instances of nested classes", Set.of("Marc Phillip"), List.of("bug"), "paschi", new GregorianCalendar(2020, Calendar.SEPTEMBER, 30), Set.of(2430), true, new GregorianCalendar(2020, Calendar.NOVEMBER, 7), 8);
    private Issue issue4 = new Issue(4, "Duplicate annotations are swallowed by AnnotationSupport", Set.of("Nicolai Parlog"), List.of("question"), "paschi", new GregorianCalendar(2019, Calendar.DECEMBER, 17), Set.of(2131), true, new GregorianCalendar(2019, Calendar.DECEMBER, 27), 5);
    private Issue issue5 = new Issue(5, "Wrong encoding of standard output", Set.of("Marc Phillip"), List.of("question"), "romanzek", new GregorianCalendar(2020, Calendar.OCTOBER, 5), Set.of(2440), false, new GregorianCalendar(2020, Calendar.OCTOBER, 8), 4);

    @Nested
    public class Empty {

        @Test
        public void shouldAddIssueToEmptyList() {
            boolean actual = manager.add(issue1);
            assertTrue(actual);
        }

        @Test
        public void shouldGetEmptyList() {
            int actual = manager.getAll().size();
            int expected = 0;
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class SingleIssue {
        @BeforeEach
        public void setUp() {
            manager.add(issue1);
        }

        @Test
        public void getOpenedIssues() {
            List<Issue> actual = manager.getOpenedIssues();
            List<Issue> expected = List.of(issue1);
            assertEquals(expected, actual);
        }

        @Test
        public void getClosedIssues() {
            manager.add(issue2);
            List<Issue> actual = manager.getClosedIssues();
            List<Issue> expected = List.of(issue2);
            assertEquals(expected, actual);
        }

        @Test
        public void getClosedIssuesIfNotExist() {
            manager.getClosedIssues();
            int actual = manager.getClosedIssues().size();
            int expected = 0;
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class MultipleIssue {
        @BeforeEach
        public void setUp() {
            manager.add(issue1);
            manager.add(issue2);
            manager.add(issue3);
            manager.add(issue4);
            manager.add(issue5);
        }

        @Test
        public void getOpenedIssues() {
            List<Issue> actual = manager.getOpenedIssues();
            List<Issue> expected = List.of(issue1, issue3, issue4);
            assertEquals(expected, actual);
        }

        @Test
        public void getClosedIssues() {
            List<Issue> actual = manager.getClosedIssues();
            List<Issue> expected = List.of(issue2, issue5);
            assertEquals(expected, actual);
        }

        @Test
        public void searchByAssignee() {
            FilterByAssignee filter = new FilterByAssignee("Marc Phillip");
            List<Issue> actual = manager.searchBy(filter);
            List<Issue> expected = List.of(issue2, issue3, issue5);
            assertEquals(expected, actual);
        }

        @Test
        public void searchByAuthor() {
            FilterByAuthor filter = new FilterByAuthor("paschi");
            List<Issue> actual = manager.searchBy(filter);
            List<Issue> expected = List.of(issue3, issue4);
            assertEquals(expected, actual);
        }

        @Test
        public void searchByLabel() {
            FilterByLabel filter = new FilterByLabel("question");
            List<Issue> actual = manager.searchBy(filter);
            List<Issue> expected = List.of(issue2, issue4, issue5);
            assertEquals(expected, actual);
        }

        @Test
        public void directOrderSortByDateOfCreation() {
            SortByDateOfCreationAscComparator sort = new SortByDateOfCreationAscComparator();
            List<Issue> actual = manager.directOrderSortBy(sort);
            List<Issue> expected = List.of(issue4, issue1, issue3, issue5, issue2);
            assertEquals(expected, actual);
        }

        @Test
        public void directOrderSortByDateOfUpdate() {
            SortByDateOfUpdateAscComparator sort = new SortByDateOfUpdateAscComparator();
            List<Issue> actual = manager.directOrderSortBy(sort);
            List<Issue> expected = List.of(issue4, issue5, issue3, issue1, issue2);
            assertEquals(expected, actual);
        }

        @Test
        public void directOrderSortByQuantityOfComments() {
            SortByQuantityOfComments sort = new SortByQuantityOfComments();
            List<Issue> actual = manager.directOrderSortBy(sort);
            List<Issue> expected = List.of(issue2, issue1, issue5, issue4, issue3);
            assertEquals(expected, actual);
        }

        @Test
        public void reverseOrderSortByDateOfCreation() {
            SortByDateOfCreationAscComparator sort = new SortByDateOfCreationAscComparator();
            List<Issue> actual = manager.reverseOrderSortBy(sort);
            List<Issue> expected = List.of(issue2, issue5, issue3, issue1, issue4);
            assertEquals(expected, actual);
        }

        @Test
        public void reverseOrderSortByDateOfUpdate() {
            SortByDateOfUpdateAscComparator sort = new SortByDateOfUpdateAscComparator();
            List<Issue> actual = manager.reverseOrderSortBy(sort);
            List<Issue> expected = List.of(issue2, issue1, issue3, issue5, issue4);
            assertEquals(expected, actual);
        }

        @Test
        public void reverseOrderSortByQuantityOfComments() {
            SortByQuantityOfComments sort = new SortByQuantityOfComments();
            List<Issue> actual = manager.reverseOrderSortBy(sort);
            List<Issue> expected = List.of(issue3, issue4, issue5, issue1, issue2);
            assertEquals(expected, actual);
        }

        @Test
        public void openById() {
            manager.openById(2);
            boolean actual = manager.getAll().get(1).isStatusOpened();
            assertTrue(actual);
        }

        @Test
        public void closeById() {
            manager.closeById(3);
            boolean actual = manager.getAll().get(2).isStatusOpened();
            assertFalse(actual);
        }
    }
}