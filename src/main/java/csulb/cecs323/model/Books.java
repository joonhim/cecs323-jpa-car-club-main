package csulb.cecs323.model;

// Imported the necessary Java packages and extensions needed for this class.
import javax.persistence.*;

/**
 * A printed work consisting of sheets of paper bound together that is written by a writer.
 */
@Entity
public class Books {
    // Variables

    /**
     * The number of the ISBN.
     */
    @Id // Primary Key annotation
    @Column(length = 17, nullable = false)
    private String ISBN;

    /**
     * The title of the Book.
     */
    @Column(length = 80, nullable = false)
    private String title;

    /**
     * The year the Book is published.
     */
    @Column(length = 4, nullable = false)
    private int yearPublished;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PUBLISHER_NAME", referencedColumnName = "PUBLISHER_NAME", nullable = false)
    private Publishers publishers;

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "AUTHORING_ENTITY_EMAIL")
    private authoring_entities authoringEntities;


    // Constructor

    public Books() {}

    /**
     * The constructor for the Book class.
     * @param name
     * @param isbn
     * @param book_title
     * @param year_published
     * @param author_entity_email
     */
    public Books(String isbn, String bookTitle, int yearPublished, Publishers publisher, authoring_entities author) {
        this.ISBN = isbn;
        this.title = bookTitle;
        this.year_published = yearPublished;
        this.publishers = publisher;
        this.authoringEntities = author;
    }

    // Getters & Setters

    /**
     * Gets the name of the Publisher.
     * @return Name of the Publisher
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the number of the ISBN.
     * @return ISBN of the Book
     */
    public String getISBN() {
        return this.ISBN;
    }

    /**
     * Gets the title of the Book.
     * @return Title of the Book
     */
    public String getBookTitle() {
        return this.bookTitle;
    }

    /**
     * Gets the published year of the Book.
     * @return Year the Book is published
     */
    public int getYearPublished() {
        return this.yearPublished;
    }

    public String getAuthorEntityEmail() {
        return this.authorEntityEmail;
    }

    /**
     * Changes the name of the Publisher of the Book.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Changes the ISBN number of the Book.
     * @param isbn
     */
    public void setISBN(String isbn) {
        this.ISBN = isbn;
    }

    /**
     * Changes the title of the Book.
     * @param book_title
     */
    public void setBookTitle(String book_title) {
        this.bookTitle = book_title;
    }

    /**
     * Changes the year published of the Book.
     * @param year_published
     */
    public void setYearPublished(int year_published) {
        this.yearPublished = year_published;
    }

    /**
     * Changes the email of the Author Entity.
     * @param author_entity_email
     */
    public void setAuthorEntityEmail(String author_entity_email) {
        this.authorEntityEmail = author_entity_email;
    }

    /**
     * toString() method prints the string values of Books.
     *
     * @return Publisher Name, ISBN, Book Title, Year Published, Author Entity Email
     */
    @Override
    public String toString () {
        return "Publisher Name: " + this.name + " ISBN: " + this.ISBN +
                " Book Title: " + this.bookTitle + " Year Published: " + this.yearPublished +
                " Email: " + this.authorEntityEmail;
    }
}
