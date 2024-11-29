package christmas.domain;

import java.util.Arrays;

public enum Badge {
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000)
    ;

    private final String name;
    private final int threshold;

    Badge(final String name, final int threshold) {
        this.name = name;
        this.threshold = threshold;
    }

    public static String findBadgeByAmount(final int totalBenefit) {
        return Arrays.stream(values())
                .sorted((b1, b2) -> Integer.compare(b2.threshold, b1.threshold))
                .filter(badge -> totalBenefit >= badge.threshold)
                .map(badge -> badge.name)
                .findFirst()
                .orElse(null);
    }
}
