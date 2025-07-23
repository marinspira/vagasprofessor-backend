🔐 Authentication (/auth)
Handles registration and login for both roles: ROLE_TEACHER and ROLE_SCHOOL.

| Method | Endpoint         | Description                     | Access |
| ------ | ---------------- | ------------------------------- | ------ |
| POST   | `/auth/register` | Register as a school or teacher | Public |
| POST   | `/auth/login`    | Log in and get JWT token        | Public |

The RegisterRequest should include: username, email, password, and role.

---

👨‍🏫 Teacher Profiles (/teachers)

| Method | Endpoint         | Description                     | Access            |
| ------ | ---------------- | ------------------------------- | ----------------- |
| GET    | `/teachers`      | List all teacher profiles       | **ROLE\_SCHOOL**  |
| GET    | `/teachers/{id}` | View a specific teacher profile | **ROLE\_SCHOOL**  |
| GET    | `/teachers/me`   | View own teacher profile        | **ROLE\_TEACHER** |
| PUT    | `/teachers/me`   | Update own teacher profile      | **ROLE\_TEACHER** |

---

🏫 School Profiles (/schools)

| Method | Endpoint        | Description                    | Access            |
| ------ | --------------- | ------------------------------ | ----------------- |
| GET    | `/schools`      | List all school profiles       | **ROLE\_TEACHER** |
| GET    | `/schools/{id}` | View a specific school profile | **ROLE\_TEACHER** |
| GET    | `/schools/me`   | View own school profile        | **ROLE\_SCHOOL**  |
| PUT    | `/schools/me`   | Update own school profile      | **ROLE\_SCHOOL**  |

---

📄 Job Listings (/jobs)

| Method | Endpoint     | Description                         | Access           |
| ------ | ------------ | ----------------------------------- | ---------------- |
| POST   | `/jobs`      | Create a job vacancy                | **ROLE\_SCHOOL** |
| GET    | `/jobs`      | List all job vacancies              | Public           |
| GET    | `/jobs/{id}` | View a specific job vacancy         | Public           |
| GET    | `/jobs/my`   | List jobs created by current school | **ROLE\_SCHOOL** |

---

📨 Applications (/applications)

| Method | Endpoint                 | Description                                      | Access            |
| ------ | ------------------------ | ------------------------------------------------ | ----------------- |
| POST   | `/applications`          | Apply to a job (teacher can attach a CV)         | **ROLE\_TEACHER** |
| GET    | `/applications/my`       | View own applications                            | **ROLE\_TEACHER** |
| GET    | `/applications/job/{id}` | View applications for a specific job (by school) | **ROLE\_SCHOOL**  |

---

