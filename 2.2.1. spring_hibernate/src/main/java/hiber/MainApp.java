package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        User user5 = new User("Vasya", "Petrov", "petrov@mail.ru");
        user5.setCar(new Car("NIVA", 10500));
        userService.add(user5);

        User user6 = new User("Gosha", "Laptev", "laptev@mail.ru");
        user6.setCar(new Car("Zhiguli", 2109));
        userService.add(user6);

        User user7 = new User("Pasha", "Rybin", "rybin@mail.ru");
        user7.setCar(new Car("Toyota", 1000));
        userService.add(user7);

        List<User> users2 = userService.listUsers();
        users2.forEach(System.out::println);

        User userFromSearch = userService.findBy("Zhiguli", 2109);
        System.out.println(userFromSearch);


        context.close();
    }
}
