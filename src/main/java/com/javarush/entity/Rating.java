package com.javarush.entity;

public enum Rating {
    G,
    PG,
    PG_13("PG-13"), // Используем символ подчеркивания для имени
    R,
    NC_17("NC-17");

    private final String displayName;

    Rating() {
        this.displayName = name();
    }

    Rating(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Rating fromDisplayName(String displayName) {
        for (Rating rating : values()) {
            if (rating.getDisplayName().equals(displayName)) {
                return rating;
            }
        }
        throw new IllegalArgumentException("Unknown display name: " + displayName);
    }
}