package items;

import java.util.Date;

public class Newspaper extends Item {

    private Date publishedDate;

    public Newspaper(Date publishedDate, String title, String publication){
        super(title, publication);
        this.publishedDate =publishedDate;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public String toString() {
        return "Newspaper{" +
                "publishedDate=" + publishedDate +
                "} " + super.toString();
    }
}