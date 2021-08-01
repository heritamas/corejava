package ch12.completable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphProcessor {
    private final Pattern pattern;

    public ParagraphProcessor(String regex) {
        pattern = Pattern.compile(regex);
    }

    public Optional<String> checkParagraph(String paragraph) {
        Matcher matcher = pattern.matcher(paragraph);
        String result = null;

        if (matcher.find()) {
            result = paragraph;
        }

        return Optional.ofNullable(result);
    }


}
