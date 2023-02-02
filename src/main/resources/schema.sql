CREATE TABLE IF NOT EXISTS speakers(
    id integer NOT NULL,
    first_name character varying(20) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(20) COLLATE pg_catalog."default" NOT NULL,
    favourite boolean DEFAULT false,
    company character varying(20) COLLATE pg_catalog."default" NOT NULL,
    twitter_handle character varying(20) COLLATE pg_catalog."default",
    session_id integer,
    session_title character varying(40) COLLATE pg_catalog."default",
    event_year character varying(10) COLLATE pg_catalog."default",
    CONSTRAINT speakers_pkey PRIMARY KEY (id)
);