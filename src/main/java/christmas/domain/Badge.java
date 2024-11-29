package christmas.domain;

public enum Badge {
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int threshold;

    Badge(String name, int threshold) {
        this.name = name;
        this.threshold = threshold;
    }

    public static String findBadgeByAmount(int totalBenefit) {
        if (totalBenefit >= SANTA.threshold) {
            return SANTA.name;
        }
        if (totalBenefit >= TREE.threshold) {
            return TREE.name;
        }
        if (totalBenefit >= STAR.threshold) {
            return STAR.name;
        }
        return null;
    }
}
