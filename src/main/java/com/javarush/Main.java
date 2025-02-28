package com.javarush;

import com.javarush.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class Main {
    private final SessionFactory sessionFactory;

    public Main() {
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
        properties.put(Environment.URL, "jdbc:p6spy:mysql://localhost:3306/movie");
        properties.put("hibernate.show_sql", "true");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "mysql");
        properties.put(Environment.HBM2DDL_AUTO, "validate");
        sessionFactory = new Configuration().
                addProperties(properties).
                addAnnotatedClass(Actor.class).
                addAnnotatedClass(Address.class).
                addAnnotatedClass(Category.class).
                addAnnotatedClass(City.class).
                addAnnotatedClass(Country.class).
                addAnnotatedClass(Customer.class).
                addAnnotatedClass(Film.class).
                addAnnotatedClass(FilmText.class).
                addAnnotatedClass(Inventory.class).
                addAnnotatedClass(Language.class).
                addAnnotatedClass(Payment.class).
                addAnnotatedClass(Rental.class).
                addAnnotatedClass(Staff.class).
                addAnnotatedClass(Store.class).
                buildSessionFactory();
    }

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.createCustomer(main));
        System.out.println(main.customerReturnFilm(main));
        System.out.println(main.customerRentInventory(main));
        System.out.println(main.newFilmWasReleased(main));
        main.sessionFactory.close();
    }

    private Customer createCustomer(Main main) {
        Session session = main.sessionFactory.openSession();
        Customer customer = null;
        try (session) {
            Transaction tx = session.beginTransaction();
            try {
                Store store = session.get(Store.class, (byte) 1);
                Address address = session.get(Address.class, (short) 1);
                customer = Customer.builder()
                        .store(store)
                        .firstName("nik")
                        .lastName("efr")
                        .email("nik@example.com")
                        .address(address)
                        .isActive(true).build();
                session.persist(customer);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                e.printStackTrace();
            }
        }
        return customer;
    }

    private Integer customerReturnFilm(Main main){
        Session session = main.sessionFactory.openSession();
        Integer rentalId = null;
        try (session) {
            Transaction tx = session.beginTransaction();
            try {
                Rental rental = session.createQuery("select r from Rental r where r.returnDate is null", Rental.class).list().get(0);
                rental.setReturnDate(LocalDateTime.now());
                session.merge(rental);
                rentalId = rental.getId();
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                e.printStackTrace();
            }
        }
        return rentalId;
    }

    private Inventory customerRentInventory(Main main){
        Session session = main.sessionFactory.openSession();
        Inventory inventory = null;
        try (session) {
            Transaction tx = session.beginTransaction();
            try {
                Customer customer = session.get(Customer.class, (short) 1);
                Film film = session.createQuery("select f from Film f where f.id not in (select film.id from Inventory )",Film.class).list().get(0);
                Store store = session.get(Store.class, (byte) 1);

                inventory = Inventory.builder()
                        .film(film)
                        .store(store)
                        .build();
                session.persist(inventory);
                Staff staff = store.getStaff();

                Rental rental = Rental.builder()
                        .rentalDate(LocalDateTime.now())
                        .customer(customer)
                        .staff(staff)
                        .inventory(inventory)
                        .build();
                session.persist(rental);

                Payment payment = Payment.builder()
                        .rental(rental)
                        .paymentDate(LocalDateTime.now())
                        .customer(customer)
                        .amount(BigDecimal.valueOf(52.00))
                        .staff(staff)
                        .build();
                session.persist(payment);

                tx.commit();

            } catch (Exception e) {
                tx.rollback();
                e.printStackTrace();
            }
        }
        return inventory;
    }

    private Film newFilmWasReleased(Main main){
        Session session = main.sessionFactory.openSession();
        Film film = null;
        try (session) {
            Transaction tx = session.beginTransaction();
            try {
                Language language = session.get(Language.class,(byte)1);
                List<Category> categories = session.createQuery("select c from Category c",Category.class).setMaxResults(3).list();
                List<Actor> actors = session.createQuery("select a from Actor a", Actor.class).setMaxResults(52).list();

                film = Film.builder()
                        .language(language)
                        .categories(categories)
                        .actors(actors)
                        .rating(Rating.PG)
                        .specialFeatures("Commentaries,Deleted Scenes,Behind the Scenes")
                        .length((short) 100)
                        .replacementCost(BigDecimal.ZERO)
                        .rentalRate(BigDecimal.ZERO)
                        .description("wow!")
                        .title("Movie")
                        .rentalDuration((byte) 52)
                        .originalLanguage(language)
                        .year(Year.now())
                        .build();
                session.persist(film);

                FilmText filmText = FilmText.builder()
                        .film(film)
                        .description("wow!")
                        .title("Movie")
                        .build();
                session.persist(filmText);

                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                e.printStackTrace();
            }
        }
        return film;
    }

}
