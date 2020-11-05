INSERT INTO author
(id, firstname, lastname) VALUES
(nextval('hibernate_sequence'), 'John', 'Irving'),
(nextval('hibernate_sequence'), 'Paul', 'Auster');

INSERT INTO book
(id, title, author_id) VALUES
(nextval('hibernate_sequence'), 'The World According to Garp', 1),
(nextval('hibernate_sequence'), 'The Hotel New Hampshire', 1),
(nextval('hibernate_sequence'), 'The Cider House Rules', 1),
(nextval('hibernate_sequence'), 'A Prayer for Owen Meany', 1),
(nextval('hibernate_sequence'), 'Last Night in Twisted River', 1),
(nextval('hibernate_sequence'), 'In One Person', 1),
(nextval('hibernate_sequence'), 'Avenue of Mysteries', 1),
(nextval('hibernate_sequence'), 'The New York Trilogy', 2),
(nextval('hibernate_sequence'), 'Mr. Vertigo', 2),
(nextval('hibernate_sequence'), 'The Brooklyn Follies', 2),
(nextval('hibernate_sequence'), 'Invisible', 2),
(nextval('hibernate_sequence'), 'Sunset Park', 2),
(nextval('hibernate_sequence'), '4 3 2 1', 2);
