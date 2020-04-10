package user;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());

        try(Handle handle =jdbi.open()){
            UserDao dao = handle.attach(UserDao.class);
            dao.createTable();

            User user1 = User.builder()
                    .username("007")
                    .password("gtNGfqbW")
                    .name("James Bond")
                    .email("mail@example.hu")
                    .gender(User.Gender.MALE)
                    .dob(LocalDate.parse("1920-11-11"))
                    .enabled(true)
                    .build();

            User user2 = User.builder()
                    .username("002")
                    .password("DKqZ3WKZ")
                    .name("Leroy Carter")
                    .email("babbling@simplelifthub.com")
                    .gender(User.Gender.MALE)
                    .dob(LocalDate.parse("1945-10-24"))
                    .enabled(true)
                    .build();

            User user3 = User.builder()
                    .username("003")
                    .password("fyQP3LBn")
                    .name("Ibraheem Jackson")
                    .email("babbling@wowmyksd.gq")
                    .gender(User.Gender.MALE)
                    .dob(LocalDate.parse("2000-02-14"))
                    .enabled(true)
                    .build();

            User user4 = User.builder()
                    .username("004")
                    .password("WCB4nS78")
                    .name("Liam Trelawney")
                    .email("babbling@smart-5-shop.online")
                    .gender(User.Gender.MALE)
                    .dob(LocalDate.parse("1986-09-01"))
                    .enabled(true)
                    .build();

            long key1 = dao.insertUser(user1);
            dao.insertUser(user2);
            dao.insertUser(user3);
            dao.insertUser(user4);

            System.out.println();
            System.out.println("User with "+key1+" id:");
            System.out.println(dao.findById(key1));
            System.out.println();

            String username1 = user2.getUsername();
            System.out.println("User with "+username1+" username:");
            System.out.println(dao.findByUsername(username1));
            System.out.println();

            System.out.println("List of users:");
            dao.list().stream().forEach(System.out::println);
            System.out.println();

            System.out.println("Delete user2.");
            dao.delete(user2);
            System.out.println();

            System.out.println("List of users after deleting user2:");
            dao.list().stream().forEach(System.out::println);
            System.out.println();
        }

    }


}
