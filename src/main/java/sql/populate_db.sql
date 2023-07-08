INSERT INTO worker (name, birthday, level, salary)
VALUES
  ('John Doe', '1990-01-01', 'Trainee', 800),
  ('Jane Smith', '1985-02-15', 'Junior', 1200),
  ('Michael Johnson', '1982-05-10', 'Middle', 3500),
  ('Emily Brown', '1978-09-20', 'Senior', 8000),
  ('William Davis', '1992-04-05', 'Junior', 1500),
  ('Olivia Wilson', '1995-08-12', 'Trainee', 900),
  ('James Taylor', '1989-06-25', 'Middle', 4500),
  ('Sophia Anderson', '1984-03-18', 'Senior', 9500),
  ('Benjamin Martinez', '1991-11-30', 'Middle', 5000),
  ('Ava Hernandez', '1993-07-08', 'Junior', 1800);


INSERT INTO client (name)
VALUES
    ('Company'),
    ('Corporation'),
    ('Industries'),
    ('AcmeCorporation'),
    ('SmithCo');


INSERT INTO project (client_id, start_date, finish_date)
VALUES
    (1, '2022-01-01', '2022-04-30'),
    (2, '2022-02-15', '2022-07-31'),
    (3, '2022-03-10', '2022-05-31'),
    (4, '2022-04-20', '2022-06-30'),
    (5, '2022-05-01', '2022-09-30'),
    (1, '2022-06-15', '2022-08-31'),
    (2, '2022-07-10', '2022-09-30'),
    (3, '2022-08-20', '2022-12-31'),
    (4, '2022-09-01', '2023-02-28'),
    (5, '2022-10-15', '2023-01-31');


INSERT INTO project_worker (PROJECT_ID, WORKER_ID)
VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (2, 4),
    (2, 5),
    (3, 6),
    (3, 7),
    (3, 8),
    (4, 9),
    (5, 10),
    (6, 1),
    (6, 2),
    (6, 3),
    (7, 4),
    (8, 5),
    (8, 6),
    (8, 7),
    (8, 8),
    (9, 9),
    (9, 10),
    (10, 1),
    (10, 2),
    (10, 3),
    (10, 4),
    (10, 5);