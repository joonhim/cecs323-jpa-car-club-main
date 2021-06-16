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
 */

package csulb.cecs323.app;

// Import all of the entity classes that we have written for this application.
import csulb.cecs323.model.*;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * A simple application to demonstrate how to persist an object in JPA.
 * <p>
 * This is for demonstration and educational purposes only.
 * </p>
 * <p>
 *     Originally provided by Dr. Alvaro Monge of CSULB, and subsequently modified by Dave Brown.
 * </p>
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
    private Scanner enquiry;

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
         System.out.println("8. Add an Individual Author");
         System.out.println("9. Add an Ad Hoc Team");
         System.out.println("10. Add an author to an existing Writing Group");
         System.out.println("11. Retrieve all Books");
         System.out.println("12. Retrieve all Writing Group");
         System.out.println("13. Delete a Book");
         System.out.println("14. Update a Book");
         System.out.println("15. Return all Primary Key for Authoring Entities");
         System.out.println("16. Exit");

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
                tx.begin();
                System.out.println("Add an Individual Author");
                carclub.createEntity(carclub.addindividual_author());
                tx.commit();
                LOGGER.fine("End of Transaction");
                break;
            case 9:
                tx.begin();
                System.out.println("Add an Ad Hoc Teams");
                carclub.createEntity(carclub.addad_hoc_team());
                tx.commit();
                LOGGER.fine("End of Transaction");
                break;
            case 10:
                break;
            case 11:
                System.out.println("Returning all Books");
                carclub.returnBook();
                break;
            case 12:
                System.out.println("Returning all Books");
                carclub.returnWritingGroup();
                break;
            case 13:
                tx.begin();
                System.out.println("Delete a Book");
                carclub.deleteBooks();
                tx.commit();
                LOGGER.fine("End of Transaction");
                break;
            case 14:
                tx.begin();
                System.out.println("Update Books");
                carclub.updateBooks();
                tx.commit();
                LOGGER.fine("End of Transaction");
                break;
            case 15:
                System.out.println("Return all Primary Key for Authoring Entities");
                carclub.returnPKAuthoringEntity();
                break;
            case 16:
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

   public void returnBook(){
       query = entityManager.createNativeQuery("SELECT * FROM BOOKS");
       List<Object[]> result = query.getResultList();
       System.out.print("Books: ");
       for(int i = 0; i < result.size(); i++){
           System.out.printf("\n" + (i+1) + ". " + result.get(i)[0] + " " + result.get(i)[1] + " "
                   + result.get(i)[2] + " " + result.get(i)[3] + " " + result.get(i)[4]);
       }
   }

   public void returnWritingGroup(){
       query = entityManager.createNativeQuery("SELECT * FROM AUTHORING_ENTITIES WHERE AUTHORING_ENTITY_TYPE = 'Writing Group'");
       List<Object[]> result = query.getResultList();
       System.out.print("Writing Group: ");
       for(int i = 0; i < result.size(); i++ ){
           System.out.printf("\n" + (i+1) +  ". " + result.get(i)[0] + " " + result.get(i)[1] + " "
                   + result.get(i)[2] + " " + result.get(i)[3] + " " + result.get(i)[4]);
       }
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

   public void returnPKAuthoringEntity(){
       query = entityManager.createNativeQuery("SELECT AUTHORING_ENTITIES.EMAIL FROM AUTHORING_ENTITIES");
       List<Object[]> result = query.getResultList();
       System.out.print("PK of Authoring Entities");
       for (int i = 0; i < result.size(); i++){
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
        Scanner scan = new Scanner(System.in);
        System.out.println("What is the name of the book");
        System.out.println("Enter the name of the publisher of the book: ");
        String name_publisher = scan.nextLine();
        System.out.println("Enter the title of the book: ");
        String title = scan.nextLine();
        System.out.println("Enter the Authoring Entity Email: ");
        String authoring_email = scan.nextLine();
        System.out.println("Enter the ISBN");
        int isbn = scan.nextInt();
        query = entityManager.createNativeQuery("SELECT Publisher_Name, title, Authoring_Entity_Email FROM BOOKS");
        List<Books> book = query.getResultList();
        boolean ans = book.isEmpty();
        if (ans == false)
        {
            query = entityManager.createNativeQuery("DELETE FROM BOOKS WHERE BOOKS.ISBN = '" + isbn + "'");
            query.executeUpdate();
            System.out.println(title+ " was deleted from the database");
        }
        else{
            System.out.println("This Book Does Not Exist");
        }
    }


    /**
     * Updating Books in DB
     */
    public void updateBooks(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the ISBN of the book: ");
        String isbn1 = scan.nextLine();
        query = entityManager.createNativeQuery("SELECT ISBN, TITLE, YEAR_PUBLISHED, AUTHORING_ENTITY_NAME, PUBLISHER_NAME  FROM BOOKS WHERE ISBN =  isbn1");
        List<Books> book = query.getResultList();
        for (Books a : book) {
            System.out.println("Enter New Authoring Entity Name: ");
            String authoring_name = scan.nextLine();
            List<WritingGroups> bookAuthorEmail = addWritingGroup();
            a.setAuthorEmail(bookAuthorEmail.get(0));
        }
    }

    /**
     * Returns PK of Books
     */
    public void returnPkBook(){
        query = entityManager.createNativeQuery("SELECT ISBN, BOOKS.TITLE  FROM BOOKS");
        List<Object[]> res = query.getResultList();
        for(int i = 0; i < res.size(); i++){
            System.out.println((i+1) + ". " + res.get(i)[0]);
        }
    }
    public List<individual_author> addindividual_author() {
        List<individual_author> individualAuthors = new ArrayList<individual_author>();

        Scanner scan = new Scanner(System.in);
        System.out.println("Adding an Authoring Entity");
        System.out.println("Enter Author Email: ");
        String email = scan.nextLine();

        individualAuthors.add(new individual_author(email));
        System.out.println("Authoring Entity " + email + " has been added to the database");
        return individualAuthors;
    }
    public List<ad_hoc_team> addad_hoc_team() {
        List<ad_hoc_team> adHocTeams = new ArrayList<ad_hoc_team>();
        Scanner scan = new Scanner(System.in);
        System.out.println("Adding an Authoring Entity");
        System.out.println("Enter Ad Hoc Team Email: ");
        String email = scan.nextLine();
        adHocTeams.add(new ad_hoc_team(email));
        System.out.println("Authoring Entity " + email + " has been added to the database");
        return adHocTeams;
    }

} // End of CarClub class
