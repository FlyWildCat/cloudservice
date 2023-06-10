CREATE TABLE IF NOT EXISTS auth_user (
	id bigint not null generated always as identity primary key,
	username text not null,
	password text,
	role text,
	CONSTRAINT uk_username UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS token (
    id bigint not null generated always as identity primary key,
	uid bigint not null,
	token text not null
);

CREATE TABLE IF NOT EXISTS user_file (
	id bigint not null generated always as identity primary key,
	uid bigint not null,
	file_name text not null,
	file_content bytea,
	file_size integer,
	CONSTRAINT uk_file_name UNIQUE (uid, file_name)
);