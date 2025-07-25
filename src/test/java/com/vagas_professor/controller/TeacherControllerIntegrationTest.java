// package com.vagas_professor.controller;

// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.util.List;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.context.TestPropertySource;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.test.context.support.WithMockUser;
// import org.springframework.security.test.context.support.WithUserDetails;

// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// // import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

// import com.vagas_professor.VagasProfessorApplication;
// import com.vagas_professor.entity.Teacher;
// import com.vagas_professor.entity.User;
// import com.vagas_professor.repository.TeacherRepository;
// import com.vagas_professor.repository.UserRepository;

// @SpringBootTest(classes = VagasProfessorApplication.class) // Load the full context (Controller + Service + Repository)
// @AutoConfigureMockMvc
// @TestPropertySource(locations = "classpath:application-test.properties")
// public class TeacherControllerIntegrationTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     private TeacherRepository teacherRepository;

//     @Autowired // skip creating a constructor and pass by this
//     private UserRepository userRepository;

//     private Teacher savedTeacher;

//     @BeforeEach
//     void setupAuthenticatedTeacherUser() {
//         User user = new User();
//         user.setUsername("marinspira");
//         user.setPassword("123");
//         user.setRole("TEACHER");
//         user.setEmail("marinspira@example.com");

//         // save user in test database (JPA repo)
//         userRepository.save(user);

//         UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null,
//                 List.of(new SimpleGrantedAuthority(user.getRole())));

//         SecurityContextHolder.getContext().setAuthentication(auth);
//     }

//     // @BeforeEach
//     // void setup() {
//     //     teacherRepository.deleteAll();

//     //     Teacher teacher = new Teacher();
//     //     teacher.setFullName("Ana Silva");
//     //     teacher.setPhone("1111111111");
//     //     teacher.setCpf("123.456.789-10");
//     //     teacher.setDateOfBirth(LocalDate.of(1990, 1, 1));
//     //     teacher.setCity("Rio de Janeiro");
//     //     teacher.setState("RJ");
//     //     teacher.setEducation("PhD in Mathematics");
//     //     teacher.setExperience("10 years");
//     //     teacher.setDegree("PhD");
//     //     teacher.setSkills(List.of("Math", "Algebra"));
//     //     teacher.setCvUrl("http://example.com/ana-cv.pdf");
//     //     teacher.setPhotoUrl("http://example.com/ana.jpg");
//     //     teacher.setCreatedAt(LocalDateTime.now().minusDays(30));
//     //     teacher.setUpdatedAt(LocalDateTime.now());

//     //     savedTeacher = teacherRepository.save(teacher);
//     // }

//     // @Test
//     // void shouldReturnTeacherProfileFromApi() throws Exception {
//     // mockMvc.perform(get("/api/teacher/" + savedTeacher.getId())
//     // .accept(MediaType.APPLICATION_JSON))
//     // .andExpect(status().isOk())
//     // .andExpect(jsonPath("$.fullName").value("Ana Silva"))
//     // .andExpect(jsonPath("$.city").value("Rio de Janeiro"))
//     // .andExpect(jsonPath("$.skills[0]").value("Math"));
//     // }

//     @Test
//     // @WithMockUser(username = "marinspira", roles = {"TEACHER"})
//     void shouldCreateAndReturnATeacherProfile() throws Exception {
//         String requestBody = """
//                 {
//                     "fullName": "Carlos Oliveira",
//                     "phone": "2222222222",
//                     "cpf": "987.654.321-00",
//                     "dateOfBirth": "1985-06-20",
//                     "city": "São Paulo",
//                     "state": "SP",
//                     "education": "MSc in Physics",
//                     "experience": "8 years",
//                     "degree": "MSc",
//                     "skills": ["Physics", "Thermodynamics"],
//                     "cvUrl": "http://example.com/carlos-cv.pdf",
//                     "photoUrl": "http://example.com/carlos.jpg"
//                 }
//                 """;

//         mockMvc.perform(post("/api/teacher/create")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(requestBody)
//                 .accept(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.fullName").value("Carlos Oliveira"))
//                 .andExpect(jsonPath("$.city").value("São Paulo"))
//                 .andExpect(jsonPath("$.skills[0]").value("Physics"));
//     }
// }
