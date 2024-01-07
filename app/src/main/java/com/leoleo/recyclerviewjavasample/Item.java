package com.leoleo.recyclerviewjavasample;

import androidx.annotation.NonNull;

import java.util.Objects;

public final class Item {
    private final int internalId;

    @NonNull
    private final String title;

    public Item(int internalId, @NonNull String title) {
        this.internalId = internalId;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return internalId == item.internalId && Objects.equals(title, item.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internalId, title);
    }

    @NonNull
    @Override
    public String toString() {
        return "Item{" +
                "internalId=" + internalId +
                ", title='" + title + '\'' +
                '}';
    }
}