/*
 * Licensed under the Academic Free License (AFL 3.0).
 *     http://opensource.org/licenses/AFL-3.0
 *
 *  This code is distributed to CSULB students in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, other than educational.
 *
 *  2018 Alvaro Monge <alvaro.monge@csulb.edu>
 *
 * Originally provided by Dr. Alvaro Monge of CSULB, and subsequently modified by Dave Brown.
 */

package csulb.cecs323.app;

// Imported the necessary Java packages and extensions needed for this program.
import csulb.cecs323.model.*;
import javax.persistence.*;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * A program using Java Persistence API (JPA) with Books as an example.
 * Used the database from the CarClub lab and modified it to fit this program.
 *
 * Project: JPA - Books
 * Class: CECS 323
 * Professor: David Brown
 * Date: June 16, 2021
 *
 * @author Edward Ahn
 * @author Joon Im
 * @author Marie Payad
 * @author Nathaniel Monte de Ramos
 *
 */

public class CarClub {
   /**
    * You will likely need the entityManager in a great many functions throughout your application.
    * Rather than make this a global variable, we will make it an instance variable within the CarClub
    * class, and create an instance of CarClub in the main.
    */
   private EntityManager entityManager;

   /**
    * The Logger can easily be configured to log to a file, rather than, or in addition to, the console.
    * We use it because it is easy to control how much or how little logging gets done without having to
    * go through the application and comment out/uncomment code and run the risk of introducing a bug.
    * Here also, we want to make sure that the one Logger instance is readily available throughout the
    * application, without resorting to creating a global variable.
    */
   private static final Logger LOGGER = Logger.getLogger(CarClub.class.getName());

   /**
    * The constructor for the CarClub class.  All that it does is stash the provided EntityManager
    * for use later in the application.
    * @param manager    The EntityManager that we will use.
    */
   public CarClub(EntityManager manager) {
      this.entityManager = manager;
   }
   private Query query;

   public static void main(String[] args) {
      LOGGER.fine("Creating EntityManagerFactory and EntityManager");
      EntityManagerFactory factory = Persistence.createEntityManagerFactory("CarClub");
      EntityManager manager = factory.createEntityManager();
      // Create an instance of CarClub and store our new EntityManager as an instance variable.
      CarClub carclub = new CarClub(manager);


      // Any changes to the database need to be done within a transaction.
      // See: https://en.wikibooks.org/wiki/Java_Persistence/Transactions

      LOGGER.fine("Begin of Transaction");
      EntityTransaction tx = manager.getTransaction();

      boolean valid = false;
      do{
         System.out.println("---Project : JPA Books---");
         System.out.println("1. Adding a Publisher");
         System.out.println("2. Retrieve all Publishers");
         System.out.println("3. Delete a Publisher");
         System.out.println("4. Return all Primary Key for Publisher");
         System.out.println("5. Return all Primary Key for Books");
         System.out.println("6. Add a Writing Group");
         System.out.println("7. Add a Book");
         System.out.println("8. ");
         System.out.println("9. ");
         System.out.println("10. ");
         System.out.println("11. ");
         System.out.println("12. ");
         System.out.println("13. ");
         System.out.println("8. Exit");

         Scanner scan = new Scanner(System.in);

         int ans = scan.nextInt();

         switch (ans){
            case 1:
               //Add something to the Database
               tx.begin();
               System.out.println("Add a Publisher");
               carclub.createEntity(carclub.addPublisher());
               // Commit the changes so that the new data persists and is visible to other users.
               tx.commit();
               LOGGER.fine("End of Transaction");
               break;
            case 2:
               //return publishers
               System.out.println("Returning Publishers");
               carclub.returnPublisher();
               break;
            case 3:
               //Delete a publisher
               tx.begin();
               System.out.println("Delete publisher");
               carclub.deletePublisher();
               tx.commit();
               LOGGER.fine("End of Transaction");
               break;
            case 4:
               System.out.println("Returning PK for Publisher");
               carclub.returnPkPublisher();
                break;
            case 5:
                System.out.println("Returning PK Books");
                carclub.returnPkBook();
                break;
            case 6:
                tx.begin();
                System.out.println("Adding a Writing Group");
                carclub.createEntity(carclub.addWritingGroup());
                tx.commit();
                LOGGER.fine("End of Transaction");
                break;
            case 7:
                tx.begin();
                System.out.println("Add a Book");
                carclub.createEntity(carclub.addBook());
                tx.commit();
                LOGGER.fine("End of Transaction");
                break;
            case 8:
                 System.out.println("Thank you. Have a nice Day.");
                 return;
            default:
               valid = false;
               System.out.println("Invalid Response: " + ans + ". Please select from the menu.");
         }
         System.out.println("\n\n");
      }while(!valid);
      //carclub.createEntity (owners);

      // Commit the changes so that the new data persists and is visible to other users.


   } // End of the main method

