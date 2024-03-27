package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class EmailContainsKeywordPredicateTest {

    @Test
    public void equals() {
        // This methods tests if the equal object is working well.
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        EmailContainsKeywordPredicate firstPredicate = new EmailContainsKeywordPredicate(firstPredicateKeywordList);
        EmailContainsKeywordPredicate secondPredicate = new EmailContainsKeywordPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EmailContainsKeywordPredicate firstPredicateCopy = new EmailContainsKeywordPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emailContainsNumber_returnsTrue() {
        // One keyword
        EmailContainsKeywordPredicate predicate =
                new EmailContainsKeywordPredicate(Collections.singletonList("jerome@gmail.com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("jerome@gmail.com").build()));

        // Multiple keywords
        predicate = new EmailContainsKeywordPredicate(Arrays.asList("jerome@gmail.com", "chua_jerome@gmail.com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("jerome@gmail.com").build()));

        // Only one matching keyword
        predicate = new EmailContainsKeywordPredicate(Arrays.asList("gmail.com", "jerome@gmail"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("jerome@gmail.com").build()));
    }

    @Test
    public void test_emailDoesNotContainString_returnsFalse() {
        // Zero keywords
        EmailContainsKeywordPredicate predicate = new EmailContainsKeywordPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withEmail("jerome@outlook.com").build()));

        // Non-matching keyword
        predicate = new EmailContainsKeywordPredicate(Arrays.asList("Carollim"));
        assertFalse(predicate.test(new PersonBuilder().withEmail("carol@google.com").build()));

        // Keywords match phone, email, but does not match email
        predicate = new EmailContainsKeywordPredicate(Arrays.asList("12345", "alice124@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("999")
                .withEmail("alice@email.com").withId(12).build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        EmailContainsKeywordPredicate predicate = new EmailContainsKeywordPredicate(keywords);

        String expected = EmailContainsKeywordPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
