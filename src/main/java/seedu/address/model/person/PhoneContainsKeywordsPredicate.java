package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;

import java.util.List;
import java.util.function.Predicate;

public class PhoneContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PhoneContainsKeywordsPredicate(List<String> keywords){
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsStringIgnoreCase(person.getPhone().toString(), keyword));
    }

    //TODO: Override toString and equals
}