   /**
    * Create and persist a list of objects to the database.
    * @param entities   The list of entities to persist.  These can be any object that has been
    *                   properly annotated in JPA and marked as "persistable."  I specifically
    *                   used a Java generic so that I did not have to write this over and over.
    */
   public <E> void createEntity(List <E> entities) {
      for (E next : entities) {
         LOGGER.info("Persisting: " + next);
         // Use the CarClub entityManager instance variable to get our EntityManager.
         this.entityManager.persist(next);
      }

      // The auto generated ID (if present) is not passed in to the constructor since JPA will
      // generate a value.  So the previous for loop will not show a value for the ID.  But
      // now that the Entity has been persisted, JPA has generated the ID and filled that in.
      for (E next : entities) {
         LOGGER.info("Persisted object after flush (non-null id): " + next);
      }
   } // End of createEntity member method

   /**
    * Think of this as a simple map from a String to an instance of auto_body_styles that has the
    * same name, as the string that you pass in.  To create a new Cars instance, you need to pass
    * in an instance of auto_body_styles to satisfy the foreign key constraint, not just a string
    * representing the name of the style.
    * @param name       The name of the autobody style that you are looking for.
    * @return           The auto_body_styles instance corresponding to that style name.
    */
   public auto_body_styles getStyle (String name) {
      // Run the native query that we defined in the auto_body_styles entity to find the right style.
      List<auto_body_styles> styles = this.entityManager.createNamedQuery("ReturnAutoBodyStyle",
              auto_body_styles.class).setParameter(1, name).getResultList();
      if (styles.size() == 0) {
         // Invalid style name passed in.
         return null;
      } else {
         // Return the style object that they asked for.
         return styles.get(0);
      }
   }// End of the getStyle method



   public List<Publishers> addPublisher(){
      List<Publishers> adding_publisher = new ArrayList<Publishers>();
      Scanner scan = new Scanner(System.in);
      System.out.println("Adding a Publisher");
      System.out.print("Enter the Publisher Name: ");
      String name = scan.nextLine();
      System.out.print("\nEnter Publisher Phone: ");
      String phone = scan.nextLine();
      System.out.print("\nEnter Publisher Email: ");
      String email = scan.nextLine();
      System.out.println();
      adding_publisher.add(new Publishers(name, email, phone));
      System.out.println("Publisher " + name + " has been added to the database");
      return adding_publisher;
   }

   public void returnPublisher(){
       query = entityManager.createNativeQuery("SELECT * FROM PUBLISHERS");
       List<Object[]> result = query.getResultList();
       System.out.print("Publishers: ");
       for(int i = 0; i < result.size(); i++) {
           System.out.printf("\n" + (i+1) + ". " + result.get(i)[0] + " " + result.get(i)[1] + " " + result.get(i)[2]);
       }
       System.out.println();
   }

