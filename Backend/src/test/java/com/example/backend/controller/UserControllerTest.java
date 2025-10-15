package com.example.backend.controller;

import com.example.backend.dto.user.UserRequestDto;
import com.example.backend.model.users.User;
import com.example.backend.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    private List<User> testUsers;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        entityManager.flush();
        entityManager.clear();

        this.testUsers = Arrays.asList(
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
            new User(null, "Ethan Wright", "ethan.wright@sample.com")
        );
        userRepository.saveAll(this.testUsers);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        entityManager.flush();
        entityManager.clear();
    }    

    @Test
    void getUsersSliceTest() throws Exception {
        mockMvc.perform(get("/users").param("page", "0").param("size", "25"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(testUsers.size())))
                .andExpect(jsonPath("$.content[0].name").exists()).andExpect(jsonPath("$.content[0].name").value(testUsers.get(testUsers.size() - 1).getName()))
                .andExpect(jsonPath("$.content[0].email").exists()).andExpect(jsonPath("$.content[0].email").value(testUsers.get(testUsers.size() - 1).getEmail()));
    }

    @Test
    void getUsersSmallSliceTest() throws Exception {
        mockMvc.perform(get("/users").param("page", "0").param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(5)))
                .andExpect(jsonPath("$.content[0].name").exists()).andExpect(jsonPath("$.content[0].name").value(testUsers.get(testUsers.size() - 1).getName()))
                .andExpect(jsonPath("$.content[0].email").exists()).andExpect(jsonPath("$.content[0].email").value(testUsers.get(testUsers.size() - 1).getEmail()));
    }


    @Test
    void getUsersSliceFailTest() throws Exception {
        mockMvc.perform(get("/users").param("page", "999999").param("size", "999999"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid pagination parameters: 999999, 999999"));
    }
    

    @Test
    void getUserByIdTest() throws Exception {
        User thirdUser = userRepository.findAll().get(2);
        mockMvc.perform(get("/users/" + thirdUser.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(thirdUser.getId()))
                .andExpect(jsonPath("$.name").value(thirdUser.getName()))
                .andExpect(jsonPath("$.email").value(thirdUser.getEmail()));
    }

    @Test
    void getUserByIdFailTest() throws Exception {
        mockMvc.perform(get("/users/999999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("User 999999 not found"));
    }

    @Test
    void createUserTest() throws Exception {
        UserRequestDto request = new UserRequestDto("New User", "newuser@example.com");

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("New User"))
                .andExpect(jsonPath("$.email").value("newuser@example.com"));
    }

    @Test
    void createUserFailTest() throws Exception {
        UserRequestDto request = new UserRequestDto("Duplicate User", "victor@example.com");

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("This email is already in use. Please use a different email."));
    }

    @Test
    void updateUserTest() throws Exception {
        User existingUser = userRepository.findAll().get(0);
        UserRequestDto request = new UserRequestDto("Updated Name", "updated@example.com");

        mockMvc.perform(put("/users/" + existingUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(existingUser.getId()))    
                .andExpect(jsonPath("$.name").value("Updated Name"))
                .andExpect(jsonPath("$.email").value("updated@example.com"));
    }

    @Test
    void updateUserFailTest() throws Exception {
        User existingUser = userRepository.findAll().get(0);
        User secondExistingUser = userRepository.findAll().get(1);
        UserRequestDto request = new UserRequestDto("Updated Name", secondExistingUser.getEmail());

        mockMvc.perform(put("/users/" + existingUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("This email is already in use. Please use a different email."));
    }

    @Test
    void deleteUserTest() throws Exception {
        User existingUser = userRepository.findAll().get(0);

        mockMvc.perform(delete("/users/" + existingUser.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/users/" + existingUser.getId()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("User " + existingUser.getId() + " not found"));
    }

    @Test
    void deleteUserFailTest() throws Exception {
        mockMvc.perform(delete("/users/999999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("User 999999 not found"));
    }
}

