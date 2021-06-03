insert into authorities (authority)
values ('ROLE_USER');
INSERT INTO public.users (username, password, authority_id)
VALUES ('89eeb9bc-ddaa-452c-9ea3-6ebf60d05e4b', '$2a$10$8oxBsHIPu8uY4H3Lhfa8mukpjLMz3qrYwrj7A.FQUqWnnzIPLr7PO', 1);
INSERT INTO public.site (name)
VALUES ('mail.ru');
INSERT INTO public.users_site (user_id, site_id)
VALUES (1, 1);