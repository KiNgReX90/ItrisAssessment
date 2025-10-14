package com.example.backend.bootstrap;

import java.util.List;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.backend.model.users.User;
import com.example.backend.repository.UserRepository;


// Just to create some mocking data, class can be removed when in production
@Configuration
public class DataInitializer {
    @Bean
    ApplicationRunner initUsers(UserRepository repo) {
      return args -> {
        if (repo.count() == 0) {
          repo.saveAll(List.of(
            new User(null, "James Doe", "james@example.com"),
            new User(null, "Victor Doe", "victor@example.com"),
            new User(null, "Jane Doe", "jane@example.com"),
            new User(null, "John Doe", "john@example.com"),
            new User(null, "Alice Smith", "alice.smith@example.com"),
            new User(null, "Bob Johnson", "bobby.j@example.org"),
            new User(null, "Clara Barton", "clara.barton@example.net"),
            new User(null, "Matthew Clark", "matt.clark@example.com"),
            new User(null, "Samantha Lee", "sam.lee@samplemail.com"),
            new User(null, "Oscar Ford", "oscarf98@domain.net"),
            new User(null, "Grace Kim", "grace.kim@mailtest.org"),
            new User(null, "Lucas Reed", "lucas.reed99@testmail.com"),
            new User(null, "Priya Patel", "priya.patel@example.com"),
            new User(null, "David Green", "david.green@emailer.dev"),
            new User(null, "Michelle Lewis", "michelle.lewis@netmail.com"),
            new User(null, "Ethan Wright", "ethan.wright@sample.com"),
            new User(null, "Zara Hughes", "zara.hughes@mailme.org"),
            new User(null, "Henry Mills", "henrymills@fastmail.com"),
            new User(null, "Linda Turner", "linda.turner@testmail.net"),
            new User(null, "Simon Chen", "simon.chen@samplemail.com"),
            new User(null, "Tina Fox", "tina.fox@domain.dev"),
            new User(null, "Kyle Baker", "kyle.baker9@email.com"),
            new User(null, "Julia Roman", "julia.roman@netmail.org"),
            new User(null, "Charles Knight", "charles.knight@example.com"),
            new User(null, "Rebecca Owens", "rebecca.owens@mailtest.com"),
            new User(null, "Ivan Novak", "ivan.novak@sample.org"),
            new User(null, "Diana Lane", "diana.lane@mail.it"),
            new User(null, "George Grant", "george.grant@worldmail.com"),
            new User(null, "Monica Stewart", "monica.stewart@emailer.io"),
            new User(null, "Tyler Young", "tyleryoung@samplemail.org"),
            new User(null, "Fiona Murphy", "fiona.murphy@demo.io"),
            new User(null, "Brandon Price", "brandon.price@example.dev"),
            new User(null, "Hailey Cook", "hailey.cook@testmail.org"),
            new User(null, "Nathan Cole", "nathan.cole@sample.com"),
            new User(null, "Olivia Adams", "olivia.adams@domain.org"),
            new User(null, "Sophie Weber", "sophie.weber@mail.com"),
            new User(null, "Mason Perez", "mason.perez@webmail.com"),
            new User(null, "Isabella King", "isabella.king@sample.net"),
            new User(null, "Gabriel Ramos", "gabriel.ramos@netmail.net"),
            new User(null, "Audrey Foster", "audrey.foster@demo.com"),
            new User(null, "William Scott", "william.scott@myemail.com"),
            new User(null, "Elena Sokolov", "elena.sokolov@mail.com"),
            new User(null, "Amir Ali", "amir.ali@fastmail.org"),
            new User(null, "Chloe Evans", "chloe.evans@openmail.io"),
            new User(null, "Ben Castillo", "ben.c@sample.org"),
            new User(null, "Sofia Mendes", "sofia.mendes@domain.io"),
            new User(null, "Logan Clark", "logan.clark@example.com"),
            new User(null, "Norah Ross", "norah.ross@email.com"),
            new User(null, "Gregory Wood", "gregory.wood@samplemail.net"),
            new User(null, "Ashley Palmer", "ashley.palmer@tester.org"),
            new User(null, "Philipp Meyer", "philipp.meyer@mailz.net"),
            new User(null, "Leah Campbell", "leah.campbell@openmail.com"),
            new User(null, "Dominic Ward", "dominic.ward@samply.com"),
            new User(null, "Harper Bell", "harper.bell@myinbox.com"),
            new User(null, "Zane Fox", "zane.fox@demo.mail"),
            new User(null, "Ella Martin", "ella.martin@inboxmail.org"),
            new User(null, "Miles Reed", "miles.reed@webmail.net"),
            new User(null, "Evelyn Barnes", "evelyn.barnes@samplemail.io"),
            new User(null, "Jonas Schmidt", "jonas.schmidt@openmail.net"),
            new User(null, "Layla Rivera", "layla.rivera@sample.org"),
            new User(null, "Victor Sanchez", "victor.sanchez@mailmail.com"),
            new User(null, "Ruby Diaz", "ruby.diaz@newmail.com"),
            new User(null, "Alexandra Fisher", "alex.fisher@mydomain.org"),
            new User(null, "Samuel Burns", "samuel.burns@emailer.com"),
            new User(null, "Mila Russo", "mila.russo@maildemo.com"),
            new User(null, "Noah Vaughn", "noah.vaughn@example.org"),
            new User(null, "Ava Bailey", "ava.bailey@site.com"),
            new User(null, "Vincent Cruz", "vincent.cruz@domain.net"),
            new User(null, "Paula Simmons", "paula.simmons@testmail.com"),
            new User(null, "Carter Graham", "carter.graham@mail.net"),
            new User(null, "Hannah James", "hannah.james@justmail.com"),
            new User(null, "Jack Webb", "jack.webb@email.net"),
            new User(null, "Sophia Olsen", "sophia.olsen@mailer.org"),
            new User(null, "Maddox Brooks", "maddox.brooks@demo.org"),
            new User(null, "Emily Ford", "emily.ford@quickmail.com"),
            new User(null, "Owen Bennett", "owen.bennett@nomail.org"),
            new User(null, "Faith Dean", "faith.dean@mail.net"),
            new User(null, "Ezekiel Lane", "ezekiel.lane@domain.org"),
            new User(null, "Charlotte Chen", "charlotte.chen@myemail.com"),
            new User(null, "Nathaniel Bishop", "nathaniel.bishop@orgmail.com"),
            new User(null, "Aurora Ortiz", "aurora.ortiz@example.com"),
            new User(null, "Liam Patel", "liam.patel@samplemail.com"),
            new User(null, "Jasmine Stone", "jasmine.stone@demo.io")
          ));
        }
      };
    }
}