# COMP3005 A3
## Author: Kaven Muraleitharan

### Description
This is an application which performs basic CRUD operations on a table of students

### Implemented functions
getAllStudents(): Retrieves and displays all student records from the database

addStudent(first_name, last_name, email, enrollment_date): Inserts a new student into the database

updateStudentEmail(student_id, new_email): Updates the email of a student with the specified ID

deleteStudent(student_id): Deletes a student record based on their ID

### Database setup
```
CREATE TABLE students (
    student_id SERIAL PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    enrollment_date DATE
);
INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES
('John', 'Doe', 'john.doe@example.com', '2023-09-01'),
('Jane', 'Smith', 'jane.smith@example.com', '2023-09-01'),
('Jim', 'Beam', 'jim.beam@example.com', '2023-09-02');
```

### How to run

1. Using pgAdmin, create a new database and execute the database setup listed above
2. Run the project through intellij using Maven
3. Once running enter the url of your database as well as the user and password.