package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User userOne = new User("John", "Smith", "smith@mail.ru");
        User userTwo = new User("Doctor", "Freeman", "half_life@mail.ru");
        User userThree = new User("Jane", "Smith", "j_smith@mail.ru");
        User userFour = new User("Bruce", "Allmightly", "god@mail.ru");

        Car amg = new Car("Mersedes", 2);
        Car x3 = new Car("BMW X", 3);
        Car ford = new Car("Mustang", 7);
        Car uaz = new Car("UAZ", 5);

        userService.add(userOne.setCar(amg).setUser(userOne));
        userService.add(userTwo.setCar(x3).setUser(userTwo));
        userService.add(userThree.setCar(ford).setUser(userThree));
        userService.add(userFour.setCar(uaz).setUser(userFour));


        for (User user : userService.listUsers()) {
            System.out.println(user + " - " + user.getCar());
        }

        System.out.println(userService.getUserByCar("uaz", 5));


        context.close();
    }
}
