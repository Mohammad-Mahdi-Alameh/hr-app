
BEGIN;
ALTER SEQUENCE roles_id_seq RESTART WITH 1;
ALTER SEQUENCE employees_id_seq RESTART WITH 1;
ALTER SEQUENCE departments_id_seq RESTART WITH 1;
ALTER SEQUENCE leave_types_id_seq RESTART WITH 1;
ALTER SEQUENCE expense_claim_types_id_seq RESTART WITH 1;
ALTER SEQUENCE expense_claims_id_seq RESTART WITH 1;
ALTER SEQUENCE expense_claim_details_id_seq RESTART WITH 1;
ALTER SEQUENCE leaves_id_seq RESTART WITH 1;
-- roles table
INSERT INTO roles (name) VALUES 
                    ('ROLE_EMPLOYEE'),
                    ('ROLE_ADMIN');

INSERT INTO departments(location,name) values('ededed','engineering');

INSERT INTO leave_types(name) values('Sick'),
                                    ('Travel'),
                                    ('Bussiness'),
                                    ('Education');

INSERT INTO expense_claim_types(name) values    ('Hotel'),
                                                ('Car Rental'),
                                                ('Food'),
                                                ('Ticket');

INSERT INTO employees (username,email,password,address,name,department_id) VALUES 
                    ('user','user@gmail.com','$2a$12$vDWCL66/RNppipEOUhk/g.mbX.kPqcUzycZ3kAJ7jd0wT6rn9VROq','user','user',1),
                     ('admin','admin@gmail.com','$2a$12$vDWCL66/RNppipEOUhk/g.mbX.kPqcUzycZ3kAJ7jd0wT6rn9VROq','admin','admin',1);

INSERT INTO employee_roles (employee_id,role_id) VALUES 
                    (1,1),
                    (2,2);
COMMIT;