   public void returnPkPublisher(){
       query = entityManager.createNativeQuery("SELECT PUBLISHERS.NAME FROM PUBLISHERS");
       List<Object[]> result = query.getResultList();
       System.out.print("PK of Publisher: ");
       for(int i = 0; i < result.size(); i++){
           System.out.printf("\n" + result.get(i));
       }
       System.out.println();
   }
   public void deletePublisher(){
       returnPublisher();
       Scanner scan = new Scanner(System.in);
       System.out.println("Deleting a Publisher will remove all information the publisher has in the database.");
       System.out.println("Who will you like to remove?");
       System.out.print("Enter the Publisher Name: ");
       String name = scan.nextLine();
       System.out.print("\nEnter Publisher Phone: ");
       String phone = scan.nextLine();
       System.out.print("\nEnter Publisher Email: ");
       String email = scan.nextLine();
       System.out.println();
       query = entityManager.createNativeQuery("DELETE FROM PUBLISHERS WHERE PUBLISHERS.NAME = '" + name + "' AND " + " EMAIL = '" + email + "' AND PHONE = '"+phone+"'");
       query.executeUpdate();
       System.out.println("Publisher " + name + " has been deleted.");
   }
   /**
   * Adding Book into DB
   */
   public List<Books> addBook() {
       List<Books> book = new ArrayList<Books>();

      Scanner scan = new Scanner(System.in);
      System.out.println("Adding a Book");
      System.out.println("Enter the ISBN: ");
      String isbn = scan.nextLine();
      System.out.println("Enter the Title: ");
      String title = scan.nextLine();
      System.out.println("Enter Year Published: ");
      int year = scan.nextInt();

      List<Publishers> bookPublisher = addPublisher();
      List<WritingGroups> bookAuthorEmail = addWritingGroup();

      book.add(new Books(isbn, title, year, bookPublisher.get(0), bookAuthorEmail.get(0)));

      System.out.println("Book " + title + " has been added to the database");
      return book;
   }

    /**
     * Adding Authoring Entity into DB
     */
    public List<WritingGroups> addWritingGroup() {
        List<WritingGroups> writingGroups = new ArrayList<WritingGroups>();

        Scanner scan = new Scanner(System.in);
        System.out.println("Adding an Authoring Entity");
        System.out.println("Enter Author Email: ");
        String email = scan.nextLine();
        System.out.println("Enter the Author Name: ");
        String name = scan.nextLine();
        System.out.println("Enter the Head Writer: ");
        String headWriter = scan.nextLine();
        System.out.println("Enter the Year Formed: ");
        int yearFormed = scan.nextInt();

        writingGroups.add(new WritingGroups(email, name, headWriter, yearFormed));
        System.out.println("Authoring Entity " + name + " has been added to the database");
        return writingGroups;
    }

    /**
     * Deleting Books in DB
     */
    public void deleteBooks(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        Scanner scan = new Scanner(System.in);
        System.out.println("What is the name of the book");
        System.out.println("Enter the name of the publisher of the book: ");
        String name_publisher = scan.nextLine();
        System.out.println("Enter the title of the book: ");
        String title = scan.nextLine();
        System.out.println("Enter the Authoring Entity Email: ");
        String authoring_email = scan.nextLine();
        Query q = em.createNativeQuery("DELETE ISBN, TITLE, YEAR_PUBLISHED, AUTHORING_ENTITY_NAME, PUBLISHER_NAME  FROM BOOK a WHERE PUBLISHER_NAME = :name_publisher AND TITLE = :title AND AUTHORING_ENTITY_NAME = :authoring_email", Books.class);
        List<Books> book = q.getResultList();
        for (Books a : book) {
            System.out.println(a.getBookTitle()+ " was deleted from the database");
        }
    }

    /**
     * Updating Books in DB
     */
//    public void updateBooks(){
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
//        EntityManager em = emf.createEntityManager();
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Enter the ISBN of the book: ");
//        String isbn1 = scan.nextLine();
//        Book a = em.find(Book.class, isbn);
//        Query q = em.createNativeQuery("SELECT ISBN, TITLE, YEAR_PUBLISHED, AUTHORING_ENTITY_NAME, PUBLISHER_NAME  FROM BOOK a WHERE ISBN = :isbn1", Books.class);
//        List<Books> book = q.getResultList();
//        for (Books a : book) {
//            System.out.println("Enter Authoring Entity Name: ");
//            String authoring_name = scan.nextLine();
//            a.setAuthorEntityEmail(authoring_name);
//        }
//    }

    /**
     * Returns PK of Books
     */
    public void returnPkBook(){
        query = entityManager.createNativeQuery("SELECT ISBN, BOOK.TITLE  FROM BOOK");
        List<Object[]> res = query.getResultList();
        for(int i = 0; i < res.size(); i++){
            System.out.println((i+1) + ". " + res.get(i)[0]);
        }
    }
} // End of CarClub class
