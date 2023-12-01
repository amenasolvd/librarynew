package items;

import java.util.Objects;

public class Magazine extends Item {

    private String category;

    public Magazine(String category, String title, String publication) {
        super(title, publication);
        this.category = category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Magazine magazine)) return false;
        return Objects.equals(getItemId(), magazine.getItemId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemId());
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "category='" + category + '\'' +
                "} " + super.toString();
    }
}