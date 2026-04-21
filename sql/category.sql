CREATE TABLE category
(
    id   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

INSERT INTO public.category (name)
VALUES ('Protein');
INSERT INTO public.category (name)
VALUES ('Vitamin & Mineral');
INSERT INTO public.category (name)
VALUES ('Diet');
INSERT INTO public.category (name)
VALUES ('Food');