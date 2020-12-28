create table person (
	id INTEGER generated as identity constraint person_pk primary key,
	name VARCHAR(255) not null,
	photo_path VARCHAR(900) not null
);
